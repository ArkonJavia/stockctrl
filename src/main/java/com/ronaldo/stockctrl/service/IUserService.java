package com.ronaldo.stockctrl.service;

import java.util.List;

import com.ronaldo.stockctrl.config.entity.User;

public interface IUserService {

	List<User> getUsers();

	User getUserById(Long id);

	User createUser(User user);

	User updateUser(Long id, User user);

	void deleteUser(Long id);
}
