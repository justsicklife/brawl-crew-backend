package com.gamecrew.demo.repository;

import com.gamecrew.demo.dto.service.UserBrawlersDto;
import com.gamecrew.demo.mapper.UserBrawlerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserBrawlerMapperTest {

    @Autowired
    private UserBrawlerMapper userBrawlerMapper;

    @Test
    public void testMapperLoaded() {
        assertNotNull(userBrawlerMapper);
    }

//    @Test
//    public void testInsertMapper() {
//
//        List<UserBrawlersDto> userBrawlers = List.of(
//                new UserBrawlersDto(1L, 2L, 300),
//                new UserBrawlersDto(1L, 3L, 400)
//        );
//
//        userBrawlerMapper.batchInsertUserBrawlers(userBrawlers);
//    }
}