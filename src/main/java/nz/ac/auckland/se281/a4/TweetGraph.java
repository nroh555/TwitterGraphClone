package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n) {
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for (Tweet t : getTweets(n)) {
			texts.add(t.getTextString());
		}
		return texts;
	}

	/**
	 * This method would search for the first occurrence of the substring within the
	 * list of all nodes obtained from the depth first search algorithm
	 * 
	 * @param user         This is the starting node to begin the DFS
	 * @param tweetKeyword The substring to search for
	 * @return tweetText the first occurrence of the tweet that contains the
	 *         substring
	 */

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {

		// Always set the boolean to true because we only interested in successors
		List<Node<String>> successors = this.depthFirstSearch(user, true);

		// Traverse through all the nodes within the successors list and return the
		// first node that tweeted the substring

		for (Node<String> twitterAccount : successors) {
			for (int i = 0; i < nodeTweets.get(twitterAccount).size(); i++) {
				if (getTweets(twitterAccount).get(i).getTextString().indexOf(tweetKeyword) >= 0) {
					TwitterHandle desiredNode = new TwitterHandle(twitterAccount.getValue());
					return "The tweet string found is: " + getTweetsTexts(desiredNode).get(i) + "\n" + "User "
							+ desiredNode.getName() + " {" + desiredNode.getValue() + "} tweeted " + tweetKeyword;
				}
			}
		}

		return "No successor of " + user.getName() + "tweeted " + tweetKeyword;

	}
}
