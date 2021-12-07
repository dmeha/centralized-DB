package com.centdb.model;

import java.util.List;

public class SelectQueryModel {

	private String tableName;
	
	private List<String> columnNameList;
	
	private Boolean selectAllColumn;
	
	private Boolean selectAllRows;
	
	private String conditionColName;
	
	private String conditionColVal;

	public SelectQueryModel(String tableName, List<String> columnNameList, Boolean selectAllColumn,
			Boolean selectAllRows, String conditionColName, String conditionColVal) {
		super();
		this.tableName = tableName;
		this.columnNameList = columnNameList;
		this.selectAllColumn = selectAllColumn;
		this.selectAllRows = selectAllRows;
		this.conditionColName = conditionColName;
		this.conditionColVal = conditionColVal;
	}

	@Override
	public String toString() {
		return "SelectQueryModel [tableName=" + tableName + ", columnNameList=" + columnNameList + ", selectAllColumn="
				+ selectAllColumn + ", selectAllRows=" + selectAllRows + ", conditionColName=" + conditionColName
				+ ", conditionColVal=" + conditionColVal + "]";
	}
}
