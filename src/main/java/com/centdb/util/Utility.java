package com.centdb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
			myWriter.write("\r" + body + "\n");
			myWriter.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static Boolean createDirectory(String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				throw new RuntimeException("Databse already exists.");
			}
			return file.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	public static Boolean deleteDirectory(String path) {
		try {
			File file = new File(path);
			if (!file.exists()) {
				throw new RuntimeException("Database does not exists.");
			} else {
				String[] tables = file.list();
				for (String table : tables) {
					File currentFile = new File(file.getPath(), table);
					currentFile.delete();
				}
			}
			return file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	public static Boolean createFile(String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				throw new RuntimeException("Table already exists.");
			}
			return file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	public static Boolean deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new RuntimeException("Table does not exist.");
			}
			return file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	public static Boolean deleteContentOfFile(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new RuntimeException("Table does not exist.");
			}
			PrintWriter writer = new PrintWriter(filePath);
			writer.write("");
			writer.close();
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	public static Boolean writeLineToFile(String filePath, String line) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				throw new RuntimeException("Table does not exist.");
			}
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, Boolean.TRUE));
			writer.append(line + "\n");
			writer.close();
			return Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

	public static Boolean isFileExists(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public static String readFirstLine(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String firstLine = reader.readLine();
			reader.close();
			return firstLine;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
