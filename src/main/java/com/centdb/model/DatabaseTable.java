package com.centdb.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTable {

	public String tableName;
	
	public List<Column> columnList;

	public DatabaseTable() {
		columnList = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return "DatabaseTable [tabeName=" + tableName + ", columnList=" + columnList + "]";
	}
}
