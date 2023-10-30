package de.test.java;

import java.util.ArrayList;
import java.util.List;

import de.util.java.arrayop.ArrayOP;

public class Tests {
	public static void main(String[] args) {
		String[] funct = new String[] {"2", "*", "(", "(", "1", "+", "3", ")", ")", "+", "1"};
		while(contains(funct, "(")) {
			String[] inner = test3(funct);
			System.out.println(String.join(";", funct) + "\n" + String.join(";", inner) + "\n");
		}
	}
	
	public static void test1() {
		//Integer[] arr = new Integer[] {3, 3, 3, 22, 3, 22, 111, 3};
		Integer[] arr = new Integer[] {1, 1, 1, 22, 22, 333, 333, 333, 4444};
		ArrayOP.sortByLength(arr);
		for(int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + ";");
	}
	
	public static void test2() {
		String[] funct = new String[] {"2", "*", "(", "1", "+", "3", ")"};
		
		for(int i = 0; i < funct.length; i++) {
			if(funct[i] == "(") {
				List<String> lstInner = new ArrayList<String>();
				while(funct[i] != ")") {
					lstInner.add(funct[i]);
					i++;
				}
				String[] inner = lstInner.toArray(new String[0]);
				
				System.out.println(inner.toString());
			}
		}
	}
	
	public static String[] test3(String[] funct) {
		//List<String> innerFunct = new ArrayList<String>();
		String[] innerFunct;
		int start = 0, end = 0;
		
		for(int i = 0; i < funct.length; i++) {
			if(funct[i] == "(") {
				start = i;
			} else if(funct[i] == ")") {
				end = i;
				break;
			}
		}
		
		innerFunct = new String[end - start - 1];
		for(int i = 0; i < innerFunct.length; i++) {
			innerFunct[i] = funct[start + i + 1];
		}
		
		String[] updateFunct = new String[funct.length - (end - start)];
		
		//int index = 0;
		for(int i = 0, j = 0; i < updateFunct.length; i++, j++) {
			if(j == start) {
				j = end;
				updateFunct[i] = "inner";
				continue;
			}
			updateFunct[i] = funct[j];
		}
		funct = updateFunct;
		
		return innerFunct;
	}
	
	public static boolean contains(String[] arr, String lookFor) {
		for(int i = 0; i < arr.length; i++)
			if(arr[i] == lookFor) return true;
		
		return false;
	}
}
