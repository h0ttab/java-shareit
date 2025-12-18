package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.practicum.shareit.request.model.ItemRequest;

@Data
public class ItemCreateDto {
    @NotBlank(message = "Название вещи не может быть пустым")
    private final String name;

    @NotBlank(message = "Описание вещи не может быть пустым")
    private final String description;

    @NotNull
    private final Boolean available;

    private final ItemRequest request;
}
