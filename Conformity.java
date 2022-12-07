import java.util.*;
import java.io.*;

public class Conformity {

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		HashMap<String, Integer> combiTable = new HashMap<String, Integer>();

		int numStudents = -1;
		try {
			numStudents = Integer.parseInt(input.readLine());
		} catch (Exception e) {

		}

		for (int i = 0; i < numStudents; i++) {
			String[] combiString = new String[5];
			int[] combiArray = new int[5];
			try {
				combiString = input.readLine().split(" ");
			} catch (Exception e) {

			}
			
			for (int j = 0; j < 5; j++) {
				combiArray[j] = Integer.parseInt(combiString[j]);
			}

			Arrays.sort(combiArray);

			StringBuilder combiToStringBuilder = new StringBuilder();

			for (int n = 0; n < 5; n++) {
				combiToStringBuilder.append(String.valueOf(combiArray[n]));
			}
			
			String combiToString = combiToStringBuilder.toString();
			//output.println("StudentNum: " + i);
			if (combiTable.containsKey(combiToString)) {
			//	output.println("if StudentNum: " + i);
				combiTable.put(combiToString, combiTable.get(combiToString) + 1);
			} else {
			//	output.println("else studentNum: " + i);
				combiTable.put(combiToString, 1);
			}

		}

		Set<String> keys = combiTable.keySet();

		int max = Integer.MIN_VALUE;

		for (String key : keys) {
			if (combiTable.get(key) > max) {
				max = combiTable.get(key);
			}
		}

		int result = 0;

		for (String key : keys) {
			if (combiTable.get(key) == max) {
				result += combiTable.get(key);
			}
		}
		//output.println("Max: " + max);
		output.println(result);
		
		/*
		for (String key : keys) {
			System.out.print("\nKey: " + key);
			System.out.print(", Value: " + combiTable.get(key));
		}
		*/
		

		try {
			input.close();
		} catch (Exception e) {

		}

		output.close();
	}
}
