package com.jp.dataservicepoc.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface JPRepository<D, E, Q extends com.querydsl.core.types.EntityPath<E>, ID>
        extends ListCrudRepository<E, ID>,
                PagingAndSortingRepository<E, ID>,
                JpaRepository<E, ID>,
                QuerydslPredicateExecutor<E>,
                QuerydslBinderCustomizer<Q> {}
