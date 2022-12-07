import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int numNames = 0;
		try {
			numNames = Integer.parseInt(input.readLine());
		} catch (Exception e) {
		
		}

		HashMap<Character, AVL> avlMap = new HashMap<>();

		
		for (int i = 0; i < numNames; i++) {
			String name = "";

			try {
				name = input.readLine();
			} catch (Exception e) {

			}
			char charN = name.charAt(0);
			if (avlMap.get(charN) == null) { 
				avlMap.put(charN, new AVL());
				avlMap.get(charN).insert(name);
			} else {
				avlMap.get(charN).insert(name);
			}	
			
		}

		int numNick = 0;

		try {
			numNick = Integer.parseInt(input.readLine());
		} catch (Exception e) {

		}

		HashMap<String, Integer> resultMap = new HashMap<>();

		for (int i = 0; i < numNick; i++) {
			String nick = "";

			try {
				nick = input.readLine();
			} catch (Exception e) {

			}
			
			char firstChar = nick.charAt(0);

			if (resultMap.get(nick) != null) {
				output.println(resultMap.get(nick));
			} else {

			// char lastChar = (char) (((int) nick.charAt(nick.length() - 1)) + 1);
			// String upperString = nick.substring(0, nick.length() - 1) + lastChar;
				
				AVL avl = avlMap.get(firstChar);
				int result;
				if (avl != null) { 
					result = (avlMap.get(firstChar).search(avl.root, nick));
				} else {
					result = 0;
				}
				
				resultMap.put(nick, result);
				
				output.println(result);
			}

		}

//		output.flush();

		/*
		String direction = "0";
		Node cur = avl.root;
		while (!direction.equals("-1")) {
			System.out.println(cur.key);
			System.out.println("Size: " + cur.size);
			try {
				direction = input.readLine();
			} catch (Exception e) {

			}
			
			if (direction.equals("1")) {
				System.out.println("left");
				cur = cur.left;
			} else if (direction.equals("2")) {
				System.out.println("right");
				cur = cur.right;
			} else {
				System.out.println("parent");
				cur = cur.parent;
			}
			
		}

*/

		output.close();
		try {
			input.close();
		} catch (Exception e) {

		}
	}
}
