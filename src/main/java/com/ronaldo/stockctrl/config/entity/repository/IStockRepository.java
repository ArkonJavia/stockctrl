package com.ronaldo.stockctrl.config.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ronaldo.stockctrl.config.entity.Item;
import com.ronaldo.stockctrl.config.entity.Stock;

public interface IStockRepository extends JpaRepository<Stock, Long> {

//	Stock findStockByItem(Item item);

	@Query("SELECT s FROM Stock s WHERE s.item = :item AND s.quantity = (SELECT MAX(s2.quantity) FROM Stock s2 WHERE s2.item = :item)")
	Stock findStockWithMaxQuantityByItem(@Param("item") Item item);

}
