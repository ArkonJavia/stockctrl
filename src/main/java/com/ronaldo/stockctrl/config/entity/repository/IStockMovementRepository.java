package com.ronaldo.stockctrl.config.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ronaldo.stockctrl.config.entity.Order;
import com.ronaldo.stockctrl.config.entity.StockMovement;

@Repository
public interface IStockMovementRepository extends JpaRepository<StockMovement, Long> {
	List<StockMovement> findByOrder(Order order);

}
