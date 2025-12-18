package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.request.model.ItemRequest;

@Data
@AllArgsConstructor
public class Item {
    private final long id;
    private String name;
    private String description;
    private boolean available;
    private final long ownerId;
    private final ItemRequest request;
}