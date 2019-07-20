package com.kobobook.www.admin.dto;

import com.kobobook.www.admin.domain.Address;
import com.kobobook.www.admin.domain.DeliveryStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {

    private Integer id;

    private Address address;

    private DeliveryStatus status;

}
