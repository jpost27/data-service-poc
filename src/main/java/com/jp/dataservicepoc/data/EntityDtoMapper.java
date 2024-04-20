package com.jp.dataservicepoc.data;

import graphql.com.google.common.collect.Streams;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EntityDtoMapper {

    private final ModelMapper modelMapper;

    public <D, E> List<D> entitiesToDtos(Iterable<E> entities, Class<D> dtoClass) {
        return Streams.stream(entities)
                .map(entity -> modelMapper.map(entity, dtoClass))
                .toList();
    }

    public <D, E> List<D> entitiesToDtos(
            Iterable<E> entities, Class<D> dtoClass, @Nullable Collection<String> fetchClauses) {
        Map<String, FetchNode> fetchGraph =
                Optional.ofNullable(fetchClauses).map(this::createFetchGraph).orElse(Collections.emptyMap());
        return Streams.stream(entities)
                .map(entity -> entityToDto(entity, dtoClass, fetchGraph))
                .toList();
    }

    public <D, E> D entityToDto(E entity, Class<D> dtoClass, @Nullable Collection<String> fetchClauses) {
        Map<String, FetchNode> fetchGraph =
                Optional.ofNullable(fetchClauses).map(this::createFetchGraph).orElse(Collections.emptyMap());
        return entityToDto(entity, dtoClass, fetchGraph);
    }

    private <D, E> D entityToDto(E entity, Class<D> dtoClass, @NonNull Map<String, FetchNode> fetchGraph) {
        D dto = modelMapper.map(entity, dtoClass);
        fetchGraph.forEach((key, fetchNode) -> {
            try {
                Method entityGetMethod = entity.getClass().getMethod("get" + nameToEntityName(key));
                Object entityResponse = entityGetMethod.invoke(entity);
                Method dtoGetMethod = Arrays.stream(dto.getClass().getDeclaredMethods())
                        .filter(method -> method.getName().equals("get" + nameToDtoName(key)))
                        .findFirst()
                        .orElseThrow(NoSuchMethodException::new);
                Method dtoSetMethod =
                        dto.getClass().getDeclaredMethod("set" + nameToDtoName(key), dtoGetMethod.getReturnType());
                if (entityResponse instanceof Collection<?>) {
                    dtoSetMethod.invoke(
                            dto,
                            ((Collection<?>) entityResponse)
                                    .stream()
                                            .map(entityObj -> entityToDto(
                                                    entityObj,
                                                    (Class<?>) ((ParameterizedType) dtoGetMethod.getGenericReturnType())
                                                            .getActualTypeArguments()[0],
                                                    fetchNode.children()))
                                            .collect(Collectors.toList()));
                } else {
                    dtoSetMethod.invoke(
                            dto, entityToDto(entityResponse, dtoGetMethod.getReturnType(), fetchNode.children()));
                }
            } catch (NoSuchMethodException e) {
                log.warn("Could not find entity method: {}", nameToEntityName(key));
                e.printStackTrace();
            } catch (InvocationTargetException | IllegalAccessException e) {
                log.warn("Could not invoke entity method: {}, {}", nameToEntityName(key), entity.getClass());
                e.printStackTrace();
            }
        });
        return dto;
    }

    private Map<String, FetchNode> createFetchGraph(Collection<String> fetchClauses) {
        Map<String, FetchNode> fetchNodeMap = new HashMap<>();
        for (String fetchClause : fetchClauses) {
            String[] nodeNames = fetchClause.split("\\.");
            FetchNode currentNode = null;
            for (String nodeName : nodeNames) {
                if (currentNode == null) {
                    // Root node
                    currentNode = fetchNodeMap.getOrDefault(nodeName, new FetchNode(nodeName, new HashMap<>()));
                    fetchNodeMap.put(nodeName, currentNode);
                } else if (currentNode.children().containsKey(nodeName)) {
                    // Node has been created by another fetch clause
                    currentNode = currentNode.children().get(nodeName);
                } else {
                    // New path found in fetch clause
                    currentNode.children().put(nodeName, new FetchNode(nodeName, new HashMap<>()));
                    currentNode = currentNode.children().get(nodeName);
                }
            }
        }
        return fetchNodeMap;
    }

    private String nameToDtoName(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    private String nameToEntityName(String name) {
        return "Fd" + nameToDtoName(name);
    }

    record FetchNode(String name, @NonNull Map<String, FetchNode> children) {}
}
