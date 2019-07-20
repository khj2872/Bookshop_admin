package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.DeliveryStatus;
import com.kobobook.www.admin.domain.Order;
import com.kobobook.www.admin.domain.OrderItem;
import com.kobobook.www.admin.repository.OrderItemRepository;
import com.kobobook.www.admin.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderItemRepository orderItemRepository;

    private OrderRepository orderRepository;

    /*
    * 배송 처리
    * */
    @Transactional
    public void startOrder(Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        orderItem.getOrder().getDelivery().setStatus(DeliveryStatus.COMP);
        orderItemRepository.save(orderItem);
    }

    /*
    * 주문 취소
    * */
    @Transactional
    public void cancelOrder(Integer orderId) throws RuntimeException{
        Order order = orderRepository.findById(orderId).orElse(null);
        order.cancel();
    }

}
