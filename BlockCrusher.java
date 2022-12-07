import java.util.*;
import java.io.*;

public class BC {
	public static int INF = 1000000;
	public static int[] traverseX = {-1, 1, 0};
	public static int[] traverseY = {-1, 1, 0};
	public static void main(String args[]) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int h = -1;
		int w = -1;

		while (h != 0 || w != 0) {
			String[] line = input.readLine().split(" ");
			h = Integer.parseInt(line[0]);
			w = Integer.parseInt(line[1]);

			if (h == 0 || w == 0) {
				break;
			}

			int[][] matrix = new int[h][w];
			int[][] strength = new int[h][w];

			for (int i = 0; i < h; i++) {
				String lineH = input.readLine();
				for (int j = 0; j < w; j++) {
					matrix[i][j] = Character.getNumericValue(lineH.charAt(j));
					strength[i][j] = INF;
				}
			}

			PriorityQueue<Node> pq = new PriorityQueue<>();
			TreeSet<Node> set = new TreeSet<>();
			

			for (int i = 0; i < w; i++) {
				pq.offer(new Node(0, i, matrix[0][i], null));
			}


			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				if (cur.r == h - 1) {
					set.add(cur);
				}
				for (int i = 0; i < traverseX.length; i++) {
					for (int j = 0; j < traverseY.length; j++) {
						int adjR = cur.r + traverseX[i];
						int adjC = cur.c + traverseY[j];
						if (adjR < 0 || adjR >= h || adjC < 0 || adjC >= w) {
							continue;
						}
						int str = cur.str + matrix[adjR][adjC];
						if (str < strength[adjR][adjC]) {
							strength[adjR][adjC] = str;
							pq.offer(new Node(adjR, adjC, str, cur));
						}
					}
				}


			}

			Node result = set.first();
			while (result != null) {
				matrix[result.r][result.c] = -1;
				result = result.prev;
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (matrix[i][j] == -1) {
						output.print(" ");
					} else {
						output.print(matrix[i][j]);
					}
				}
				output.print("\n");
			}

			output.println("");

		}
		output.close();
		input.close();
	}

}

class Node implements Comparable<Node> {

	int r;
	int c;
	int str;
	Node prev;

	public Node(int r, int c, int e, Node p) {
		this.r = r;
		this.c = c;
		this.str = e;
		this.prev = p;
	}

	@Override
	public int compareTo(Node x) {
		return this.str - x.str;
	}

}


