import java.util.*;
import java.io.*;

/*
 * Problem: doing DFS normally starts from vertice 1, 2, ... n
 * but by knocking dominos in numerical order, you would miss knocking down parents,
 * e.g: if you knock down domino 1 first, domino 1 might have a parent, say domino 12, that you could have knocked instead.
 *
 * The minimum number of pushes to knock down all is when you hit the dominoes at the top of the "hierarchy"
 *
 * Hence, need to DFS the dominoes in an order such that all top dominoes come first, i.e topo order
 *
 */ 

public class Dominos {


	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int numCases = 0;

		try {
			numCases = Integer.parseInt(input.readLine());
		} catch (Exception e) {

		}

		for (int n = 0; n < numCases; n++) {
			int numDom = 0;
			int numEdge = 0;
			
			String[] tempInput = new String[2];
			try {
				tempInput = input.readLine().split(" ");
			} catch (Exception e) {

			}

			numDom = Integer.parseInt(tempInput[0]);
			numEdge = Integer.parseInt(tempInput[1]);

		
			ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

			for (int i = 0; i < numDom; i++) {
				adjList.add(new ArrayList<Integer>());
				adjList.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < numEdge; i++) {
	
				String[] tempDom = new String[2];
				try {
					tempDom = input.readLine().split(" ");
				} catch (Exception e) {

				}

				int parent = Integer.parseInt(tempDom[0]) - 1; // -1 to convert to 0-indexing
				int child = Integer.parseInt(tempDom[1]) - 1;
				
				adjList.get(parent).add(child);
			}

			Stack<Integer> topoOrder = new Stack<>(); 	// stack, because of the nature of DFS, the "most dependent" domino is visited first.
									// and the parent is added only finally at the end when the recursive stack is depleted
									// most dependent domino should come last, i.e FILO	
			int[] visited = new int[numDom];
			
			for (int i = 0; i < numDom; i++) { 		// first DFS in numerical order, to get topo order
				if (visited[i] == 0) {
					dfs(visited, topoOrder, adjList, i);
					topoOrder.push(i);
				}
			}
	
			int[] visited2 = new int[numDom];			
			
			int count = 0;					// count number of times had to DFS
			while(!topoOrder.empty()) {			// dfs again but instead of numerical order, we do dfs in topo order
				int curNode = topoOrder.pop();	
				if (visited2[curNode] == 0) {
					count++;	
					dfs(visited2, adjList, curNode);
				}
			}
			output.println(count);
		

		}	
		output.close();
		try {
			input.close();
		} catch (Exception e) {

		}


	}
	public static void dfs(int[] visited, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adjList, int i) {
		visited[i] = 1;
		ArrayList<Integer> curAdj = adjList.get(i);
		for (int n = 0; n < curAdj.size(); n++) {
			int adjNode = curAdj.get(n);
			if (visited[adjNode] == 0) {
				dfs(visited, stack, adjList, adjNode);
			}
		}
	}

	public static void dfs(int[] visited, ArrayList<ArrayList<Integer>> adjList, int i) {
		visited[i] = 1;
		ArrayList<Integer> curAdj = adjList.get(i);
		for (int n = 0; n < curAdj.size(); n++) {
			int adjNode = curAdj.get(n);
			if (visited[adjNode] == 0) {
				dfs(visited, adjList, adjNode);
			}
		}
	}


}














