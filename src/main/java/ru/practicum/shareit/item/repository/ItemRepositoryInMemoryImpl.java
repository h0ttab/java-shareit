package ru.practicum.shareit.item.repository;

import java.util.*;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

@Repository
public class ItemRepositoryInMemoryImpl implements ItemRepository {
    private final Map<Long, Item> itemStorage = new HashMap<>();

    @Override
    public Item create(Item item) {
        itemStorage.put(item.getId(), item);
        return item;
    }

    @Override
    public Optional<Item> get(long itemId) {
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        return null;
    }

    @Override
    public void delete(long itemId) {

    }
}