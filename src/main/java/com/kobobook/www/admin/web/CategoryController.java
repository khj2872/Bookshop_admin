package com.kobobook.www.admin.web;

import com.kobobook.www.admin.domain.Category;
import com.kobobook.www.admin.dto.CategoryDTO;
import com.kobobook.www.admin.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryRepository categoryRepository;

    @GetMapping("")
    public String readCategoryList(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);

        return "item/category";
    }

    @PostMapping("")
    public String createCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

}
