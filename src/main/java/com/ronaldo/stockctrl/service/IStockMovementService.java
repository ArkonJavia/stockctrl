package com.ronaldo.stockctrl.service;

import java.util.List;

import com.ronaldo.stockctrl.config.entity.StockMovement;

public interface IStockMovementService {

	List<StockMovement> getAllStockMovements();

	StockMovement getStockMovementById(Long id);

	StockMovement createStockMovement(StockMovement stockMovement);

	StockMovement updateStockMovement(Long id, StockMovement stockMovement);

	void deleteStockMovement(Long id);

}
