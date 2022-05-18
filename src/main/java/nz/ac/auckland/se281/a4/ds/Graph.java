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
	 * @param edges
	 *            a list of edges to be added to the graph
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
	 * This method returns a boolean based on whether the
	 * input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set
	 *            A list of TwitterHandles
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * This method returns a boolean based on whether the
	 * input set is symmetric.
	 * 
	 * If the method returns false, then the following must
	 * be printed to the console:
	 * "For the graph to be symmetric tuple: " + requiredElement + " MUST be
	 * present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {
		throw new java.lang.UnsupportedOperationException();
	}

	/**
	 * This method returns a boolean based on whether the
	 * input set is transitive.
	 * 
	 * If the method returns false, then the following must
	 * be printed to the console:
	 * "For the graph to be transitive tuple: " + requiredElement + " MUST be
	 * present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * This method returns a boolean based on whether the
	 * input sets are anti-symmetric
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set
	 *            A list of TwitterHandles
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then
	 * The following must be printed to the console:
	 * "Can't compute equivalence class as this is not an equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node
	 *            A "TwitterHandle" in the graph
	 * @param set
	 *            A list of TwitterHandles
	 * @param relation
	 *            A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * This method returns a List nodes using
	 * the BFS (Breadth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root);
	}

	/**
	 * This method returns a List nodes using
	 * the BFS (Breadth First Search) algorithm
	 * 
	 * @param start
	 *            A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start) {// name to breadthFirstSearch
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * This method returns a List nodes using
	 * the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root);
	}

	/**
	 * This method returns a List nodes using
	 * the DFS (Depth First Search) algorithm
	 * 
	 * @param start
	 *            A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
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
