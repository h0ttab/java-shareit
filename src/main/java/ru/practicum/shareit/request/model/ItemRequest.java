package ru.practicum.shareit.request.model;

import java.time.LocalDateTime;

import ru.practicum.shareit.user.model.User;

public record ItemRequest(long id, String description, User requester, LocalDateTime created) {
}