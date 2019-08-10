package com.kobobook.www.admin.service;


import com.kobobook.www.admin.domain.Category;
import com.kobobook.www.admin.domain.Item;
import com.kobobook.www.admin.dto.ItemDTO;
import com.kobobook.www.admin.repository.CategoryRepository;
import com.kobobook.www.admin.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;


    @Transactional
    public void create(Item item) {
        Optional<Category> category = categoryRepository.findById(item.getCategory().getId());
        item.setCategory(category.orElse(null));
        item.setRegDate(LocalDateTime.now());
        itemRepository.save(item);
    }

    @Transactional
    public ItemDTO.ItemWithCategory findItemWithCategory(Integer itemId) {
        return modelMapper.map(itemRepository.findItemWithCategory(itemId), ItemDTO.ItemWithCategory.class);

    }

    @Transactional
    public void updateItem(Integer itemId, Item item) {
        Item originalItem = itemRepository.findItemWithCategory(itemId);
        Category category = categoryRepository.findById(item.getCategory().getId()).orElse(null);
        item.setCategory(category);
        originalItem.setItem(item);
    }
}
