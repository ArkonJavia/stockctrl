package com.ronaldo.stockctrl.config.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ronaldo.stockctrl.config.entity.Item;
import com.ronaldo.stockctrl.config.entity.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
	List<Order> findOrdersByCompletedFalseAndItemEqualsOrderByCreationdateAsc(Item item);
}
