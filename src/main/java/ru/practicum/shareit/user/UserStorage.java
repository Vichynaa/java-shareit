package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserStorage {
    public User create(UserDto userDto);

    public User update(UserDto userDto, Long userId);

    public List<User> findAll();

    public User findUserById(Long userId);

    public String deleteUserById(Long userId);

    public Boolean isUserContains(Long userId);
}
