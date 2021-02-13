package com.tinyurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.servic.TinyUrlService;

@RestController
@RequestMapping("/tiny-url")
public class TinyUrlController {
	
	@Autowired
	private TinyUrlService tinyUrlService;
	
	@PostMapping("/create/{inputUrl}")
	public ResponseEntity<Object> createTinyUrl(@PathVariable("inputUrl") String inputUrl){
		
		try {
			return ResponseEntity.ok(tinyUrlService.createTinyUrl(inputUrl));
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception caught on create tinyUrl...");
		}
		
	}
}
