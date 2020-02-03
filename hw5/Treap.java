package hw5;
/***
 * Name: Donglin Yang
 */
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private static class Node<E>{
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		/***
		 * 
		 * @param data
		 * @param priority
		 */
		public Node (E data , int priority ) {
			this.data = data;
			this.priority = priority;
		}
		Node <E> rotateRight(){
			//System.out.println("rotateRight");
			Node<E> hold = this;
			Node<E> hold2 = hold.left;
			hold.left = hold2.right;
			hold2.right = hold;
			return hold2;
		}
		Node <E> rotateLeft(){
			//System.out.println("rotateLeft");
			Node<E> hold = this;
			Node<E> hold2 = hold.right;
			hold.right = hold2.left;
			hold2.left = hold;
			return hold2;
		}
        public String toString() {
            return "(key= " + this.data.toString() + ", priority=" + this.priority + ")";
        }
	}
	private Random priorityGenerator ;
	private Node<E> root ;
	public Treap () {
        this.priorityGenerator = new Random();
        this.root = null;
	}
	/***
	 * 
	 * @param seed
	 */
	public Treap ( long seed ) {
        this.priorityGenerator = new Random(seed);
        this.root = null;
	}
	/***
	 * 
	 * @param key
	 * @return boolean add
	 */
	boolean add (E key ) {
		return add(key, this.priorityGenerator.nextInt());
	}
	/***
	 * 
	 * @param key
	 * @param priority
	 * @return true if add else return false
	 */
	boolean add (E key , int priority ) {
		if(find(key)) {
			return false;
		}
		else {
			if(root == null) {
				root = new Node<E>(key,priority);
				return true;
			}
			else {
				Node<E> current = this.root;
				Node<E> newNode = new Node<E>(key,priority);
				Stack<Node<E>> Nstack = new Stack<Node<E>>();
				int flag = 0; 
		        while (current != null) {
		            Nstack.push(current);
		            if (key.compareTo(current.data) < 0) {
		            	current = current.left;
		            	flag = 1; // left
		            } else {
		                current = current.right;
		                flag = 2; //right
		            }
		        }
	            if (flag == 1) {
	            	Nstack.peek().left = newNode;
	            } else {
	            	Nstack.peek().right = newNode;
	            }
	            reheap(Nstack, newNode);
			}
			return true;
		}
	}
	
	/***
	 * a helper function 
	 * @param Nstack
	 * @param newNode
	 */
	void reheap(Stack<Node<E>> Nstack, Node<E> newNode) {
        while((!Nstack.empty()) && (newNode.priority > Nstack.peek().priority)) {
        	Node<E> parent = Nstack.pop();
        	if(parent == this.root){
        		if(this.root.left == newNode) {
        			this.root.rotateRight();
        		}
        		else{
        			this.root.rotateLeft();
        		}
    			this.root = newNode;
        	}
        	else
        	{
        		if(newNode == parent.left) {
        			parent.rotateRight();
        		}
        		else {
        			parent.rotateLeft();
        		}
        		if(Nstack.peek().left == parent) {
        			Nstack.peek().left = newNode;
        		}
        		else {
        			Nstack.peek().right = newNode;
        		}
        	}
        }
	}
	/***
	 *  deletes the node with the given key
	 * @param key
	 * @return
	 */
	boolean delete ( E key ) {
		if(find(key)==false) {
			return false;
		}
		else {
			root = delete(root,key);
			return true;
		}
	}
	/***
	 * a helper function for delete
	 * @param node
	 * @param key
	 * @return
	 */
	private Node<E> delete(Node<E> node, E key){
		if(key.compareTo(node.data) > 0) {
			node.right = delete(node.right,key);
		}
		else if(key.compareTo(node.data) < 0) {
			node.left = delete(node.left,key);
		}
		else {
			//System.out.println("find!!!");
				if (node.right == null) {
					node = node.left;
				}else if (node.left == null) {
					node = node.right;
				}
				else
				{
					if(node.right.priority < node.left.priority) {
						node = node.rotateRight();
						node.right = delete(node.right,key);
					}else{
						node = node.rotateLeft();
						node.left = delete(node.left,key);
					}
				}
			}
		return node;
	}
	/***
	 *  Find a node with the given key in the treap rooted at root and returns true 
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node <E> root , E key ) {
		if(root == null) {
			return false;
		}
		if(root.data == key) {
			return true;
		}
		else {
			if(key.compareTo(root.data) > 0) {
				return find(root.right, key);
			}
			else	
				return  find(root.left, key);
		}
		
	}
	public boolean find (E key ) {
			return find(root, key);
	}
	/***
	 * the function from class
	 * @param node
	 * @param depth
	 * @param sb
	 */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth+1, sb);
            preOrderTraverse(node.right, depth+1, sb);
        }
    }
	public String toString ()
	{
		StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,14);
		testTree.add(2,31);	
		testTree.add(6,70);
//		testTree.delete(4);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		testTree.delete(5);
		System.out.println(testTree);
	}
	
}
