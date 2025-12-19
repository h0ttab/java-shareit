package ru.practicum.shareit.item;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.util.IdGenerator;
import ru.practicum.shareit.validation.ItemValidator;
import ru.practicum.shareit.validation.UserValidator;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ItemService {
    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final ItemIdGenerator idGenerator;
    private final UserValidator userValidator;
    private final ItemValidator itemValidator;

    public ItemReturnDto create(ItemCreateDto dto, long ownerId) {
        userValidator.validateExistingUser(ownerId);
        Item item = repository.create(
                new Item(
                        idGenerator.getNextId(),
                        ownerId,
                        dto.request(),
                        dto.name(),
                        dto.description(),
                        dto.available()
                )
        );
        return mapper.toItemReturnDto(item);
    }

    public ItemReturnDto update(ItemUpdateDto dto, long ownerId, long itemId) {
        userValidator.validateExistingUser(ownerId);
        itemValidator.validateExistingItem(itemId);
        itemValidator.validateCorrectOwner(ownerId, itemId);

        Item item = repository.get(itemId).get();

        Item itemUpdated = repository.update(
                new Item(
                        itemId,
                        ownerId,
                        item.getRequest(),
                        dto.name() == null ? item.getName() : dto.name(),
                        dto.description() == null ? item.getDescription() : dto.description(),
                        dto.available() == null ? item.isAvailable() : dto.available()
                )
        );
        return mapper.toItemReturnDto(itemUpdated);
    }

    public ItemReturnDto get(long itemId) {
        Item item = repository.get(itemId).orElseThrow(
                () -> new NotFoundException(String.format("Вещь с id %d не найдена", itemId))
        );
        return mapper.toItemReturnDto(item);
    }

    public List<ItemReturnDto> getAllByOwner(long ownerId) {
        return repository.getAllByOwner(ownerId).stream().map(mapper::toItemReturnDto).toList();
    }

    @Component
    private static class ItemIdGenerator extends IdGenerator {
    }
}
