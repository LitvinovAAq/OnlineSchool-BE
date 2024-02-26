package ru.litvinov.onlineSchool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.litvinov.onlineSchool.models.AppUsers;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUsers, Integer> {

    Optional<AppUsers> findByEmail(String email);
}