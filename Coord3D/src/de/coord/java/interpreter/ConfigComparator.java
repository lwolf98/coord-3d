package de.coord.java.interpreter;

import java.util.Comparator;

public class ConfigComparator implements Comparator<String[]> {

	public int compare(String[] e1, String[] e2) {
		return e2[0].length() - e1[0].length();
	}

}
