package nz.ac.auckland.se281.a4.ds;

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */

	// Changed the return type for this function from Node to Node<T>
	public Node<T> locateNode(int pos) throws InvalidPositionException {
		if (pos > this.size() - 1 || pos < 0) {
			throw new InvalidPositionException();
		}

		// Iterates through all the nodes in the linked list within reference to head
		// until desired node is found
		else {
			Node<T> start = head;
			for (int i = 0; i < pos; i++) {
				start = start.getNext();
			}

			return start;
		}
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		Node<T> n = new Node<T>(element);
		n.setNext(head);
		head = n;
	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> n = new Node<T>(element);

		// Checks if there is an element in the list
		if (head == null) {
			head = n;
		}

		else {
			Node<T> temp = head;
			for (int i = 0; i < this.size() - 1; i++) {
				temp = temp.getNext();
			}
			// Appends the next node of the last node
			temp.setNext(n);
		}
	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */

	public T get(int pos) throws InvalidPositionException {
		// Calls the locate node helper function
		return this.locateNode(pos).getValue();

	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos:     an integer, which is the position
	 * @param element: the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public void insert(int pos, T element) throws InvalidPositionException {

		// Wants to insert in between two nodes
		if (pos < this.size() - 1 || pos > 0) {
			// Determines the previous node for the desired position to be inserted
			Node<T> newNode = new Node<T>(element);
			Node<T> previous = this.locateNode(pos - 1);
			// Sets the next node of the previous node to the desired inserted node
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
		}

		// If want to insert at the first position
		else if (pos == 0) {
			this.prepend(element);
		}

		// If want to insert at the end
		else if (pos == this.size() - 1) {
			this.append(element);
		}

		else {
			throw new InvalidPositionException();
		}

	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos: an integer, which is the position
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size -1
	 */
	public void remove(int pos) throws InvalidPositionException {
		if (pos > this.size() - 1 || pos < 0) {
			throw new InvalidPositionException();
		}

		else {

			// The case when the first element needs to be deleted an there is no previous
			// node
			if (pos == 0) {
				// Not deleted the memory of the heads
				this.head = this.head.getNext();

			}

			else {
				// Determines the previous node for the desired node to be deleted
				Node<T> previous = locateNode(pos - 1);

				// The node to be deleted
				Node<T> current = previous.getNext();
				previous.setNext(current.getNext());
			}
		}
	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {
		int i = 0;
		Node<T> start = head;
		while (start != null) {
			++i;
			start = start.getNext();
		}

		return i;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}