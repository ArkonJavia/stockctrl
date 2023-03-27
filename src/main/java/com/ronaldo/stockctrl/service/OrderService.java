package com.ronaldo.stockctrl.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldo.stockctrl.config.entity.Item;
import com.ronaldo.stockctrl.config.entity.Order;
import com.ronaldo.stockctrl.config.entity.Stock;
import com.ronaldo.stockctrl.config.entity.StockMovement;
import com.ronaldo.stockctrl.config.entity.User;
import com.ronaldo.stockctrl.config.entity.repository.IOrderRepository;
import com.ronaldo.stockctrl.config.entity.repository.IStockMovementRepository;
import com.ronaldo.stockctrl.config.entity.repository.IStockRepository;
import com.ronaldo.stockctrl.config.entity.repository.IUserRepository;
import com.ronaldo.stockctrl.utils.MailSender;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private IStockRepository stockRepository;

	@Autowired
	private IStockMovementRepository stockMovementRepository;

	@Autowired
	private IUserRepository userRepository;

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
		order.setCreationdate(new Date());
		if (order.getCompleted() == null) {
			order.setCompleted(Boolean.FALSE);
		}
		order = orderRepository.save(order);
		// verifica se existe em estoque
		Stock inStock = stockRepository.findStockWithMaxQuantityByItem(order.getItem());
		if (inStock != null) {
			Integer qtInStock = inStock.getQuantity();
			if (qtInStock >= order.getQuantity()) {
				// cria a movimentacao
				StockMovement sm = new StockMovement();
				sm.setOrder(order);
				sm.setItem(order.getItem());
				sm.setQuantity(order.getQuantity());
				sm.setCreationdate(new Date());
				stockMovementRepository.save(sm);
				// atualiza o stock
				inStock.setQuantity(inStock.getQuantity() - order.getQuantity());
				stockRepository.save(inStock);
				// atualiza a ordem para completa
				order.setCompleted(Boolean.TRUE);
				orderRepository.save(order);
				orderMailConfirmation(order);
			} else if (qtInStock < order.getQuantity()) {
				// cria a movimentacao
				StockMovement sm = new StockMovement();
				sm.setOrder(order);
				sm.setItem(order.getItem());
				sm.setQuantity(qtInStock);
				sm.setCreationdate(new Date());
				stockMovementRepository.save(sm);
				// zera o stock
				inStock.setQuantity(0);
				stockRepository.save(inStock);
				// ordem incompleta, nao faz nada.
			}
		}

		return order;
	}

	private void orderMailConfirmation(Order order) {
		StringBuffer body = new StringBuffer();
		body.append("Mr/Mrs,");
		body.append(order.getUser().getName());
		body.append("\n");
		body.append("Your order number ");
		body.append(order.getId());
		body.append("Related to");
		body.append(order.getQuantity());
		body.append(" ");
		body.append(order.getItem().getName());
		body.append("has been completed.");

		MailSender ms = new MailSender("sibstest@mail.com", "ronaldoapi");
		try {
			ms.sendEmail(getUserEmailByUserId(order.getUser().getId()), "Your Order has been completed", body.toString());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private String getUserEmailByUserId(Long id) {
		Optional<User> u = userRepository.findById(id);
		if (u.isPresent()) {
			User user = u.get();
			return user.getEmail();
		}
		return null;
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
