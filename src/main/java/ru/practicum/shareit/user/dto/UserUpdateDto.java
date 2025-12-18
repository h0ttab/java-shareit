package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateDto {
    @Pattern(
            regexp = "^\\s*\\S.*$",
            message = "Имя пользователя не может быть пустым или состоять из пробелов"
    )
    private final String name;

    @Email(message = "Указан некорректный формат email")
    private final String email;
}
