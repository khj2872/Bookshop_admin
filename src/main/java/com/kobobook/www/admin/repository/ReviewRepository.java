package com.kobobook.www.admin.repository;

import com.kobobook.www.admin.domain.Item;
import com.kobobook.www.admin.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r WHERE r.item.id = :itemId")
    List<Review> findByItem(Integer itemId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.item = :item")
    Double findAvgRatingByItem(Item item);

}
