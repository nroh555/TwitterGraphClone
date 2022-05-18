package nz.ac.auckland.se281.a4.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonIOException;

import nz.ac.auckland.se281.a4.TweetGraph;
import nz.ac.auckland.se281.a4.TwitterHandle;
import nz.ac.auckland.se281.a4.ds.Graph;
// *******************************
// YOU CANNOT MODIFY THIS CLASS
// *******************************

/**
 * This program uses MVC and has the following classes The SetUI class reads a
 * file in dot format It also has a method to read user commands over this file
 * The entity classes are SetOfStrings and StringRelations The SetContrtol is
 * the main controller which has a execute() method that joins all the classes
 * by passing appropriate messages
 * <p>
 * The controller class parses the user commands by invoking the appropriate
 * method of the SetUI class and then processes the command The program runs
 * until user specifies the "exit" command and to support set operations on
 * these SetOfStrings
 *
 * @author PARTHA ROOP
 */
public class GraphControl {
	// private Graph graph;
	private TweetGraph tweetGraph;
	private final GraphUI graphUI;

	/**
	 * The constructor that initializes all private members
	 */
	public GraphControl() {
		graphUI = new GraphUI();
	}

	/**
	 * The main method that reads the next user command and processes it until the
	 * "exit" command is entered
	 * 
	 * @throws Exception       Cause something went wrong
	 * @throws JsonIOException If json file can not be read
	 * @throws IOException     If file can not be found
	 */
	public void execute() throws Exception, JsonIOException, IOException {
		// initialisation step
		// sUI.CreateJsonTweetFile();
		boolean isAnotherCommand;
		do {
			String nextCmd = graphUI.getCommand();
			isAnotherCommand = processCommand(nextCmd).get(0);
		} while (isAnotherCommand);
	}

	/**
	 * This method processes a given user command
	 *
	 * @param command the user command
	 * @return true if another command is required false if we need to terminate the
	 *         program
	 * @throws Exception       Cause something went wrong
	 * @throws JsonIOException If Json can not be read
	 * @throws IOException     If the file is not found
	 */
	public List<Boolean> processCommand(String command) throws Exception, JsonIOException, IOException {
		boolean isAnotherCommand = true;
		boolean commnadResult = false;

		System.out.println("The command is " + command);
		String[] parts = command.split(" ");

		switch (parts[0]) {
		case "open":
			if (parts.length == 1) {
				System.out.println("\u001b[31;1mInvalid file name\u001b[0m");
			} else if (parts.length == 2) {
				graphUI.open(parts[1]);
				createTweetGraph();
			} else if (!graphUI.getFileStatus()) {
				System.out.println("\u001b[31;1mFile can't be opened\u001b[0m");
				System.out.println("\u001b[31;1mEnter a valid file name\u001b[0m");
			}
			break;

		case "clear":
			System.out.print("\033[H\033[2J"); // clear the screen
			graphUI.printTopicScreen();
			break;

		case "info":
			System.out.println(
					graphUI.getFileName() == null ? "No file opened" : "File opened: " + graphUI.getFileName());
			break;

		case "help":
			System.out.println("You can either *open* a file or *list* an opened file or *exit* the program");
			System.out.println("Once a valid file is open you can perform *search* operations as follows:");
			System.out.println(
					"Command *tweet-search* takes a user id and a sub-string from a tweet to determine which follower has a tweet containing this sub-string");
			System.out.println(
					"Command *tgraph-search* takes BFS / DFS as an argument to display followers in BFS / DFS order");
			System.out.println("Command *tweet-size* takes a user id to determine the number of tweets of this user");
			System.out.println("*clear* to clear the screen");
			System.out.println("*info* to display current open file name");
			System.out.println("*exit* to exit the program");
			break;
		case "list":
			if (graphUI.getFileStatus()) {
				graphUI.list();
			}
			break;
		case "check":
			if (!graphUI.getFileStatus()) {
				System.out.println("\u001b[31;1mFirst open a valid file \u001b[0m");
				break;
			} else if (parts.length == 1) {
				System.out.println("\u001b[31;1mInvalid check command: specify -r / -s/ -t / -e\u001b[0m");
			} else if (parts.length == 2) {
				switch (parts[1]) {
				case "-r":
					if (tweetGraph.isReflexive(graphUI.getSetElements(), graphUI.getRelationElements())) {
						System.out.println("\u001b[32mThe graph is reflexive\u001b[0m");
						commnadResult = true;
					} else {
						System.out.println("\u001b[31mThe graph is NOT reflexive\u001b[0m");
					}
					break;
				case "-s":
					if (tweetGraph.isSymmetric(graphUI.getRelationElements())) {
						System.out.println("\u001b[32mThe graph is symmetric\u001b[0m");
						commnadResult = true;
					} else {
						System.out.println("\u001b[31mThe graph is NOT symmetric\u001b[0m");
					}
					break;
				case "-t":
					if (tweetGraph.isTransitive(graphUI.getRelationElements())) {
						System.out.println("\u001b[32mThe graph is transitive\u001b[0m");
						commnadResult = true;
					} else {
						System.out.println("\u001b[31mThe graph is NOT transitive\u001b[0m");
					}
					break;
				case "-e":
					if (tweetGraph.isEquivalence(graphUI.getSetElements(), graphUI.getRelationElements())) {
						System.out.println("\u001b[32mThe graph represents an equivalence relation\u001b[0m");
						commnadResult = true;
					} else {
						System.out.println("\u001b[31mThe graph is NOT an equivalence relation\u001b[0m");
					}
					break;
				default:
					System.out.println("\u001b[31mInvalid check command entered .. try again\u001b[0m");
					break;
				}
			}
			break;
		case "eq-class":
			if (!graphUI.getFileStatus()) {
				System.out.println("\u001b[31mFirst open a valid file \u001b[0m");
				break;
			} else if (parts.length == 1) {
				System.out.println("\u001b[31mInvalid eq-class command: specify the User / vertex\u001b[0m");
			} else if (parts.length == 2) {
				List<String> result = tweetGraph.computeEquivalence(parts[1], graphUI.getSetElements(),
						graphUI.getRelationElements());
				if (result != null) {
					graphUI.listEqClass(parts[1], result);
					commnadResult = true;
				}

			}
		case "tweet-size":
			if (graphUI.getFileStatus()) {
				if (parts.length != 2) {
					System.out.println("\u001b[31mIncorrect arguments for tweet search (i.e. tsearch User)\u001b[0m");
					break;
				}

				System.out.println("No of tweets in User: " + parts[1] + "= "
						+ tweetGraph.getTweets(new TwitterHandle(parts[1])).size());
			}
			createTweetGraph();
			break;
		case "tweet-search":
			if (graphUI.getFileStatus()) {
				if (parts.length != 3) {
					System.out.println(
							"\u001b[31mIncorrect arguments for searching a tweet text (i.e. tweet-search User_id String)\u001b[0m");
					break;
				}
				tweetGraph.searchTweet(new TwitterHandle(parts[1]), parts[2]);
				System.out.println("No of tweets in User: " + parts[1] + " = "
						+ tweetGraph.getTweets(new TwitterHandle(parts[1])).size());
			}
			createTweetGraph();
			break;
		case "tgraph-search":
			if (graphUI.getFileStatus()) {
				if (parts.length != 2) {
					System.out.println(
							"\u001b[31mIncorrect arguments for graph-search (i.e. graph-search BFS / DFS)\u001b[0m");
					break;
				}
				switch (parts[1]) {
				case "BFS":
					graphUI.listBFS(tweetGraph.breadthFirstSearch());
					break;
				case "DFS":
					graphUI.listDFS(tweetGraph.depthFirstSearch());
					break;
				}
			}
			createTweetGraph();
			break;
		case "exit":
			System.out.println("\u001b[32mWe will exit now.. bye!!\u001b[0m");
			isAnotherCommand = false;
		default:
			System.out.println("\u001b[31mEnter a valid command:\u001b[0m");
		}

		return Arrays.asList(isAnotherCommand, commnadResult);
	}

	/**
	 * This method is used to create the tweet graph`
	 * 
	 * @throws Exception       Cause something went wrong
	 * @throws JsonIOException Throw exception if json file can not be read
	 * @throws IOException     Throw exception if the file is not found
	 */
	private void createTweetGraph() throws Exception, JsonIOException, IOException {
		tweetGraph = new TweetGraph(graphUI.getRelationElements(), graphUI.loadTweets(),
				Dataloader.allocateTweetsToUsers(new Graph(graphUI.getRelationElements()).getUsersFromNodes(),
						graphUI.loadTweets()));
	}

	public static void main(String[] args) throws Exception, JsonIOException, IOException {
		GraphControl controller = new GraphControl();
		controller.execute();
	}
}