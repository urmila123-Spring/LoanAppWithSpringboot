package com.example.config;

import org.springframework.batch.item.ItemProcessor;

import com.example.bean.User;

public class DBLogProcessor implements ItemProcessor<User, User> {
	public User process(User user) throws Exception {
		System.out.println("Inserting User : " + user);
		return user;
	}

}
