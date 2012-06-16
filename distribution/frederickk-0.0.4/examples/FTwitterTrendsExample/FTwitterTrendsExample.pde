import com.maxmind.geoip.*;  // http://www.maxmind.com/app/java
import java.net.InetAddress;

import frederickk.api.twitter.*;
import frederickk.tools.*;
import frederickk.control.*;

/**
 *  FTwitter Trends Example
 *  Ken Frederick
 * 
 *  Example showing the usage of FTwitter
 *  highlighing how to pull a feed of trends based
 *  on location using an IP address
 *  (work in progress)
 *
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FPalette palette;
TwitterThread tt;

String txt = "";

LookupService cl;
String ipaddress = "92.226.106.106";
double location[] = new double[2];

PFont typeface;



//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  size(800,600);

  loadGeoIP(); 
  CityLookup(ipaddress);

  tt = new TwitterThread(this);
  tt.start();
  palette = new FPalette(this, "/data/palette/palette.png");

  typeface = createFont("FuturaOTKF-Bold", 30);
  textFont(typeface);

}

//-----------------------------------------------------------------------------
void draw() {
  background(palette.getColor(1));
  frame.setTitle(str(frameRate));  

  txt = "";
  //println(tt.getList().size());
  for(int i=0; i<tt.getList().size(); i++) {
    String trend[] = (String[]) tt.getList().get(i);
    txt += trend[0]+"\n";
  }


  fill(palette.getColor(2));
  text(txt, 15,15, width-30,height-30);
}



/**
 *  in order to properly look up location based on IP address
 *  you will need to download the following files from:
 *  http://www.maxmind.com/app/geolite
 *
 *  put these in this sketch's "data" folder
 *  "GeoIP.dat"
 *  "GeoLiteCity.dat"
 *
 *  download this http://www.maxmind.com/app/java
 *  and place the files within this sketch's "code" folder
 *  or within the "libraries" folder
 */
void loadGeoIP() {
  try {
    cl = new LookupService(dataPath("GeoLiteCity.dat"), LookupService.GEOIP_INDEX_CACHE);

    InetAddress   in  = InetAddress.getLocalHost();
    InetAddress[] all = InetAddress.getAllByName(in.getHostName());
    //ipaddress = all[1].getHostAddress().toString();

    /*
    for (int i=0; i<all.length; i++) {
      System.out.println(i + "\taddress\t" + all[i].getHostAddress().toString());
    }
    */

  } catch (IOException e) {
    println("IO Exception:" + e);
  }
}

void CityLookup(String address) {
  try {
    Location loc = cl.getLocation(address);
    location[0] = loc.latitude;
    location[1] = loc.longitude;
    
    println("countryCode:\t" + loc.countryCode +
      "\ncountryName:\t" + loc.countryName +
      "\nregion:\t\t" + loc.region +
      "\nregionName:\t\t" + regionName.regionNameByCode(loc.countryCode, loc.region) +
      "\ncity:\t\t" + loc.city +
      "\npostalCode:\t\t" + loc.postalCode +
      "\nlatitude:\t\t" + loc.latitude +
      "\nlongitude:\t\t" + loc.longitude +
      "\nmetro code:\t\t" + loc.metro_code +
      "\narea code:\t\t" + loc.area_code +
      "\ntimezone:\t\t" + timeZone.timeZoneByCountryAndRegion(loc.countryCode, loc.region));
    cl.close();
  
  } catch (Exception e) {
    println("Exception: " + e);
  }

}


