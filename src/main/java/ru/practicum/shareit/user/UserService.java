package ru.practicum.shareit.user;

import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.EmailAlreadyExistsException;
import ru.practicum.shareit.user.dto.UserCreateDto;
import ru.practicum.shareit.user.dto.UserReturnDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.util.IdGenerator;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class UserService {
    private final UserRepository repository;
    private final UserIdGenerator idGenerator;
    private final UserMapper mapper;

    private final Set<String> uniqueEmailSet = new HashSet<>();

    public UserReturnDto create(UserCreateDto dto) {
        boolean isUniqueEmail = uniqueEmailSet.add(dto.getEmail());

        if (!isUniqueEmail) {
            throw new EmailAlreadyExistsException(dto.getEmail());
        }

        User user = repository.create(
                new User(idGenerator.getNextId(), dto.getName(), dto.getEmail())
        );
        return mapper.toUserReturnDto(user);
    }

    @Component
    private static class UserIdGenerator extends IdGenerator {

    }
}
