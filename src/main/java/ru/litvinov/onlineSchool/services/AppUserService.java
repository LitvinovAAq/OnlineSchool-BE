package ru.litvinov.onlineSchool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.litvinov.onlineSchool.models.AppUser;
import ru.litvinov.onlineSchool.repositories.AppUserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserRoleService appUserRoleService;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, AppUserRoleService appUserRoleService) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleService = appUserRoleService;
    }

    public List<AppUser> findAllAppUser(){
        return appUserRepository.findAll();
    }

    public AppUser findAppUserById(int id){
        Optional<AppUser> appUser = appUserRepository.findById(id);
        return appUser.orElse(null);
    }

    public AppUser findAppUserByEmail(String email){
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        return appUser.orElse(null);
    }

    @Transactional
    public void saveAppUser(AppUser appUser){
        enrichAppUser(appUser);
        appUserRepository.save(appUser);
    }

    public void enrichAppUser(AppUser appUser){
        appUser.setRegistrationDate(LocalDateTime.now());
        appUser.setAppUserRole(appUserRoleService.findAppUserRoleByRoleName("ROLE_USER"));
    }
}
