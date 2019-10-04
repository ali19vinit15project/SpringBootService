package com.vinit.inventorymanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
public class InvManController {

	@GetMapping("/healthCheck")
	public ResponseEntity<String> welcome() {
		log.info("in welcome method");
		return new ResponseEntity<>("healthy...", HttpStatus.OK);
	}

}
