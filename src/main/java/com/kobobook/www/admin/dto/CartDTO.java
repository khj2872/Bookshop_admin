package com.kobobook.www.admin.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Integer id;

    private MemberDTO member;

    private ItemDTO.Item item;

    private long price;

    private int count;

    private double savingRate;

}
