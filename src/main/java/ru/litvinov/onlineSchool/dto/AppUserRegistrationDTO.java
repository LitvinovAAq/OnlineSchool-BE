package ru.litvinov.onlineSchool.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

;

@Data
public class AppUserRegistrationDTO {

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неверный формат Email")
    private String email;

    private String password;
}
