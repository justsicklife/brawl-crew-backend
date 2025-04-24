package com.gamecrew.demo.mapper;

import com.gamecrew.demo.dto.service.UserBrawlersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserBrawlerMapper {
    void batchInsertUserBrawlers(@Param("userBrawlers") List<UserBrawlersDto> userBrawlers);
}