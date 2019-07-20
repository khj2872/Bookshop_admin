package com.kobobook.www.admin.dto;

import com.kobobook.www.admin.domain.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderInfo {

    private Address address;

    private Integer[] orderListId;

    private long usingPoint;

}
