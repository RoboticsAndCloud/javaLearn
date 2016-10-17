package com.lonefeifei.service.user;

import com.lonefeifei.domain.entity.User;
import com.lonefeifei.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by baidu on 16/8/30.
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException("user not exist");
        }
        return user;
    }
}
