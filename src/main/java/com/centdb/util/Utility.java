package com.centdb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static List<String> readFileByLines(File file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static String getEquivalentSqlDatatype(String centDbDatatype) {
        switch (centDbDatatype) {
            case "int":
                return "int";
            case "string":
                return "varchar(255)";
            default:
                return null;
        }
    }
    
    public static boolean writeTofile(String path, String body) {
		try {
			FileWriter myWriter = new FileWriter(path, true);
            myWriter.write("\r"+body+"\n");
            myWriter.close();
            return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
