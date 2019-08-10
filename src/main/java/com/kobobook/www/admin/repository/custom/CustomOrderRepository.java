package com.kobobook.www.admin.repository.custom;

import com.kobobook.www.admin.domain.Order;
import com.kobobook.www.admin.domain.OrderItem;
import com.kobobook.www.admin.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {

    List<OrderItem> searchReadyOrderList(OrderSearch orderSearch);

    List<OrderItem> searchAllOrderList(OrderSearch orderSearch);

    List<OrderItem> searchOrderDetail(Integer orderId);

}
