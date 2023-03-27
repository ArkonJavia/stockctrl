package com.ronaldo.stockctrl.config.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldo.stockctrl.config.entity.Item;
import com.ronaldo.stockctrl.config.entity.Stock;

public interface IStockRepository  extends JpaRepository<Stock, Long> {
	
	Stock findStockByItem(Item item);
	
}
