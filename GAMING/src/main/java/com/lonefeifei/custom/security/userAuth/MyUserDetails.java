package com.lonefeifei.custom.security.userAuth;

import com.lonefeifei.domain.entity.SysRole;
import com.lonefeifei.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by baidu on 16/9/7.
 */
public class MyUserDetails extends User {

//    private List<SysRole> roles;
//
//    public MyUserDetails(User user, List<SysRole> roles){
//        this.roles = roles;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(roles == null || roles.size() <1){
//            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
//        }
//        StringBuilder commaBuilder = new StringBuilder();
//        for(SysRole role : roles){
//            commaBuilder.append(role.getName()).append(",");
//        }
//        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
