package AlmostUnionFind;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class almost {
	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		for (int u = 0; u < 20; u++) {
			int numInt = 0;
			int numCmd = 0;
			String[] initInput = new String[2];

			try {
				initInput = input.readLine().split(" ");
				numInt = Integer.parseInt(initInput[0]);
				numCmd = Integer.parseInt(initInput[1]);

			} catch (Exception e) {
				break;
			}

			if (numInt != 0) {
				
				Node[] reps = new Node[numInt + 1]; // array keys represent the elements, their values are the set they belong to.
				
				for (int i = 1; i < numInt + 1; i++) {	
					reps[i] = new Node(i);
				}
				
				for (int x = 0; x < numCmd; x++) {
					String[] cmdInput = new String[3];
					try {
						cmdInput = input.readLine().split(" ");
					} catch (Exception e) {

					}

					int[] cmd = new int[3];
					for (int i = 0; i < cmdInput.length; i++) {
						cmd[i] = Integer.parseInt(cmdInput[i]);
					}		

				
			
					if (cmd[0] == 1) {
						Node rep1 = reps[cmd[1]];
						Node rep2 = reps[cmd[2]];
						if (rep1 != rep2) {
							rep1.cmd1(rep2);
						}
	
					} else if (cmd[0] == 2) {
						Node rep1 = reps[cmd[1]];
						Node rep2 = reps[cmd[2]];
						if (rep1 != rep2) {
							rep1.cmd2(rep2, cmd[1]);
							reps[cmd[1]] = rep2;
						}	

					} else {	// cmd 3
						output.println(reps[cmd[1]].cmd3());
					}
				
				}
			}
		}
//		System.out.println("######################### ");
		output.close();
		try {
			input.close();
		} catch (Exception e) {

		}
	}
}
