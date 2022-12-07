import java.util.*;
import java.io.*;

public class Fire {

	public static int[] moveX = {-1, 1, 0, 0};
	public static int[] moveY = {0, 0, -1, 1};

	public static void main(String args[]) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String line = input.readLine();

		int r = Integer.parseInt(line.split(" ")[0]);
		int c = Integer.parseInt(line.split(" ")[1]);

		Node[][] map = new Node[r][c];

		Queue<Node> fq = new LinkedList<Node>();
		Queue<Node> uq = new LinkedList<Node>();

		for (int i = 0; i < r; i++) {
			line = input.readLine();
			for (int j = 0; j < c; j++) {
				Node cur = new Node(i, j, line.charAt(j), 0);
				map[i][j] = cur;
				if (line.charAt(j) == 'F') {
					fq.offer(cur);
				} else if (line.charAt(j) == 'J') {
					uq.offer(cur);
				}
			}
		}

		int time = 0;
		boolean escaped = false;
		while ((!uq.isEmpty() || !fq.isEmpty()) && escaped == false) {
			// System.out.println(uq.peek());
			// System.out.println(fq.peek());
			// System.out.println(time);
			if (fq.peek() != null && fq.peek().time == time) {
				Node curF = fq.poll();
				for (int i = 0; i < moveX.length; i++) {
					int adjR = curF.r + moveX[i];
					int adjC = curF.c + moveY[i];

					if (adjR < 0 || adjR >= r || adjC < 0 || adjC >= c) {
						continue;
					}
					if (map[adjR][adjC].type == '#' || map[adjR][adjC].type == 'F') { 
						continue;	
					}							

					Node adjNode = new Node(adjR, adjC, 'F', curF.time + 1);
					map[adjR][adjC] = adjNode;
					fq.offer(adjNode);
					// System.out.println(adjNode);
				}
			} 

			if (uq.peek() != null && uq.peek().time == time) {
				Node curU = uq.poll();
				if (curU.r == r - 1 || curU.r == 0 || curU.c == c - 1 || curU.c == 0) {
					escaped = true;
					break;
				}

				for (int i = 0; i < moveX.length; i++) {
					int adjR = curU.r + moveX[i];
					int adjC = curU.c + moveY[i];

					if (adjR < 0 || adjR >= r || adjC < 0 || adjC >= c) {
						continue;
					}
					if (map[adjR][adjC].type == '#' || map[adjR][adjC].type == 'F' || map[adjR][adjC].type == 'J') { 
						continue;	
					}

					Node adjNode = new Node(adjR, adjC, 'J', curU.time + 1);
					map[adjR][adjC] = adjNode;
					uq.offer(adjNode);
				//	System.out.println(adjNode);

				}				
			}
			int fqTime = fq.peek() == null ? 100000 : fq.peek().time;
			int uqTime = uq.peek() == null ? 100000 : uq.peek().time;

			if (fqTime == 100000 && uqTime == 100000) {
				break;
			}
			time = Math.min(fqTime, uqTime);

		}

		output.println(escaped == false ? "IMPOSSIBLE" : time + 1);
		output.close();
		input.close();

	}
}



class Node {
	int r, c, type, time; 	//type 0 = wall, 1 = you, 2 = fire	

	public Node(int r, int c, int type, int time) {
		this.r = r;
		this.c = c;
		this.type = type;
		this.time = time;
	}

	@Override
	public String toString() {
		return r + " " + c + " " + time;
	}
}
