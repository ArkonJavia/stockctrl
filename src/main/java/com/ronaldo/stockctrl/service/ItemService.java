package com.ronaldo.stockctrl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronaldo.stockctrl.config.entity.Item;
import com.ronaldo.stockctrl.config.entity.repository.IItemRepository;

@Service
public class ItemService implements IItemService {

	@Autowired
	private IItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item getItemById(Long id) {
		return itemRepository.findById(id).orElse(null);
	}

	@Override
	public Item createItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item updateItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public void deleteItemById(Long id) {
		itemRepository.deleteById(id);
	}

}
