package ru.practicum.shareit.item.model;

import lombok.Data;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

@Data
public class Item {
    private final long id;
    private String name;
    private String description;
    private boolean available;
    private final User owner;
    private final ItemRequest request;
}