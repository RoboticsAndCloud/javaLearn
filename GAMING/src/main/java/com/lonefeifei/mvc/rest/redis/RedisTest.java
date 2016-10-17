/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.mvc.rest.redis;

import com.lonefeifei.mvc.dto.GameDto;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

/**
 * Created by baidu on 16/10/15.
 */
@RestController
@RequestMapping("/redis")
public class RedisTest {
    private static final Logger LOG = LoggerFactory.getLogger(RedisTest.class);

    private static final String REDIS_PREFIX = "testRedis_";
    private static final String REDIS_OBJ_PREFIX = "testRedisObj_";


    @Autowired
    StringRedisTemplate stringRedisTemplate;  //针对键值对为字符串类型

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valueOperations;

    @RequestMapping(value = "/str", method = RequestMethod.POST)
    public void setValueRedis(@RequestParam String key, @RequestParam String value) {

        valOpsStr.set(REDIS_PREFIX + key, value);

        stringRedisTemplate.execute(new RedisCallback<Object>() {
                                  public Object doInRedis(RedisConnection connection) throws DataAccessException {
                                      Long size = connection.dbSize();
                                      // Can cast to StringRedisConnection if using a StringRedisTemplate
                                      ((StringRedisConnection)connection).set("connection" + key, value);
                                      return null;
                                  }
                              }
        );
    }

    @RequestMapping(value = "/str", method = RequestMethod.GET)
    public String getValueRedis(@RequestParam String key) {
       return valOpsStr.get(REDIS_PREFIX + key);
    }

    @RequestMapping(value = "/strList", method = RequestMethod.POST)
    public void setListTest(String key, String value){

        stringRedisTemplate.opsForList().leftPush(key, value);
        BoundListOperations boundListOperations =  stringRedisTemplate.boundListOps(key);
        List<String> stringList = new ArrayList<>();
        stringList.add("strList1");
        stringList.add("strList2");
        boundListOperations.rightPushAll(stringList.toArray());
    }

    @RequestMapping(value = "/obj", method = RequestMethod.GET)
    public Object getObjValueRedis(@RequestParam String key) {
//        return valueOperations.get(REDIS_OBJ_PREFIX + key);
        return redisTemplate.opsForValue().get(REDIS_OBJ_PREFIX+key);
    }

    @RequestMapping(value = "/obj", method = RequestMethod.POST)
    public void setObjValueRedis(String key, String value){

        redisTemplate.opsForValue().set(REDIS_OBJ_PREFIX+key, value);
    }


    @RequestMapping(value = "/strList", method = RequestMethod.GET)
    public List<String> getListTest(String key){
        stringRedisTemplate.boundListOps(key);

        return stringRedisTemplate.opsForList().range(key, 0, -1);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Set<Object> getReidsInfo(String key){

        System.out.println("stringRedisTemplate.getKeySerializer:" + stringRedisTemplate.getKeySerializer());
        System.out.println("stringRedisTemplate.getValueSerializer:" + stringRedisTemplate.getValueSerializer());
        System.out.println("redisTemplate.getKeySerializer: " + redisTemplate.getKeySerializer());
        System.out.println("redisTemplate.getValueSerializer:" + redisTemplate.getValueSerializer());
        System.out.println(stringRedisTemplate.keys(key));
        System.out.println(redisTemplate.keys(key));
        stringRedisTemplate.execute(new RedisCallback<Object>() {
                                        public Object doInRedis(RedisConnection connection)
                                                throws DataAccessException {
                                            Long size = connection.dbSize();
                                            // Can cast to StringRedisConnection if using a StringRedisTemplate
                                            System.out.println("connection.dbSize :" + size);
                                            System.out.println(((StringRedisConnection) connection).keys(key.getBytes()));
                                            return null;
                                        }
                                    });
        Set < Object > keys = redisTemplate.keys(key);

        return keys;
    }




    public String getKeys() {
        redisTemplate.opsForValue();
        redisTemplate.keys("*");

        LOG.info(stringRedisTemplate.getKeySerializer()
                .toString());

        stringRedisTemplate.opsForValue();

        return "";
    }
}
