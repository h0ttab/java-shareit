package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.Pattern;

public record ItemUpdateDto (
        @Pattern(regexp = "^\\s*\\S.*$", message = "Название вещи не может быть пустым или состоять из пробелов")
        String name,

        @Pattern(regexp = "^\\s*\\S.*$", message = "Название вещи не может быть пустым или состоять из пробелов")
        String description,

        Boolean available
) {}