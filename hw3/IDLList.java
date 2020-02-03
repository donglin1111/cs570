// Donglin Yang   homework 3 
// This assignment consists in implementing a double-linked list with fast accessing.
package hw3;

import java.util.ArrayList;

public class IDLList<E> {
	private static class Node<E>{
		//three data fields
		E data;
		Node<E> next;
		Node<E> prev;
		//constructor
		Node (E elem){
			data = elem;
			next = null;
			prev = null;
		}
		Node (E elem, Node<E> prev, Node<E> next){
			data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	public IDLList() {
		head =null;
		tail = null;
		indices = new ArrayList<Node<E>>(); //List interface
		size = 0;
	}
	/** Add an item at the specified index.
		@param index adds elem at position index 
		@param elem the object to be added
		@throws IndexOutOfBoundsException if the index is out of range(i < 0 || i >= size())
	*/
	public boolean add (int index, E elem) {
		if(index < 0 || index>=size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else if (index == 0) {
			add(elem);
		}
		else if (index == size) {
			append(elem);
		}
		else {
			Node<E> prev_node = indices.get(index-1);
			Node<E> new_node = new Node<E>(elem, prev_node, prev_node.next);
			prev_node.next = new_node;
			indices.add(index, new_node);
			++size;
		}
		return true;
	} 
	/** Add an item .
		@param elem the object to be added
	 */
	public boolean add(E elem) {
		Node<E> new_node = new Node<E>(elem);
		if (size == 0) 
		{
			head = new_node;
			tail = head;
		}
		else 
		{
			head.prev = new_node;
			new_node.next = head;
			head = new_node;
		}
		indices.add(0, new_node);
		++size;
		return true;
	}
	/** Add an item at the specified index.
		@param elem the object to be append
	*/
	public boolean append (E elem) {
		if (size == 0) 
		{
			add(elem);
		}
		else 
		{	
			Node<E> new_node = tail;
			tail = new Node<E>(elem, tail, null);
			new_node.next = tail;
			indices.add(tail);
			++size;
		}
		return true;
	}
	/** returns the object at position index from the head. 
		@param index get elem at position index 
		@throws IndexOutOfBoundsException if the index is out of range(i < 0 || i >= size())
	 */
	public E get (int index) {
		if(index < 0 || index>=size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		E hold  = indices.get(index).data; 
		return hold;
	}
	public E getHead () {
		return head.data;
	}
	public E getLast () {
		return tail.data;
	}
	public int size() {
		return size;
	}
	public E remove() {
		Node<E> hold = head;
		head = head.next;
		indices.remove(0);
		size--;
		return hold.data;
	}
	
	public E removeLast () {
			if(size == 1)
			{
				E hold = remove();
				return hold;
			}else {
				Node<E> hold = tail;
				tail = tail.prev;
				tail.next = null;
				indices.remove(size-1);
				--size;
				return hold.data;
			}
	}
	/** removes and returns the element at the index index
		@param index remove elem at position index 
		@throws IndexOutOfBoundsException if the index is out of range(i < 0 || i >= size())
	*/
	public E removeAt (int index) {
		if(index < 0 || index>=size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		else if (index == 0)
		{
			E hold = remove();
			return hold;
		}
		else if (index == (size-1)){
			E hold = removeLast();
			return hold;
		}
		else {
			Node<E> hold = indices.get(index);
			hold.next.prev = hold.prev;
			hold.prev.next = hold.next;
			indices.remove(index);
			--size;
			return hold.data;
		}
	}
	/**  removes the ﬁrst occurrence of elem.
		@param elem the object to be remove
	*/
	public boolean remove (E elem) {
		Node<E> target = new Node<E>(elem);
		for(int i = 0; i < size;i++)
		{
			if(indices.get(i).data == target.data) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	public String toString() {
		String temp ="(";
		Node<E> nodeRef = head;
		while(nodeRef != null) {
			temp = temp + nodeRef.data;
			if(nodeRef.next != null)
				temp = temp + ",";
			nodeRef = nodeRef.next;
		}
		temp = temp + ")";
		return temp;
	}
}

