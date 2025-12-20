package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.dto.UserReturnDto;
import ru.practicum.shareit.user.model.User;

@Component
public class UserMapper {
    public UserReturnDto toUserReturnDto(User user) {
        return new UserReturnDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
