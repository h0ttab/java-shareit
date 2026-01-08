package ru.practicum.shareit.item.dto;

import java.util.List;

import lombok.Data;
import ru.practicum.shareit.booking.dto.BookingReturnDto;

@Data
public class ItemOwnerDto {
    private Long id;
    private String name;
    private String description;
    private boolean available;
    private BookingReturnDto lastBooking;
    private BookingReturnDto nextBooking;
    private List<CommentReturnDto> comments;
}