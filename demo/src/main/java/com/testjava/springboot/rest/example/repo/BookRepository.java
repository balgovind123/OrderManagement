package com.testjava.springboot.rest.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testjava.springboot.rest.example.Model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{


}
