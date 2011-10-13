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



public interface FTwitterConstants {
	public static String VERSION = "0.0.2";
	
	//-----------------------------------------------------------------------------
	// constants
	//-----------------------------------------------------------------------------
	public static final int ASYNC = 0;
	public static final int NORMAL = 1;

	public static final int STRING_ARRAY = 2;	
	public static final int OBJECT = 3;	
	public static final int FTTWEET = 4;	
	public static final int FTSTATUS = 5;	

	

	//-----------------------------------------------------------------------------
	// defaults
	//-----------------------------------------------------------------------------
	static final int DEFAULT_TWEET_COUNT = 20;
	static final String DEFAULT_LANGUAGE = "en";

	
	//-----------------------------------------------------------------------------
	// services
	//-----------------------------------------------------------------------------
	static final String PUBLIC_TIMELINE_URL = "http://twitter.com/1/statuses/public_timeline.xml";
	static final String USER_TIMELINE_URL = "http://api.twitter.com/1/statuses/user_timeline";

}