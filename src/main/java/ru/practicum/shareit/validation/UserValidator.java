package ru.practicum.shareit.validation;

import java.util.Set;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EmailAlreadyExistsException;

@Component
public class UserValidator {

    public void validateUniqueEmail(String email, Set<String> emailSet) {
        if (!isUniqueEmail(email, emailSet)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    private boolean isUniqueEmail(String email, Set<String> emailSet) {
        return emailSet.add(email);
    }

}
