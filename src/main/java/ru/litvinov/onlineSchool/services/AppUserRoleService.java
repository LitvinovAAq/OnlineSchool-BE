package ru.litvinov.onlineSchool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.litvinov.onlineSchool.models.AppUserRole;
import ru.litvinov.onlineSchool.repositories.AppUserRoleRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AppUserRoleService {
    private final AppUserRoleRepository appUserRoleRepository;

    @Autowired
    public AppUserRoleService(AppUserRoleRepository appUserRoleRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
    }

    public AppUserRole findAppUserRoleByRoleName(String name){
        Optional<AppUserRole> appUsersRoles = appUserRoleRepository.findByRoleName(name);
        return appUsersRoles.orElse(null);
    }
}
