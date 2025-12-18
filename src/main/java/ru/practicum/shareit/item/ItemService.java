package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemCreateDto;
import ru.practicum.shareit.item.dto.ItemReturnDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.util.IdGenerator;
import ru.practicum.shareit.validation.UserValidator;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ItemService {
    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final ItemIdGenerator idGenerator;
    private final UserValidator userValidator;

    public ItemReturnDto create(ItemCreateDto dto, long ownerId) {
        userValidator.validateExistingUser(ownerId);
        Item item = repository.create(
                new Item(
                        idGenerator.getNextId(),
                        dto.getName(),
                        dto.getDescription(),
                        dto.getAvailable(),
                        ownerId,
                        dto.getRequest()
                )
        );
        return mapper.toItemReturnDto(item);
    }

    @Component
    private static class ItemIdGenerator extends IdGenerator {}
}
