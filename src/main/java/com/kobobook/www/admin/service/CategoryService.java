package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.Category;
import com.kobobook.www.admin.domain.OrderItem;
import com.kobobook.www.admin.dto.CategoryDTO;
import com.kobobook.www.admin.dto.OrderItemDTO;
import com.kobobook.www.admin.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    public List<CategoryDTO> findCategoryList() {
        return categoryRepository.findAll().stream()
                .map(this::convertToCategoryDto)
                .collect(Collectors.toList());

    }

    private CategoryDTO convertToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
