package com.ronaldo.stockctrl.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ronaldo.stockctrl.config.entity.StockMovement;
import com.ronaldo.stockctrl.service.IStockMovementService;

@RestController
@RequestMapping("/stockMovements")
public class StockMovementController {

	private static final Logger logger = LogManager.getLogger(StockMovementController.class);

	@Autowired
	private IStockMovementService stockMovementService;

	@GetMapping("")
	public List<StockMovement> getStockMovements() {
		if (logger.isDebugEnabled()) {
			logger.info("getStockMovements ");
		}
		return stockMovementService.getAllStockMovements();
	}

	@GetMapping("/search/{id}")
	public StockMovement getStockMovementById(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("getStockMovements ");
		}
		return stockMovementService.getStockMovementById(id);
	}

	@PostMapping("/create")
	public StockMovement createStockMovement(@RequestBody StockMovement stockMovement) {
		if (logger.isDebugEnabled()) {
			logger.info("createStockMovement " + stockMovement.toString());
		}
		return stockMovementService.createStockMovement(stockMovement);
	}

	@PutMapping("/save/{id}")
	public StockMovement updateStockMovement(@PathVariable Long id, @RequestBody StockMovement stockMovement) {
		if (logger.isDebugEnabled()) {
			logger.info("updateStockMovement " + stockMovement);
		}
		stockMovement.setId(id);
		return stockMovementService.updateStockMovement(id, stockMovement);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStockMovement(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("deleteStockMovement " + id);
		}
		stockMovementService.deleteStockMovement(id);
	}

}
