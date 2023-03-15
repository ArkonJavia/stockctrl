package com.ronaldo.stockctrl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldo.stockctrl.config.entity.StockMovement;
import com.ronaldo.stockctrl.config.entity.repository.StockMovementRepository;

@Service
public class StockMovementService implements IStockMovementService {

	@Autowired
	private StockMovementRepository stockMovementRepository;

	@Override
	public List<StockMovement> getAllStockMovements() {
		return stockMovementRepository.findAll();
	}

	@Override
	public StockMovement getStockMovementById(Long id) {
		return stockMovementRepository.findById(id).orElse(null);
	}

	@Override
	public StockMovement createStockMovement(StockMovement stockMovement) {
		return stockMovementRepository.save(stockMovement);
	}

	@Override
	public StockMovement updateStockMovement(Long id, StockMovement stockMovement) {
		stockMovement.setId(id);
		return stockMovementRepository.save(stockMovement);
	}

	@Override
	public void deleteStockMovement(Long id) {
		stockMovementRepository.deleteById(id);
	}

}
