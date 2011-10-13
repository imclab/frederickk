package frederickk.tools;

/*
 *  Frederickk.Tools 003
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a much simpler (for me anyway) processing GUI
 *  http://github.com/frederickk/frederickk
 *
 */


import java.util.Calendar;
//import java.util.GregorianCalendar;

public class FTime {

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return the current date as string "yyyyMMdd"
	 * 
	 * @return date
	 */
	public static String date() {
		Calendar cal = Calendar.getInstance();
		String[] date = new String[3];
		
		date[0] = String.valueOf( cal.get(Calendar.YEAR) );
		date[1] = String.valueOf( cal.get(Calendar.MONTH) + 1 );
		date[2] = String.valueOf( cal.get(Calendar.DATE) );

		for (int i = 0; i != date.length; i++) {
			if (date[i].length() == 1)
				date[i] = "0" + date[i];
		}

		return date[0] + date[1] + date[2];
	}

	/**
	 * return the current time as string "HHmmss"
	 * 
	 * @return time
	 */
	public static String time() {
		//Calendar cal = new GregorianCalendar();
		Calendar cal = Calendar.getInstance();
		String[] time = new String[3];

		time[0] = String.valueOf( cal.get(Calendar.HOUR_OF_DAY) ); 
		time[1] = String.valueOf( cal.get(Calendar.MINUTE) );
		time[2] = String.valueOf( cal.get(Calendar.SECOND) );

		for (int i = 0; i != time.length; i++) {
			if (time[i].length() == 1)
				time[i] = "0" + time[i];
		}

		return time[0] + time[1] + time[2];
	}
}
