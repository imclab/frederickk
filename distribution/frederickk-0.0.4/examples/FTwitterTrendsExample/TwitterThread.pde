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
  public TwitterThread(PApplet parent) {
    parent.registerDispose(this);
    twitter = new FTwitter(parent);
    timer = new FTimer(parent, 10000);

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

  private void update() {
    println("checking for trends");
    result = twitter.getTrendsLocation(location[0], location[1]);
    //result = twitter.getTrends();
  }

  public void stop() {
    thread = null;
  }

  // this will magically be called by the parent once the user hits stop
  // this functionality hasn't been tested heavily so if it doesn't work, file a bug
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
  // methods
  // -----------------------------------------------------------------------------
  public ArrayList getList() {
    return result;
  }
} 
