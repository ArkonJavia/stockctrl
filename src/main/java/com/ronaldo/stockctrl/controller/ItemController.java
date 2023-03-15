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
import com.ronaldo.stockctrl.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private static final Logger logger = LogManager.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	@GetMapping("")
	public List<Item> getItems() {
		if (logger.isDebugEnabled()) {
			logger.info("getItems");
		}
		return itemService.getAllItems();
	}

	@GetMapping("/search/{id}")
	public Item getItemById(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("getItemById " + id);
		}
		return itemService.getItemById(id);
	}

	@PostMapping("/create")
	public Item createItem(@RequestBody Item item) {
		if (logger.isDebugEnabled()) {
			logger.info("createItem " + item.toString());
		}
		return itemService.createItem(item);
	}

	@PutMapping("/save/{id}")
	public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
		if (logger.isDebugEnabled()) {
			logger.info("updateItem " + item);
		}
		item.setId(id);
		return itemService.updateItem(item);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteItem(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("deleteItem " + id);
		}
		itemService.deleteItemById(id);
	}

}
