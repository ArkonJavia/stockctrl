package com.ronaldo.stockctrl.service;

import java.util.List;

import com.ronaldo.stockctrl.config.entity.Item;

public interface IItemService {

	List<Item> getAllItems();

	Item getItemById(Long id);

	Item createItem(Item item);

	Item updateItem(Item item);

	void deleteItemById(Long id);

}
