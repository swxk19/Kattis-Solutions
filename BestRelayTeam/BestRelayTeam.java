import java.io.*;
import java.util.*;

public class BestRelayTeam {
	
	static Comparator<Runner> otherLapComparator = new Comparator<Runner>() {
		@Override
		public int compare(final Runner r1, final Runner r2) {
			return Double.valueOf(r1.otherLap).compareTo(Double.valueOf(r2.otherLap));
		}
	};


	public static void main(String args[]) {

		BufferedReader takeInput = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int numRunners;

		try {
			numRunners = Integer.parseInt(takeInput.readLine());
		} catch (Exception e) {
			numRunners = 0;
			output.println("1");
		}

		Runner[] runnerArray = new Runner[numRunners]; 

		for (int i = 0; i < numRunners; i++) {
			String runnerInput;
			try {
				runnerInput = takeInput.readLine();
			} catch (Exception e) {
				runnerInput = "";
				output.println("2");
			}	
			String[] inputParts = runnerInput.split(" "); 
			runnerArray[i] = new Runner(inputParts[0], Double.parseDouble(inputParts[1]), Double.parseDouble(inputParts[2]));
		}

		try {
			takeInput.close();
		} catch (Exception e) {
			output.println("gg");
		}
		
	
		Runner[] fantasticFour = getFantasticFour(runnerArray, numRunners);
		output.println(getRaceTime(fantasticFour));
		for (int i = 0; i < 4; i++) {
			Runner currentRunner = fantasticFour[i];
			output.println(currentRunner.name);
		}
		output.close();
	}
 

	public static Runner[] getFantasticFour(Runner[] runnerArray, int numRunners) {
		Runner[] fantasticFour = new Runner[4];
		double timeToBeat = Double.POSITIVE_INFINITY;


		for (int i = 0; i < numRunners; i++) {
			Runner[] runnerArrayCopy = Arrays.copyOf(runnerArray, numRunners); 
			Runner firstRunner = runnerArray[i];
			runnerArrayCopy[i] = new Runner("TAKEN OUT", Double.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
			
			Arrays.sort(runnerArrayCopy, otherLapComparator);
			
			Runner[] currentRaceTeam = new Runner[4];
			currentRaceTeam[0] = firstRunner;
			for (int j = 1; j < 4; j++) {
				currentRaceTeam[j] = runnerArrayCopy[j - 1];
			}
	
			double currentRaceTime = getRaceTime(currentRaceTeam);
			if (currentRaceTime < timeToBeat) {
				fantasticFour = Arrays.copyOf(currentRaceTeam, 4);
				timeToBeat = currentRaceTime;
			}
		}
		return fantasticFour;
	}
	
	public static double getRaceTime(Runner[] fantasticFour) {
		double resultTime = 0;
		for (int i = 0; i < 4; i++) {
			Runner currentRunner = fantasticFour[i];
			if (i == 0) {
				resultTime += currentRunner.firstLap;
			} else {
				resultTime += currentRunner.otherLap;
			}
		}

		return resultTime;
	} 
}
