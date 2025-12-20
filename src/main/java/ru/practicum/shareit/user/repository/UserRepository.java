package ru.practicum.shareit.user.repository;

import java.util.Optional;

import ru.practicum.shareit.user.model.User;

public interface UserRepository {
    User create(User user);

    Optional<User> get(long userId);

    User update(User user);

    void delete(long userId);
}