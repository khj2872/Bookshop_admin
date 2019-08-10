package com.kobobook.www.admin.dto;

import com.kobobook.www.admin.domain.OrderStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;

    private MemberDTO member;

    private Date orderDate;

    private OrderStatus status;

    private List<OrderItemDTO> orderItems;

    private DeliveryDto delivery;

    private long usingPoint;

    private long savingPoint;

    private long totalPrice;

}