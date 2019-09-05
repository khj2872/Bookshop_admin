package com.kobobook.www.admin.web;

import com.kobobook.www.admin.domain.Item;
import com.kobobook.www.admin.dto.CategoryDTO;
import com.kobobook.www.admin.dto.ItemDTO;
import com.kobobook.www.admin.repository.ItemRepository;
import com.kobobook.www.admin.service.CategoryService;
import com.kobobook.www.admin.service.ItemService;
import com.kobobook.www.admin.util.BoardPager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/items")
@NoArgsConstructor
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    private ItemRepository itemRepository;

    private CategoryService categoryService;

    @GetMapping("/register")
    public String registerItemForm(Model model) {
        List<CategoryDTO> categoryList = categoryService.findCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "item/register";
    }

    @PostMapping("/register")
    public String registerItem(Item item) {
        itemService.create(item);
        return "redirect:/admin/items/register";

    }

    @GetMapping("")
    public String list(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable, Model model) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        model.addAttribute("itemPage", itemPage);

        int totalPages = itemPage.getTotalPages();
        BoardPager pager = new BoardPager(itemPage.getTotalElements(), itemPage.getNumber() + 1);
        model.addAttribute("pager", pager);

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(pager.getBlockBegin(), pager.getBlockEnd())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "item/list";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Integer itemId, Model model) {
        ItemDTO.ItemWithCategory item = itemService.findItemWithCategory(itemId);
        List<CategoryDTO> categoryList = categoryService.findCategoryList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("item", item);

        return "item/update";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable("id") Integer itemId, Item item) {
        itemService.updateItem(itemId, item);

        return "redirect:/admin/items/update/"+itemId;
    }

}
