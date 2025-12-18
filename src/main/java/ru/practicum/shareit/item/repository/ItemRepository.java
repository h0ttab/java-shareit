package ru.practicum.shareit.item.repository;

import java.util.Optional;

import ru.practicum.shareit.item.model.Item;

public interface ItemRepository {
    Item create(Item item);

    Optional<Item> get(long itemId);

    Item update(Item item);

    void delete(long itemId);
}
