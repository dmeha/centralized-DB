package com.centdb.module2;

import java.util.Scanner;

import com.centdb.model.DatabaseTable;
import com.centdb.model.DeleteQueryModel;
import com.centdb.model.InsertQueryModel;
import com.centdb.model.SelectQueryModel;
import com.centdb.model.UpdateQueryModel;

public class QueryRunner {

    public static void run() {
        Scanner sc = new Scanner(System.in);
        while (Boolean.TRUE) {
            System.out.print("\nEnter a query: ");
            String query = sc.nextLine();
            if (SyntaxChecker.isUseDatabaseQuery(query)) {
                String databaseName = QueryParser.getDatabaseNameFromUseDatabaseQuery(query);
                QueryExecutor.executeUseDatabaseQuery(databaseName);
            } else if (SyntaxChecker.isCreateDatabaseQuery(query)) {
                String databaseName = QueryParser.getDatabaseNameFromCreateDatabaseQuery(query);
                QueryExecutor.executeCreateDatabaseQuery(databaseName);
            } else if (SyntaxChecker.isDropDatabaseQuery(query)) {
                String databaseName = QueryParser.getDatabaseNameFromDropDatabaseQuery(query);
                QueryExecutor.executeDropDatabaseQuery(databaseName);
            } else if (SyntaxChecker.isCreateTableQuery(query)) {
                DatabaseTable table = QueryParser.getDatabaseTableFromCreateTableQuery(query);
                QueryExecutor.executeCreateTableQuery(table);
            } else if (SyntaxChecker.isDropTableQuery(query)) {
                String tableName = QueryParser.getTabaleNameFromDropTableQuery(query);
                QueryExecutor.executeDropTableQuery(tableName);
            } else if (SyntaxChecker.isTruncateTableQuery(query)) {
                String tableName = QueryParser.getTabaleNameFromTruncateTableQuery(query);
                QueryExecutor.executeTruncateTableQuery(tableName);
            } else if (SyntaxChecker.isSelectQuery(query)) {
                SelectQueryModel selectQuery = QueryParser.getSelectQueryModel(query);
                QueryExecutor.executeSelectQuery(selectQuery);
            } else if (SyntaxChecker.isInsertQuery(query)) {
                InsertQueryModel insertQuery = QueryParser.getInsertQueryModel(query);
                QueryExecutor.executeInsertQuery(insertQuery);
            } else if (SyntaxChecker.isUpdateQuery(query)) {
                UpdateQueryModel updateQuery = QueryParser.getUpdateQueryModel(query);
                QueryExecutor.executeUpdateQuery(updateQuery);
            } else if (SyntaxChecker.isDeleteQuery(query)) {
                DeleteQueryModel deleteQuery = QueryParser.getDeleteQueryModel(query);
                QueryExecutor.executeDeleteQuery(deleteQuery);
            } else {
                System.out.println("Invalid.");
            }
            System.out.println("\nDo you want to continue querying? [y/n]: ");
            String continueQueries = sc.nextLine();
            if (!continueQueries.toLowerCase().equals("y")) {
                return;
            }
        }
        sc.close();
    }

}
