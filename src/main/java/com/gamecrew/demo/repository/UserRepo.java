package com.gamecrew.demo.repository;

import com.gamecrew.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserEmail(String userEmail);

    Optional<User> findByUserId(Long userId);
}
