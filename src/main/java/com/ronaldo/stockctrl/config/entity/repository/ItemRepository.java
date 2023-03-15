package com.ronaldo.stockctrl.config.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldo.stockctrl.config.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
