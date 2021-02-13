package com.tinyurl.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.exception.IdDoesNotExistsException;
import com.tinyurl.exception.TinyUrlAlreadyExistException;
import com.tinyurl.servic.TinyUrlService;

@RestController
@RequestMapping("/tiny-url")
public class TinyUrlController {
	
	@Autowired
	private TinyUrlService tinyUrlService;
	
	private static final Logger logger = LoggerFactory.getLogger(TinyUrlController.class);
	
	@PostMapping("/create")
	public ResponseEntity<Object> createTinyUrl(
			@RequestParam("inputUrl") String inputUrl){
		
		try {
			return ResponseEntity.ok(tinyUrlService.createTinyUrl(inputUrl));
			
		}catch(TinyUrlAlreadyExistException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception caught on create tinyUrl...");
		}
	}
	
	@GetMapping("/{tinyUrl}")
	public ResponseEntity<Object> redirectToFullUrl(
			@PathVariable("tinyUrl") String tinyUrl,
			HttpServletResponse httpServletResponse) {
		
		try {
			tinyUrlService.redirectToFullUrl(tinyUrl, httpServletResponse);
			return ResponseEntity.status(HttpStatus.FOUND).body("redirected to full url...");
			
		}catch(IdDoesNotExistsException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception caught on redirect tinyUrl...");
		}
		
	}
	
	@GetMapping("/get/{tinyUrl}")
	public ResponseEntity<Object> tinyUrlById(@PathVariable("tinyUrl") String tinyUrl) {
		
		try {
			return ResponseEntity.ok(tinyUrlService.tinyUrlDetailsById(tinyUrl));
			
		}catch(IdDoesNotExistsException e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception caught on get tinyUrl details by id...");
		}
		
	}
}
