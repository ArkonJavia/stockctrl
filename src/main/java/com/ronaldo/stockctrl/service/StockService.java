package com.ronaldo.stockctrl.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StockService implements IStockService {

	@Autowired
	private IStockRepository stockRepository;

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private IStockMovementRepository stockMovementRepository;
	
	@Autowired
	private IUserRepository userRepository;

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
		stock = stockRepository.save(stock);
		// verifica se existe alguma order para ser cumprida com este item
		// caso sim, fazer a mesma logica
		List<Order> incompletedOrders = orderRepository
				.findOrdersByCompletedFalseAndItemEqualsOrderByCreationdateAsc(stock.getItem());
		if (incompletedOrders != null && !incompletedOrders.isEmpty() && stock.getQuantity()>0) {
			do {

				Integer missingToCompleteOrder = getQuantityMissingToCompleteOrder(incompletedOrders.get(0));

				if (stock.getQuantity() >= missingToCompleteOrder) {
					StockMovement sm = new StockMovement();
					sm.setOrder(incompletedOrders.get(0));
					sm.setItem(incompletedOrders.get(0).getItem());
					sm.setQuantity(missingToCompleteOrder);
					sm.setCreationdate(new Date());
					stockMovementRepository.save(sm);

					stock.setQuantity(stock.getQuantity() - missingToCompleteOrder);
					stock = stockRepository.save(stock);

					incompletedOrders.get(0).setCompleted(Boolean.TRUE);
					orderRepository.save(incompletedOrders.get(0));
					orderMailConfirmation(incompletedOrders.get(0));
				} else if (stock.getQuantity() < missingToCompleteOrder) {

					StockMovement sm = new StockMovement();
					sm.setOrder(incompletedOrders.get(0));
					sm.setItem(incompletedOrders.get(0).getItem());
					sm.setQuantity(stock.getQuantity());
					sm.setCreationdate(new Date());
					stockMovementRepository.save(sm);

					stock.setQuantity(0);
					stock = stockRepository.save(stock);
				}
				incompletedOrders.remove(0);
			}while (stock.getQuantity() > 0 && !incompletedOrders.isEmpty());
		}

		return stock;
	}

	private Integer getQuantityMissingToCompleteOrder(Order order) {
		Integer qtd = 0;
		List<StockMovement> list = stockMovementRepository.findByOrder(order);
		if (list == null || !list.isEmpty()) {
			for (StockMovement stockMovement : list) {
				qtd += stockMovement.getQuantity();
			}
			qtd = order.getQuantity()-qtd;
		}else {
			qtd = order.getQuantity();
		}
		return qtd;
	}
	
	private void orderMailConfirmation(Order order) {
		StringBuffer body = new StringBuffer();
		body.append("Mr/Mrs," );
		body.append(order.getUser().getName());
		body.append("\n");
		body.append("Your order number ");
		body.append(order.getId());
		body.append("Related to" );
		body.append(order.getQuantity());
		body.append(" " );
		body.append(order.getItem().getName());
		body.append("has been completed." );
		
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
	public Stock updateStock(Stock stock) {
		return stockRepository.save(stock);
	}

	@Override
	public void deleteStockById(Long id) {
		stockRepository.deleteById(id);

	}

}
