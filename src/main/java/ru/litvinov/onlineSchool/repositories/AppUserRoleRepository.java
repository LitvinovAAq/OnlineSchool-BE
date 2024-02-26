package ru.litvinov.onlineSchool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.litvinov.onlineSchool.models.AppUsersRoles;

import java.util.Optional;

@Repository
public interface AppUserRoleRepository extends CrudRepository<AppUsersRoles, Integer> {
    Optional<AppUsersRoles> findByRoleName(String name);
}
