package ru.practicum.shareit.request.model;

import java.time.LocalDateTime;

import lombok.Data;
import ru.practicum.shareit.user.model.User;

@Data
public class ItemRequest {
    private final long id;
    private final String description;
    private final User requester;
    private final LocalDateTime created;
}