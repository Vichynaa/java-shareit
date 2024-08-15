package ru.practicum.shareit.user;

import exception.ConflictException;
import exception.NotFoundException;
import exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.user.dto.UserRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserDbService implements UserInterface {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(UserRequest userRequest) {
        if (userRequest.getEmail() == null || !(userRequest.getEmail().contains("@"))) {
            log.error("Электронная почта не может быть пустой и должна содержать символ @");
            throw new ValidationException("Электронная почта не может быть пустой и должна содержать символ @");
        }
        if (userRequest.getName() == null) {
            log.error("Имя не может быть пустым");
            throw new ConflictException("Имя не может быть пустым");
        }
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(UserRequest userRequest, Long userId) {
        User user;
        if (!userRepository.existsById(userId)) {
            log.error(String.format("Пользователь с id - %d, не найден", userId));
            throw new NotFoundException(String.format("Пользователь с id - %d, не найден", userId));
        }
        else {
             user = userRepository.findById(userId).get();
        }
        if (userRequest.getEmail() != null) {
            if (userRequest.getEmail().isBlank() || !(userRequest.getEmail().contains("@"))) {
                log.error("Электронная почта не может быть пустой и должна содержать символ @");
                throw new ValidationException("Электронная почта не может быть пустой и должна содержать символ @");
            }
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getName() != null) {
            if (userRequest.getName().isBlank()) {
                log.error("Имя не может быть пустым");
                throw new ValidationException("Имя не может быть пустым");
            }
            user.setName(userRequest.getName());
        }
        try {
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException e) {
            log.error(String.format("Почта %s уже существует, укажите другую", user.getEmail()));
            throw new ConflictException(String.format("Почта %s уже существует, укажите другую", user.getEmail()));
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            log.error(String.format("Пользователь с id - %d, не найден", userId));
            throw new NotFoundException(String.format("Пользователь с id - %d, не найден", userId));
        }
        return userRepository.findById(userId).get();
    }

    @Override
    @Transactional
    public String deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            log.error(String.format("Пользователь с id - %d, не найден", userId));
            throw new NotFoundException(String.format("Пользователь с id - %d, не найден", userId));
        }
        userRepository.deleteById(userId);
        return "Пользователь с id - " + ", удалил свой аккаунт";
    }

}
