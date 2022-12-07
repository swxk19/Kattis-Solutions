package Nicknames;

public class Node {

	Node parent = null;
	Node left = null;
	Node right = null;

	String key;
	int rank = 0;
	int size = 1;

	public Node(String s) {
		this.key = s;
	}

	public int compareTo(String s) {
		int loopVar = Math.min(key.length(), s.length());
		for (int i = 0; i < loopVar; i++) {
			int keyCur = (int) key.charAt(i);
			int sCur = (int) s.charAt(i);
			if (keyCur < sCur) {
				return -1;
			} 
		        if (keyCur > sCur) {
				return 1;
			}
		}

		return key.length() < s.length() ? -1 : (key.length() > s.length() ? 1 : 0);
	}

}
