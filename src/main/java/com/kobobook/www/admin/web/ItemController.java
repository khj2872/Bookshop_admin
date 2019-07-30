package com.kobobook.www.admin.web;

import com.kobobook.www.admin.domain.Category;
import com.kobobook.www.admin.domain.Item;
import com.kobobook.www.admin.dto.ItemDTO;
import com.kobobook.www.admin.repository.CategoryRepository;
import com.kobobook.www.admin.repository.ItemRepository;
import com.kobobook.www.admin.service.ItemService;
import com.kobobook.www.admin.util.BoardPager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/items")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    private ItemRepository itemRepository;

    private CategoryRepository categoryRepository;

    @GetMapping("/register")
    public String registerItemForm(Model model) {
        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("categoryList", categoryList);

        return "item/register";
    }

    @PostMapping("/register")
    public String registerItem(Item item) {
        System.out.println("item : " + item);
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
        List<Category> categoryList = categoryRepository.findAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("item", item);

        return "item/register";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable("id") Integer itemId, @RequestBody Item item) {
        itemService.updateItem(itemId, item);

        return "redirect:/admin/items/update/"+itemId;
    }

}
