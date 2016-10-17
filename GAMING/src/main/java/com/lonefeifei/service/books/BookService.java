/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.service.books;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

/**
 * Created by baidu on 16/10/12.
 */
@FeignClient(name = "booksClient", url = "${project.book-url}")
public interface BookService {
    @RequestMapping(value = "v1/books", method = GET, consumes = APPLICATION_JSON_UTF8_VALUE)
    List<Object> getAllBooks();
}
