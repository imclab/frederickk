//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import frederickk.api.twitter.*;
import frederickk.tools.*;
import frederickk.control.*;


//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FPalette palette;
TwitterThread tt;

String txt = "";
PFont typeface;

void setup() {
  size(800,600);

  //implement twitter thread
  String consumer_key = "6rfv7jUxsJws68FehxkHLw";
  String consumer_secret = "qXib0btjWcqoBN0BK7833477LrvHGGWcRQ7UUU0w";
  String access_token = "31505435-rDNPw6UT3ILcIDzREG5OroJx8ljhjDE5uU5mNB8c";
  String access_secret = "XH1ndOolyYuAg5phYaFKhGUVmNs8Rd54HlJWhLjn6I";

  tt = new TwitterThread(this, consumer_key, consumer_secret, access_token, access_secret, 2000);
  tt.start();

  //gui implementation
  palette = new FPalette(this, "/data/palette/palette.png");
  typeface = createFont("FuturaOTKF-Bold", 30);
  textFont(typeface);

}

void draw() {
  background(palette.getColor(1));
  frame.setTitle(str(frameRate));  
 
  //
  if( tt.getList().size() > 0 ) {
    txt = "";
    String[] tweet = (String[]) tt.getList().get(0);
    for(int i=0; i<tweet.length; i++) {
      txt += i + "\t" + tweet[i] + "\n";
    }
  }

  //display the results
  fill(palette.getColor(2));
  text(txt, 15,15, width-30,height-30);
}



