/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.domain.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lonefeifei.domain.entity.Game;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by baidu on 16/8/23.
 */
public interface GameRepository extends JpaRepository<Game, Long> , JpaSpecificationExecutor<Game>{
    //, JpaSpecificationExecutor<Game>

    //常用方式：使用根据属性名查询
    Optional<Game> findOneById(int id);
    Optional<Game> findOneByZoneAndName(String zone, String name);

    // 限制结果数量：查找前10条
    List<Game> findFirst3ByZone(String zone);

    // 限制结果数量：查找前10条
    List<Game> findFirst3ByZone(String zone, Sort sort);


    // @Query查询
    @Query(value = "select * from Game g where g.zone= ?1", nativeQuery=true)
    List<Game> findByZoneWithQueryParamIndex(String zone);

    //@Query查询
    @Query(value = "select * from Game g where g.zone= :zone", nativeQuery=true)
    List<Game> findByZoneWithQuery(@Param("zone") String zone);

    //@Query查询
    @Modifying
    @Query(value = "update Game g set g.comment = ?2 where g.name = ?1", nativeQuery=true)
    int setGameComment(String name, String newName);

    // @NamedQuery查询
    String findByNamedQuery(String name, String zone);

    Page<Game> findByZoneAndNameLike(String zone, String name, Pageable pageable);


    class SearchSpecification implements Specification<Game> {
        private final String name;
        public SearchSpecification(String name) {
            this.name = name;
        }

        @Override
        public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

            //http://localhost:8553/games/search/?name=name%25
            Predicate pre =  criteriaBuilder.like(root.get("name"), name);
            return pre;
        }
    }
}
