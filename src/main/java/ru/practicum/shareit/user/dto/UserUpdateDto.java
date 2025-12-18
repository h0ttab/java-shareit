package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserUpdateDto(@Pattern(
        regexp = "^\\s*\\S.*$",
        message = "Имя пользователя не может быть пустым или состоять из пробелов"
) String name, @Email(message = "Указан некорректный формат email") String email) {
}
