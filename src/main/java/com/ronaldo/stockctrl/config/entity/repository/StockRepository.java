package com.ronaldo.stockctrl.config.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldo.stockctrl.config.entity.Stock;

public interface StockRepository  extends JpaRepository<Stock, Long> {

}
