package com.centdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.centdb.module6.Export;
import com.centdb.module8.Login;
import com.centdb.module8.MainMenu;

@SpringBootApplication
public class CentDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentDbApplication.class, args);
		MainMenu.showLoginMenu();
		Export export = new Export("database_name");
		export.toSql();
	}

}
