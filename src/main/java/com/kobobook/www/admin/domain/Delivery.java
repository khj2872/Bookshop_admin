package com.kobobook.www.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kobobook.www.admin.dto.OrderInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = "order")
@NoArgsConstructor
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Integer id;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(OrderInfo orderInfo, DeliveryStatus status) {
        this.address = orderInfo.getAddress();
        this.status = status;
    }

}