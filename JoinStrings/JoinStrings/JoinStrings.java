import java.util.*;
import java.io.*;

public class JoinStrings {
	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int numStrings = 0;
		try {
			numStrings = Integer.parseInt(input.readLine());
		} catch (Exception e) {

		}
		
		ArrayList<TailedLinkedList<String>> stringArr = new ArrayList<TailedLinkedList<String>>();

		for (int i = 0; i < numStrings; i++) {
			stringArr.add(new TailedLinkedList<String>());
		}
		

		for (int j = 0; j < numStrings; j++) {
			try {
				stringArr.get(j).add(input.readLine());
			} catch (Exception e) {

			}
		}

		int[] operation = {-1, -1};
		String[] intermediate = {"", ""};

		for (int n = 0; n < numStrings - 1; n++) {
			try {
				intermediate = input.readLine().split(" ");
				operation = stringToIntArr(intermediate, 2);
				TailedLinkedList<String> a = stringArr.get(operation[0] - 1);
				TailedLinkedList<String> b = stringArr.get(operation[1] - 1);
				a.append(b);
				stringArr.set((operation[1] - 1), new TailedLinkedList<String>());
				stringArr.get(operation[1] - 1).add("");
				// System.out.println(a.head.next.value);
			} catch (Exception e) {

			}
		}
	
		try {
			input.close();
		} catch (Exception e) {

		}
		output.println(joinAll(stringArr, numStrings));	
		output.close();

	}

	public static int[] stringToIntArr(String[] stringArr, int size) {
		int[] intArr = new int[size];
		for (int i = 0; i < size; i++) {
			intArr[i] = Integer.parseInt(stringArr[i]);
		}

		return intArr;
	}

	public static String joinAll(ArrayList<TailedLinkedList<String>> resultArr, int size) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < size; i++) {
			Node<String> curItem = resultArr.get(i).head;
			while(curItem != null) {
				// System.out.println(curItem.value);
				result.append(curItem.value);
				curItem = curItem.next;
			}
		}
		return result.toString();
	}
}
