package com.vinit.inventorymanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class InvManController {

	@GetMapping("/healthCheck")
	public ResponseEntity<String> welcome() {

		return new ResponseEntity<>("healthy", HttpStatus.OK);
	}

}
