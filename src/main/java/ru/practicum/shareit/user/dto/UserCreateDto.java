package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(@NotBlank(message = "Имя пользователя не может быть пустым") String name,
                            @NotBlank(message = "Email не может быть пустым") @Email(message = "Указан некорректный формат email") String email) {
}