package nz.ac.auckland.se281.a4;

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

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}
}
