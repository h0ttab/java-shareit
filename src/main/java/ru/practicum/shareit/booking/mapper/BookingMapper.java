package ru.practicum.shareit.booking.mapper;

import org.mapstruct.*;
import ru.practicum.shareit.booking.dto.*;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BookingMapper {

    @Mapping(target = "item", source = "item")
    @Mapping(target = "booker", source = "booker")
    BookingReturnDto toDto(Booking booking);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    BookingItemDto toItemDto(Item item);

    @Mapping(target = "id", source = "id")
    BookingUserDto toUserDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", source = "item")
    @Mapping(target = "booker", source = "booker")
    @Mapping(target = "status", constant = "WAITING")
    @Mapping(target = "start", source = "dto.start")
    @Mapping(target = "end", source = "dto.end")
    Booking toEntity(BookingCreateDto dto, Item item, User booker);

    @BeforeMapping
    default void validate(
            BookingCreateDto dto,
            Item item,
            User booker
    ) {
        if (item == null || booker == null) {
            throw new IllegalArgumentException("Item and booker must not be null");
        }
    }
}