package frederickk.tools;

/*
you can put a one sentence description of your library here.

(c) copyright

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General
Public License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place, Suite 330,
Boston, MA  02111-1307  USA
 */

import processing.core.PApplet;

public class FZeit {
	// -----------------------------------------------------------------------------
	// properties
	// -----------------------------------------------------------------------------
	public static PApplet p5;
	private String[] datum = new String[3];
	private String[] uhr = new String[3];

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FZeit
	 * 
	 * @param thePApplet
	 * 			PApplet
	 */
	public FZeit(
			PApplet thePApplet) {

		p5 = thePApplet;
	}
	
	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return the current date as string "yymmdd"
	 * 
	 * @return datum
	 */
	public String datum() {
		datum[0] = String.valueOf( PApplet.year() );
		datum[1] = String.valueOf( PApplet.month() );
		datum[2] = String.valueOf( PApplet.day() );

		for (int i = 0; i != datum.length; i++) {
			if (datum[i].length() == 1)
				datum[i] = "0" + datum[i];
		}

		return datum[0] + datum[1] + datum[2];
	}

	/**
	 * return the current time as string "hhmmss"
	 * 
	 * @return uhr
	 */
	public String uhr() {
		uhr[0] = String.valueOf( PApplet.hour() );
		uhr[1] = String.valueOf( PApplet.minute() );
		uhr[2] = String.valueOf( PApplet.second() );

		for (int i = 0; i != uhr.length; i++) {
			if (uhr[i].length() == 1)
				uhr[i] = "0" + uhr[i];
		}

		return uhr[0] + uhr[1] + uhr[2];
	}
}
