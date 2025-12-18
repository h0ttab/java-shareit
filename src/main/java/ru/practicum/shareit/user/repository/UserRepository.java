package ru.practicum.shareit.user.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

@Repository
public interface UserRepository {
    Optional<User> create(User user);

    Optional<User> get(long userId);

    Optional<User> update(User user);

    void delete(long userId);
}