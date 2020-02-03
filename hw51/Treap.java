package hw51;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>>  {
	private static class Node<E> {
		//Constructors
		public E data;
		public int priority; 
		public Node<E> left;
		public Node<E> right;
		/**
		 * Constructor setting the variables data, priority, right and left
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if(data == null) {
				throw new NullPointerException();
			} else {
				this.data = data;
				this.priority = priority;
				right = null;
				left = null; 
			}
		}
		/**
		 * Function that rotates the tree right
		 * @return Returns the rotated tree
		 */
		public Node<E> rotateRight() {
			Node<E> par = this;
			Node<E> temp = par.left;
			par.left = temp.right;
			temp.right = par;
			return par;
		}
		/**
		 * Function that rotates the tree left
		 * @return returns the rotated tree
		 */
		public Node<E> rotateLeft() {
			Node<E> par = this;
			Node<E> temp = par.right;
			par.right = temp.left;
			temp.left = par;
			return par;
		}
		/**
		 * returns the string of data for the Node class
		 */
		public String toString() {
			return "(key= " + data +", priority= " + priority + ")";
		}
		
	}
	//Constructors
	private Random priorityGenerator;
	private Node<E> root;
	
	/**
	 * sets the values for root and priorityGenerator
	 */
	public Treap() {
		this.root = null; 
		this.priorityGenerator = new Random();
	}
	/**
	 * sets the values for root and priorityGenerator when there is long seed
	 * @param seed
	 */
	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);				
	}
	/**
	 * Boolean function that adds an element to the tree using a randomly generated priority and a specified key
	 */
	public boolean add(E key) {
		if(key == null){
			return false;
		}
		if(find(key) == true){ 
			return false;
		} else {
			if(root == null){
				root = new Node<E>(key, priorityGenerator.nextInt());
				return true;
			}else{ 
				Node<E> cur = root;
				Stack<Node<E>> nodeS = new Stack<Node<E>>();
				while(cur != null){
					nodeS.push(cur);
					if(cur.data.compareTo(key) < 0){
						cur = cur.right;
					} else {
						cur = cur.left;
					}
				}
				cur = new Node<E>(key, priorityGenerator.nextInt());
				if(nodeS.peek().data.compareTo(key) > 0){
					nodeS.peek().left = cur;
				}else{
					nodeS.peek().right = cur;
				}
				
				while(!nodeS.empty() && nodeS.peek().priority < cur.priority){
					Node<E> par = nodeS.pop();
					if(par == root){
						if(root.left == cur){
							root.rotateRight();
							root = cur;
							return true;
						} else{
							root.rotateLeft();
							root = cur;
							return true;
						}
					}
					else if(cur == par.left){
						par.rotateRight();
						if(nodeS.peek().left == par){
							nodeS.peek().left = cur;
						} else{
							nodeS.peek().right = cur;
						}
					} else{
						par.rotateLeft();
						if(nodeS.peek().left == par){
							nodeS.peek().left = cur;
						} else{
							nodeS.peek().right = cur;
						}
					}
				}
			} return true;
		}
		
	}
	/**
	 * Adds a value to the tree using key and a specific integer priority
	 * @param key value
	 * @param priority value
	 * @return returns a boolean 
	 */
	public boolean add(E key, int priority){
		if(key == null){
			return false;
		}
		if(find(key) == true){ 
			return false;
		} else {
			if(root == null){
				root = new Node<E>(key, priority);
				return true;
			}else{ 
				Node<E> cur = root;
				Stack<Node<E>> nodeS = new Stack<Node<E>>();
				while(cur != null){
					nodeS.push(cur);
					if(cur.data.compareTo(key) < 0){
						cur = cur.right;
					} else {
						cur = cur.left;
					}
				}
				cur = new Node<E>(key, priority);
				if(nodeS.peek().data.compareTo(key) > 0){
					nodeS.peek().left = cur;
				}else{
					nodeS.peek().right = cur;
				}
				
				while(!nodeS.empty() && nodeS.peek().priority < cur.priority){
					Node<E> par = nodeS.pop();
					if(par == root){
						if(root.left == cur){
							root.rotateRight();
							root = cur;
							return true;
						} else{
							root.rotateLeft();
							root = cur;
							return true;
						}
					}
					else if(cur == par.left){
						par.rotateRight();
						if(nodeS.peek().left == par){
							nodeS.peek().left = cur;
						} else{
							nodeS.peek().right = cur;
						}
					} else{
						par.rotateLeft();
						if(nodeS.peek().left == par){
							nodeS.peek().left = cur;
						} else{
							nodeS.peek().right = cur;
						}
					}
				}
			} return true;
		}
		
	}
	/**
	 * function that gets the first element
	 * @return returns the first node root
	 */
	public E first() {
		return first(root);
	}
	/**
	 * runs through the nodes to get the first root
	 * @param n
	 * @return
	 */
	private E first(Node<E> n) {
		while(n.left != null) {
			n = n.left;
		}
		return n.data;
	}
	/**
	 * deletes a node that has a matching key value
	 * @param key
	 * @return
	 */
 	public boolean delete(E key) {
		if(key == null) {
			return false;
		}
		else if(!find(key) == true) {
			return false;
		}
		else{
			root = deleteH(root,key);
			return true;
		}
	}
 	/**
 	 * Helper function for delete that actually runs through the list and removes it.
 	 * @param node4
 	 * @param key
 	 * @return returns a boolean value
 	 */
	private Node<E> deleteH(Node <E> node4, E key) {
		if (node4 != null) {
            int comp = key.compareTo(node4.data);
            if (comp < 0) {
                node4.left = deleteH(node4.left, key);
            } else if (comp > 0) {
                node4.right = deleteH(node4.right, key);
            } else {
                if (node4.left == null) {
                    return node4.right;
                } else if (node4.right == null) {
                    return node4.left;
                } else {
                    node4.data = first(node4.right);
                    node4.right = deleteH(node4.right, node4.data);
                }
            }
        }
        return node4;
    }
	/**
	 * finds a root with a matching key
	 * @param key
	 * @return
	 */
	public boolean find(E key) {
		if(key == null) {
			return false;
		}
		if(root == null) {
			return false;
		} else {
			return find(root, key);
		}
	}
	/**
	 * helper function for find 
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node <E> root, E key) {
		if(root == null) {
			return false;
		}
		if(root.data == key) {
			return true;
		}
		else {
			return find(root.right, key) || find(root.left, key);
		}
	}
	/**
	 * toString method
	 */
	public String toString() {
		return toString(root, 0);
	}
	/**
	 * To string method that sets up the proper return on the toString
	 * @param node3
	 * @param height
	 * @return
	 */
	private String toString(Node<E> node3, int height) {
		StringBuilder string1 = new StringBuilder();
		for(int i = 0; i<height; i++) {
			string1.append("  ");
		}
		if(node3 == null) {
			string1.append("null");
		} 
		else {
			string1.append(node3.toString());
			string1.append("\n");
			string1.append(toString(node3.left, height+1));
			string1.append("\n");
			string1.append(toString(node3.right,height +1));
		}
		return string1.toString();
		}
	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31); 
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83); 
		testTree.add(7,26);
		System.out.println(testTree);
		testTree.delete(1);
		System.out.println();
		System.out.println(testTree);
	}
}