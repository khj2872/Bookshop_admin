package com.kobobook.www.admin.repository;

import com.kobobook.www.admin.domain.Member;
import com.kobobook.www.admin.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findByMember(Member member, Pageable pageable);


}
