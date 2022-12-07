import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class ArcticNetwork {
	
	public static PriorityQueue<Pair> prim;
	public static ArrayList<ArrayList<Pair>> adjList;

	public static void main(String args[]) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int cases = Integer.parseInt(input.readLine());

		for (int n = 0; n < cases; n++) {
			String line = input.readLine();
			int s = Integer.parseInt(line.split(" ")[0]);
			int p = Integer.parseInt(line.split(" ")[1]);
			
			Outpost[] oArr = new Outpost[p];

			PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
			
			prim = new PriorityQueue<>();


			for (int i = 0; i < p; i++) {
				line = input.readLine();
				String[] oCoord = line.split(" ");
				oArr[i] = new Outpost(Double.parseDouble(oCoord[0]), Double.parseDouble(oCoord[1]));
			}


			adjList = new ArrayList<>();
			
			for (int i = 0; i < p; i++) {
				adjList.add(new ArrayList<>());
			}

			for (int i = 0; i < p; i++) {
				for (int j = i + 1; j < p; j++) { 
					Outpost cur1 = oArr[i];
					Outpost cur2 = oArr[j];
					double distance = oArr[i].distanceTo(oArr[j]);
					adjList.get(i).add(new Pair(distance, j));
					adjList.get(j).add(new Pair(distance, i));
				}
			}

			process(0);
			
			int[] visited = new int[p];
			visited[0] = 1;

			while (!prim.isEmpty()) {
				Pair cur = prim.poll();
				if (visited[cur.second] == 0) {
					visited[cur.second] = 1;
					ArrayList<Pair> curAdj = adjList.get(cur.second);
					for (int i = 0; i < curAdj.size(); i++) {
						prim.offer(curAdj.get(i));
					}
					pq.offer(cur);
				}
			}

			for (int i = 0; i < s - 1; i++) {
				pq.poll();
			}


			output.printf("%.2f\n", pq.poll().first);
			
			


		}

		input.close();
		output.close();
		
	}

	public static void process(int j) {
		ArrayList<Pair> cur = adjList.get(j);
		for (int i = 0; i < cur.size(); i++) {
			prim.offer(cur.get(i));
		}
	}

}

class Pair implements Comparable<Pair> {
	double first;
	int second;

	public Pair (double first, int second) {
		this.first = first;
		this.second = second;
	}

	public int compareTo(Pair o) {
		return this.first < o.first ? -1 : (this.first > o.first ? 1 : this.second - o.second);
	}
}


class Outpost {
	double x;
	double y;

	public Outpost (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanceTo(Outpost o) {
		return Math.hypot(Math.abs(this.x - o.x), Math.abs(this.y - o.y));
	}
		
	

}
