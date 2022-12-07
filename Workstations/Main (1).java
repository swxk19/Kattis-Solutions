import java.util.*; import java.io.*;

public class Main {
	
	static Comparator<User> uCompare = new Comparator<User>() {
		@Override
		public int compare(final User u1, final User u2) {
			return Integer.valueOf(u1.arrivalTime).compareTo(u2.arrivalTime);
		}
	};

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		String[] tempInput = new String[2];

		try {
			tempInput = input.readLine().split(" ");
		} catch (Exception e) {

		}

		int numUsers = Integer.parseInt(tempInput[0]);
		int waitTime = Integer.parseInt(tempInput[1]);
		
		User[] userArray = new User[numUsers];

		for (int i = 0; i < numUsers; i++) {
			String[] tempInput2 = new String[2];

			try {
				tempInput2 = input.readLine().split(" ");	
			} catch (Exception e) {

			}

			userArray[i] = new User(Integer.parseInt(tempInput2[0]), Integer.parseInt(tempInput2[1]));

		}

		Arrays.sort(userArray, uCompare);
		// output.println(Arrays.toString(userArray));
				
		try {
			input.close();
		} catch (Exception e) {

		}

		PriorityQueue<Integer> inUse = new PriorityQueue<Integer>();
		PriorityQueue<Integer> notUsedUnlocked = new PriorityQueue<Integer>();

		int result = 0;
		for (int i = 0; i < numUsers; i++) {
			User curUser = userArray[i];
			
			while (inUse.peek() != null && inUse.peek() <= curUser.arrivalTime) {
				notUsedUnlocked.add(inUse.poll() + waitTime);
			}

			
			while (notUsedUnlocked.peek() != null && notUsedUnlocked.peek() < curUser.arrivalTime) {
				notUsedUnlocked.poll();
			}

			
			if (notUsedUnlocked.size() != 0) {
				notUsedUnlocked.poll();
				result += 1;
			}

			inUse.add(curUser.arrivalTime + curUser.stayTime);
		}
		output.println(result);
		output.close();
	}
}
