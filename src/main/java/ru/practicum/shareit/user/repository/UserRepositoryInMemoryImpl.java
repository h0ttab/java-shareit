package ru.practicum.shareit.user.repository;

import java.util.*;

import ru.practicum.shareit.user.model.User;

public class UserRepositoryInMemoryImpl implements UserRepository {
    private final Map<Long, User> userStorage = new HashMap<>();

    @Override
    public Optional<User> create(User user) {
        // Вернёт null если всё ОК
        return Optional.ofNullable(
                userStorage.putIfAbsent(user.getId(), user)
        );
    }

    @Override
    public Optional<User> get(long userId) {
        // Вернёт null если ничего не найдено
        return Optional.ofNullable(
                userStorage.get(userId)
        );
    }

    @Override
    public Optional<User> update(User user) {
        // Вернёт null если пользователь не существует
        return Optional.ofNullable(
                userStorage.replace(user.getId(), user)
        );
    }

    @Override
    public void delete(long userId) {
        userStorage.remove(userId);
    }
}
