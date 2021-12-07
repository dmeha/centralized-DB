package com.centdb.model;

public class UpdateQueryModel {

	private String tableName;
	
	private Boolean updateAll;
	
	private String updateColName;
	
	private String updateColValue;
	
	private String conditionColName;
	
	private String conditionColVal;

	public UpdateQueryModel(String tableName, Boolean updateAll, String updateColName, String updateColValue,
			String conditionColName, String conditionColVal) {
		super();
		this.tableName = tableName;
		this.updateAll = updateAll;
		this.updateColName = updateColName;
		this.updateColValue = updateColValue;
		this.conditionColName = conditionColName;
		this.conditionColVal = conditionColVal;
	}

	@Override
	public String toString() {
		return "UpdateQueryModel [tableName=" + tableName + ", updateAll=" + updateAll + ", updateColName="
				+ updateColName + ", updateColValue=" + updateColValue + ", conditionColName=" + conditionColName
				+ ", conditionColVal=" + conditionColVal + "]";
	}
}
