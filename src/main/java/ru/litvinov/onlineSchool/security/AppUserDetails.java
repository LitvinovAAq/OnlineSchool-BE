package ru.litvinov.onlineSchool.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.litvinov.onlineSchool.models.AppUsers;

import java.util.Collection;

public class AppUserDetails implements UserDetails{

    private final AppUsers appUser;

    public AppUserDetails(AppUsers appUser) {
        this.appUser = appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.appUser.getEmail();
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

    public AppUsers getAppUser(){
        return this.appUser;
    }
}
