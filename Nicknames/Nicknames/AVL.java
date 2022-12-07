package Nicknames;

public class AVL {
	Node root;

	public AVL() {
		this.root = null;
	}
	
	public void insert(String s) {
		this.root = insert(this.root ,s);
	}

	public Node insert(Node T, String s) {
		if (T == null) {
			return new Node(s);
		}

		if (T.compareTo(s) >= 0) {
			T.left = insert(T.left, s);
			T.left.parent = T;
		} else {
			T.right = insert(T.right, s);
			T.right.parent = T;
		}

		T.rank = rank(T);
		T.size = size(T);
		return rotationCase(T);

	}


	public int search(Node T, String name) {
		if (T == null) {
			return 0;
		} else if (T.key.startsWith(name)) {
			return 1 + search(T.left, name) + search(T.right, name);
		} else if (T.key.compareTo(name) < 0) {
			return search(T.right, name);
		} else {
			return search(T.left, name);
		}
	}
/*
	public int order(Node T, Node cur) {
		if (cur == null) {
			return -999999;
		}
		if (T.compareTo(cur.key) == 0) {
			return 1 + size(T.left);
		} else if (T.compareTo(cur.key) == -1) {
			return order(T, cur.left);	
		} else {
			return 1 + size(cur.left) + order(T, cur.right);
		}
	}

	public Node findBase(Node T, String name) { 
		if (T == null) {
			return T;
		}
		if (T.key.indexOf(name) == 0) {
			return T;
		}
		if (T.key.compareTo(name) == 1) {
			return findBase(T.left, name);
		} else {
			return findBase(T.right, name);
		}
	}

	public int findLower(Node T, String name) {
		if (T == null) {
			return 0;
		}
		if (T.key.indexOf(name) == 0) {
			return 1 + findLower(T.left, name) + size(T.right);
		} else {
			return findLower(T.right, name);
		}
	}

	public int findUpper(Node T, String name) {
		if (T == null) {
			return 0;
		}
		if (T.key.indexOf(name) == 0) {
			return 1 + findUpper(T.right, name) + size(T.left);
		} else {
			return findUpper(T.left, name);
		}
	}
	
	public int findResult(Node T, String name) {
		Node base = findBase(T ,name);
		if (base == null) {
			return 0;
		} 

		return findLower(base, name) + findUpper(base, name) + 1;

	}
*/
/*
	public Node findLowerBound(Node T, Node prev, String name) {
		if (T == null) {
			return prev;
		} 
		
		if (T.key.indexOf(name) == 0) {
			return findLowerBound(T.left, T, name);
		} else {
			return findLowerBound(T.right, T, name);
		}
	} 

	public Node findUpperBound(Node T, Node prev, String name) {
		if (T == null) {
			return prev;
		} 

		if (T.key.indexOf(name) == 0) {
			return findUpperBound(T.right, T, name);
		} else {
			return findUpperBound(T.left, T, name);
		}
	}

	public int findResult(Node T, String name) {
		Node base = findBase(T, name);
		if (base == null) {
			return 0;
		}
		Node upper = findUpperBound(base, null, name);
		Node lower = findLowerBound(base, null, name);
*/
	/*	
		System.out.println(upper.key);
		System.out.println(lower.key);
		System.out.println(order(upper,T));
		System.out.println(order(lower, T));
	*/ 
	/*
		int result = order(upper, T) - order(lower, T);
		if (lower.key.indexOf(name) == 0) {
			result += 1;
		}
		if (upper.key.indexOf(name) != 0) {
			result -= 1;
		}
		return result;
		
	} 
	*/

/*
	public int findResult(Node T, String name) {
		int result = 0;

		
	}
*/
/*
	public int findResult(Node T, String name) {
		if (T == null) {
			return 0;
		}
		if (T.key.indexOf(name) == 0) {
			return  1 + findResult(T.right, name) + size(T.left);
		} else {
			return findResult(T.left, name) + findResult(T.right, name);
		}
	} 
*/	

/*
	public Node lowerBound(Node T, String name) {
		if (T == null) {
			return T;
		}
		int tCompN = T.compareTo(name);

		Node cur = T;

		if (tCompN == -1) {
			while (cur.compareTo(name) == -1) {
				if (cur.right == null) {
					return null;
				}
				cur = cur.right;
			}
		} else {
			while (cur.compareTo(name) >= 0) {
				if (cur.left == null) {
					return cur;
				} else if (cur.left.compareTo(name) == -1) {
					return cur;
				}
				cur = cur.left;
			}
		}
		return cur;

	}

	public Node upperBound(Node T, String name, String upper) {
		if (T == null) {
			return T;
		}

		int tCompN = T.compareTo(name);
		int tCompU = T.compareTo(upper);

		Node cur = T;
		System.out.println("upper: " + upper);
		System.out.println("tcompn: " + tCompN);
		System.out.println("tCompU: " + tCompU);
		if (tCompN >= 0 && tCompN == -1) {
			System.out.println("here1");
			while (cur.compareTo(upper) == -1) {
				if (cur.right == null) {
					return cur;
				} else if (cur.right.compareTo(upper) >= 0) {
					return cur;
				}
				cur = cur.right;
			}
		} else if (tCompN == -1) {
			System.out.println("here2");
			while (cur.compareTo(name) == -1) {
				if (cur.right == null) {
					return null;
				}	
				cur = cur.right;
			}
		} else {
			System.out.println("here3");
			while(cur.compareTo(upper) != -1) {
				if (cur.left == null) {
					return null;
				}
				cur = cur.left;
			}
		}

		return cur;	
	}	
*/
	public int size(Node T) {
		if (T == null) {
			return 0;
		}
		int leftSize = T.left == null ? 0 : T.left.size;
		int rightSize = T.right == null ? 0 : T.right.size;
		return leftSize + rightSize + 1;
	}

	public int rank(Node T) {
		if (T == null) {
			return -1;
		}
		int leftRank = T.left == null ? -1 : T.left.rank;
		int rightRank = T.right == null ? -1 : T.right.rank;
		return Math.max(leftRank, rightRank) + 1;
	}

	public int bf(Node T) {
		if (T == null) {
			return 0;
		} 
		int leftRank = T.left == null ? -1 : T.left.rank;
		int rightRank = T.right == null ? -1 : T.right.rank;
		return leftRank - rightRank;
	}

	public Node rotationCase(Node T) {
		if (bf(T) == -2) {
			if (bf(T.right) == 1) {
				T.right = rotateR(T.right);
			}
			T = rotateL(T);
		} else if (bf(T) == 2) {
			if (bf(T.left) == -1) {
				T.left = rotateL(T.left);
			}
			T = rotateR(T);
		}
		return T;
	}

	public Node rotateL(Node T) {
		if (T.right != null) {
			Node r = T.right;
			T.right = r.left;
			
			if (r.left != null) {
				r.left.parent = T;
			}
			
			r.parent = T.parent;
			if (T.parent != null) {
				if (T.parent.left == T) {
					T.parent.left = r;
				}
				if (T.parent.right == T) {
					T.parent.right = r;
				}
			} else {
				this.root = r;
			}
			r.left = T;
			T.parent = r;
		
			r.size = T.size;
			T.size = size(T);
			T.rank = rank(T);
			r.rank = rank(r);
			return r;
		}
	
		return T;
	}

	public Node rotateR(Node T) {
		if (T.left != null) {
			Node l = T.left;
			T.left = l.right;

			if (l.right != null) {
				l.right.parent = T;
			}

			l.parent = T.parent;
			if (T.parent != null) {
				if (T.parent.left == T) {
					T.parent.left = l;
				}
			        if (T.parent.right == T) {
					T.parent.right = l;
				}	
			} else {
				this.root = l;
			}
			l.right = T;
			T.parent = l;

			l.size = T.size;
			T.size = size(T);
			T.rank = rank(T);
			l.rank = rank(l);
			return l;
		}
		return T;
	}
}
