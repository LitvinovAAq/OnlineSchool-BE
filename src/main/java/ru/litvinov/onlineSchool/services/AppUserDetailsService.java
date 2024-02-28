package ru.litvinov.onlineSchool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.litvinov.onlineSchool.models.AppUser;
import ru.litvinov.onlineSchool.repositories.AppUserRepository;
import ru.litvinov.onlineSchool.security.AppUserDetails;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserRepository.findByEmail(s);
        if (appUser.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new AppUserDetails(appUser.get());
    }
}
