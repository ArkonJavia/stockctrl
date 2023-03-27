package com.ronaldo.stockctrl.config.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ronaldo.stockctrl.config.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}

