package com.centdb;

import com.centdb.module6.Export;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CentDbApplication {

	public static void main(String[] args) {
		Export export = new Export("database_name");
		export.toSql();
	}

}
