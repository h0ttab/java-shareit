package ru.practicum.shareit.booking.dto;

import java.time.LocalDateTime;

import lombok.*;
import ru.practicum.shareit.booking.model.BookingStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingReturnDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
    private BookingItemDto item;
    private BookingUserDto booker;
}