package com.testjava.springboot.rest.example.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {
	@Id
	@GeneratedValue
	int id;
	String authorName;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", authorName=" + authorName + "]";
	}
	
}
