import java.io.*;
import java.util.*;

public class SortOfSorting {

	static Comparator<String> sortOfComparator = new Comparator<String>() {
		@Override
		public int compare(final String s1, String s2) {		
			if ((int) s1.charAt(0) < (int) s2.charAt(0)) {
				return -1;
			} else if (s1.charAt(0) == s2.charAt(0)){
				return Integer.valueOf((int) s1.charAt(1)).compareTo((int) s2.charAt(1));
			} else {
				return 1;
			}
		}
	};

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		int numStudents = -1;
		
		while (numStudents != 0) {
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		try {
			numStudents = Integer.parseInt(input.readLine());
		} catch(Exception e) {

		}

		String[] studentArray = new String[numStudents];

		for (int i = 0; i < numStudents; i++) {
			String name = "";
			
			try {
				name = input.readLine();
			} catch (Exception e) {
			
			}

			studentArray[i] = name;
		}

		Arrays.sort(studentArray, sortOfComparator);

		for (int j = 0; j < numStudents; j++) {
			output.println(studentArray[j]);
		}
		output.flush();
		}

		try {
			input.close();
			
		} catch(Exception e) {

		}
	
	}
	
}
