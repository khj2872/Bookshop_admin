package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.DeliveryStatus;
import com.kobobook.www.admin.domain.Order;
import com.kobobook.www.admin.domain.OrderItem;
import com.kobobook.www.admin.dto.OrderDTO;
import com.kobobook.www.admin.repository.OrderItemRepository;
import com.kobobook.www.admin.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OrderBy;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderItemRepository orderItemRepository;

    private OrderRepository orderRepository;

    private ModelMapper modelMapper;

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

    /*
    * 배송준비중 조회
    * */
//    @Transactional
//    public Page<OrderDTO> readBeginOrderList() {
////        Page<Order> orderPage = orderRepository.findBiginOrders(PageRequest.of(0, 10, new Sort(Sort.Direction.DESC, "orderDate")));
////        return orderPage.map(o -> convertToDto(o));
//    }

    private OrderDTO convertToDto(Order order) {
        OrderDTO orderDto = modelMapper.map(order, OrderDTO.class);
        return orderDto;
    }

}
