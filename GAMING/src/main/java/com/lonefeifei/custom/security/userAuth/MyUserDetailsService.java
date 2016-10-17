package com.lonefeifei.custom.security.userAuth;

import com.lonefeifei.domain.entity.User;
import com.lonefeifei.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by baidu on 16/9/7.
 */
@Service("MyUserDetailsImpl")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user = userRepository.findOneByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException("user not exist");
        }
        return user;
    }
}
