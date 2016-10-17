/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lonefeifei.domain.entity.User;

/**
 * Created by baidu on 16/8/25.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findOneById(int id);
    User findOneByUsername(String name);
    Page<User> findByUsernameLike(String name, Pageable pageable);
}
