import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int numOperations = 0;
		try {
			numOperations = Integer.parseInt(input.readLine());
		} catch (Exception e) {

		}

		String[] operationInput = {"", ""};
		String operation = "";
		int element = -1;

		Teque teque = new Teque();
		for (int i = 0; i < numOperations; i++) {
			try {
				operationInput = input.readLine().split(" ");
				operation = operationInput[0];
				element = Integer.parseInt(operationInput[1]);
			} catch (Exception e) {
				
			}
			if (operation.equals("push_back")) {
				teque.push_back(element);
			} else if (operation.equals("push_front")) {
				teque.push_front(element);
			} else if (operation.equals("push_middle")) {
				teque.push_middle(element);
			} else {
				output.println(teque.get(element));
			}
			//output.print("\nFrontArrSize");
			//output.print(teque.frontArrSize);
			//output.print("\nBackArrSize");
			//output.print(teque.backArrSize);
			//output.println(" ");
		}
			
/*		
		System.out.println("Front Arr");
		//System.out.println(teque.frontArrSize);
		for (int i = teque.frontArrFirst; i != teque.frontArrLast; i = (i + 1) % teque.maxSize) {
			int val = teque.frontArr.get(i);
			if (val != 0) {
				System.out.println(val);
			}
		}
		System.out.println(teque.frontArr.get(teque.frontArrLast));
		
		System.out.println("Back Arr");
		//System.out.println(teque.backArrSize);
		for (int j = teque.backArrFirst; j != teque.backArrLast + 1 ; j = (j + 1) % teque.maxSize) {
			int val = teque.backArr.get(j);
			if (val != 0) {
				System.out.println(val);
			}
		}
		//System.out.println(teque.backArr.get(teque.backArrLast));
		

		//output.println(teque.backArrFirst);
*/		//output.println(teque.backArrLast);
	/*	output.println("FrontArr");
		for (int i = 0; i < teque.frontArr.size(); i++) {
			output.println(teque.frontArr.get(i));
		}

		output.println("BackArr");
		for (int j = 0; j < teque.backArr.size(); j++) {
			output.println(teque.backArr.get(j));
		}

		output.println(teque.frontArrFirst);
		output.println(teque.frontArrLast);
		output.println(teque.backArrFirst);
		output.println(teque.backArrLast);
*/
		output.close();	
		try {
			input.close();
		} catch (Exception e) {

		}
	}
}
