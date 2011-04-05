package frederickk.tools;

/*
 *  Frederickk.Tools 002
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a much simpler (for me anyway) processing GUI
 *  http://code.google.com/p/frederickk/
 *
 */


import processing.core.PApplet;

public class FTime {

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;
	private String[] date = new String[3];
	private String[] time = new String[3];

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FTime
	 * 
	 * @param thePApplet
	 * 			PApplet
	 */
	public FTime(
			PApplet thePApplet) {

		p5 = thePApplet;
	}
	
	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return the current date as string "yymmdd"
	 * 
	 * @return date
	 */
	public String date() {
		date[0] = String.valueOf( PApplet.year() );
		date[1] = String.valueOf( PApplet.month() );
		date[2] = String.valueOf( PApplet.day() );

		for (int i = 0; i != date.length; i++) {
			if (date[i].length() == 1)
				date[i] = "0" + date[i];
		}

		return date[0] + date[1] + date[2];
	}

	/**
	 * return the current time as string "hhmmss"
	 * 
	 * @return time
	 */
	public String time() {
		time[0] = String.valueOf( PApplet.hour() );
		time[1] = String.valueOf( PApplet.minute() );
		time[2] = String.valueOf( PApplet.second() );

		for (int i = 0; i != time.length; i++) {
			if (time[i].length() == 1)
				time[i] = "0" + time[i];
		}

		return time[0] + time[1] + time[2];
	}
}
