package ru.practicum.shareit.user.repository;

import java.util.*;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

@Repository
public class UserRepositoryInMemoryImpl implements UserRepository {
    private final Map<Long, User> userStorage = new HashMap<>();

    @Override
    public User create(User user) {
        userStorage.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> get(long userId) {
        // Вернёт null если ничего не найдено
        return Optional.ofNullable(
                userStorage.get(userId)
        );
    }

    @Override
    public User update(User user) {
        userStorage.replace(user.getId(), user);
        return user;
    }

    @Override
    public void delete(long userId) {
        userStorage.remove(userId);
    }
}
