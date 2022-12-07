import java.util.*;
import java.io.*;

public class lostmap {

	public static PriorityQueue<IntegerTriple> pq;
	public static ArrayList<ArrayList<IntegerTriple>> adjList = new ArrayList<>();
	public static ArrayList<Boolean> taken;

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int numVillage = 0;

		try {
			numVillage = Integer.parseInt(input.readLine());
		} catch (Exception e) {

		}

		
		pq = new PriorityQueue<>();
		taken = new ArrayList<>();
		taken.addAll(Collections.nCopies(numVillage, false));		

		ArrayList<IntegerTriple> result = new ArrayList<>();

		for (int i = 0; i < numVillage; i++) {
			String[] rowWeight = new String[numVillage];
			adjList.add(new ArrayList<IntegerTriple>());
			try {
				rowWeight = input.readLine().split(" ");
			} catch (Exception e) {

			}
			
			ArrayList<IntegerTriple> curAdj = adjList.get(i);		
			for (int j = 0; j < numVillage; j++) {
				curAdj.add(new IntegerTriple(Integer.parseInt(rowWeight[j]), j, i));
			}

		}
		process(0);

		while (!pq.isEmpty()) {
			IntegerTriple cur = pq.poll();

			if (!taken.get(cur.second)) {
				result.add(cur);
				process(cur.second);
			}
		}

		for (int i = 0; i < result.size(); i++) {
			IntegerTriple cur = result.get(i);
			output.println((cur.third + 1) + " " + (cur.second + 1));
		}

		output.close();
		try {
			input.close();
		} catch (Exception e) {

		}

	}

	public static void process(int v) {
		taken.set(v, true);
		ArrayList<IntegerTriple> curAdj = adjList.get(v);
		for (int i = 0; i < curAdj.size(); i++) {
			IntegerTriple adjEdge = curAdj.get(i);
			if (!taken.get(adjEdge.second)) {
				pq.offer(adjEdge);
			}
		}
	}
}

class IntegerTriple implements Comparable<IntegerTriple> {
	Integer first;
	Integer second;
	Integer third;

	public IntegerTriple (int one, int two, int three) {
		this.first = one;
		this.second = two;
		this.third = three;
	}

	public int compareTo(IntegerTriple o) {
		if (!this.first.equals(o.first)) {
			return this.first - o.first;
		} else {
			return this.second - o.second;
		}
	}

}
