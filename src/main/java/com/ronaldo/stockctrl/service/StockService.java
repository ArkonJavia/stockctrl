package com.ronaldo.stockctrl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldo.stockctrl.config.entity.Stock;
import com.ronaldo.stockctrl.config.entity.repository.IStockRepository;

@Service
public class StockService implements IStockService {
	
	@Autowired
	private IStockRepository stockRepository;

	@Override
	public List<Stock> getAllStock() {
		return stockRepository.findAll();
	}

	@Override
	public Stock getStockById(Long id) {
		return stockRepository.findById(id).orElse(null);
	}

	@Override
	public Stock createStock(Stock stock) {
		return stockRepository.save(stock);
	}

	@Override
	public Stock updateStock(Stock stock) {
		return stockRepository.save(stock);
	}

	@Override
	public void deleteStockById(Long id) {
		stockRepository.deleteById(id);
		
	}

}
