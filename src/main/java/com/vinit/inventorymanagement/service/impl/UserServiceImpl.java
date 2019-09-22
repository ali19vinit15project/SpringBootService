package com.vinit.inventorymanagement.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinit.inventorymanagement.dao.UserRepository;
import com.vinit.inventorymanagement.exception.InventoryManException;
import com.vinit.inventorymanagement.model.UserModel;
import com.vinit.inventorymanagement.security.JwtTokenProvider;
import com.vinit.inventorymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String login(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
		} catch (AuthenticationException e) {
			throw new InventoryManException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override

	public String signup(UserModel user) {
		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
		} else {
			throw new InventoryManException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

	@Override
	public UserModel search(String username) {
		UserModel user = userRepository.findByUsername(username);
		if (user == null) {
			throw new InventoryManException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return user;
	}

	@Override
	public UserModel whoami(HttpServletRequest req) {
		return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	@Override
	public String refresh(String username) {
		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
	}

}
