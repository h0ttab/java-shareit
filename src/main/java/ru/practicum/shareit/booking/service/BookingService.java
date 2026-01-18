package ru.practicum.shareit.booking.service;

import java.util.List;

import ru.practicum.shareit.booking.dto.*;

public interface BookingService {

    BookingReturnDto create(Long bookerId, BookingCreateDto dto);

    BookingReturnDto approve(Long ownerId, Long bookingId, boolean approved);

    BookingReturnDto getById(Long userId, Long bookingId);

    List<BookingReturnDto> getByBooker(Long bookerId, BookingState state);

    List<BookingReturnDto> getByOwner(Long ownerId, BookingState state);
}