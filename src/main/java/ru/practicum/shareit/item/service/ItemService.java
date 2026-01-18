package ru.practicum.shareit.item.service;

import java.util.List;

import ru.practicum.shareit.item.dto.*;

public interface ItemService {

    ItemReturnDto create(Long ownerId, ItemCreateDto dto);

    ItemReturnDto update(Long ownerId, Long itemId, ItemUpdateDto dto);

    ItemReturnDto getById(Long userId, Long itemId);

    List<ItemOwnerDto> getByOwner(Long ownerId);

    List<ItemReturnDto> search(String text);

    CommentReturnDto addComment(Long authorId, Long itemId, CommentCreateDto dto);
}