import java.util.*;
import java.io.*;


public class Kattis {

	static Comparator<Quest> qCompare = new Comparator<Quest>() {
		@Override
		public int compare(final Quest q1, final Quest q2) {
			int result = Integer.valueOf(q1.energy).compareTo(q2.energy);
			int result2 = Long.valueOf(q1.gold).compareTo(q2.gold);
			return result == 0 ? (result2 == 0 ? Integer.valueOf(q1.id).compareTo(q2.id) : result2) : result;
		}
	}; 
	
	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		TreeMap<Quest, Integer> M = new TreeMap<Quest, Integer>(qCompare); 

		int n = 0;
	        try {
			n = Integer.parseInt(input.readLine()); 
		} catch (Exception e) {

		}

		String[] command = new String[3];
		
		for (int i = 0; i < n; i++) {

			try {
				command = input.readLine().split(" ");
			} catch (Exception e) {
				System.out.println("salah bro");
			}

			if (command[0].equals("add")) {
				add(M, Integer.parseInt(command[1]), Long.parseLong(command[2]));
			} else {
				output.println(query(M, Integer.valueOf(command[1])));
			}

		}
		
		output.close();
		try {
			input.close();
		} catch(Exception e) {

		}

		// System.out.println(Arrays.toString(command));
	}

	public static void add(TreeMap<Quest, Integer> M, Integer energy, long gold) {
		Quest newQuest = new Quest(energy, gold);
		M.put(newQuest, 1);
	}

	public static long query(TreeMap<Quest, Integer> M, int x) {
		long result = 0;
		
		while (x > 0) {
			if (M.size() == 0) {
				break;
			}

			Quest cur = M.floorKey(new Quest(x, Long.MAX_VALUE));
			
			if (cur != null) {
				x -= cur.energy;
				result += cur.gold;
				M.remove(cur);
			} else {
				break;
			}
		}

		return result;
	}









}
