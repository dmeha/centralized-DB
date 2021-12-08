package com.centdb;

import com.centdb.LogManagement.SqlLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.centdb.module8.MainMenu;

@SpringBootApplication
public class CentDbApplication {

	public static void main(String[] args) {
		// SpringApplication.run(CentDbApplication.class, args);
		MainMenu.showLoginMenu();

		// new SqlLogger().generalLog("DB1", "Table1", "1", "STATE");
		// new SqlLogger().generalLog("DB1", "Table1", "CREATE", "QUERY");
		// new SqlLogger().eventLog("DB2", "Crash query table 1", "2021-12-05
		// 12:12:12");
		// new SqlLogger().queryLog("DB3", "Table1", "CREATE", "2021-11-01 12:12:12");

	}
}
