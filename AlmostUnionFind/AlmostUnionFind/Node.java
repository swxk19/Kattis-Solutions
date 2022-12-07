package AlmostUnionFind;

public class Node {

	Node parent = null;
	int size = 1;
	long sum;
	int height = 0;

	public Node(int i) {
		this.sum = i;
	}

	public Node findParent() {
		if (this.parent == null) {
			return this;
		} else {
			this.parent = (this.parent).findParent();
			return this.parent;
		}
	}

	public void cmd1(Node j) {
		
		Node parentI = this.findParent();
		Node parentJ = j.findParent();

		// merging the representatives of the 2 sets
		if (parentI != parentJ) {
			if (parentI.height < parentJ.height) {
				parentI.parent = parentJ;
				parentJ.sum += parentI.sum;
				parentJ.size += parentI.size;
			} else if (this.height > j.height) {
				parentJ.parent = parentI;
				parentI.sum += parentJ.sum;
				parentI.size += parentJ.size;
			} else {  // equal height
				parentJ.parent = parentI;
				parentI.sum += parentJ.sum;
				parentI.size += parentJ.size;
				parentI.height++;
			}		
		}

	}

	public void cmd2(Node j, long sumX) {
		Node parentI = this.findParent();
		Node parentJ = j.findParent();

		if (parentI != parentJ) {
			// actual shifting of the element was done in main function
			// here i only deal with their fields
			// because the array is located in main
			parentI.size--;
			parentI.sum -= sumX;
			parentJ.size++;
			parentJ.sum += sumX;
		}
	}

	public String cmd3() {
		Node parentI = this.findParent();
		return (parentI.size + " " + parentI.sum);
	}

}
