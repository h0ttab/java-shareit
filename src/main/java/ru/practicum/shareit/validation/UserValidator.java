package ru.practicum.shareit.validation;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EmailAlreadyExistsException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.repository.UserRepository;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserValidator {
    private final UserRepository userRepository;

    public void validateUniqueEmail(String email, Set<String> emailSet) {
        if (!isUniqueEmail(email, emailSet)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    private boolean isUniqueEmail(String email, Set<String> emailSet) {
        return emailSet.add(email);
    }

    public void validateExistingUser(long userId) {
        if (isExistingUser(userId)) {
            throw new NotFoundException(String.format("Пользователь с ID %d не найден", userId));
        }
    }

    private boolean isExistingUser(long userId) {
        return userRepository.get(userId).isEmpty();
    }
}
