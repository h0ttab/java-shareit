package ru.practicum.shareit.booking.model;

import java.time.LocalDateTime;

import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

public record Booking(long id, LocalDateTime start, LocalDateTime end, Item item, User booker, BookingStatus status) {
}