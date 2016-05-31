package com.ems.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtility {
	static String readFile(File file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		String returnStr = "";
		while((line = br.readLine()) != null){
			returnStr += line;
		}
		br.close();
		return returnStr;
	}
	
	static void writeFile(File file, String content) throws IOException{
		FileWriter fw = new FileWriter(file);
		fw.write(content);
		fw.close();
	}
}
