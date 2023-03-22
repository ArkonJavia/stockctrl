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

import com.ronaldo.stockctrl.config.entity.Item;
import com.ronaldo.stockctrl.config.entity.Stock;
import com.ronaldo.stockctrl.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	private static final Logger logger = LogManager.getLogger(StockController.class);

	@Autowired
	private StockService stockService;

	@GetMapping("")
	public List<Stock> getStock() {
		if (logger.isDebugEnabled()) {
			logger.info("getStock");
		}
		return stockService.getAllStock();
	}

	@GetMapping("/search/{id}")
	public Stock getStockById(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("getStockById " + id);
		}
		return stockService.getStockById(id);
	}

	@PostMapping("/create")
	public Stock createItem(@RequestBody Stock stock) {
		if (logger.isDebugEnabled()) {
			logger.info("createItem " + stock.toString());
		}
		return stockService.createStock(stock);
	}

	@PutMapping("/save/{id}")
	public Stock updateItem(@PathVariable Long id, @RequestBody Stock stock) {
		if (logger.isDebugEnabled()) {
			logger.info("updateItem " + stock);
		}
		stock.setId(id);
		return stockService.updateStock(stock);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteStock(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("deleteItem " + id);
		}
		stockService.deleteStockById(id);
	}

}
