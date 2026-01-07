package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.*;
import ru.practicum.shareit.user.dto.UserReturnDto;

import java.util.List;

public interface UserService {

    UserReturnDto create(UserCreateDto userDto);

    UserReturnDto update(Long userId, UserUpdateDto userDto);

    UserReturnDto getById(Long userId);

    List<UserReturnDto> getAll();

    void delete(Long userId);
}