package com.kobobook.www.admin.repository;

import com.kobobook.www.admin.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
