package com.centdb;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.centdb.module2.QueryParser;
import com.centdb.module2.SyntaxChecker;
import com.centdb.module8.MainMenu;

@SpringBootApplication
public class CentDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentDbApplication.class, args);
		MainMenu.showLoginMenu();
		Scanner sc = new Scanner(System.in);
		while (Boolean.TRUE) {
			String query = sc.nextLine();
			if (SyntaxChecker.isUseDatabaseQuery(query)) {
				System.out.println(QueryParser.getDatabaseNameFromUseDatabaseQuery(query));
			} else if (SyntaxChecker.isCreateDatabaseQuery(query)) {
				System.out.println(QueryParser.getDatabaseNameFromCreateDatabaseQuery(query));
			} else if (SyntaxChecker.isDropDatabaseQuery(query)) {
				System.out.println(QueryParser.getDatabaseNameFromDropDatabaseQuery(query));
				System.out.println(QueryParser.getDatabaseNameFromDropDatabaseQuery(query));
			} else if (SyntaxChecker.isCreateTableQuery(query)) {
				System.out.println(QueryParser.getDatabaseTableFromCreateTableQuery(query));
			} else if (SyntaxChecker.isDropTableQuery(query)) {
				System.out.println(QueryParser.getTabaleNameFromDropTableQuery(query));
			} else if (SyntaxChecker.isTruncateTableQuery(query)) {
				System.out.println(QueryParser.getTabaleNameFromTruncateTableQuery(query));
			} else if (SyntaxChecker.isSelectQuery(query)) {
				System.out.println(QueryParser.getSelectQueryModel(query));
			} else if (SyntaxChecker.isInsertQuery(query)) {
				System.out.println(QueryParser.getInsertQueryModel(query));
			} else if (SyntaxChecker.isUpdateQuery(query)) {
				System.out.println(QueryParser.getUpdateQueryModel(query));
			} else if (SyntaxChecker.isDeleteQuery(query)) {
				System.out.println(QueryParser.getDeleteQueryModel(query));
			} else {
				System.out.println("Invalid.");
				break;
			}
		}
	}
}
