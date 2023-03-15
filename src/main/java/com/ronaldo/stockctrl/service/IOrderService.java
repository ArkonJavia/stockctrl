package com.ronaldo.stockctrl.service;

import java.util.List;

import com.ronaldo.stockctrl.config.entity.Order;

public interface IOrderService {
	List<Order> getOrders();

	Order getOrderById(Long id);

	Order createOrder(Order order);

	Order updateOrder(Long id, Order order);

	void deleteOrder(Long id);
}
