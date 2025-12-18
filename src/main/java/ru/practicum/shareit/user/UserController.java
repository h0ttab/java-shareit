package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.*;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService service;

    @PostMapping
    public UserReturnDto create(@Valid @NotNull @RequestBody UserCreateDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{userId}")
    public UserReturnDto get(@PathVariable long userId) {
        return service.get(userId);
    }

    @PatchMapping("/{userId}")
    public UserReturnDto update(@RequestBody UserUpdateDto dto, @PathVariable long userId) {
        return service.update(dto, userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable long userId) {
        service.delete(userId);
    }
}
