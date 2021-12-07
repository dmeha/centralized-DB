package com.centdb.module2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.centdb.constants.DatabaseConstants;
import com.centdb.constants.DatabaseConstants.RegExConstants;
import com.centdb.model.Column;
import com.centdb.model.DatabaseTable;
import com.centdb.model.DeleteQueryModel;
import com.centdb.model.InsertQueryModel;
import com.centdb.model.SelectQueryModel;
import com.centdb.model.SqlDataType;
import com.centdb.model.UpdateQueryModel;

public class QueryParser {

	public static Map<String, SqlDataType> findDataType;

	static {
		findDataType = new TreeMap<>();
		findDataType.put("int", SqlDataType.INT);
		findDataType.put("char", SqlDataType.CHAR);
		findDataType.put("varchar", SqlDataType.VARCHAR);
		findDataType.put("double", SqlDataType.DOUBLE);
	}

	public static String getDatabaseNameFromUseDatabaseQuery(String query) {
		return null;
	}

	public static String getDatabaseNameFromDropDatabaseQuery(String query) {
		query = query.trim();
		query = query.replaceAll(" +", " ");
		return query.split(" ")[2];
	}

	public static String getDatabaseNameFromCreateDatabaseQuery(String query) {
		return findGroupFromRegEx(query, RegExConstants.CREATE_DATABASE_QUERY_REGEX, 20);
	}

	public static String getTabaleNameFromDropTableQuery(String query) {
		query = query.trim();
		query = query.replaceAll(" +", " ");
		return query.split(" ")[2];
	}

	public static String getTabaleNameFromTruncateTableQuery(String query) {
		query = query.trim();
		query = query.replaceAll(" +", " ");
		return query.split(" ")[2];
	}

	public static DatabaseTable getDatabaseTableFromCreateTableQuery(String query) {
		DatabaseTable table = new DatabaseTable();
		// create table table_name
		table.tableName = findGroupFromRegEx(query, RegExConstants.CREATE_TABLE_QUERY_REGEX, 17);

		// create table table_name(col1 type)
		String columnName = findGroupFromRegEx(query, RegExConstants.CREATE_TABLE_QUERY_REGEX, 20);
		String dataType = findGroupFromRegEx(query, RegExConstants.CREATE_TABLE_QUERY_REGEX, 22);
		Column column = new Column();
		column.columnName = columnName;
		column.dataType = findDataType.get(dataType.toLowerCase());
		table.columnList.add(column);

		// create table table_name(col1 type (, colX type)*)
		// create table table_name(col1 type, col2 type, col2 type)
		String[] optionalColumn = query.split(",");
		for (int i = 1; i < optionalColumn.length - 1; i++) {
			Column optionalCol = fetchColumn(optionalColumn[i]);
			if (checkColumnExists(table.columnList, optionalCol)) {
				throw new RuntimeException("Duplicate column name.");
			}
			table.columnList.add(optionalCol);
		}
		if (optionalColumn.length >= 2) {
			Column optionalCol = new Column();
			StringBuilder builder = new StringBuilder(optionalColumn[optionalColumn.length - 1]);
			while (builder.charAt(builder.length() - 1) != ')') {
				builder.deleteCharAt(builder.length() - 1);
			}
			builder.deleteCharAt(builder.length() - 1);
			optionalCol = fetchColumn(builder.toString());
			if (checkColumnExists(table.columnList, optionalCol)) {
				throw new RuntimeException("Duplicate column name.");
			}
			table.columnList.add(optionalCol);
		}
		return table;
	}

	public static SelectQueryModel getSelectQueryModel(String query) {
		// select * from tableName where a = 'abc'
		// select a, b from tableName where a = 'abc'
		String tableName = getTableName(query);
		Boolean selectAllColumn = query.replaceAll(" +", "").charAt(6) == '*';
		Boolean selectAllRows = Boolean.TRUE;
		String conditionColName = null;
		String conditionColVal = null;
		List<String> columnNameList = new ArrayList<>();
		if (!selectAllColumn) {
			columnNameList = fetchColumnNames(query);
		}
		if (Boolean.FALSE.equals(query.matches(".*" + DatabaseConstants.RegExConstants.WHERE_CLAUSE_REGEX + ".*"))) {
			return new SelectQueryModel(tableName, columnNameList, selectAllColumn, selectAllRows, conditionColName,
					conditionColVal);
		}
		selectAllRows = Boolean.FALSE;
		conditionColName = getConditionColName(query);
		conditionColVal = getConditionColVal(query);
		return new SelectQueryModel(tableName, columnNameList, selectAllColumn, selectAllRows, conditionColName,
				conditionColVal);
	}

	private static String getConditionColName(String query) {
		return query.trim()
				.replaceAll(RegExConstants.START_SYMBOL + RegExConstants.OPTIONAL_SPACES_REGEX
						+ RegExConstants.SELECT_REGEX + RegExConstants.REQUIRED_SPACES_REGEX
						+ RegExConstants.SELECT_QUERY_COLUMN_REGEX + RegExConstants.REQUIRED_SPACES_REGEX
						+ RegExConstants.FROM_REGEX + RegExConstants.REQUIRED_SPACES_REGEX + RegExConstants.PLACEHOLDER
						+ RegExConstants.REQUIRED_SPACES_REGEX + RegExConstants.WHERE_REGEX, "")
				.trim().replaceAll(" +", "").split("=")[0];
	}

	private static String getConditionColVal(String query) {
		String s = query.trim()
				.replaceAll(RegExConstants.START_SYMBOL + RegExConstants.OPTIONAL_SPACES_REGEX
						+ RegExConstants.SELECT_REGEX + RegExConstants.REQUIRED_SPACES_REGEX
						+ RegExConstants.SELECT_QUERY_COLUMN_REGEX + RegExConstants.REQUIRED_SPACES_REGEX
						+ RegExConstants.FROM_REGEX + RegExConstants.REQUIRED_SPACES_REGEX + RegExConstants.PLACEHOLDER
						+ RegExConstants.REQUIRED_SPACES_REGEX + RegExConstants.WHERE_REGEX
						+ RegExConstants.REQUIRED_SPACES_REGEX + RegExConstants.PLACEHOLDER
						+ RegExConstants.OPTIONAL_SPACES_REGEX + RegExConstants.EQUAL_SYMBOL
						+ RegExConstants.OPTIONAL_SPACES_REGEX, "")
				.trim();
		return s.substring(1, s.length() - 1);
	}

	private static String getTableName(String query) {
		return query.trim()
				.replaceAll(RegExConstants.START_SYMBOL + RegExConstants.OPTIONAL_SPACES_REGEX
						+ RegExConstants.SELECT_REGEX + RegExConstants.REQUIRED_SPACES_REGEX
						+ RegExConstants.SELECT_QUERY_COLUMN_REGEX + RegExConstants.REQUIRED_SPACES_REGEX
						+ RegExConstants.FROM_REGEX + RegExConstants.REQUIRED_SPACES_REGEX, "")
				.trim().split(" ")[0];
	}

	private static List<String> fetchColumnNames(String query) {
		List<String> columnNameList = new ArrayList<>();
		String[] columns = query.trim().replaceAll(" +", " ").split(",");
		if (columns.length == 1) {
			String c = query.trim().replaceAll(" +", " ").split(" ")[1];
			columnNameList.add(c);
		} else {
			for (int i = 0; i < columns.length - 1; i++) {
				String[] cur = columns[i].trim().split(" ");
				columnNameList.add(cur[cur.length - 1]);
			}
			String[] cur = columns[columns.length - 1].trim().split(" ");
			columnNameList.add(cur[0]);
		}
		return columnNameList;
	}

	public static InsertQueryModel getInsertQueryModel(String query) {
		// insert into tablename values ('1234', 'xyz', 'patel') , ('3214', 'abc',
		// 'shah')
		String tableName = query.trim().split(" ")[2];
		List<String> quotes = fetchAllTheQuoteValues(query);
		int totalRows = query.replaceAll(" +", "").split("\\),").length;
		List<List<String>> columnData = new ArrayList<>();
		int column = quotes.size() / totalRows;
		List<String> curRow = new ArrayList<>();
		for (int i = 0; i < quotes.size(); i++) {
			if (i % column == 0) {
				if (!curRow.isEmpty()) {
					columnData.add(curRow);
				}
				curRow = new ArrayList<>();
			}
			curRow.add(quotes.get(i));
		}
		columnData.add(curRow);
		return new InsertQueryModel(tableName, columnData);
	}

	public static UpdateQueryModel getUpdateQueryModel(String query) {
		// update tablename set col = 'val' where col = 'val';
		List<String> quotes = fetchAllTheQuoteValues(query);
		String tableName = query.split("=")[0].trim().replaceAll(" +", " ").split(" ")[1];
		Boolean updateAll = Boolean.TRUE;
		String updateColName = query.split("=")[0].trim().replaceAll(" +", " ").split(" ")[3];
		String updateColVal = quotes.get(0);
		String conditionColName = null;
		String conditionColVal = null;
		if (Boolean.FALSE.equals(query.matches(".*" + DatabaseConstants.RegExConstants.WHERE_CLAUSE_REGEX + ".*"))) {
			return new UpdateQueryModel(tableName, updateAll, updateColName, updateColVal, conditionColName,
					conditionColVal);
		}
		updateAll = Boolean.FALSE;
		conditionColName = query.split("'")[2].trim().replaceAll(" +", " ").replaceAll("=", "").trim().split(" ")[1];
		conditionColVal = quotes.get(1);
		return new UpdateQueryModel(tableName, updateAll, updateColName, updateColVal, conditionColName,
				conditionColVal);
	}

	public static DeleteQueryModel getDeleteQueryModel(String query) {
		// delete from tablename where col = 'val'
		String tableName = query.split("=")[0].trim().replaceAll(" +", " ").split(" ")[2];
		Boolean deleteAll = Boolean.TRUE;
		String conditionColName = null;
		String conditionColVal = null;
		if (Boolean.FALSE.equals(query.matches(".*" + DatabaseConstants.RegExConstants.WHERE_CLAUSE_REGEX + ".*"))) {
			return new DeleteQueryModel(tableName, deleteAll, conditionColName, conditionColVal);
		}
		deleteAll = Boolean.FALSE;
		conditionColName = query.split("=")[0].trim().replaceAll(" +", " ").split(" ")[4];
		conditionColVal = fetchAllTheQuoteValues(query).get(0);
		return new DeleteQueryModel(tableName, deleteAll, conditionColName, conditionColVal);
	}

	private static List<String> fetchAllTheQuoteValues(String query) {
		Pattern p = Pattern.compile(Pattern.quote("'") + "(.*?)" + Pattern.quote("'"));
		Matcher m = p.matcher(query);
		List<String> quotes = new ArrayList<>();
		while (m.find()) {
			quotes.add(m.group(1));
		}
		return quotes;
	}

	private static Boolean checkColumnExists(List<Column> columns, Column column) {
		for (Column col : columns) {
			if (col.columnName.equalsIgnoreCase(column.columnName)) {
				return true;
			}
		}
		return false;
	}

	private static Column fetchColumn(String s) {
		String[] words = s.split(" ");
		List<String> c = new ArrayList<>();
		for (String word : words) {
			if (word.isBlank())
				continue;
			c.add(word);
		}
		assert (c.size() == 2);
		Column col = new Column();
		col.columnName = c.get(0);
		col.dataType = findDataType.get(c.get(1).toLowerCase());
		return col;
	}

	private static String findGroupFromRegEx(String str, String regex, Integer groupId) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return matcher.group(groupId);
		}
		return "";
	}
}
