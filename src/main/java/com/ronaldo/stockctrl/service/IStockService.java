package com.ronaldo.stockctrl.service;

import java.util.List;

import com.ronaldo.stockctrl.config.entity.Stock;

public interface IStockService {
	List<Stock> getAllStock();

	Stock getStockById(Long id);

	Stock createStock(Stock stock);

	Stock updateStock(Stock stock);

	void deleteStockById(Long id);

}
