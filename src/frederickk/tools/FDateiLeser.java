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
import processing.core.PImage;
import java.io.File;

public class FDateiLeser {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	private File ordner;
	private PImage[] bild;

	private String[] daten;
	//private String ordnerName;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * @param papplet
	 * 			PApplet
	 * @param ordnerName
	 *          the path to load
	 */
	public FDateiLeser(
			PApplet papplet,
			String ordnerName) {

		p5 = papplet;
		setOrdner(p5, ordnerName);
	}

	//-----------------------------------------------------------------------------
	//helpers
	//-----------------------------------------------------------------------------
	/**
	 * @param papplet
	 * 			PApplet
	 * @param ordnerName
	 *          the path to load
	 */
	private void setOrdner(
			PApplet papplet,
			String ordnerName) {

		p5 = papplet;

		setOrdnerName(p5.sketchPath + ordnerName);

		//if(ordner.isFile()) {
		boolean ds;
		String dsTest = ordner.list()[0];

		if(dsTest.equals(".DS_Store") == true) {
			daten = new String[ordner.list().length-1];
			ds = true;
		} else {
			daten = new String[ordner.list().length];
			ds = false;
		}

		if(daten != null || daten.length != 0) {
			bild = new PImage[daten.length];

			if(ds) {
				for(int i=0; i != ordner.list().length-1; i++) {
					daten[i] = ordner.list()[i+1];
					bild[i] = p5.loadImage(ordnerName + "/" + daten[i]);
					System.out.println("loading image " + daten[i]);
				}

			} else if(!ds) {
				for(int i=0; i != ordner.list().length; i++) {
					daten[i] = ordner.list()[i];
					bild[i] = p5.loadImage(ordnerName + "/" + daten[i]);
					System.out.println("loading image " + daten[i]);
				}
			}

		}

		/*
		} else {
			System.out.println(ordner + " is not a file");
		}
		 */
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param ordnerName
	 *          set the file to read
	 */
	private void setOrdnerName(String ordnerName) {
		ordner = new File(ordnerName);
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return number of files
	 * 
	 * @return d
	 */
	public int getDatenZahl() {
		int d = daten.length;
		return d;

	}

	/**
	 * return number of images 
	 * 
	 * @return b
	 */
	public int getBildZahl() {
		int b = bild.length;
		return b;
	}

	/**
	 * @param w
	 *          number of image to use
	 *          
	 * return a specific image
	 * 
	 * @return bild[w]
	 */
	public PImage getBild(int w) {
		w = inBounds(w);
		return bild[w];
	}

	/**
	 * @param w
	 *          number of file to use
	 *          
	 * return a specific file 
	 * 
	 * @return daten[w]
	 */
	public String getDaten(int w) {
		w = inBounds(w);
		return daten[w];
	}

	/**
	 * @param w
	 *          value to keep within the range of colors created
	 */
	private int inBounds(int w) {
		w = PApplet.constrain(w, 0,getBildZahl());
		return w;
	}

}


