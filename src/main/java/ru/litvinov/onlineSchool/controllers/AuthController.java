package ru.litvinov.onlineSchool.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.litvinov.onlineSchool.dto.AppUserRegistrationDTO;
import ru.litvinov.onlineSchool.models.AppUsers;
import ru.litvinov.onlineSchool.services.AppUserService;
import ru.litvinov.onlineSchool.utils.AppUserValidator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final AppUserService appUserService;
    private final AppUserValidator appUserValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AppUserService appUserService, AppUserValidator appUserValidator, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.appUserValidator = appUserValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> performRegistration(@Valid @RequestBody AppUserRegistrationDTO appUserDTO, BindingResult bindingResult){
        appUserValidator.validate(convertToAppUser(appUserDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", true);
            response.put("message", "Ошибка валидации");
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            response.put("details", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        appUserService.saveAppUser(convertToAppUser(appUserDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body("Новый пользователь успешно зарегистрирован");
    }

    private AppUsers convertToAppUser(AppUserRegistrationDTO studentDTO) {
        return modelMapper.map(studentDTO, AppUsers.class);
    }
}
