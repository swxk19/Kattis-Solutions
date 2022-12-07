import java.util.*;
import java.io.*;

public class PrimDemo {
	public static ArrayList < ArrayList < IntegerPair > > AdjList;
	public static ArrayList < Boolean > taken;
	public static PriorityQueue < IntegerPair > pq;

	public static void process(int vtx) {
		taken.set(vtx, true);
		for (int j = 0; j < AdjList.get(vtx).size(); j++) {
			IntegerPair v = AdjList.get(vtx).get(j);
			if (!taken.get(v.first())) {
				pq.offer(new IntegerPair(v.second(), v.first()));  // we sort by weight then by adjacent vertex
		//		System.out.println(">> Inserting (" + v.second() + ", " + v.first() + ") to priority queue");
			}
		//	else
		//		System.out.println(">> Ignoring (" + v.second() + ", " + v.first() + ")");
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		/*
		// Sample graph shown in lecture
		5 7
		1 2 4
		1 3 4
		2 3 2
		1 4 6
		3 4 8
		1 5 6
		4 5 9
		*/
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		String[] tempInput = new String[2];
		try {
			tempInput = input.readLine().split(" ");
		} catch (Exception e) {
			
		}

		int numRows = Integer.parseInt(tempInput[0]);
		int numCols = Integer.parseInt(tempInput[1]);
		int v = numRows * numCols;
		int[][] matrix = new int[numRows][numCols];
	//	System.out.println("numrows: " + numRows);
	//	System.out.println("numcols: " + numCols);

		for (int i = 0; i < numRows; i++) {
			String[] curRow = new String[numCols];
			try {
				curRow = input.readLine().split(" ");
			} catch (Exception e) {

			}
			for (int j = 0; j < numCols; j++) {
				matrix[i][j] = Integer.parseInt(curRow[j]);
			}
		}


		AdjList = new ArrayList <ArrayList<IntegerPair>>();

		for (int i = 0; i < v; i++) {
			ArrayList<IntegerPair> neighbor = new ArrayList <IntegerPair>(); // create vector of Integer pair 
			AdjList.add(neighbor); // store blank vector first
		}


		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				
				if (i + 1 < numRows) {
					AdjList.get(cToK(numCols, i, j)).add(new IntegerPair(cToK(numCols, i + 1, j), Math.max(0, matrix[i + 1][j] - matrix[i][j])));
					AdjList.get(cToK(numCols, i + 1, j)).add(new IntegerPair(cToK(numCols, i, j), Math.max(0, matrix[i][j] - matrix[i + 1][j])));
				}
				
				if (i - 1 >= 0) {
					AdjList.get(cToK(numCols, i, j)).add(new IntegerPair(cToK(numCols, i - 1, j), Math.max(0, matrix[i - 1][j] - matrix[i][j])));
					AdjList.get(cToK(numCols, i - 1, j)).add(new IntegerPair(cToK(numCols, i, j), Math.max(0, matrix[i][j] - matrix[i - 1][j])));
				} 
				if (j + 1 < numCols) {
					AdjList.get(cToK(numCols, i, j)).add(new IntegerPair(cToK(numCols, i, j + 1), Math.max(0, matrix[i][j + 1] - matrix[i][j])));
					AdjList.get(cToK(numCols, i, j + 1)).add(new IntegerPair(cToK(numCols, i, j), Math.max(0, matrix[i][j] - matrix[i][j + 1])));
				}
				if (j - 1 >= 0) {
					AdjList.get(cToK(numCols, i, j)).add(new IntegerPair(cToK(numCols, i, j - 1), Math.max(0, matrix[i][j - 1] - matrix[i][j])));
					AdjList.get(cToK(numCols, i, j - 1)).add(new IntegerPair(cToK(numCols, i, j), Math.max(0, matrix[i][j] - matrix[i][j - 1])));
				} 
			}
		}
	/*
		for (int i = 0; i < E; i++) { // store graph information in Adjacency List
			// we decrease index by 1 to change input to 0-based indexing
			int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
			AdjList.get(u).add(new IntegerPair(v, w)); // bi-directional
			AdjList.get(v).add(new IntegerPair(u, w));
		}
*/
		taken = new ArrayList < Boolean >(); 
		taken.addAll(Collections.nCopies(v, false));
		pq = new PriorityQueue < IntegerPair > ();
		// take any vertex of the graph, for simplicity, vertex 0, to be included in the MST
		process(0);
		int mst_cost = 0;
		int ladder = 0;


		while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
			IntegerPair front = pq.poll();

			if (!taken.get(front.second())) { // we have not connected this vertex yet
				mst_cost += front.first(); // add the weight of this edge
				if (front.first() > ladder) {
					ladder = front.first();
				}
			//	System.out.println("Adding edge: (" + front.first() + ", " + front.second() + "), MST cost now = " + mst_cost);
				if(front.second() == v - 1) {
					break;	
				}
			
				process(front.second());
			}
		//	else  // this vertex has been connected before via some other tree branch
			//	System.out.println("Ignoring edge: (" + front.first() + ", " + front.second() + "), MST cost now = " + mst_cost);
		}

		output.println(ladder);
		output.close();
		try {
			input.close();
		} catch (Exception e) {

		}
	}

	public static int cToK(int numCols, int row, int col) {
		return (numCols * row) + col;
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	public Integer _first, _second;

	public IntegerPair(Integer f, Integer s) {
		_first = f;
		_second = s;
	}

	public int compareTo(IntegerPair o) {
		if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else
			return this.second() - o.second();
	}

	Integer first() { return _first; }

	Integer second() { return _second; }
}
