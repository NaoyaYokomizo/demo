package com.nc.demo.config;

import java.util.ArrayList;
import java.util.Collection;

import com.nc.demo.db.Users;
import com.nc.demo.db.UsersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersMapper.selectByPrimaryKey(username);
        if(users == null) {
            throw new UsernameNotFoundException(username + " is not found");
        }
        
        // ここで認証とロールの付与
        Collection<GrantedAuthority> authorityList = new ArrayList<>();
        switch (users.getAuthority()) {
            case 0:
                authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            default:
                authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new WebUserDetails(users, authorityList);
    }
}