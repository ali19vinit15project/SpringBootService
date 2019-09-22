package com.vinit.inventorymanagement.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinit.inventorymanagement.dto.UserDTO;
import com.vinit.inventorymanagement.dto.UserResponseDTO;
import com.vinit.inventorymanagement.service.UserService;

@RestController
@RequestMapping("/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/login")
	public String login(@RequestBody UserDTO user) {
		return userService.login(user.getUsername(), user.getPassword());
	}

	@GetMapping(value = "/me")
	public UserResponseDTO whoami(HttpServletRequest req) {
		return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
	}

}
