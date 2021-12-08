package com.centdb.model;

public class Column {

	private String columnName;
	
	private Boolean isPrimaryKey;
	
	private SqlDataType dataType;
	
	public Column() {
		isPrimaryKey = Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "Column [columnName=" + columnName + ", isPrimaryKey=" + isPrimaryKey + ", dataType=" + dataType + "]";
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(Boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public SqlDataType getDataType() {
		return dataType;
	}

	public void setDataType(SqlDataType dataType) {
		this.dataType = dataType;
	}
}
