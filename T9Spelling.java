import java.io.*;
import java.util.*;

public class T9Spelling {
	public static void main(String args[]) {	
		
		BufferedReader takeInput = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] inputMap = new String[26];
		int keypadNum = 2;
		int currentDigitsLen = 1;
		

		//static int currentDigitsLen = 1;
		for (int i = 0; i < 26; i++) {
			int charToNum = keypadNum;
			for (int j = 1; j != currentDigitsLen; j++) {
				charToNum = (charToNum * 10) + keypadNum;
			}
			inputMap[i] = Integer.toString(charToNum);
			currentDigitsLen++;
			if (currentDigitsLen == 4 && keypadNum != 7 && keypadNum != 9) {
				keypadNum++;
				currentDigitsLen = 1;
			}

			if (currentDigitsLen == 5) {
				keypadNum++;
				currentDigitsLen = 1;
			}

		}	

		

		int numLines;
		
		try {
			numLines = Integer.parseInt(takeInput.readLine());
		} catch(Exception e) {
			numLines = 0;
		}

		for (int n = 0; n < numLines; n++) {
			String input;
			try {
				input = takeInput.readLine();
			} catch (Exception e) {
				input = "";
			}
			StringBuilder resultString = new StringBuilder(100);
			char prevChar = 'A';
		
			for (int charPos = 0; charPos < input.length(); charPos++) {
				int charIndex = input.charAt(charPos) - 97;

				if (charIndex < 0) {
					if (prevChar == ' ') {
						resultString.append(" ");
					}
					resultString.append("0");
					prevChar = ' ';
				} else {
					String mappedNum = inputMap[charIndex];
					if (mappedNum.charAt(0) == prevChar) {
						resultString.append(" ");
					}
					resultString.append(mappedNum);
					prevChar = mappedNum.charAt(0);
				}
			}
		
				output.println("Case #" + (n + 1) + ": " + resultString);
		}
		output.close();
		
	}
}
