import java.util.*;
import java.io.*;

public class Game {

	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] stringInput = {"0", "0"};
		try {
			stringInput = input.readLine().split(" ");
		} catch (Exception e) {

		}
		
		int numSyllables = Integer.parseInt(stringInput[0]);
		int numPlayers = Integer.parseInt(stringInput[1]);

		Deque<Hand> hands = new LinkedList<>();
		
		for (int i = 0; i < numPlayers; i++) {
			Coconut curCoco = new Coconut(i + 1);
			hands.add(curCoco);	
		}

		
		while (hands.size() > 1) {
			iterateSyllables(numSyllables, hands);
			hands.removeFirst().splat(hands);
			// System.out.print("Size: ");
			// System.out.println(hands.size());
		
		}

		output.println(hands.removeFirst().getId());
		output.close();
	}

	public static void iterateSyllables(int s, Deque<Hand> deque) {
		if (s == 1) {
			// System.out.println("Splat");
			// System.out.println(deque.peek().getId());
		} else {
			// System.out.println(deque.peek());
			// System.out.println(deque.peek().getId());
			deque.add(deque.removeFirst());
			iterateSyllables(s - 1, deque);
		}
	}

}
