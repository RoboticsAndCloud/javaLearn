/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.lonefeifei.mvc.rest.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonefeifei.domain.entity.Game;
import com.lonefeifei.domain.entity.SysRole;
import com.lonefeifei.domain.repository.GameRepository;
import com.lonefeifei.mvc.dto.GameDto;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;

/**
 * Created by baidu on 16/8/25.
 */
@RestController
@RequestMapping("/games")
public class GameController {

    private static final Logger LOG = LoggerFactory.getLogger(GameController.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    private static final String REDIS_PREFIX = "redis";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valueOperations;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(method = RequestMethod.GET)
//    @Cacheable
    public List<Game> getGames(String times) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("authentiation {}", authentication);
        if (times != null) {
//            return gameRepository.findFirst3ByZone("default");
            return gameRepository.findFirst3ByZone("default", new Sort(Sort.Direction.DESC, "id"));

        }
        return gameRepository.findAll();
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public List<Game> searchGames(String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("authentiation {}", authentication);

        return gameRepository.findAll(new GameRepository.SearchSpecification(name));
    }

//    @RequestMapping(value = "/searchByNamedQuery",method = RequestMethod.GET)
//    public String findGameByNamedQuery(String name, String zone) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LOG.info("authentiation {}", authentication);
//
//        return gameRepository.findByNamedQuery(name, "default");
//    }

    @RequestMapping(value = "/zones/{zone}", method = RequestMethod.GET)
    public List<Game> getGamesByZone(@PathVariable("zone") String zone) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOG.info("authentiation {}", authentication);
        return gameRepository.findByZoneWithQueryParamIndex(zone); //@query
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public Page<Game> getGameByPage(String name){
        return gameRepository.findByZoneAndNameLike("default", name, new PageRequest(0, 5));
    }

    @RequestMapping(value = "/namesUpdate", method = RequestMethod.PUT)
    @Transactional
    public void setGameName(String name, String newName){
        gameRepository.setGameComment(name, newName);
    }

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public GameDto getOneGame() {

        Object redisDto = valueOperations.get(REDIS_PREFIX + "onedto1");

        if (redisDto != null) {
            return (GameDto)redisDto;
        }

        GameDto dto =  new GameDto().fromModel(gameRepository.findAll()
                .get(0));
        valOpsStr.set(REDIS_PREFIX + "one", dto.getName());
        valueOperations.set(REDIS_PREFIX + "onedto1", dto);

        return dto;
    }

    @RequestMapping(value = "/oneStrRedis", method = RequestMethod.GET)
    public String getOneGameNameFromRedis() {
        String testRedis = valOpsStr.get(REDIS_PREFIX + "one");
        System.out.println(testRedis);
        GameDto dto =  new GameDto().fromModel(gameRepository.findAll()
                .get(0));
        valueOperations.set(REDIS_PREFIX + "onedto", dto);
//        Object object = valueOperations.get(REDIS_PREFIX + "onedto");
        return testRedis;
    }

    @RequestMapping(value = "/oneDtoRedis", method = RequestMethod.GET)
    public GameDto getOneGameDtoFromRedis() {

        valueOperations.get(REDIS_PREFIX + "onedto");
        Object object = valueOperations.get(REDIS_PREFIX + "onedto");
        return (GameDto)object;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @Cacheable(value="people", key = "#p0")
    public GameDto findGame(@PathVariable("name") String name) {
        Optional<Game> game =  gameRepository.findOneByZoneAndName("default", name);
        GameDto dto =  new GameDto().fromModel(game.get());
        return dto;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable("name") String name) throws Exception {
        Optional<Game> game = gameRepository.findOneByZoneAndName("default", name);
        if (!game.isPresent()) {
            throw new Exception("game not exist");
        }
        gameRepository.delete(game.get());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    public GameDto updateGame(@PathVariable("name") String name, @RequestBody GameDto dto) throws Exception {
        Optional<Game> game = gameRepository.findOneByZoneAndName("default", name);
        if (!game.isPresent()) {
            throw new Exception("game not exist");
        }

        Game gameFind = game.get();
        dto.toModel(gameFind);
        gameRepository.delete(game.get());

        Game gameSave = gameRepository.saveAndFlush(gameFind);
        return new GameDto().fromModel(gameSave);
    }


    @RequestMapping(method = RequestMethod.POST)
    public GameDto createGames(@RequestBody GameDto dto) {
        //LOG.debug("createGames", dto);

        Game game = new Game();
        dto.toModel(game);
        Game saved = gameRepository.save(game);
        //LOG.debug("Configure {} saved succeed.", saved);
        return new GameDto().fromModel(saved);
    }


    //测试 json格式创建game
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public GameDto addGames(@RequestBody JSONObject gameJson) {
        //LOG.debug("createGames", dto);

        Game cfg = new Game();
        cfg.setName((String)gameJson.get("name"));
        cfg.setComment((String) gameJson.get("comment"));
        cfg.setTitle((String) gameJson.get("title"));
        cfg.setContent((String) gameJson.get("content"));
        cfg.setMaxParticipantCount((int) gameJson.get("maxParticipantCount"));
        cfg.setParticipantCount((int) gameJson.get("participantCount"));
        cfg.setMoney((int) gameJson.get("money"));
        cfg.setBetType((String) gameJson.get("betType"));

        Game saved = gameRepository.save(cfg);
        //LOG.debug("Configure {} saved succeed.", saved);
        return new GameDto().fromModel(saved);
    }

}
