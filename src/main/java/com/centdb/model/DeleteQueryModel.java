package com.centdb.model;

public class DeleteQueryModel {

	private String tableName;
	
	private Boolean deleteAll;
	
	private String conditionColName;
	
	private String conditionColVal;

	public DeleteQueryModel(String tableName, Boolean deleteAll, String conditionColName, String conditionColVal) {
		super();
		this.tableName = tableName;
		this.deleteAll = deleteAll;
		this.conditionColName = conditionColName;
		this.conditionColVal = conditionColVal;
	}

	@Override
	public String toString() {
		return "DeleteQueryModel [tableName=" + tableName + ", deleteAll=" + deleteAll + ", conditionColName="
				+ conditionColName + ", conditionColVal=" + conditionColVal + "]";
	}
}
