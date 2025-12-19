package ru.practicum.shareit.validation;

import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.repository.ItemRepository;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ItemValidator {
    private final ItemRepository itemRepository;

    public void validateExistingItem(long itemId) {
        if (!isExistingItem(itemId)) {
            throw new NotFoundException(String.format("Вещь с id %d не найдена", itemId));
        }
    }

    private boolean isExistingItem(long itemId) {
        return itemRepository.get(itemId).isPresent();
    }

    public void validateCorrectOwner(long headerOwnerId, long itemId) {
        if (
                !isCorrectOwner(headerOwnerId, itemRepository.get(itemId).orElseThrow(
                        () -> new NotFoundException(String.format("Вещь с id %d не найдена", itemId))).getOwnerId())
        ) {
            throw new NotFoundException(
                    String.format("Пользователь id %d не является владельцем вещи id %d", headerOwnerId, itemId)
            );
        }
    }

    private boolean isCorrectOwner(long headerOwnerId, long itemDtoOwnerId) {
        return Objects.equals(headerOwnerId, itemDtoOwnerId);
    }
}
