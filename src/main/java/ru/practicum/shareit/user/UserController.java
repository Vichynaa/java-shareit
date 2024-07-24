package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */

@Slf4j
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserInterface userService;

    @PostMapping
    public User create(@Valid @RequestBody UserDto userDto) {
        log.info("POST /users - с даннами: name - {}; email - {}", userDto.getName(), userDto.getEmail());
        return userService.create(userDto);
    }

    @PatchMapping("/{userId}")
    public User update(@Valid @RequestBody UserDto userDto,  @PathVariable Long userId) {
        log.info("PATCH /users/{} - с даннами: name - {}; email - {}", userId, userDto.getName(), userDto.getEmail());
        return userService.update(userDto, userId);
    }

    @GetMapping
    public List<User> findAll() {
        log.info("GET /users");
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Long userId) {
        log.info("GET /users/{}", userId);
        return userService.findUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteUserById(@PathVariable Long userId) {
        log.info("DELETE /users/{}", userId);
        return userService.deleteUserById(userId);
    }

}
