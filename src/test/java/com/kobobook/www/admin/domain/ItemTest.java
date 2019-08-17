package com.kobobook.www.admin.domain;

import com.kobobook.www.admin.exception.NotEnoughStockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemTest {

    @Test
    public void testAddStock() {
        //given
        Item item = Item.builder()
                .stock(5).build();

        //when
        item.addStock(5);

        //then
        assertThat(item.getStock()).isEqualTo(10);
    }

    @Test
    public void testNotEnoughStock() {
        //given
        Item item = Item.builder()
                .stock(2).build();

        //when, then
        assertThatExceptionOfType(NotEnoughStockException.class).isThrownBy(() -> {
            item.removeStock(3);
        });

    }

    @Test
    public void testRemoveStock() {
        //given
        Item item = Item.builder()
                .stock(2).build();

        //when
        item.removeStock(1);

        //then
        assertThat(item.getStock()).isEqualTo(1);
    }

}
