package com.kobobook.www.admin.repository;

import com.kobobook.www.admin.domain.Category;
import com.kobobook.www.admin.domain.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindItemWithCategory() {
        //given
        Item item = Item.builder()
                .name("테스트 주도 개발")
                .category(Category.builder()
                        .name("IT/컴퓨터")
                        .build())
                .build();
        itemRepository.save(item);

        //when
        Item found = itemRepository.findItemWithCategory(1);

        //then
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getCategory().getName()).isEqualTo("IT/컴퓨터");
    }

    @Test
    public void testSelectCountAllItems() {
        //given
        Item item1 = Item.builder()
                .name("자바 성능 튜닝 이야기").build();

        Item item2 = Item.builder()
                .name("디자인 패턴").build();

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        Long count = itemRepository.selectCountAllItems();

        //given
        assertThat(count).isEqualTo(2);
    }

}
