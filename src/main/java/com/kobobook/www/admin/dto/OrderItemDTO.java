package com.kobobook.www.admin.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Integer id;

    private OrderDTO order;

    private ItemDTO.ItemSimple item;

    private long price;

    private int count;

    private long totalPrice;

}
