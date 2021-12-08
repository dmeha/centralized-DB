package com.centdb.module7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Analysis {

	public Analysis() {}
	
	public void readInput() {
		String beginstr="count";
		String cmtstr="query";
		String dbName = "";
		Scanner input = new Scanner(System.in);
		String lineNew=input.nextLine();
		String[] tokens= lineNew.split(" "); 
		if(tokens[0].equalsIgnoreCase(beginstr) && tokens[1].equalsIgnoreCase(cmtstr)) {
			dbName = tokens[2];
			queresForDb(dbName);
		} else {
			System.out.println("Wrong Query");
		}
	}
	
	public void queresForDb(String dbName) {
		int result= ExtractText("C:\\sumit\\studies\\Dmws\\project\\csci-5408-f2021-g19\\LogResources\\LogQuery.txt", dbName);
		System.out.println("total " +result  +" queries were perforemed on DB1");
	}
	
	public int ExtractText(String fullFilePath, String dbName)
	{	
		try {
			int count =0;
			String data = "";
			data = new String(Files.readAllBytes(Paths.get(fullFilePath)));
			String[] logs= data.split("\n"); 
			for( String log : logs ) {
				log = log.toLowerCase();
				if(log.contains(dbName.toLowerCase())) {
					count++;
				}
				}
			return count;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}
}
