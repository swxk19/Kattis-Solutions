import java.util.*;

public class Teque {

	int maxSize = 1;
	ArrayList<Integer> frontArr = new ArrayList<Integer>(maxSize);
	int frontArrSize = 0;
	int frontArrFirst = 0;
	int frontArrLast = 0;

	ArrayList<Integer> backArr = new ArrayList<Integer>(maxSize);
	int backArrSize = 0;
	int backArrFirst = 0;
	int backArrLast = 0;

	public Teque() {
		for (int i = 0; i < maxSize; i++) {
			frontArr.add(0);
			backArr.add(0);
		} 
	}	

	public void push_front(int item) {
		while (frontArrSize + 1 >= maxSize || backArrSize + 1 >= maxSize) {
			expandArray();
		}
		if (frontArrSize <= backArrSize || frontArrSize == 1) {
			if (frontArrSize != 0) {
				frontArrFirst = (frontArrFirst + 1) % maxSize;
			}
			frontArr.set(frontArrFirst, item);
			frontArrSize += 1;
		} else {
			int transferVal = frontArr.get(frontArrLast);
			frontArr.set(frontArrLast, 0);
			frontArrLast = (frontArrLast + 1) % maxSize;
			backArrFirst = (backArrFirst - 1) % maxSize;
			if (backArrFirst < 0) {
				backArrFirst += maxSize;
			}
			backArr.set(backArrFirst, transferVal);
			
			backArrSize += 1;
			if (backArrSize == 1) {
				backArrLast = backArrFirst;
			}
			frontArrFirst = (frontArrFirst + 1) % maxSize;
			frontArr.set(frontArrFirst, item);
		}
	}

	public void push_middle(int item) {
		while (frontArrSize + 1 >= maxSize || backArrSize + 1 >= maxSize) {
			expandArray();
		}

		int insertIndex = (frontArrSize + backArrSize + 1) / 2;

		if (insertIndex < frontArrSize) {
			if (frontArrSize > backArrSize) {
				int transferVal = frontArr.get(frontArrLast);
				backArrFirst = (backArrFirst - 1) % maxSize;
				if (backArrFirst < 0) {
					backArrFirst += maxSize;
				}
	
				backArr.set(backArrFirst, transferVal);
				backArrSize += 1;

				frontArr.set(frontArrLast, item);

			} else {
				frontArrLast = (frontArrLast - 1) % maxSize;
				if (frontArrLast < 0) {
					frontArrLast += maxSize;
				}
				frontArr.set(frontArrLast, item);
			}

		} else {
			if (backArrSize > frontArrSize) {
				int transferVal = backArr.get(backArrFirst);
				frontArrLast = (frontArrLast - 1) % maxSize;
				if (frontArrLast < 0) {
					frontArrLast += maxSize;
				} 
				if (frontArrSize == 0) {
					frontArrFirst = frontArrLast;
				}
				//System.out.println("TransferVal");
				//System.out.println(transferVal);
				frontArr.set(frontArrLast, transferVal);
				frontArrSize += 1;

				backArr.set(backArrFirst, item);
			} else {
				backArrFirst = (backArrFirst - 1) % maxSize;
				if (backArrFirst < 0) {
					backArrFirst += maxSize;
				}
				backArr.set(backArrFirst, item);
				backArrSize += 1;
			}
		}
		
	}

	public void push_back(int item) {
		while (backArrSize + 1 >= maxSize || frontArrSize + 1 >= maxSize) {
			expandArray();
		}

		if (backArrSize <= frontArrSize || backArrSize == 1) {
			if (backArrSize != 0) {
				backArrLast = (backArrLast + 1) % maxSize;
			}
			backArr.set(backArrLast, item);
			backArrSize += 1;
		} else {
			int transferVal = backArr.get(backArrFirst);
			backArr.set(backArrFirst, 0);
			backArrFirst = (backArrFirst + 1) % maxSize;
			frontArrLast = (frontArrLast - 1) % maxSize;
			if (frontArrLast < 0) {
				frontArrLast += maxSize;
			}
			frontArr.set(frontArrLast, transferVal);
			
			frontArrSize += 1;
			if (frontArrSize == 1) {
				frontArrFirst = frontArrLast;
			}
			backArrLast = (backArrLast + 1) % maxSize;
			backArr.set(backArrLast, item);
		}
	}

	public int get(int index) {
		if (index < frontArrSize) {
			int frontIndex = (frontArrFirst - index) % maxSize;
			if (frontIndex < 0) {
				frontIndex += maxSize;
			}
			//System.out.println("FrontSize");
			//System.out.println(frontArrSize);
			return frontArr.get(frontIndex);
		} else {
			int backIndex = index - frontArrSize;
			return backArr.get((backArrFirst + backIndex) % maxSize);
		}
	}

	public void expandArray() {
		int newMaxSize = maxSize * 2;
		ArrayList<Integer> newEmptyFront = new ArrayList<Integer>(newMaxSize);
		ArrayList<Integer> newEmptyBack = new ArrayList<Integer>(newMaxSize);
		
		if (frontArrSize > 1) {
			for (int i = frontArrLast; i != frontArrFirst; i = (i + 1) % maxSize) {
				int item = frontArr.get(i);
				if (item != 0) { 
					newEmptyFront.add(item);
				}
			}
			newEmptyFront.add(frontArr.get(frontArrFirst));
		} else {
			if (frontArrSize == 1) {
				if (frontArr.get(frontArrLast) == 0) { 
					newEmptyFront.add(frontArr.get(frontArrFirst));
				} else {
					newEmptyFront.add(frontArr.get(frontArrLast));
				}
			}
		}
		
		frontArrFirst = frontArrSize - 1;
		if (frontArrFirst < 0) {
			frontArrFirst += 1;
		}
		frontArrLast = 0;
		frontArr = newEmptyFront;
		
		if (backArrSize > 1) {
			for (int i = backArrFirst; i != backArrLast; i = (i + 1) % maxSize) {
				int item = backArr.get(i);
				if (item != 0) { 
					newEmptyBack.add(backArr.get(i));
				}
			}
			newEmptyBack.add(backArr.get(backArrLast));
		} else {
			if (backArrSize == 1) {
				if (backArr.get(backArrFirst) == 0) {
					newEmptyBack.add(backArr.get(backArrLast));
				} else {
					newEmptyBack.add(backArr.get(backArrFirst));
				}
			}
		}
		
		backArrFirst = 0;
		backArrLast = backArrSize - 1;
		if (backArrLast < 0) {
			backArrLast += 1;
		}
		backArr = newEmptyBack;

		
		maxSize = newMaxSize;
		
		while (frontArr.size() < newMaxSize || backArr.size() < newMaxSize ) {
			if (frontArr.size() < newMaxSize) {
				frontArr.add(0);
			}
			if (backArr.size() < newMaxSize) {
				backArr.add(0);
			
			}
		}


	}

}
