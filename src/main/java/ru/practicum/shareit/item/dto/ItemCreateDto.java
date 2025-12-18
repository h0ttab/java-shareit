package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.practicum.shareit.request.model.ItemRequest;

public record ItemCreateDto(@NotBlank(message = "Название вещи не может быть пустым") String name,
                            @NotBlank(message = "Описание вещи не может быть пустым") String description,
                            @NotNull Boolean available, ItemRequest request) {
}
