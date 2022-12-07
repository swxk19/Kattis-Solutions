import java.util.*;
import java.io.*;

public class Hcr {

	public static final double INF = 99999999;

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		Pair start = new Pair(0, 0);
		String[] tempStart = new String[2];
		Pair end = new Pair(0, 0);
		String[] tempEnd = new String[2];


		try {
			tempStart = input.readLine().split(" ");		
			tempEnd = input.readLine().split(" ");
		} catch (Exception e) {

		}

		start.first = Double.parseDouble(tempStart[0]);
		start.second = Double.parseDouble(tempStart[1]);

		end.first = Double.parseDouble(tempEnd[0]);
		end.second = Double.parseDouble(tempEnd[1]);



		int numCan = 0;

		try {
			numCan = Integer.parseInt(input.readLine());
		} catch (Exception e) {

		}

		ArrayList<ArrayList<Pair>> adjList = new ArrayList<>();
		double[] dist = new double[numCan + 2];
		Pair[] nodes = new Pair[numCan + 2];

		for (int i = 0; i < numCan + 2; i++) {
			if (i == 0) {
				nodes[i] = start;
				dist[i] = 0;
			} else if (i == 1) {
				nodes[i] = end;
				dist[i] = INF;
			} else {
				String[] tempCan = new String[2];
				try {
					tempCan = input.readLine().split(" ");
				} catch (Exception e) {
				
				}
				Pair cannon = new Pair(Double.parseDouble(tempCan[0]), Double.parseDouble(tempCan[1]));
			        nodes[i] = cannon;	
				dist[i] = INF;
			}
			adjList.add(new ArrayList<Pair>());
		}

		// output.println(Arrays.toString(nodes));
		
		for (int i = 0; i < nodes.length; i++) {
			ArrayList<Pair> curAdj = adjList.get(i);
			Pair outer = nodes[i];
			for (int j = 0; j < nodes.length; j++) {
				Pair inner = nodes[j];
				double distance = Math.hypot(outer.first - inner.first, outer.second - inner.second);
				double timeRun = distance / 5;
				double timeCannon = INF;
				if (i != 0 && i != 1) {
					timeCannon = 2 + (Math.abs(distance - 50) / 5); 
				}
				curAdj.add(new Pair(j, Math.min(timeRun, timeCannon)));
			}
		}


		PriorityQueue<Pair> pq = new PriorityQueue<>();

		pq.add(new Pair(0,0));

		while (!pq.isEmpty()) {
			Pair cur = pq.poll();
			if (dist[(int) cur.first] == cur.second) {
				ArrayList<Pair> curAdj = adjList.get((int) cur.first);
				for (int i = 0; i < curAdj.size(); i++) {
					Pair n = curAdj.get(i);
					if (dist[(int) cur.first] + n.second < dist[(int) n.first]) {
						dist[(int) n.first] = dist[(int) cur.first] + n.second;
						pq.offer(new Pair(n.first, dist[(int) n.first]));
					}
				}
			}
		}

		output.println(dist[1]);


		output.close();

		try {
			input.close();
		} catch (Exception e) {

		}
	}

}

class Pair implements Comparable<Pair> {
	double first;
	double second;

	public Pair(double first, double second) {
		this.first = first;
		this.second = second;
	}

	@Override	// edge is second
	public int compareTo(Pair b) {
		if (this.second == b.second) {
			return 0;
		} else if (this.second > b.second){
			return 1;
		} else if (this.second < b.second) {
			return -1;
		} else {
			return (int) (this.first - b.first);
		}
	}

	@Override
	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}
}

