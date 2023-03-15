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

import com.ronaldo.stockctrl.config.entity.Order;
import com.ronaldo.stockctrl.service.IOrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private static final Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	private IOrderService orderService;

	@GetMapping("")
	public List<Order> getOrders() {
		if (logger.isDebugEnabled()) {
			logger.info("getOrders");
		}
		return orderService.getOrders();
	}

	@GetMapping("/search/{id}")
	public Order getOrderById(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("getOrderById " + id);
		}
		return orderService.getOrderById(id);
	}

	@PostMapping("/create")
	public Order createOrder(@RequestBody Order order) {
		if (logger.isDebugEnabled()) {
			logger.info("createOrder " + order.toString());
		}
		return orderService.createOrder(order);
	}

	@PutMapping("/save/{id}")
	public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
		if (logger.isDebugEnabled()) {
			logger.info("updateOrder" + order);
		}
		order.setId(id);
		return orderService.updateOrder(id, order);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteOrder(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("deleteOrder " + id);
		}
		orderService.deleteOrder(id);
	}
}
