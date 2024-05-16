package com.jp.dataservice.controller;

import com.jp.dataservice.data.EntityDtoMapper;
import com.jp.dataservice.data.FetchTreeGenerator;
import com.jp.dataservice.data.ParameterPredicateBuilder;
import com.jp.dataservice.data.PersistenceMapping;
import com.jp.dataservice.data.SearchPredicateBuilder;
import com.jp.dataservice.repository.base.JPRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataRestController {

    private final EntityDtoMapper entityDtoMapper;
    private final PersistenceMapping persistenceMapping;
    private final FetchTreeGenerator fetchTreeGenerator;

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/{queryName}/{id}")
    public <D, E, Q extends EntityPath<E>, I> ResponseEntity<D> findById(
            @PathVariable(name = "queryName") String queryName,
            @PathVariable(name = "id") String id,
            @RequestParam MultiValueMap<String, String> params) {
        PersistenceMapping.EntityMetadata<D, E, Q, I> entityMetadata =
                persistenceMapping.entityMetadataFromQueryName(queryName);
        JPRepository<E, Q, I> repository = entityMetadata.repository();

        List<String> fetchClauses = params.remove("fetch");
        FetchTreeGenerator.FetchNode fetchTreeRoot;
        if (fetchClauses != null) {
            fetchTreeRoot = fetchTreeGenerator.createFetchTree(entityMetadata.entityClass(), fetchClauses);
        } else {
            fetchTreeRoot = null;
        }

        Class<?> idClass = Arrays.stream(entityMetadata.entityClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .map(Field::getType)
                .findFirst()
                .orElseThrow();
        Optional<E> entityOptional;
        if (idClass.isAssignableFrom(Integer.class)) {
            entityOptional = repository.findById((I) Integer.valueOf(id));
        } else {
            entityOptional = repository.findById((I) id);
        }
        return entityOptional
                .map(entity -> entityDtoMapper.entityToDto(entity, entityMetadata.dtoClass(), fetchTreeRoot))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/{queryName}/all")
    public <D, E, Q extends EntityPath<E>, I> List<D> searchAll(
            @PathVariable(name = "queryName") String queryName,
            @RequestParam(value = "search", required = false) String search,
            @SortDefault Sort sort,
            @RequestParam MultiValueMap<String, String> params) {
        PersistenceMapping.EntityMetadata<D, E, Q, I> entityMetadata =
                persistenceMapping.entityMetadataFromQueryName(queryName);
        JPRepository<E, Q, I> repository = entityMetadata.repository();

        List<String> fetchClauses = params.remove("fetch");
        Optional<BooleanExpression> predicate = getBooleanExpression(entityMetadata.entityClass(), params, search);

        EntityPathBase<E> qClass = (EntityPathBase<E>) entityMetadata.qRoot();
        EntityGraph<E> entityGraph = null;
        FetchTreeGenerator.FetchNode fetchTreeRoot = null;
        if (fetchClauses != null) {
            entityGraph = (EntityGraph<E>) entityManager.createEntityGraph(qClass.getType());
            fetchTreeRoot = fetchTreeGenerator.createFetchTree(entityMetadata.entityClass(), fetchClauses);
            fetchTreeGenerator.applyToEntityGraph(fetchTreeRoot, entityGraph);
        }

        return entityDtoMapper.entitiesToDtos(
                repository.findAll(qClass, entityManager, predicate.orElse(null), sort, entityGraph),
                entityMetadata.dtoClass(),
                fetchTreeRoot);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/{queryName}")
    public <D, E, Q extends EntityPath<E>, I> Page<D> search(
            @PathVariable(name = "queryName") String queryName,
            @RequestParam(value = "query", required = false) String searchQuery,
            @PageableDefault(size = 20) Pageable pageable,
            @RequestParam MultiValueMap<String, String> params) {
        PersistenceMapping.EntityMetadata<D, E, Q, I> entityMetadata =
                persistenceMapping.entityMetadataFromQueryName(queryName);
        JPRepository<E, Q, I> repository = entityMetadata.repository();

        List<String> fetchClauses = params.remove("fetch");
        BooleanExpression predicate = getBooleanExpression(entityMetadata.entityClass(), params, searchQuery)
                .orElse(null);

        EntityPathBase<E> qClass = (EntityPathBase<E>) entityMetadata.qRoot();
        EntityGraph<E> entityGraph = null;
        FetchTreeGenerator.FetchNode fetchTreeRoot = null;
        if (fetchClauses != null) {
            entityGraph = (EntityGraph<E>) entityManager.createEntityGraph(qClass.getType());
            fetchTreeRoot = fetchTreeGenerator.createFetchTree(entityMetadata.entityClass(), fetchClauses);
            fetchTreeGenerator.applyToEntityGraph(fetchTreeRoot, entityGraph);
        }

        Page<E> entityPage = repository.findAll(qClass, entityManager, predicate, pageable, entityGraph);
        List<D> responseDtos =
                entityDtoMapper.entitiesToDtos(entityPage.getContent(), entityMetadata.dtoClass(), fetchTreeRoot);
        long count = entityPage.getTotalElements();
        return new PageImpl<>(responseDtos, pageable, count);
    }

    private <E> Optional<BooleanExpression> getBooleanExpression(
            Class<E> entityClass, MultiValueMap<String, String> params, String searchQuery) {
        BooleanExpression exp = null;
        params.remove("size");
        params.remove("sort");
        params.remove("page");
        if (searchQuery != null) {
            SearchPredicateBuilder<E> builder = new SearchPredicateBuilder<>(entityClass);
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(searchQuery + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            exp = builder.build();
        } else if (!params.isEmpty()) {
            ParameterPredicateBuilder<E> builder = new ParameterPredicateBuilder<>(entityClass, params);
            exp = builder.build();
        }
        return Optional.ofNullable(exp);
    }
}
