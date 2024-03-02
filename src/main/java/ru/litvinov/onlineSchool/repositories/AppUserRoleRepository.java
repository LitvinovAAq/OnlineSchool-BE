package ru.litvinov.onlineSchool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.litvinov.onlineSchool.models.AppUserRole;

import java.util.Optional;

@Repository
public interface AppUserRoleRepository extends CrudRepository<AppUserRole, Integer> {
    Optional<AppUserRole> findByRoleName(String name);
}
