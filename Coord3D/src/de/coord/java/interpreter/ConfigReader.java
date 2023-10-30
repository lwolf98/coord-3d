package de.coord.java.interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {
	public static List<String[]> read(String fileName) {
		try {
			return read(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			return null;
		}
		
	}
	public static List<String[]> read(InputStream stream) {
		List<String[]> config = new ArrayList<String[]>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			
			while((line = reader.readLine()) != null) {
				config.add(line.split(";"));
			}
			reader.close();
		} catch (IOException e) {
		}
		
		return config;
	}
}
