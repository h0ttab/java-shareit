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
        return Optional.ofNullable(itemStorage.get(itemId));
    }

    @Override
    public List<Item> getAllByOwner(long ownerId) {
        return itemStorage.values().stream().filter(item -> item.getOwnerId() == ownerId).toList();
    }

    @Override
    public List<Item> search(String searchQuery) {
        return itemStorage.values().stream().filter(item -> {
            String description = item.getDescription().toLowerCase();
            String name = item.getName().toLowerCase();
            String normalizedQuery = searchQuery.toLowerCase();
            return (description.contains(normalizedQuery) || name.contains(normalizedQuery)) && item.isAvailable();
        }).toList();
    }

    @Override
    public Item update(Item item) {
        itemStorage.replace(item.getId(), item);
        return item;
    }

    @Override
    public void delete(long itemId) {
        itemStorage.remove(itemId);
    }
}