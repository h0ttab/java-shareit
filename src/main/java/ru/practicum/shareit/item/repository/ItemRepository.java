package ru.practicum.shareit.item.repository;

import java.util.List;
import java.util.Optional;

import ru.practicum.shareit.item.model.Item;

public interface ItemRepository {
    Item create(Item item);

    Optional<Item> get(long itemId);

    List<Item> getAllByOwner(long ownerId);

    List<Item> search(String searchQuery);

    Item update(Item item);

    void delete(long itemId);
}
