package ru.practicum.shareit.user;

import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.*;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.util.IdGenerator;
import ru.practicum.shareit.validation.UserValidator;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class UserService {
    private final UserRepository repository;
    private final UserIdGenerator idGenerator;
    private final UserMapper mapper;
    private final UserValidator validator;

    private final Set<String> uniqueEmailSet = new HashSet<>();

    public UserReturnDto create(UserCreateDto dto) {
        validator.validateUniqueEmail(dto.email(), uniqueEmailSet);

        User user = repository.create(
                new User(idGenerator.getNextId(), dto.name(), dto.email())
        );
        return mapper.toUserReturnDto(user);
    }

    public UserReturnDto get(long userId) {
        User user = repository.get(userId).orElseThrow(
                () -> new NotFoundException(String.format("Пользователь с id %d не найден", userId))
        );
        return mapper.toUserReturnDto(user);
    }

    public void delete(long userId) {
        repository.delete(userId);
    }

    public UserReturnDto update(UserUpdateDto dto, long userId) {
        validator.validateUniqueEmail(dto.email(), uniqueEmailSet);

        User user = repository.get(userId).orElseThrow(
                () -> new NotFoundException(String.format("Пользователь с id %d не найден", userId))
        );

        User userUpdated = repository.update(
                new User(
                        userId,
                        dto.name() == null ? user.getName() : dto.name(),
                        dto.email() == null ? user.getEmail() : dto.email()
                )
        );
        return mapper.toUserReturnDto(userUpdated);
    }

    @Component
    private static class UserIdGenerator extends IdGenerator {

    }
}
