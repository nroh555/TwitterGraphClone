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
	private Node<T> locateNode(int pos) throws InvalidPositionException {
		if (pos > this.size() - 1 || pos < 0) {
			throw new InvalidPositionException();
		}

		else {
			int i = 0;
			Node<T> start = head;
			while (i < pos) {
				start.setNext(start);
				i++;
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
			return;
		}

		Node<T> last = head;
		Node<T> temp = last;
		while (last != null) {
			// A temporary node to store the last node before it is null
			temp = last;
			last = last.getNext();
		}

		// Appends the next node of the last node
		temp.setNext(n);
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
		if (pos > this.size() - 1 || pos < 0) {
			throw new InvalidPositionException();
		}

		else {
			// If want to insert at the first position
			if (pos == 0) {
				this.prepend(element);
			}

			// If want to insert at the end
			else if (pos == this.size() - 1) {
				this.append(element);
			}

			// If want to insert in the middle
			else {

			}

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
			Node<T> previous = head;
			int count = 1;
			while (count < pos - 1) {
				previous = previous.getNext();
				count++;
			}

			Node<T> current = previous.getNext();
			while (count < this.size() - 1) {
				previous.setNext(current.getNext());
				previous = current;
				current = current.getNext();
				count++;
			}

			current.setNext(null);
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

	/**
	 * This method is used for access the previous node of the desired node
	 * 
	 * @param pos: The position of the desired node
	 * @return Node<T> : The previous node of the desired node
	 */
}