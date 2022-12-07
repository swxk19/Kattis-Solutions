import java.io.*;
import java.util.*;

public class Greedy {

	static Comparator<Card> comboComparator = new Comparator<Card>() {
		@Override
		public int compare(final Card c1, final Card c2) {
			return Long.valueOf(c1.profitToCombo).compareTo(Long.valueOf(c2.profitToCombo));
		}
	};

	public static void main(String args[]) {
		BufferedReader takeInput = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		String[] firstLineInput = {"0", "0", "0"};

		try {
			firstLineInput = takeInput.readLine().split(" ");
		} catch (Exception e) {
			
		}

		int numCardsAnthony = Integer.parseInt(firstLineInput[0]);
		int numTypes = Integer.parseInt(firstLineInput[1]);
		int numDesiredCombos = Integer.parseInt(firstLineInput[2]);

		String[] secondLineInput = new String[numCardsAnthony];

		try {
			secondLineInput = (takeInput.readLine()).split(" ");
		} catch (Exception e) {

		}
		
		int[] AnthonyCards = new int[numCardsAnthony];
		
		for (int antCard = 0; antCard < numCardsAnthony; antCard++) {
			AnthonyCards[antCard] = Integer.parseInt(secondLineInput[antCard]);
		}

		Card[] cardArray = new Card[numTypes];

		for (int cardNum = 0; cardNum < numTypes; cardNum++) {
			String[] cardInfo = {"-1,", "-1"};
			try {
				cardInfo = (takeInput.readLine()).split(" ");
			} catch (Exception e) {

			}
			cardArray[cardNum] = new Card(cardNum + 1, Long.parseLong(cardInfo[0]), Long.parseLong(cardInfo[1]));
		}

		for (int cardOfAnt = 0; cardOfAnt < numCardsAnthony; cardOfAnt++) {
			//System.out.println(AnthonyCards[cardOfAnt]);
			//System.out.println(cardArray[AnthonyCards[cardOfAnt]]);
			cardArray[AnthonyCards[cardOfAnt] - 1].count++;
		}

		for (int iterTypes = 0; iterTypes < numTypes; iterTypes++) {
			cardArray[iterTypes].evalCostToCombo();
		}

		Arrays.sort(cardArray, comboComparator);
		
		//System.out.println(Arrays.toString(cardArray));

		
		int arrayIndex = 0;
		long profit = 0;
		int currentCombos = 0;
		
		//System.out.println(Arrays.toString(cardArray));		
		while (arrayIndex < cardArray.length) {
			if (currentCombos < numDesiredCombos) {
				profit -= cardArray[arrayIndex].buyToCombo;  			
				currentCombos++;
			} else {
				//System.out.println(profit);
				profit += cardArray[arrayIndex].sellAll;
			}

			arrayIndex++;
		}
		
		try {
			takeInput.close();
		} catch (Exception e) {

		}
		
		output.println(profit);
		output.close();
	}

	public static int countCombos(Card[] cardArray) {
		int count = 0;
		for (int i = 0; i < cardArray.length; i++) {
			if (cardArray[i].count >= 2) {
				count++;
			}
		}
		return count;
	}

	public static Card[] removeAll(int type, Card[] cardArray) {
		LinkedList<Card> cardList = new LinkedList<Card>();

		for (int i = 0; i < cardArray.length; i++) {
			if (cardArray[i].type != type) {
				cardList.add(cardArray[i]);
			}
		}

		return cardList.toArray(new Card[cardList.size()]);
	}
}
