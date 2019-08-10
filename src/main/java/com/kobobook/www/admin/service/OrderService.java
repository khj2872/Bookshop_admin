package com.kobobook.www.admin.service;

import com.kobobook.www.admin.domain.Order;
import com.kobobook.www.admin.domain.OrderItem;
import com.kobobook.www.admin.domain.OrderSearch;
import com.kobobook.www.admin.dto.OrderDTO;
import com.kobobook.www.admin.dto.OrderItemDTO;
import com.kobobook.www.admin.repository.OrderRepository;
import com.kobobook.www.admin.repository.custom.OrderRepositoryImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private OrderRepositoryImpl orderRepositoryImpl;

    private ModelMapper modelMapper;

    /*
    * 배송준비중 조회
    * */
    @Transactional
    public List<OrderItemDTO> readReadyOrderList(OrderSearch orderSearch) {
        List<OrderItem> orderList = orderRepositoryImpl.searchReadyOrderList(orderSearch);
        return orderList.stream()
                .map(this::convertToOrderItemDto)
                .collect(Collectors.toList());
    }

    /*
     * 전체 주문 조회
     * */
    @Transactional
    public List<OrderItemDTO> readAllOrderList(OrderSearch orderSearch) {
        List<OrderItem> orderItemList = orderRepositoryImpl.searchAllOrderList(orderSearch);
        return orderItemList.stream()
                .map(this::convertToOrderItemDto)
                .collect(Collectors.toList());
    }

    /*
     * 주문 상세내역 조회
     * */
    @Transactional
    public List<OrderItemDTO> readOrderDetail(Integer orderId) {
        return orderRepositoryImpl.searchOrderDetail(orderId).stream()
                .map(this::convertToOrderItemDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public long findTodayTotalPrice() {
        return getDayTotalPrice(LocalDate.now());
    }

    @Transactional
    public long findYesterdayTotalPrice() {
        return getDayTotalPrice(LocalDate.now().minusDays(1));
    }

    @Transactional
    public long findCurMonthTotalPrice() {
        return getMonthTotalPrice(LocalDate.now());
    }

    @Transactional
    public long findPrevMonthTotalPrice() {
        return getMonthTotalPrice(LocalDate.now().minusMonths(1));
    }

    private long getDayTotalPrice(LocalDate localDate) {
        List<Order> todayOrderList = getOrders(localDate, localDate);
        return getTotalPrice(todayOrderList);
    }

    private long getMonthTotalPrice(LocalDate localDate) {
        LocalDate start = localDate.withDayOfMonth(1); //해당 달의 1일
        List<Order> monthOrderList = getOrders(localDate, start);
        return getTotalPrice(monthOrderList);
    }

    private List<Order> getOrders(LocalDate localDate, LocalDate start) {
        LocalDateTime startDatetime = LocalDateTime.of(start, LocalTime.of(0, 0, 0)); //00:00:00
        LocalDateTime endDatetime = LocalDateTime.of(localDate, LocalTime.of(23, 59, 59)); //23:59:59
        return orderRepository.findOrderBetweenDateTime(startDatetime, endDatetime);
    }

    private long getTotalPrice(List<Order> todayOrderList) {
        long dayTotalPrice = 0;
        for (Order order : todayOrderList) {
            dayTotalPrice += order.getTotalPrice();
        }
        return dayTotalPrice;
    }

    private OrderItemDTO convertToOrderItemDto(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);
        orderItemDTO.setTotalPrice(orderItem.getTotalPrice());
        orderItemDTO.getOrder().setTotalPrice(orderItem.getOrder().getTotalPrice());
        return orderItemDTO;
    }

}
