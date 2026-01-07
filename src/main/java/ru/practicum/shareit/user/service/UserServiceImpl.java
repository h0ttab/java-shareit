package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EmailAlreadyExistsException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.*;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.util.UserUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserReturnDto create(UserCreateDto userDto) {
        log.info("Create user: email={}", userDto.email());

        validateEmailCreate(userDto.email());

        User user = userMapper.toModel(userDto);
        User saved = userRepository.save(user);
        return userMapper.toUserReturnDto(saved);
    }

    @Override
    public UserReturnDto update(Long userId, UserUpdateDto userDto) {
        log.info("Update user id={}", userId);

        User existing = getUserOrThrow(userId);

        validateEmailUpdate(userDto.email(), userId, existing.getEmail());

        UserUtils.updateUserDataFromDto(userDto, existing);

        User saved = userRepository.save(existing);
        return userMapper.toUserReturnDto(saved);
    }

    @Override
    public UserReturnDto getById(Long userId) {
        log.info("Get user id={}", userId);
        User user = getUserOrThrow(userId);
        return userMapper.toUserReturnDto(user);
    }

    @Override
    public List<UserReturnDto> getAll() {
        log.info("Get all users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserReturnDto)
                .toList();
    }

    @Override
    public void delete(Long userId) {
        log.info("Delete user id={}", userId);
        getUserOrThrow(userId);
        userRepository.deleteById(userId);
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private void validateEmailCreate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    private void validateEmailUpdate(String newEmail, Long userId, String currentEmail) {
        if (!newEmail.equals(currentEmail)
                && userRepository.existsByEmailAndIdNot(newEmail, userId)) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}