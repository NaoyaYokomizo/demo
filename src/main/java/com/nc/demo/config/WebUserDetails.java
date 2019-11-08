package com.nc.demo.config;

import java.util.Collection;

import com.nc.demo.db.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class WebUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Users users;
    private Collection<GrantedAuthority> authorityList;

    public WebUserDetails(Users users,Collection<GrantedAuthority> authorityList) {
        this.users = users;
        this.authorityList = authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        return this.users.getPasswd();
    }

    @Override
    public String getUsername() {
        return this.users.getUserid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}