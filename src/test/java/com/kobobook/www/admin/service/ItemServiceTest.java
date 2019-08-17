package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.Category;
import com.kobobook.www.admin.domain.Item;
import com.kobobook.www.admin.dto.ItemDTO;
import com.kobobook.www.admin.repository.CategoryRepository;
import com.kobobook.www.admin.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCreateItem() {
        //given
        Category category = Category.builder()
                .name("코믹").build();
        Category inputCategory = categoryRepository.save(category);

        Item item = Item.builder()
                .name("어벤져스")
                .category(Category.builder()
                    .id(inputCategory.getId()).build()).build();

        //when
        itemService.create(item);
        List<Item> foundAll = itemRepository.findAll();

        //then
        assertThat(foundAll.get(0).getName()).isEqualTo("어벤져스");
        assertThat(foundAll.get(0).getCategory().getName()).isEqualTo("코믹");
    }

    @Test
    public void testFindItemWithCategory() {
        //given
        Item item = Item.builder()
                .name("Vue.js")
                .category(Category.builder()
                    .name("컴퓨터").build()).build();

        Item insertItem = itemRepository.save(item);

        //when
        ItemDTO.ItemWithCategory itemDTO = itemService.findItemWithCategory(insertItem.getId());

        //given
        assertThat(itemDTO.getName()).isEqualTo("Vue.js");
        assertThat(itemDTO.getCategory().getName()).isEqualTo("컴퓨터");
    }

    @Test
    public void testUpdateItem() {
        //given
        Category category = Category.builder()
                .name("컴퓨터").build();
        Category category1 = Category.builder()
                .name("IT").build();
        Category inputCategory = categoryRepository.save(category);
        Category inputCategory1 = categoryRepository.save(category1);

        Item item = Item.builder()
                .name("Vue.js")
                .category(inputCategory).build();

        Item insertItem = itemRepository.save(item);

        Item updateItem = Item.builder()
                .name("수정 Vue.js")
                .category(Category.builder()
                    .id(inputCategory1.getId()).build()).build();

        //when
        itemService.updateItem(insertItem.getId(), updateItem);
        Item found = itemRepository.findById(insertItem.getId()).orElse(null);

        //then
        assertThat(found.getName()).isEqualTo("수정 Vue.js");
        assertThat(found.getCategory().getName()).isEqualTo("IT");
    }

}
