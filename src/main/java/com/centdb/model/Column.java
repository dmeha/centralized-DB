package com.centdb.model;

public class Column {

	public String columnName;
	
	public Boolean isPrimaryKey;
	
	public SqlDataType dataType;
	
	public Column() {
		isPrimaryKey = Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "Column [columnName=" + columnName + ", isPrimaryKey=" + isPrimaryKey + ", dataType=" + dataType + "]";
	}
}
