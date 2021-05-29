
import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable, E extends Comparable> {
	/*
	 * Do not touch the code inside the upcoming block If anything tempered your
	 * marks will be directly cut to zero
	 */

	/*
	 * end code start writing your code from here
	 */

	// write your code here

	public Node root; // root of BST

	// create a node
	public class Node {
		public T key;
		public E value;
		Node left, right;

		Node(T key, E value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}

	// insert node
	@SuppressWarnings("unchecked")
	public void insert(T key, E value) {
		// write your code here
		Node node = new Node(key, value);
		if (root == null)
			root = node;
		else {
			Node n1 = root;
			Node x = null;

			while (n1 != null) {
				x = n1;
				if (value.compareTo(n1.value) < 0)
					n1 = n1.left;
				else
					n1 = n1.right;
			}

			if (x == null)
				x = node;
			else if (value.compareTo(x.value) < 0)
				x.left = node;
			else
				x.right = node;

		}

	}

	// update value of node
	public void update(T key, E value) {
		// write your code here
		delete(key);
		insert(key, value);

	}

	// delete given node
	public void delete(T key) {

		E val = find(key).value;
		// delete node and arrange to complete BST

		delete1(val);

	}

	@SuppressWarnings("unchecked")
	public Node delete1(E val) {
		Node par = null;
		Node curr = root;
		while (curr != null && curr.value != val) {
			par = curr;
			if (val.compareTo(curr.value) < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
		if (curr == null)
			return root;
		// case 1
		if (curr.left == null && curr.right == null) {
			if (curr != root) {
				if (par.left == curr)
					par.left = null;
				else
					par.right = null;
			} else
				root = null;

		}
		// case2
		else if (curr.left != null && curr.right != null) {
			Node n1 = curr.right;

			while (n1.left != null) {
				n1 = n1.left;
			}
			T k = n1.key;
			E v = n1.value;
			delete1(v);
			curr.key = k;
			curr.value = v;
		}

		// case3
		else {
			Node ch;
			if (curr.left != null)
				ch = curr.left;
			else
				ch = curr.right;

			if (curr != root) {
				if (par.left == curr)
					par.left = ch;
				else
					par.right = ch;

			} else
				root = ch;

		}
		return root;

	}

	public Node find(T key) {// first find node with given key

		Node node = root;

		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.key.equals(key)) {
				node = temp;
				break;
			}
			if (temp.left != null)
				q.add(temp.left);
			if (temp.right != null)
				q.add(temp.right);
		}
		return node;

	}

	// print tree
	public void printBST() {
		// write your code here
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while (!q.isEmpty()) {
			Node temp = q.poll();
			System.out.println(temp.key + ", " + temp.value);
			if (temp.left != null)
				q.add(temp.left);
			if (temp.right != null)
				q.add(temp.right);
		}

	}

	public static void main(String[] args) {
		BST<Integer, Integer> b = new BST<Integer, Integer>();
		b.printBST();

	}

}
