package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.Delivery;
import com.kobobook.www.admin.domain.DeliveryStatus;
import com.kobobook.www.admin.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class DeliveryService {

    private DeliveryRepository deliveryRepository;

    /*
     * 배송 시작
     * */
    @Transactional
    public void startOrder(Integer[] orderIdList) {
        Arrays.stream(orderIdList).forEach(orderId -> {
            Delivery delivery = deliveryRepository.findByOrderId(orderId);
            delivery.setStatus(DeliveryStatus.DELIVERY);
        });

    }

}
