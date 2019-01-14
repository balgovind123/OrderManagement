package com.testjava.springboot.rest.example.repo;


import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testjava.springboot.rest.example.Model.Book;
import com.testjava.springboot.rest.example.Model.Order_Books;
import com.wordnik.swagger.annotations.Api;

@RestController
@RequestMapping("/OrderManagement")
public class ProductResource {
	
	@Autowired
	private BookRepository bookRepository;
	
	

	@Autowired
	private OrderRepository orderRepository;
	
    @PersistenceContext
    private EntityManager em;
	
	@PostMapping("create/Book")
	public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Book book) {
		System.out.println("saving event");
		Book savedProduct = bookRepository.save(book);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProduct.getId()).toUri();

		
		Map<String, Object> message = new HashMap<String, Object>();

	    message.put("summary", "Created successfully.");
	    message.put("code", 200);
		
	    Map<String, Object> json = new HashMap<String, Object>();
	    json.put("Status", "ok");
	    json.put("message", message);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("X-Fsl-Response-Code", "302");
	    return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
	}
	
	
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable String id) {
		orderRepository.deleteById(id);
		
		Map<String, Object> message = new HashMap<String, Object>();

	    message.put("summary", "deleted successfully.");
	    message.put("code", 200);
	    
		Map<String, Object> json = new HashMap<String, Object>();
		    json.put("Status", "ok");
		    json.put("message", message);
		
		    
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("Content-Type", "application/json; charset=UTF-8");
		    headers.add("X-Fsl-Location", "/");
		    headers.add("X-Fsl-Response-Code", "302");
		
	    return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));	
	}
	
	
	@PostMapping("create/BookOrder")
	public ResponseEntity<Map<String, Object>> createBookOrder(@RequestBody Order_Books book) {
		System.out.println("saving event");
		book.setOrderId(UUID.randomUUID().toString());
		Order_Books savedProduct = orderRepository.save(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProduct.getId()).toUri();

		
		Map<String, Object> message = new HashMap<String, Object>();

	    message.put("summary", "Created successfully.");
	    message.put("code", 200);
		
	    Map<String, Object> json = new HashMap<String, Object>();
	    json.put("Status", "ok");
	    json.put("message", message);
	    json.put("Your Order Id", book.getOrderId());

	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("X-Fsl-Response-Code", "302");
	    return (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
	}
	
	
	
	@GetMapping("/orders")
	public List<Order_Books> retrieveBooks() {
		return orderRepository.findAll();
	}
	
}
