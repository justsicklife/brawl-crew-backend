package com.gamecrew.demo.mapper;

import com.gamecrew.demo.dto.api.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    // 게시글, 사용자, MostBrawlers 데이터를 가져오는 메서드
    List<PostDTO> findPostsWithUsers(@Param("size") int size, @Param("offset") int offset);

    // 게시글의 총 개수 조회
    int countPosts();
}
