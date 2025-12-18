package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private final String name;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Указан некорректный формат email")
    private final String email;
}