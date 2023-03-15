package com.ronaldo.stockctrl.config.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ronaldo.stockctrl.config.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
