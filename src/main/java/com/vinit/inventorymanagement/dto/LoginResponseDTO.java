package com.vinit.inventorymanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {

	private String jwt;
}
