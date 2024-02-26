package ru.litvinov.onlineSchool.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AppUserRegistrationDTO {

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неверный формат Email")
    private String email;

    private String password;
}
