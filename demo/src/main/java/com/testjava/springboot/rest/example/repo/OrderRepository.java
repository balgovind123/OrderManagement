package com.testjava.springboot.rest.example.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testjava.springboot.rest.example.Model.Order_Books;

public interface OrderRepository extends JpaRepository<Order_Books, Integer>{

	Optional<Order_Books> findByOrderId(String id);

	void deleteById(String id);



}
