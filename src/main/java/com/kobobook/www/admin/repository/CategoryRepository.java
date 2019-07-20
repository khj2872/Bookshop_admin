package com.kobobook.www.admin.repository;

import com.kobobook.www.admin.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
