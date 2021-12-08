package com.centdb;

import java.io.File;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.centdb.datamodelling.DataModelling;

@SpringBootApplication
public class CentDbApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CentDbApplication.class, args);
		System.out.println("Choose one from list of database to generate ERD");
		System.out.println("Database options");
		DataModelling.showDatabases();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Databse name");
		String choice = scanner.nextLine();
		DataModelling dataModelling = new DataModelling(choice);
		String obj = dataModelling.generateERD();
		System.out.println(obj);
	}
}
