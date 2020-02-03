package hw3;

public class IDLListTest {

	public static void main(String[] args) {
	  IDLList<Integer> dll = new IDLList<Integer>();
	  dll.add(1);
	  dll.add(0,0);
	  System.out.println(dll);
	  dll.add(1,3);
	  System.out.println(dll);
	  dll.add(4);
	  dll.append(5);
	  System.out.println(dll);
	  dll.append(6);
	  System.out.println(dll);
	  dll.add(-1);
	  System.out.println(dll.remove(6));
	  System.out.println("head: " + dll.getHead());
	  System.out.println("tail: " + dll.getLast());
	  System.out.println("siez: " + dll.size());
	  System.out.println("removeLast: " + dll.removeLast());
	  System.out.println(dll);
	  System.out.println(dll.removeAt(3));
	  System.out.println(dll);
	}

}