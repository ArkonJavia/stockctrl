package com.ronaldo.stockctrl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldo.stockctrl.config.entity.Item;
import com.ronaldo.stockctrl.config.entity.Order;
import com.ronaldo.stockctrl.config.entity.Stock;
import com.ronaldo.stockctrl.config.entity.StockMovement;
import com.ronaldo.stockctrl.config.entity.repository.IOrderRepository;
import com.ronaldo.stockctrl.config.entity.repository.IStockMovementRepository;
import com.ronaldo.stockctrl.config.entity.repository.IStockRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private IStockRepository stockRepository;

	@Autowired
	private IStockMovementRepository stockMovementRepository;

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public Order createOrder(Order order) {

		// cria a ordem como incompleta
		if (order.getCompleted()==null) {
			order.setCompleted(Boolean.FALSE);
		}
		order = orderRepository.save(order);
		// verifica se existe em estoque
		Stock inStock = stockRepository.findStockByItem(order.getItem());
		if (inStock != null) {
			Integer qtInStock = inStock.getQuantity();
			if (qtInStock >= order.getQuantity()) {
				// cria a movimentacao
				StockMovement sm = new StockMovement();
				sm.setOrder(order);
				sm.setItem(order.getItem());
				sm.setQuantity(order.getQuantity());
				stockMovementRepository.save(sm);
				// atualiza o stock
				inStock.setQuantity(inStock.getQuantity() - order.getQuantity());
				stockRepository.save(inStock);
				// atualiza a ordem para completa
				order.setCompleted(Boolean.TRUE);
				orderRepository.save(order);
				// TODO envia email
			} else if (qtInStock < order.getQuantity()) {
				// cria a movimentacao
				StockMovement sm = new StockMovement();
				sm.setOrder(order);
				sm.setItem(order.getItem());
				sm.setQuantity(inStock.getQuantity());
				stockMovementRepository.save(sm);
				// zera o stock
				inStock.setQuantity(0);
				stockRepository.save(inStock);
				// ordem incompleta, nao faz nada.
			}
		}

		return order;
	}

	@Override
	public Order updateOrder(Long id, Order order) {
		order.setId(id);
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

}
