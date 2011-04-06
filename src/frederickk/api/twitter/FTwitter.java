package frederickk.api.twitter;

/*
 *  Frederickk.api.twitter 001
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a library for easier use of the twitter api (twitter4j)
 *  http://code.google.com/p/frederickk/
 *
 */


import twitter4j.*;
import processing.core.*;
import processing.xml.*;
import java.text.SimpleDateFormat;

public class FTwitter implements FTwitterConstants {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	private static PApplet p5;

	private static Twitter t;
	private java.util.List search;

	private String name = "";
	private String pass = "";
	private String term = "";
	private String[] entry;

	private boolean date;
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FTwitter
	 * 
	 * @param thePApplet
	 * 			PApplet
	 */
	public FTwitter(PApplet papplet) {
		p5 = papplet;
	}

	/**
	 * instantiate FTwitter
	 * 
	 * @param thePApplet
	 * 			PApplet
	 * @param _name
	 * 			username
	 * @param _pass
	 * 			password
	 */
	public FTwitter(PApplet papplet, String _name, String _pass) {
		p5 = papplet;
		setName( _name );
		setPassword( _pass );
	}

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	/**
	 * search public tweets
	 * 
	 * @param _term
	 * 			the search term
	 */
	public void search(String _term) {
		term = _term;

		t = new Twitter(name,pass);
		Query q = new Query(term);
		q.setRpp(100);
		//q.setSinceId(0);

		try {
			QueryResult result = t.search(q);
			search = result.getTweets();

			/*
		      System.out.println("-----------------------------------------------------------------------------");
		      System.out.println("TwitterCollect.term: " + term );
		      System.out.println("-----------------------------------------------------------------------------");
		      System.out.println("TwitterCollect.result:");
		      System.out.println(result);
			 */
		
		} catch( TwitterException e) {
			System.out.println("error on twitter status collect");
		}
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _name
	 * 			username
	 */
	public void setName(String _name) {
		name = _name;
	}

	/**
	 * @param _pass
	 * 			password
	 */
	public void setPassword(String _pass) {
		pass = _pass;
	}

	/**
	 * @param _date
	 * 			XXX
	 */
	public void setDate(boolean _date) {
		date = _date;
	}


	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * @param w
	 * 			XXX
	 */
	public String getWord(int w) {
		Tweet tweet = (Tweet) search.get(w);
		String word = tweet.getText();
		//System.out.println("TwitterCollect.tweet: " + word);

		String n_word = "";
		String[] tweetList = PApplet.split(word, ' ');

		for(int i=0; i!=tweetList.length; i++) {
			if( !PApplet.trim(tweetList[i]).equals(term) ) {
				if(i != tweetList.length-1) n_word += PApplet.trim(tweetList[i]) + " ";
				else n_word += PApplet.trim(tweetList[i]);
			}
		}

		return n_word;
	}


	/**
	 * return search results from twitter public feed
	 * 
	 * @return publicText
	 */
	public String[] getPublicTimeline() {
		t = new Twitter();
		XMLElement xml = new XMLElement(p5, TIMELINE_URL);
		String[] publicText;

		if(xml != null) {
			int numSites = xml.getChildCount();

			publicText = new String[numSites];
			for (int i=0; i<numSites; i++) {
				XMLElement stat = xml.getChild(i);
				XMLElement[] statText = stat.getChildren("text");
				publicText[i] = statText[0].getContent();
				//System.out.println(statText[0].getContent());
			}

			return publicText;

		} else {
			return null;
		}

	}

	/**
	 * return the number of search results
	 * 
	 * @return search.size()
	 */
	public int getSearchNum() {
		return search.size();
	}

	
	/**
	 * @param w
	 *          index of status to return
	 *          
	 * return the number of search results
	 * 
	 * @return entry[w]
	 */
	public String getStatus(int w) {
		return entry[w];
	}

}