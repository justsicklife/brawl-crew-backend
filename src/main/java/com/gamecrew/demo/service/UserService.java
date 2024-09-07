package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }
}
