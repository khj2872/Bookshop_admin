package com.kobobook.www.admin.controller;

import com.kobobook.www.admin.domain.Category;
import com.kobobook.www.admin.domain.Item;
import com.kobobook.www.admin.repository.CategoryRepository;
import com.kobobook.www.admin.repository.ItemRepository;
import com.kobobook.www.admin.service.ItemService;
import com.kobobook.www.admin.web.ItemController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ItemController.class).build();
    }

    @Test
    public void testRegister() throws Exception {
        //given
        List<Category> categoryList = Arrays.asList(
                Category.builder().name("경제").build(),
                Category.builder().name("과학").build()
        );

        given(categoryRepository.findAll()).willReturn(categoryList);

        //when
        ResultActions actions = mockMvc.perform(get("/admin/items/register"))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(model().attribute("categoryList", categoryList))
                .andExpect(view().name("item/register"));

//        verify(categoryRepository, times(1));

    }

}
