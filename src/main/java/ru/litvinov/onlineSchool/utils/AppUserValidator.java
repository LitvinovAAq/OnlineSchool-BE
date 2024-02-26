package ru.litvinov.onlineSchool.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.litvinov.onlineSchool.models.AppUsers;
import ru.litvinov.onlineSchool.services.AppUserService;

@Component
public class AppUserValidator implements Validator {

    private final AppUserService appUserService;

    @Autowired
    public AppUserValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AppUsers.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AppUsers appUser = (AppUsers) o;
        if (appUserService.findAppUserByEmail(appUser.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.appUser.email", "Пользователь с таким email уже существует");
        }
    }
}
