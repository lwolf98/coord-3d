package de.util.java.arrayop;

public class ArrayOP {
	public static <T> void sortByLength(T[] objects) {
		sortByLength(objects, true);
	}
	
	public static <T> void sortByLength(T[] objects, boolean longToShort) {
		T object;
		for(int i = 0; i < objects.length; i++) {
			object = objects[i];
			int j = i;
			while(j != objects.length - 1) {
				j++;
				if((objects[j].toString().length() > object.toString().length()) & longToShort) { //???
					objects[i] = objects[j];
					objects[j] = object;
					object = objects[i];
					j = i;
				}
			}
		}
	}
	
	public static Integer[] intToInteger(int[] ints) {
		Integer[] objects = new Integer[ints.length];
		for(int i = 0; i < ints.length; i++)
			objects[i] = ints[i];
		
		return objects;
	}
	
	public static Boolean[] booleanToBoolean(boolean[] bools) {
		Boolean[] objects = new Boolean[bools.length];
		for(int i = 0; i < bools.length; i++)
			objects[i] = bools[i];
		
		return objects;
	}
	
	public static Character[] charToCharacter(char[] chars) {
		Character[] objects = new Character[chars.length];
		for(int i = 0; i < chars.length; i++)
			objects[i] = chars[i];
		
		return objects;
	}
}
