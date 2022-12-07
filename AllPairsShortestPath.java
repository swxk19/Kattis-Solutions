import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AllPairsShortestPath {

	public static double INF = Double.POSITIVE_INFINITY;

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int n = -1;	// num nodes
		int m = -1;	// num edges
		int q = -1;	// num queries

		while (n != 0) {
			String[] tempInit = new String[3];

			try {	
				tempInit = input.readLine().split(" ");
			} catch (Exception e) {

			}

			n = Integer.parseInt(tempInit[0]);
			m = Integer.parseInt(tempInit[1]);
			q = Integer.parseInt(tempInit[2]);

			double[][] adjMat = new double[n][n];	
			
			for (int i = 0; i < n; i++) {		// init adjMat with no edges
				for (int j = 0; j < n; j++) {
					if (i == j) {
						adjMat[i][j] = 0;
					} else { 
						adjMat[i][j] = INF;
					}
				}
			}

			for (int i = 0; i < m; i++) {
				int u = -1; 	
				int v = -1; 
				double w = INF;
				String[] tempEdge = new String[3];	

				try {
					tempEdge = input.readLine().split(" ");
				} catch (Exception e) {

				}

				u = Integer.parseInt(tempEdge[0]);
				v = Integer.parseInt(tempEdge[1]);
				w = Integer.parseInt(tempEdge[2]);
				adjMat[u][v] = Math.min(adjMat[u][v], w);
			}



			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
					}
				}
			}

			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (adjMat[i][k] + adjMat[k][j] < adjMat[i][j]) {
							adjMat[i][j] = Double.NEGATIVE_INFINITY;
						}
					}
				}
			}


			for (int i = 0; i < q; i++) {
				int u = -1;
				int v = -1;
				String[] tempNode = new String[2];
				try {
					tempNode = input.readLine().split(" ");
				} catch (Exception e) {

				}
				u = Integer.parseInt(tempNode[0]);
				v = Integer.parseInt(tempNode[1]);
				double weight = adjMat[u][v];
				if (weight == Double.NEGATIVE_INFINITY) {
					output.println("-Infinity");
				} else if (weight == INF){
				       output.println("Impossible");	
				} else {
					output.println((int) weight);
				}
			}
			output.println("");
		}
		output.close();
		try {
			input.close();
		} catch (Exception e) {

		}
	}


}
