package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ItemUpdateDto {
    @Pattern(regexp = "^\\s*\\S.*$", message = "Название вещи не может быть пустым или состоять из пробелов")
    private String name;

    @Pattern(regexp = "^\\s*\\S.*$", message = "Описание вещи не может быть пустым или состоять из пробелов")
    private String description;

    private Boolean available;
}