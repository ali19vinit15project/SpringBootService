package com.vinit.inventorymanagement.dto;

import java.util.List;

import com.vinit.inventorymanagement.model.Role;

import lombok.Data;

@Data
public class UserResponseDTO {
	
	private Integer id;
	private String username;
	private String email;
	List<Role> roles;
}
