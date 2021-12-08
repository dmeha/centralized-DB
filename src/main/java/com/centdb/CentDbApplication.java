package com.centdb;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.centdb.model.DatabaseTable;
import com.centdb.model.DeleteQueryModel;
import com.centdb.model.InsertQueryModel;
import com.centdb.model.SelectQueryModel;
import com.centdb.model.UpdateQueryModel;
import com.centdb.module2.QueryExecutor;
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
			System.out.print("Enter a query: ");
			try {
				String query = sc.nextLine();
				if (SyntaxChecker.isUseDatabaseQuery(query)) {
					String databaseName = QueryParser.getDatabaseNameFromUseDatabaseQuery(query);
					if (QueryExecutor.executeUseDatabaseQuery(databaseName)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isCreateDatabaseQuery(query)) {
					String databaseName = QueryParser.getDatabaseNameFromCreateDatabaseQuery(query);
					if (QueryExecutor.executeCreateDatabaseQuery(databaseName)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isDropDatabaseQuery(query)) {
					String databaseName = QueryParser.getDatabaseNameFromDropDatabaseQuery(query);
					if (QueryExecutor.executeDropDatabaseQuery(databaseName)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isCreateTableQuery(query)) {
					DatabaseTable table = QueryParser.getDatabaseTableFromCreateTableQuery(query);
					if (QueryExecutor.executeCreateTableQuery(table)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isDropTableQuery(query)) {
					String tableName = QueryParser.getTabaleNameFromDropTableQuery(query);
					if (QueryExecutor.executeDropTableQuery(tableName)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isTruncateTableQuery(query)) {
					String tableName = QueryParser.getTabaleNameFromTruncateTableQuery(query);
					if (QueryExecutor.executeTruncateTableQuery(tableName)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isSelectQuery(query)) {
					SelectQueryModel selectQuery = QueryParser.getSelectQueryModel(query);
					if (QueryExecutor.executeSelectQuery(selectQuery)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isInsertQuery(query)) {
					InsertQueryModel insertQuery = QueryParser.getInsertQueryModel(query);
					if (QueryExecutor.executeInsertQuery(insertQuery)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isUpdateQuery(query)) {
					UpdateQueryModel updateQuery = QueryParser.getUpdateQueryModel(query);
					if (QueryExecutor.executeUpdateQuery(updateQuery)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else if (SyntaxChecker.isDeleteQuery(query)) {
					DeleteQueryModel deleteQuery = QueryParser.getDeleteQueryModel(query);
					if (QueryExecutor.executeDeleteQuery(deleteQuery)) {
						System.out.println("Query executed successfullly.");
					} else {
						System.err.println("Query cannot be executed.");
					}
				} else {
					System.out.println("Invalid.");
				}
			} catch (Exception e) {
				// TODO: delete temp folders if error occurs
				e.printStackTrace();
			}
		}
		sc.close();
	}
}
