package com.vinit.inventorymanagement.service;

import javax.servlet.http.HttpServletRequest;

import com.vinit.inventorymanagement.dto.LoginResponseDTO;
import com.vinit.inventorymanagement.model.UserModel;

public interface UserService {

	LoginResponseDTO login(String username, String password);

	String signup(UserModel user);

	void delete(String username);

	UserModel search(String username);

	UserModel whoami(HttpServletRequest req);

	String refresh(String username);

}
