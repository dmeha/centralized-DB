package com.centdb.model;

import java.util.List;

public class InsertQueryModel {

	private String tableName;

	private List<List<String>> colValues;

	public InsertQueryModel(String tableName, List<List<String>> colValues) {
		super();
		this.tableName = tableName;
		this.colValues = colValues;
	}

	@Override
	public String toString() {
		return "InsertQueryModel [tableName=" + tableName + ", colValues=" + colValues + "]";
	}

}
