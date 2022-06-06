package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {
		// Loops through all the strings within the set and creates the desired
		// relations
		for (String s : set) {
			String element = s + "," + s;
			if (!(relation.contains(element))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {
		for (int i = 0; i < relation.size() - 1; i++) {
			// Splits the node string into two, source and target
			String[] node = relation.get(i).split(",", 2);
			String source = node[0];
			String target = node[1];
			// Creates the opposite of the node string to determine symmetry
			String opposite = target + "," + source;

			if (!(relation.contains(opposite))) {
				System.out.println("For the graph t be symmetric tuple: " + opposite + " MUST be present");
				return false;
			}
		}

		return true;
	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {
		for (int i = 0; i < relation.size(); i++) {
			// Splits the node string into two, source and target
			String[] node = relation.get(i).split(",", 2);
			String source = node[0];
			String target = node[1];

			// Loops through the remaining elements in the relation to determine if
			// transitivity holds
			for (String s : relation) {
				if (s.startsWith(target)) {
					String[] nodeNext = s.split(",", 2);
					String targetNext = nodeNext[1];
					String transitive = source + "," + targetNext;

					if (!(relation.contains(transitive))) {
						System.out.println("For the graph to be transitive tuple: " + transitive + " MUST be present");
						return false;
					}
				}

			}

		}

		return true;

	}

	/**
	 * This method returns a boolean based on whether the input sets are
	 * anti-symmetric TODO: Complete this method (Note a set is not passed in as a
	 * parameter but a list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		// For a relation to be an equivalance relation it must be reflexive, symmetric
		// and transitive
		if (this.isReflexive(set, relation) && this.isSymmetric(relation) && this.isTransitive(relation)) {
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {
		if (this.isEquivalence(set, relation)) {
			List<String> eqClass = new ArrayList<String>();
			for (String s : relation) {
				// Splits each of the element in the relation to a source and target
				String[] element = s.split(",", 2);
				String source = element[0];
				String target = element[1];
				// If source equals to node then the target must exist in the eqClass
				if ((source.equals(node)) && (!(eqClass.contains(target)))) {
					eqClass.add(target);
				}

				// If target equals to node then the soruce must exist in the eqClass
				else if ((target.equals(node)) && (!(eqClass.contains(source)))) {
					eqClass.add(source);
				}
			}

			return eqClass;
		}

		else

		{
			System.out.println("Can't compute equivalence class as this is not an equivalence relation");
			return null;
		}
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {
		if (rooted) {
			this.root = start;
		}

		// Creates a queue for the BFS
		LinkedList<Node<String>> queue = new LinkedList<Node<String>>();

		// Creates a list to store all the visited nodes
		List<Node<String>> visited = new ArrayList<Node<String>>();

		queue.append(root);

		while (queue.size() != 0 || visited.size() < adjacencyMap.keySet().size()) {

			// Creates a variable that determine the previous size of the visited iteration
			// to check for connectedness
			int prevSize = visited.size();
			int prevQueueSize = queue.size();
			Node<String> prevRoot = root;

			for (int i = 0; i < adjacencyMap.get(root).size(); i++) {
				if (!(visited.contains(adjacencyMap.get(root).get(i).getTarget()))) {
					queue.append(adjacencyMap.get(root).get(i).getTarget());
				}
			}

			// Adds the element front of the queue to the visited list nodes
			if (!(visited.contains(queue.get(0)))) {
				visited.add(queue.get(0));
			}

			// In the case when there are not connected components
			if (!rooted && visited.size() < adjacencyMap.keySet().size() && visited.size() == prevSize
					&& prevQueueSize == queue.size()) {
				this.root = getNextNode();
				// The case when we have reached the end of the textual order
				if (this.root == null) {
					return visited;
				}

				if (!(visited.contains(root))) {
					visited.add(root);
				}
				continue;
			}

			// Only deals with connected components
			else {
				this.root = queue.get(0);
				if (!(adjacencyMap.containsKey(root))) {
					this.root = prevRoot;
				}
			}

			queue.remove(0);
		}

		return visited;

	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {

		if (rooted) {
			this.root = start;
		}

		// Creates a stack for the DFS
		LinkedList<Node<String>> stack = new LinkedList<Node<String>>();

		// Creates a list to store all the visited nodes
		List<Node<String>> visited = new ArrayList<Node<String>>();

		visited.add(root);
		stack.prepend(root);

		while (stack.size() != 0 || visited.size() < adjacencyMap.keySet().size()) {

			// Creates a variable that determine the previous size of the visited iteration
			// to check for connectedness
			int prevSize = visited.size();
			Node<String> prevRoot = root;

			for (int i = 0; i < adjacencyMap.get(root).size(); i++) {
				if (!(visited.contains(adjacencyMap.get(root).get(i).getTarget()))) {
					stack.prepend(adjacencyMap.get(root).get(i).getTarget());
				}
			}

			// Adds the top element of the stack to the visited list nodes
			if (!(visited.contains(stack.get(0)))) {
				visited.add(stack.get(0));
			}

			// In the case when there are not connected components
			if (!rooted && visited.size() < adjacencyMap.keySet().size() && visited.size() == prevSize) {
				this.root = getNextNode();
				// The case when we have reached the end of the textual order
				if (this.root == null) {
					return visited;
				}

				if (!(visited.contains(root))) {
					visited.add(root);
				}
				continue;
			}

			// Only deals with connected components
			else {
				this.root = stack.get(0);
				if (!(adjacencyMap.containsKey(root))) {
					this.root = prevRoot;
				}
			}

			stack.remove(0);
		}

		return visited;

	}

	/**
	 * This method would determine the next node in textual order of a graph that is
	 * not connected
	 * 
	 * @return The next node in textual order
	 */

	public Node<String> getNextNode() {
		boolean next = false;
		for (Node<String> nextNode : adjacencyMap.keySet()) {
			if (nextNode.equals(root)) {
				// Skips the current iteration the moment the desired not connected node is
				// found
				next = true;
				continue;
			}

			if (next) {
				// The next node in textual order
				return nextNode;
			}
		}

		return null;

	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
