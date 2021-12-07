package com.centdb.module8;

import java.util.Scanner;

import com.centdb.module6.Export;

public class MainMenu {
	public static Scanner scanner = new Scanner(System.in);
	
	MainMenu() {
	}

	public static void showLoginMenu() {
		
		System.out.println("---------------Welcome to CentDb---------------");
		System.out.println("Please chose appropriate option");
		System.out.println(" 1. Login \n 2. Register \n 3. Exit");
		System.out.println("Enter your option: ");
		String input = scanner.nextLine();
			switch(input) {
			case "1": 
				Login login = new Login();
				login.userLogin();
				break;
			case "2":
				Register register = new Register();
				register.registerUser();
				break;
			default :
				System.exit(0);
			}
	}
	
	public void showMainMenu() {
		System.out.println("---------------Welcome to Main Menu---------------");
		System.out.println("Please chose appropriate option");
		System.out.println(" 1. Write Queries \n 2. Export \n 3. Data Model \n 4. Analytics \n 5. LogOut \n 6. Exit");
		System.out.println("Enter your option: ");
		String input = scanner.nextLine();
		
		switch(input) {
		case "1": 
			// Call Queries Method 
			break;
		case "2":
			Export export = new Export("database_name");
			export.toSql();
			break;
		case "3": 
			// Call Data Model Method 
			break;
		case "4":
			// Call Analytics Method 
			break;
		case "5":
			showLoginMenu();
			break;
		default :
			System.exit(0);
		}
	}
}
