package com.kobobook.www.admin.repository;


import com.kobobook.www.admin.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT i FROM Item i JOIN i.category WHERE i.id = :itemId" )
    Item findItemWithCategory(Integer itemId);

    @Query("SELECT COUNT(i) FROM Item i")
    Long selectCountAllItems();
}
