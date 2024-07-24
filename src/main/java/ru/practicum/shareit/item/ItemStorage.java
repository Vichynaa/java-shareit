package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemStorage {
    public Item create(Long userId, ItemDto itemDto);

    public Item update(Long userId, Long itemId, ItemDto itemDto);

    public Optional<Item> getItemById(Long itemId);

    public List<Item> getItemsByUser(Long userId);

    public List<Item> search(Optional<String> text, Long userId);
}
