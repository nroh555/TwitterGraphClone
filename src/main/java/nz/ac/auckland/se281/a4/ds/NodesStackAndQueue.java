package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack.
	 * TODO: Complete this method
	 * 
	 * @param element
	 *            the element to be "pushed"
	 */
	public void push(T element) {
		Node n = new Node(element);
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element)
	 * TODO: Complete this method (Note: You may have to change the return type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public Node pop() throws EmptyStackException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * get the element from the top of the stack without removing it
	 * TODO: Complete this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException
	 *             if the stack is empty
	 */
	public Node peek() throws EmptyStackException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * append an element at the end of the queue
	 * TODO: Complete this method
	 *
	 * @param element
	 *            the element to be appended
	 */
	public void append(T element) {
		Node n = new Node(element);
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}
}
