package com.centdb.module2;

import com.centdb.model.DatabaseTable;
import com.centdb.model.DeleteQueryModel;
import com.centdb.model.InsertQueryModel;
import com.centdb.model.SelectQueryModel;
import com.centdb.model.UpdateQueryModel;

public class QueryRunner {

	public static void run(String query) {
		try {
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
				System.out.println("Invalid Query.");
			}
		} catch (Exception e) {
			// TODO: delete temp folders if error occurs
			e.printStackTrace();
		}

	}

}
