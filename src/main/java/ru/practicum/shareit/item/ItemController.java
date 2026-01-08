package ru.practicum.shareit.item;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.service.ItemService;

import static ru.practicum.shareit.util.Constants.USER_ID_HEADER;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ItemController {
    private final ItemService service;

    @PostMapping
    public ItemReturnDto create(@Valid @NotNull @RequestBody ItemCreateDto dto,
                                @RequestHeader(USER_ID_HEADER) long userId) {
        return service.create(userId, dto);
    }

    @GetMapping("/{itemId}")
    public ItemReturnDto get(@RequestHeader(USER_ID_HEADER) long userId, @PathVariable long itemId) {
        return service.getById(userId, itemId);
    }

    @GetMapping
    public List<ItemOwnerDto> getAllByOwner(@RequestHeader(USER_ID_HEADER) long userId) {
        return service.getByOwner(userId);
    }

    @GetMapping("/search")
    public List<ItemReturnDto> search(@RequestParam String text) {
        return service.search(text);
    }

    @PatchMapping("/{itemId}")
    public ItemReturnDto update(@Valid @NotNull @RequestBody ItemUpdateDto dto,
                                @RequestHeader(USER_ID_HEADER) long userId,
                                @PathVariable long itemId) {
        return service.update(userId, itemId, dto);
    }

    @DeleteMapping("/{itemId}")
    public void delete(@RequestHeader(USER_ID_HEADER) long userId,
                       @PathVariable long itemId) {
        service.getById(userId, itemId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentReturnDto addComment(@RequestHeader(USER_ID_HEADER) Long userId,
                                       @PathVariable Long itemId,
                                       @Valid @RequestBody CommentCreateDto dto) {
        return service.addComment(userId, itemId, dto);
    }
}
