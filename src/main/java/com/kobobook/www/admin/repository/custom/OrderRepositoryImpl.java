package com.kobobook.www.admin.repository.custom;

import com.kobobook.www.admin.domain.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.kobobook.www.admin.domain.QMember.member;
import static com.kobobook.www.admin.domain.QOrder.order;
import static com.kobobook.www.admin.domain.QItem.item;
import static com.kobobook.www.admin.domain.QOrderItem.orderItem;
import static com.kobobook.www.admin.domain.QDelivery.delivery;

import java.util.List;

@Repository
public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Order.class);
        this.queryFactory = queryFactory;
    }

    /*
    * 배송준비중 주문 검색
    * */
    @Override
    public List<OrderItem> searchReadyOrderList(OrderSearch orderSearch) {
        return queryFactory
                .selectDistinct(orderItem)
                .from(orderItem)
                .join(orderItem.order, order).fetchJoin()
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .where(containsItemName(orderSearch.getItemName()),
                        eqUserEmail(orderSearch.getUserEmail()),
                        eqOrderId(orderSearch.getOrderId()))
                .where(order.status.eq(OrderStatus.ORDER)
                        .and(delivery.status.eq(DeliveryStatus.READY)))
                .orderBy(order.orderDate.desc())
                .fetch();
    }

    @Override
    public List<OrderItem> searchOrderDetail(Integer orderId) {
        return queryFactory
                .selectDistinct(orderItem)
                .from(orderItem)
                .join(orderItem.order, order).fetchJoin()
                .join(order.member, member).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .where(order.id.eq(orderId))
                .fetch();
    }

    /*
    * 전체 주문 검색
    * */
    @Override
    public List<OrderItem> searchAllOrderList(OrderSearch orderSearch) {
        return queryFactory
                .selectDistinct(orderItem)
                .from(orderItem)
                .join(orderItem.order, order).fetchJoin()
                .join(order.member, member).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .where(containsItemName(orderSearch.getItemName()),
                        eqUserEmail(orderSearch.getUserEmail()),
                        eqOrderId(orderSearch.getOrderId()),
                        eqOrderStatus(orderSearch.getOrderStatus()),
                        eqDeliveryStatus(orderSearch.getDeliveryStatus()))
                .orderBy(order.orderDate.desc())
                .fetch();
    }

    /*
    * 주문자 아이디 검색
    * */
    private BooleanExpression eqUserEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        return order.member.userEmail.eq(email);
    }

    /*
    * 주문번호 검색
    * */
    private BooleanExpression eqOrderId(Integer orderId) {
        if (orderId == null || "".equals(orderId)) {
            return null;
        }
        return order.id.eq(orderId);
    }

    /*
    * 상품명 검색
    * */
    private BooleanExpression containsItemName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return orderItem.item.name.contains(name);
    }

    /*
    * 주문상태 검색
    * */
    private BooleanExpression eqOrderStatus(OrderStatus orderStatus) {
        if (StringUtils.isEmpty(orderStatus)) {
            return null;
        }
        return order.status.eq(orderStatus);
    }

    /*
    * 배송상태 검색
    * */
    private BooleanExpression eqDeliveryStatus(DeliveryStatus deliveryStatus) {
        if (StringUtils.isEmpty(deliveryStatus)) {
            return null;
        }
        return delivery.status.eq(deliveryStatus);
    }
}
