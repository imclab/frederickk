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
import java.awt.Color;

//public class FPalette extends FDateiLeser {
public class FPalette {

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	public PImage bild;
	public int[] farbe;

	private String pfad = "";
	private int loc;

	//private boolean RICHTUNG = true; //default direction is TOP_BOTTOM
	
	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FPalette(
			PApplet papplet,
			String p) {

		setPfad(p);

		p5 = papplet;

		bild = p5.loadImage( p5.sketchPath + pfad);
		bild.loadPixels();

		farbe = new int[bild.width*bild.height];
		
		int index = 0;
		//top to bottom
		//System.out.println("TOP_BOTTOM");
		for (int i=0; i<bild.width; i++) {
			for (int j=0; j<bild.height; j++) {
				//-----------------------------------------------------------------------------
				//get the color and location of images pixels
				//-----------------------------------------------------------------------------
				loc = i + j*bild.width; 
				farbe[index] = bild.pixels[loc];
				
				index++;
			}  
		}

	
	}

	//-----------------------------------------------------------------------------
	//helpers
	//-----------------------------------------------------------------------------
	/**
	 * @param w
	 *          value to keep within the range of colors created
	 */
	private int inBounds(int w) {
		w = PApplet.constrain(w, 0,getAnzahl());
		return w;
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param pfad
	 *          set the palette base image path
	 */
	public void setPfad(String pfad) {
		this.pfad = pfad;
	}


	/**
	 * @param wert
	 *          set the direction of color gathering TOP_BOTTOM or LEFT_RIGHT
	 */
	/*
	public void setRichtung(String wert) {
		if(wert == "TOP_BOTTOM") {
			RICHTUNG = true;
		} else if(wert == "LEFT_RIGHT") {
			RICHTUNG = false;
		} else {
			RICHTUNG = true;
			System.out.println("invalid: default TOP_BOTTOM");
		}
	}
	*/

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return the palette base image path  
	 * 
	 * @return pfad
	 */
	public String getPfad() {
		return pfad;
	}

	/**
	 * return all of the created colors as an array 
	 * 
	 * @return farbe
	 */
	public int[] getFarben() {
		return farbe;
	}

	/**
	 * return selected color of the created colors 
	 * 
	 * @return farbe[]
	 */
	public int getFarbe(int w) {
		return farbe[w];
	}

	/**
	 * return number of colors available 
	 * 
	 * @return f
	 */
	public int getAnzahl() {
		int f = farbe.length;
		return f;
	}

	/**
	 * @param w
	 *          number of color to use
	 * @param wert
	 *          percentage of transparency 0.0 - 1.0
	 */
	public int getFarbeTrans(int w, float wert) {
		w = inBounds(w);
		Color color = new Color( farbe[w] );

		int f = color.getRGB();
		float r =  ( f >> 16 ) & 0xFF;
		float g = ( f >> 8 ) & 0xFF;
		float b = f & 0xFF;

		Color colorHold = new Color(r,g,b, 255*wert);
		int farbeTrans = colorHold.getRGB();
		return farbeTrans;
	}

}
