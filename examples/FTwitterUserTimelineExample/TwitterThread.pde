/**
 *  until i figure out how to implement multi-threading
 *  inside of the library itself, this is my bootleg workaround
 */

public class TwitterThread implements Runnable {
  // -----------------------------------------------------------------------------
  // properties
  // -----------------------------------------------------------------------------
  private Thread thread;
  private FTwitter twitter;
  private FTimer timer;

  private ArrayList result = new ArrayList();
  protected boolean runThread;



  // -----------------------------------------------------------------------------
  // constructor
  // -----------------------------------------------------------------------------
  public TwitterThread(PApplet parent, int timing) {
    parent.registerDispose(this);

    twitter = new FTwitter(parent);
    timer = new FTimer(parent, timing);
    runThread = true;
  }
  public TwitterThread(PApplet parent, String _ConsumerKey, String _ConsumerSecret, String _AccessToken, String _AccessTokenSecret, int timing) {
    parent.registerDispose(this);

    twitter = new FTwitter(parent, _ConsumerKey,_ConsumerSecret, _AccessToken,_AccessTokenSecret);
    timer = new FTimer(parent, timing);
    runThread = true;
  }


  // -----------------------------------------------------------------------------
  // methods
  // -----------------------------------------------------------------------------
  public void start() {
    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    while(runThread) {
      if(timer.getTrigger()) update();
    }
  }
  public void stop() {
    thread = null;
  }
  public void dispose() {
    stop();
  }

  // -----------------------------------------------------------------------------
  public void stopThread() {
    runThread = false;
  }
  public void startThread() {
    runThread = true;
  }

  // -----------------------------------------------------------------------------
  private void update() {
    //println("checking for new tweets");
    
    //replace this with whatever method you're
    //looking to load from FTwitter
    result = twitter.getTimeline("GE_Deutschland");
  }



  // -----------------------------------------------------------------------------
  // methods
  // -----------------------------------------------------------------------------
  public ArrayList getList() {
    return result;
  }
} 
