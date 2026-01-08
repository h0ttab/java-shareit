package ru.practicum.shareit.user.service;

import java.util.List;

import ru.practicum.shareit.user.dto.*;

public interface UserService {

    UserReturnDto create(UserCreateDto userDto);

    UserReturnDto update(Long userId, UserUpdateDto userDto);

    UserReturnDto getById(Long userId);

    List<UserReturnDto> getAll();

    void delete(Long userId);
}