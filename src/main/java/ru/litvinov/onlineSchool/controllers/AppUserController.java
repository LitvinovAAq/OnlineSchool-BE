package ru.litvinov.onlineSchool.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.litvinov.onlineSchool.models.AppUsers;
import ru.litvinov.onlineSchool.services.AppUserService;

@RestController
public class AppUserController {

    private final AppUserService appUserService;
    private final ModelMapper modelMapper;

    @Autowired
    public AppUserController(AppUserService appUserService, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public AppUsers findStudentById(@PathVariable("id") int id){
        return appUserService.findAppUserById(id);
    }

}
