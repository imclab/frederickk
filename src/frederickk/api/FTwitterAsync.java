package frederickk.api;

/**
 *  FTwitterAsync.java
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
 *



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
//twitter4j core
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterMethod;

//twitter4j async
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;

import processing.core.*;



public class FTwitterAsync extends FTwitter {
	// -----------------------------------------------------------------------------
	// properties
	// -----------------------------------------------------------------------------
	static final Object LOCK = new Object();
	AsyncTwitterFactory factory;
	AsyncTwitter twitterAsync;



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
	 *
	public FTwitterAsync(PApplet papplet) {
		super(papplet);
		factory = new AsyncTwitterFactory();
		twitterAsync = factory.getInstance();
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
	 *
	public FTwitterAsync(PApplet papplet, String _ConsumerKey, String _ConsumerSecret, String _AccessToken, String _AccessTokenSecret) {
		super(papplet, _ConsumerKey,_ConsumerSecret, _AccessToken,_AccessTokenSecret);
		factory = new AsyncTwitterFactory();
		twitterAsync = factory.getInstance();
	}



	// -----------------------------------------------------------------------------
	// methods
	// -----------------------------------------------------------------------------
	public void update() throws InterruptedException {
		twitterAsync.addListener(new TwitterAdapter() {
			/*
			@Override
			public void updatedStatus(Status status) {
				System.out.println("Successfully updated the status to [" + status.getText() + "].");
				synchronized (LOCK) {
					LOCK.notify();
				}
			}
			*
			
			@Override
			public void gotPublicTimeline(ResponseList<Status> statuses)  {
				System.out.println("Showing public timeline.");
				for (Status status : statuses) {
					System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				}
				synchronized (LOCK) {
					LOCK.notify();
				}
			}

			@Override
			public void onException(TwitterException e, TwitterMethod method) {
				if (method == UPDATE_STATUS) {
					e.printStackTrace();
					synchronized (LOCK) {
						LOCK.notify();
					}
				} else {
					synchronized (LOCK) {
						LOCK.notify();
					}
					throw new AssertionError("Should not happen");
				}
			}
		});

		//twitterAsync.updateStatus(args[0]);
		synchronized (LOCK) {
			LOCK.wait();
		}		
	}
	
}
*/