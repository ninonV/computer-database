package com.excilys.java.CDB.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.java.CDB.configuration.SpringConfigPersistence;

public class Main {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new AnnotationConfigApplicationContext(SpringConfigPersistence.class);
		UserInterface user= context.getBean(UserInterface.class);
		user.start();
	}
}
