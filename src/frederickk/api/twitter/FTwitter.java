package frederickk.api.twitter;

/*
 *  Frederickk.api.twitter 0.0.2
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a library for easier use of the twitter of twitter4j-2.2.4
 *  http://github.com/frederickk/frederickk
 *
 */



// -----------------------------------------------------------------------------
// libraries
// -----------------------------------------------------------------------------
//twitter4j core
import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.api.*;

//twitter4j async
//import twitter4j.AsyncTwitter;
//import twitter4j.AsyncTwitterFactory;

import processing.core.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class FTwitter implements FTwitterConstants {
	// -----------------------------------------------------------------------------
	// classes
	// -----------------------------------------------------------------------------
	protected class FTwitterStatus {
		// ------------------------------------
		// properties
		// ------------------------------------
		protected Date created_at;
		protected long id;
	    protected String text;
	    protected String source;
	    protected boolean favorited;
	    protected long in_reply_to_status_id;
	    protected long in_reply_to_user_id;
	    protected String in_reply_to_screen_name;
	    protected int retweet_count;
	    protected boolean retweeted;

	    
		// ------------------------------------
		// constructor
		// ------------------------------------
	    public FTwitterStatus() {
	    	//intialize with empty values
	    	created_at = null;
	    	id = 0;
	    	text = null;
	    	source = null;
	    	favorited = false;
	        in_reply_to_status_id = 0;
	        in_reply_to_user_id = 0;
	        in_reply_to_screen_name = null;
	        retweet_count = 0;
	        retweeted = false;
	    }
	    public FTwitterStatus(Status status) {
	    	set(status);
	    }    
	    

		// ------------------------------------
		// sets
		// ------------------------------------
	    public void set(Status status) {
			created_at = status.getCreatedAt();
			id = status.getId();
			text = status.getText();
			source = status.getSource();
			favorited = status.isFavorited();
			in_reply_to_status_id = status.getInReplyToStatusId();
			in_reply_to_user_id = status.getInReplyToUserId();
			in_reply_to_screen_name = status.getInReplyToScreenName();
			//retweet_count = Integer.valueOf( status.retweet_count")[0].getContent() ).intValue();
			//retweeted = Boolean.parseBoolean( status.retweeted")[0].getContent() );
	    }

	    
		// ------------------------------------
		// gets
		// ------------------------------------
		/**
		 * return status attributes as a String array
		 * 
		 * 0 created_at
		 * 1 id
		 * 2 text
		 * 3 source
		 * 4 favorited
		 * 5 in_reply_to_status_id
		 * 6 in_reply_to_user_id
		 * 7 in_reply_to_screen_name
		 * 8 retweet_count
		 * 9 retweeted
		 * 
		 * @return result
		 */
	    public String[] asArray() {
	    	String result[] = {
	    			created_at.toString(),
	    	    	new Long(id).toString(),
	    	    	text,
	    	    	source,
	    	    	new Boolean(favorited).toString(),
	    	    	new Long(in_reply_to_status_id).toString(),
	    	    	new Long(in_reply_to_user_id).toString(),
	    	        in_reply_to_screen_name,
	    	        new Integer(retweet_count).toString(),
	    	        new Boolean(retweeted).toString()
	    		};
	    	
	    	return result;
	    }
	}


	// -----------------------------------------------------------------------------
	protected class FTwitterTweet {
		// ------------------------------------
		// properties
		// ------------------------------------
		protected Date created_at;
		protected String from_user;
		protected long from_user_id;
		protected long id;
		protected String iso_language_code; 
		protected String profile_image_url;
		protected String source;
		protected String text;
		protected String to_user; 
		protected long to_user_id;

	    
		// ------------------------------------
		// constructor
		// ------------------------------------
	    public FTwitterTweet() {
	    	//intialize with empty values
	    	created_at = null;
	    	from_user = null;
	    	from_user_id = 0;
	    	id = 0;
	    	iso_language_code = null;
	    	profile_image_url = null;
	    	source = null;
	    	text = null;
	        to_user = null;
	        to_user_id = 0;
	    }
	    public FTwitterTweet(Tweet tweet) {
	    	set(tweet);
	    }    
	    

		// ------------------------------------
		// sets
		// ------------------------------------
	    public void set(Tweet tweet) {
			created_at = tweet.getCreatedAt();
	    	from_user = tweet.getFromUser();
	    	from_user_id = tweet.getFromUserId();
			id = tweet.getId();
	    	iso_language_code = tweet.getIsoLanguageCode();
	    	profile_image_url = tweet.getProfileImageUrl();
			source = tweet.getSource();
			text = tweet.getText();
			to_user = tweet.getToUser();
	        to_user_id = tweet.getToUserId();
	    }

	    
		// ------------------------------------
		// gets
		// ------------------------------------
		/**
		 * return tweet attributes as a String array
		 * 
		 * 0 created_at
		 * 1 id
		 * 2 text
		 * 3 source
		 * 4 iso_language_code
		 * 5 profile_image_url
		 * 6 from_user
		 * 7 from_user_id
		 * 8 to_user
		 * 9 to_user_id
		 * 
		 * @return result
		 */
	    public String[] asArray() {
	    	String result[] = {
	    			created_at.toString(),
	    			new Long(id).toString(),
	    			text,
	    			source,
	    	    	iso_language_code,
	    	    	profile_image_url,
	    	    	from_user,
	    	    	new Long(from_user_id).toString(),
	    			to_user,
	    	        new Long(to_user_id).toString()
	    		};
	    	
	    	return result;
	    }
	}	
	

	// -----------------------------------------------------------------------------
	protected class FTwitterTrend {
		// ------------------------------------
		// properties
		// ------------------------------------
		protected String name;
		protected Date as_of;
		protected Date trend_at;
		protected String country_name;
		protected String place_name;
		protected int woeid;

	    
		// ------------------------------------
		// constructor
		// ------------------------------------
	    public FTwitterTrend() {
	    	//intialize with empty values
	    	name = null;
	    	as_of = null;
	    	trend_at = null;
	    	country_name = null;
	    	place_name = null;
	        woeid = 1;
	    }
	    

		// ------------------------------------
		// sets
		// ------------------------------------
	    public void set(Trends trends, Trend trend) {
	    	name = trend.getName();
	    	as_of = trends.getAsOf();
	    	trend_at = trends.getTrendAt();
	    	country_name = trends.getLocation().getCountryName();
			place_name = trends.getLocation().getCountryName();
			woeid = trends.getLocation().getWoeid();
	    }
	    
	    
	    // ------------------------------------
		// gets
		// ------------------------------------
		/**
		 * return trend attributes as a String array
		 * 
		 * 0 name
		 * 1 as_of
		 * 2 trend_at
		 * 3 counry_name
		 * 4 place_name
		 * 5 woeid
		 * 
		 * @return result
		 */
	    public String[] asArray() {
	    	String result[] = {
	    			name,
	    			as_of.toString(),
	    			trend_at.toString(),
	    			country_name,
	    			place_name,
	    	        new Integer(woeid).toString()
	    		};
	    	
	    	return result;
	    }
	}		
	
	
	
	// -----------------------------------------------------------------------------
	// properties
	// -----------------------------------------------------------------------------
	protected static PApplet p5;
	protected static Twitter twitter;
	//protected static Twitter twitter = new TwitterFactory().getInstance();
    //protected static AsyncTwitter twitter;

	
	private static int returnType;

	private static String ConsumerKey = "";
	private static String ConsumerSecret = "";
	private static String AccessToken = "";
	private static String AccessTokenSecret = "";
	
	
	// -----------------------------------------------------------------------------
	// constructor
	// -----------------------------------------------------------------------------
	/**
	 * instantiate a generic FTwitter, authentication may be required later using
	 * setAuthorization(ConsumerKey, ConsumerSecret)
	 * setAccessToken(AccessToken, AccessTokenSecret)
	 * 
	 * @param papplet
	 * 			PApplet
	 */
	public FTwitter(PApplet papplet) {
		p5 = papplet;
		twitter = new TwitterFactory().getInstance();
	
		//by default we'll return an array of Strings
		setReturnType(STRING_ARRAY);
	}

	/**
	 * instantiate an authorized FTwitter
	 * register your app at dev.twitter.com in order to get consumer key and secret
	 * 
	 * @param papplet
	 * 			PApplet
	 * @param _ConsumerKey
	 * 			authorization consumer key
	 * @param _ConsumerSecret
	 * 			authorization consumer secret
	 * @param _AccessToken
	 * 			access token
	 * @param _AccessTokenSecret
	 * 			access token secret
	 * 
	 */
	public FTwitter(PApplet papplet, String _ConsumerKey, String _ConsumerSecret, String _AccessToken, String _AccessTokenSecret) {
		p5 = papplet;
		twitter = new TwitterFactory().getInstance();
		setAuthorization( _ConsumerKey, _ConsumerSecret, _AccessToken, _AccessTokenSecret );

		//by default we'll return an array of Strings
		setReturnType(STRING_ARRAY);
	}

	/**
	 * instantiate an authorized FTwitter
	 * register your app at dev.twitter.com in order to get consumer key and secret
	 * 
	 * @param papplet
	 * 			PApplet
	 * @param _ConsumerKey
	 * 			authorization consumer key
	 * @param _ConsumerSecret
	 * 			authorization consumer secret
	 * @param _AccessToken
	 * 			access token
	 * @param _AccessTokenSecret
	 * 			access token secret
	 * @param _mode
	 * 			mode can be set to NORMAL or ASYNC
	 *
	public FTwitter(PApplet papplet, String _ConsumerKey, String _ConsumerSecret, String _AccessToken, String _AccessTokenSecret, int mode) {
		p5 = papplet;
		twitter = new TwitterFactory().getInstance();
		setAuthorization( _ConsumerKey, _ConsumerSecret, _AccessToken, _AccessTokenSecret);
		setAccessToken(_AccessToken, _AccessTokenSecret);

		//by default we'll return an array of Strings
		setReturnType(STRING_ARRAY);
	}
	*/
		
	
	
	// -----------------------------------------------------------------------------
	// methods
	// -----------------------------------------------------------------------------
	private void init() {
		System.out.println( "" );
		System.out.println( "-----------------------------------------------------------------------------" );
		System.out.println( "FTwitter " + VERSION );
		System.out.println( "http://github.com/frederickk/frederickk/" );
		System.out.println( "http://kenfrederick.blogspot.com/\n" );
	}
	
	
	
	// -----------------------------------------------------------------------------
	// sets
	// -----------------------------------------------------------------------------
	/**
	 * register your app at dev.twitter.com in order to get consumer key and secret
	 * 
	 * authorization methods copied from 
	 * https://github.com/RobotGrrl/Simple-Processing-Twitter
	 * http://RobotGrrl.com/
	 * 
	 * Code licensed under:
	 * CC-BY
	 * 
	 * @param _ConsumerKey
	 * 			authorization consumer key
	 * @param _ConsumerSecret
	 * 			authorization consumer secret
	 * @param _AccessToken
	 * 			access token
	 * @param _AccessTokenSecret
	 * 			access token secret
	 * 
	 */
	public void setAuthorization(String _ConsumerKey, String _ConsumerSecret, String _AccessToken, String _AccessTokenSecret) {
		ConsumerKey = _ConsumerKey;
		ConsumerSecret = _ConsumerSecret;
		AccessToken = _AccessToken;
		AccessTokenSecret = _AccessTokenSecret;

		twitter.setOAuthConsumer(ConsumerKey, ConsumerSecret);
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);
	}
	private static AccessToken loadAccessToken() {
		return new AccessToken(AccessToken, AccessTokenSecret);
	}

	
	/**
	 * @param _returnType
	 * 			sets globally what type the methods will return
	 * 			- STRING_ARRAY returns all of the properties as an array of String items
	 *  		- OBJECT returns an Object whose properties are accessible via their respective methods
	 */
	public void setReturnType(int _returnType) {
		returnType = _returnType;
	}

	
	
	
	// -----------------------------------------------------------------------------
	// gets
	// -----------------------------------------------------------------------------

	/* /////////////////////////////////////////////////////////////////////////////
	 *
	 * timelines
	 *
	 * /////////////////////////////////////////////////////////////////////////////

	/**
	 * returns an ArrayList of the 20 most recent statuses from the twitter public feed
	 *
	 * @return result
	 */
	public ArrayList getPublicTimeline() {
		ArrayList result = new ArrayList();
		List<Status> statuses;

		try {
            statuses = twitter.getPublicTimeline();
			for(Status status : statuses) {
				FTwitterStatus FTStatus = new FTwitterStatus(status);

				if(returnType == STRING_ARRAY) {
					//each element of the ArrayList will be a string[]
					String[] FTStatusStr = ((FTwitterStatus) FTStatus).asArray(); 
					result.add( FTStatusStr );
				} else if(returnType == OBJECT) {
					//each element of the ArrayList will be an instance of FTwitterStatus
					result.add( FTStatus );
				} else {
					System.out.println("Invalid return type specified. Returned ArrayList will be null");
				}
			}
		} catch (TwitterException te) {
			System.out.println("Error getting timeline: " + te.getMessage());
			//te.printStackTrace();
		}
		
		return result;
	}

	
	/**
	 * return a number of search results from a specified user's twitter feed
	 *
	 * @param _user
	 * 			username of timeline to be found
	 * @param _count
	 * 			number of tweets to return
	 *
	 * @return result
	 */
	public ArrayList getTimeline(String _user, int _count) {
		ArrayList result = new ArrayList();
		List<Status> statuses;
		Paging page = new Paging();
		page.setCount(_count);

		try {
			statuses = twitter.getUserTimeline(_user, page);
			for(Status status : statuses) {
				FTwitterStatus FTStatus = new FTwitterStatus(status);
				if(returnType == STRING_ARRAY) {
					//each element of the ArrayList will be a string[]
					String[] FTStatusStr = ((FTwitterStatus) FTStatus).asArray(); 
					result.add( FTStatusStr );
				} else if(returnType == OBJECT) {
					//each element of the ArrayList will be an instance of FTwitterStatus
					result.add( FTStatus );
				} else {
					System.out.println("Invalid return type specified. Returned ArrayList will be null");
				}
			}
		} catch (TwitterException te) {
			System.out.println("Error getting timeline: " + te.getMessage());
			//te.printStackTrace();
		}

		return result;
	}

	
	/**
	 * returns 20 (default) most recent statuses from specified user's twitter feed
	 *
	 * @param _user
	 * 			username of timeline to be found
	 *
	 * @return result
	 */
	public ArrayList getTimeline(String _user) {
		ArrayList result = new ArrayList();
		result = getTimeline(_user, DEFAULT_TWEET_COUNT);
		return result;
	}

	
	/**
	 * returns 20 (default) most recent statuses from authenticated user's twitter feed
	 *
	 * @return result
	 */
	public ArrayList getTimeline() {
		ArrayList result = new ArrayList();
		List<Status> statuses;

		try {
			statuses = twitter.getUserTimeline();
			for(Status status : statuses) {
				FTwitterStatus FTStatus = new FTwitterStatus(status);

				if(returnType == STRING_ARRAY) {
					//each element of the ArrayList will be a string[]
					String[] FTStatusStr = ((FTwitterStatus) FTStatus).asArray(); 
					result.add( FTStatusStr );
				} else if(returnType == OBJECT) {
					//each element of the ArrayList will be an instance of FTwitterStatus
					result.add( FTStatus );
				} else {
					System.out.println("Invalid return type specified. Returned ArrayList will be null");
				}
			}
		} catch (TwitterException te) {
			System.out.println("Error getting timeline: " + te.getMessage());
			//te.printStackTrace();
		}

		return result;
	}
	

	
	
	/* /////////////////////////////////////////////////////////////////////////////
	 *
	 * searches
	 * 
	 * /////////////////////////////////////////////////////////////////////////////

	/**
	 * searches the public timeline
	 * 
	 * @param _term
	 * 			the search term
	 * @param _num
	 * 			the number of results to return
	 * @param _lang
	 * 			the language to search for http://en.wikipedia.org/wiki/ISO_639-1
	 *
	 * @return tweets
	 */
	public ArrayList search(String _term, int _num, String _lang) {
		ArrayList result = new ArrayList();
		List<Tweet> tweets;
		Query q = new Query(_term);
		q.setRpp(_num);
		q.setLang(_lang);
		//q.setSinceId(0);


		try {
			QueryResult queryresult = twitter.search(q);
			tweets = queryresult.getTweets();

			for(Tweet tweet : tweets) {
				FTwitterTweet FTTweet = new FTwitterTweet(tweet);
				if(returnType == STRING_ARRAY) {
					//each element of the ArrayList will be a string[]
					String[] FTTweetStr = ((FTwitterTweet) FTTweet).asArray(); 
					result.add( FTTweetStr );
				} else if(returnType == OBJECT) {
					//each element of the ArrayList will be an instance of FTwitterStatus
					result.add( FTTweet );
				} else {
					System.out.println("Invalid return type specified. Returned ArrayList will be null");
				}
			}
		} catch( TwitterException te ) {
			System.out.println("Error: " + te.getMessage());
			//te.printStackTrace();
		}

		return result;
	}

	
	/**
	 * searches the public timeline and english (default) tweets
	 * 
	 * @param _term
	 * 			the search term
	 * @param _num
	 * 			the number of results to return
	 *
	 * @return tweets
	 */
	public ArrayList search(String _term, int _num) {
		ArrayList result = new ArrayList();
		result = search(_term, _num, DEFAULT_LANGUAGE);
		return result;
	}

	
	/**
	 * searches the public timeline and returns 20 (default) english (default) tweets
	 * 
	 * @param _term
	 * 			the search term
	 *
	 * @return tweets
	 */
	public ArrayList search(String _term) {
		ArrayList result = new ArrayList();
		result = search(_term, DEFAULT_TWEET_COUNT, DEFAULT_LANGUAGE);
		return result;
	}
	

	/**
	 * searches a user's timeline and returns a list statuses whose text contains the term
	 * 
	 * @param _term
	 * 			the search term
	 * @param _user
	 * 			username of timeline to be found
	 *
	 * @return statuses
	 */
	public ArrayList searchUserTimeline(String _term, String _user) {
		ArrayList result = new ArrayList();
		List<Status> statuses = getTimeline(_user);

		for (Status status : statuses) {
			String text = ((Status) status).getText();
			String[] tokens = text.split(" ");
			for(int i=0; i<tokens.length; i++) {
				if(tokens[i].equalsIgnoreCase(_term)) {
					FTwitterStatus FTStatus = new FTwitterStatus(status);

					if(returnType == STRING_ARRAY) {
						//each element of the ArrayList will be a string[]
						String[] FTStatusStr = ((FTwitterStatus) FTStatus).asArray(); 
						result.add( FTStatusStr );
					} else if(returnType == OBJECT) {
						//each element of the ArrayList will be an instance of FTwitterStatus
						result.add( FTStatus );
					}
					break;
				}

			}
		}

		return result;
	}
	

	/* /////////////////////////////////////////////////////////////////////////////
	 *
	 * trends
	 *
	 * /////////////////////////////////////////////////////////////////////////////

	/**
	 * returns the top 10 topics that are currently trending on Twitter
	 * 
	 * @return result
	 */
	public ArrayList getTrends() {
		ArrayList result = new ArrayList();
		Trends trends = null;

		//the list of trends
		try {
			trends = twitter.getTrends();
			for (Trend trend : ((Trends) trends).getTrends()) {
				FTwitterTrend FTTrend = new FTwitterTrend();
				FTTrend.set(trends, trend);
				
				if(returnType == STRING_ARRAY) {
					//each element of the ArrayList will be a string[]
					String[] FTTrendStr = ((FTwitterTrend) FTTrend).asArray(); 
					result.add( FTTrendStr );
				} else if(returnType == OBJECT) {
					result.add(FTTrend );
				}
				
			}
		} catch (TwitterException te) {
			System.out.println("Error geting Trends: " + te.getMessage());
			//te.printStackTrace();
		}

		return result;
	}

	/**
	 * returns the top 20 topics for each hour of a specified day that are trending on Twitter
	 * 
	 * @param _yyyy_MM_dd
	 * 			specifiy a start date "2001-09-25"
	 * 
	 * @return result
	 */
	public ArrayList getDailyTrends(String _yyyy_MM_dd) {
		ArrayList result = new ArrayList();
		List<Trends> trendsList = null;

		//set the date
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		try {
			date = format.parse(_yyyy_MM_dd);
			System.out.println( date.toString() );
		}
        catch(ParseException pe) {
        	System.out.println("error cannot parse date: " + _yyyy_MM_dd + " " + pe);
        }		
		
		//the list of trends
		try {
			trendsList = twitter.getDailyTrends(date, false);
            for (Trends trends : trendsList) {
            	for (Trend trend : ((Trends) trends).getTrends()) {
            		FTwitterTrend FTTrend = new FTwitterTrend();
            		FTTrend.set(trends, trend);
				
            		if(returnType == STRING_ARRAY) {
            			//each element of the ArrayList will be a string[]
            			String[] FTTrendStr = ((FTwitterTrend) FTTrend).asArray(); 
            			result.add( FTTrendStr );
            		} else if(returnType == OBJECT) {
            			result.add(FTTrend );
            		}
            	} //end trends loop
            } //end trendsList loop

		} catch (TwitterException te) {
			System.out.println("Error geting Trends: " + te.getMessage());
			//te.printStackTrace();
		}

		return result;
	}	

	/**
	 * returns the top 30 topics of each day of a specified week that are trending on Twitter
	 * 
	 * @param _yyyy_MM_dd
	 * 			specifiy a start date "2001-09-25"
	 * 
	 * @return result
	 */
	public ArrayList getWeeklyTrends(String _yyyy_MM_dd) {
		ArrayList result = new ArrayList();
		List<Trends> trendsList = null;

		//set the date
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		try {
			date = format.parse(_yyyy_MM_dd);
			System.out.println( date.toString() );
		}
        catch(ParseException pe) {
            System.out.println("error cannot parse date: " + _yyyy_MM_dd + " " + pe);
        }		
		
		//the list of trends
		try {
			trendsList = twitter.getWeeklyTrends(date, false);
			//if(date != null) trendsList = twitter.getWeeklyTrends(date, false);
			//else trendsList = twitter.getWeeklyTrends();

			for (Trends trends : trendsList) {
            	for (Trend trend : ((Trends) trends).getTrends()) {
            		FTwitterTrend FTTrend = new FTwitterTrend();
            		FTTrend.set(trends, trend);
				
            		if(returnType == STRING_ARRAY) {
            			//each element of the ArrayList will be a string[]
            			String[] FTTrendStr = ((FTwitterTrend) FTTrend).asArray(); 
            			result.add( FTTrendStr );
            		} else if(returnType == OBJECT) {
            			result.add(FTTrend );
            		}
            	} //end trends loop
            } //end trendsList loop

		} catch (TwitterException te) {
			System.out.println("Error geting Trends: " + te.getMessage());
			//te.printStackTrace();
		}

		return result;
	}		
	
	/**
	 * returns an ArrayList of trends based on location
	 * 
	 * @param _latitude
	 * 			latitude as decimal
	 * @param _longitude 
	 * 			logitude as decimal
	 *
	 * @return result
	 */
	public ArrayList getTrendsLocation(double _latitude, double _longitude) {
		ArrayList result = new ArrayList();
		ResponseList<Location> locations = null;
		int woeid = 1;
		Trends trends = null;

		//first let's get the WOEID based on our latitude & longitude
		try {
			locations = twitter.getAvailableTrends( new GeoLocation(_latitude,_longitude) );
			woeid = (int) locations.get(0).getWoeid();
		} catch (TwitterException te) {
			System.out.println("Error geting WOEID showing Worldwide trends: " + te.getMessage());
			//te.printStackTrace();
		}
		/*
		for (Location location : locations) {
            System.out.println(location.getName() + " (woeid:" + location.getWoeid() + ")");
        }
        */		

		//the list of trends
		try {
			trends = twitter.getLocationTrends(woeid);
        	for (Trend trend : trends.getTrends()) {
        		FTwitterTrend FTTrend = new FTwitterTrend();
        		FTTrend.set(trends, trend);
			
        		if(returnType == STRING_ARRAY) {
        			//each element of the ArrayList will be a string[]
        			String[] FTTrendStr = FTTrend.asArray(); 
        			result.add( FTTrendStr );
        		} else if(returnType == OBJECT) {
        			result.add(FTTrend );
        		}
        	} //end trends loop
		} catch (TwitterException te) {
			System.out.println("Error geting Trends: " + te.getMessage());
			//te.printStackTrace();
		}

		return result;
	}
	
	
	
}