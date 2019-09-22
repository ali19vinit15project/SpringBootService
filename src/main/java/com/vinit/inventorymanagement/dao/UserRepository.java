package com.vinit.inventorymanagement.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinit.inventorymanagement.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

	boolean existsByUsername(String username);

	UserModel findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);

}
