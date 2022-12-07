import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class WeakVertices {
	
	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int numRows = 0;
		
		while (true) {

			try {
				numRows = Integer.parseInt(input.readLine());
			} catch (Exception e) {
		
			}
			
			if (numRows == -1) {
				break;
			}

			int[][] adjMat = new int[numRows][numRows];

			for (int i = 0; i < numRows; i++) {
			
				String[] temp = new String[numRows];
				int[] cur = new int[numRows];

				try {
					temp = input.readLine().split(" ");
				} catch (Exception e){
	
				}
				
		//		System.out.println(i);
		//		System.out.println(Arrays.toString(temp));	

				for (int j = 0; j < temp.length; j++) {
					cur[j] = Integer.parseInt(temp[j]);
				}
		
				adjMat[i] = cur;
			}

			int[] result = new int[numRows];

			for (int i = 0; i < numRows; i++) {
				result[i] = 0;
			}

			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numRows; j++) {
					if (adjMat[i][j] == 1) {
						for (int n = 0; n < numRows; n++) {
							if (adjMat[j][n] == 1) {
								if (adjMat[n][i] == 1) {
									result[i] = 1;
								}
							}
						}
					}
				}
			}

		String printResult = "";
		for (int i = 0; i < result.length; i++) {
			if (result[i] == 0) {
				printResult += i;
				printResult += " ";
			}
		}

		output.println(printResult);
		output.flush();
	}
	output.close();
	try {
		input.close();
	} catch (Exception e) {

	}
	

}
}























