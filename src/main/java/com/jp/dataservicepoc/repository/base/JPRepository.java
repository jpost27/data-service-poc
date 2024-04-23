package com.jp.dataservicepoc.repository.base;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import org.hibernate.jpa.AvailableHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@NoRepositoryBean
public interface JPRepository<E, Q extends com.querydsl.core.types.EntityPath<E>, ID>
        extends ListCrudRepository<E, ID>,
                PagingAndSortingRepository<E, ID>,
                JpaRepository<E, ID>,
                QuerydslPredicateExecutor<E>,
                QuerydslBinderCustomizer<Q> {

    @SuppressWarnings("unchecked")
    default Page<E> findAll(
            @NonNull EntityPathBase<E> path,
            @NonNull EntityManager em,
            @Nullable Predicate predicate,
            @NonNull Pageable pageable,
            @Nullable EntityGraph<E> fetchGraph) {
        Querydsl querydsl = new Querydsl(em, new PathBuilder<>(path.getType(), path.getMetadata()));
        long total;
        if (predicate == null) {
            total = count();
        } else {
            total = count(predicate);
        }

        JPAQuery<Object> query = new JPAQuery<>(em).from(path);
        if (predicate != null) {
            query.where(predicate);
        }
        if (fetchGraph != null) {
            query.setHint(AvailableHints.HINT_SPEC_FETCH_GRAPH, fetchGraph);
        }
        if (total > pageable.getOffset()) {
            return (Page<E>) new PageImpl<>(
                    querydsl.applyPagination(pageable, query).from(path).fetch(), pageable, total);
        } else {
            return new PageImpl<>(Collections.emptyList(), pageable, total);
        }
    }

    @SuppressWarnings("unchecked")
    default List<E> findAll(
            @NonNull EntityPathBase<E> path,
            @NonNull EntityManager em,
            @Nullable Predicate predicate,
            @NonNull Sort sort,
            @Nullable EntityGraph<E> fetchGraph) {
        Querydsl querydsl = new Querydsl(em, new PathBuilder<>(path.getType(), path.getMetadata()));

        JPAQuery<Object> query = new JPAQuery<>(em).from(path);
        if (predicate != null) {
            query.where(predicate);
        }
        if (fetchGraph != null) {
            query.setHint(AvailableHints.HINT_SPEC_FETCH_GRAPH, fetchGraph);
        }
        return (List<E>) querydsl.applySorting(sort, query).from(path).fetch();
    }
}
