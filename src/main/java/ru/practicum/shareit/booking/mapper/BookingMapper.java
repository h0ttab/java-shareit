package ru.practicum.shareit.booking.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.dto.*;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BookingMapper {

    public static BookingReturnDto toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        return BookingReturnDto.builder()
                .id(booking.getId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .status(booking.getStatus())
                .item(toItemDto(booking))
                .booker(toUserDto(booking))
                .build();
    }

    private static BookingItemDto toItemDto(Booking booking) {
        if (booking.getItem() == null) {
            return null;
        }

        return BookingItemDto.builder()
                .id(booking.getItem().getId())
                .name(booking.getItem().getName())
                .build();
    }

    private static BookingUserDto toUserDto(Booking booking) {
        if (booking.getBooker() == null) {
            return null;
        }

        return BookingUserDto.builder()
                .id(booking.getBooker().getId())
                .build();
    }

    public static Booking toEntity(BookingCreateDto dto, Item item, User booker) {
        if (dto == null) {
            return null;
        }
        if (item == null || booker == null) {
            throw new IllegalArgumentException("Item and booker must not be null");
        }

        Booking booking = new Booking();
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStart(dto.getStart());
        booking.setEnd(dto.getEnd());
        booking.setStatus(BookingStatus.WAITING);
        return booking;
    }
}