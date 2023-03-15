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

import com.ronaldo.stockctrl.config.entity.User;
import com.ronaldo.stockctrl.service.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@GetMapping("")
	public List<User> getUsers() {
		if (logger.isDebugEnabled()) {
			logger.info("getUsers ");
		}
		return userService.getUsers();
	}

	@GetMapping("/search/{id}")
	public User getUserById(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("getUserById " + id);
		}
		return userService.getUserById(id);
	}

	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		if (logger.isDebugEnabled()) {
			logger.info("createUser " + user.toString());
		}
		return userService.createUser(user);
	}

	@PutMapping("/save/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		if (logger.isDebugEnabled()) {
			logger.info("updateUser " + user.toString());
		}
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		if (logger.isDebugEnabled()) {
			logger.info("deleteUser " + id);
		}
		userService.deleteUser(id);
	}
}
