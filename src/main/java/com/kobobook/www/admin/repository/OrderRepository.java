package com.kobobook.www.admin.repository;

import com.kobobook.www.admin.domain.DeliveryStatus;
import com.kobobook.www.admin.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o " +
            "FROM Order o JOIN FETCH o.member " +
            "JOIN FETCH o.delivery " +
            "WHERE o.id = :orderId")
    Order findOrderWithMemberAndDelivery(Integer orderId);

    @Query("SELECT COUNT(o) FROM Order o JOIN o.delivery d WHERE d.status = :status")
    Long selectCountOrder(DeliveryStatus status);

    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.orderDate BETWEEN :startDateTime AND :endDateTime")
    List<Order> findOrderBetweenDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
