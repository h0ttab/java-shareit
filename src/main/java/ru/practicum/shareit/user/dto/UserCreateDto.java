package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreateDto {
    @NotBlank
    private final String name;

    @NotBlank
    @Email(message = "Указан некорректный формат email")
    private final String email;
}