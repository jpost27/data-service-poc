package com.jp.dataservicepoc.controller;

import com.jp.dataservicepoc.data.EntityDtoMapper;
import com.jp.dataservicepoc.data.ParameterPredicateBuilder;
import com.jp.dataservicepoc.data.SearchPredicateBuilder;
import com.jp.dataservicepoc.data.PersistenceMapping;
import com.jp.dataservicepoc.repository.base.JPRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import graphql.com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("search")
@RequiredArgsConstructor
public class DataRestController {

    private final EntityDtoMapper entityDtoMapper;
    private final PersistenceMapping persistenceMapping;


    @SuppressWarnings("unchecked")
    @GetMapping(value = "/{dtoName}/all")
    public <D, E> List<D> searchAll(
            @PathVariable(name = "dtoName") String dtoName,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam MultiValueMap<String, String> params) {
        Class<E> entityClass = (Class<E>) persistenceMapping.getQueryStringToEntityClassMap().get(dtoName);
        Class<D> dtoClass = (Class<D>) persistenceMapping.getEntityClassToDtoClassMap().get(entityClass);
        JPRepository<D, E, ?, ?> repository = (JPRepository<D, E, ?, ?>) persistenceMapping.getEntityClassToRepositoryMap().get(entityClass);

        Optional<BooleanExpression> predicate = getBooleanExpression(entityClass, params, search);

        return entityDtoMapper.entitiesToDtos(predicate.map(repository::findAll).orElseGet(repository::findAll), dtoClass);
    }

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/{dtoName}")
    public <D, E> Page<D> search(
            @PathVariable(name = "dtoName") String dtoName,
            @RequestParam(value = "query", required = false) String searchQuery,
            @PageableDefault(size = 20) Pageable pageable,
            @RequestParam MultiValueMap<String, String> params) {
        Class<E> entityClass = (Class<E>) persistenceMapping.getQueryStringToEntityClassMap().get(dtoName);
        Class<D> dtoClass = (Class<D>) persistenceMapping.getEntityClassToDtoClassMap().get(entityClass);
        JPRepository<D, E, ?, ?> repository = (JPRepository<D, E, ?, ?>) persistenceMapping.getEntityClassToRepositoryMap().get(entityClass);

        params.remove("size");
        params.remove("sort");
        params.remove("page");
        List<String> fetchClauses = params.remove("fetch");
        Optional<BooleanExpression> predicate = getBooleanExpression(entityClass, params, searchQuery);

        List<D> responseDtos = entityDtoMapper.entitiesToDtos(
                        predicate.map(pred -> repository.findAll(pred, pageable)).orElseGet(() -> repository.findAll(pageable)),
                dtoClass,
                fetchClauses);
        long count = predicate.map(repository::count).orElseGet(repository::count);
        return new PageImpl<>(responseDtos, pageable, count);
    }

    private <E> Optional<BooleanExpression> getBooleanExpression(Class<E> entityClass, MultiValueMap<String, String> params, String searchQuery) {
        BooleanExpression exp = null;
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
