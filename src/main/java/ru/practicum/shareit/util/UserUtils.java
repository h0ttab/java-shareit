package ru.practicum.shareit.util;

import ru.practicum.shareit.user.dto.UserUpdateDto;
import ru.practicum.shareit.user.model.User;

public class UserUtils {
    public static void updateUserDataFromDto(UserUpdateDto dto, User existingUser) {
        existingUser.setEmail(dto.email() == null ? existingUser.getEmail() : dto.email());
        existingUser.setName(dto.name() == null ? existingUser.getName() : dto.name());
    }
}