package com.kobobook.www.admin.repository.custom;

import com.kobobook.www.admin.domain.Order;
import com.kobobook.www.admin.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {

    List<Order> searchList(Integer memberId, OrderSearch orderSearch);

}
