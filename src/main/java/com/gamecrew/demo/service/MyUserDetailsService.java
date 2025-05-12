package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.domain.UserPrincipal;
import com.gamecrew.demo.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername");

        User user = repo.findByUserEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
