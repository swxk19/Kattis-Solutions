import java.util.*;
import java.io.*;

public class Islands {

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] inputRC = new String[2];

		try {
			inputRC = input.readLine().split(" ");
		} catch (Exception e) {

		}

		int r = Integer.parseInt(inputRC[0]);
		int c = Integer.parseInt(inputRC[1]);

		char[][] M = new char[r][c];
		int[][] N = new int[r][c];


		for (int i = 0; i < r; i++) {
			String line = "";
			try {
				line = input.readLine();
			} catch (Exception e) { 
			
			}
			for (int j = 0; j < c; j++) {
				M[i][j] = line.charAt(j);
				N[i][j] = 0;
			}
		}
		
		int count = 0;

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (M[i][j] == 'L' && N[i][j] == 0) {
					count += 1;
					traverse(M, N, r, c, j, i);
				}
			}
		}

		output.println(count);

		output.close();
		try {
			input.close();
		} catch (Exception e) {

		}

		
	}

		public static void traverse(char[][] M, int[][] N, int r, int c, int x, int y) {
			if (y < 0 || y > r - 1 || x < 0 || x > c - 1) {
				return; 
			}

			char cur = M[y][x];
			int visited = N[y][x];
			if (visited != 1 && cur != 'W') {
				N[y][x] = 1;
				traverse(M, N, r, c, x, y + 1);		
				traverse(M, N, r, c, x, y - 1);
				traverse(M, N, r, c, x + 1, y);
				traverse(M, N, r, c, x - 1, y);
			}

		}
}
