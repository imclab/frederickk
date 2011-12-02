package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.4
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a collection of tools that i tend to use frequently
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Color;



public class FPalette {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	public PImage img;
	public int[] colors;

	private String pfad = "";
	private int loc;

	//private boolean RICHTUNG = true; //default direction is TOP_BOTTOM


	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FPalette
	 * 
	 * @param thePApplet
	 * 			PApplet
	 * @param p
	 *		  the path of the palette image to load
	 * 
	 */
	public FPalette(
			PApplet papplet,
			String p) {

		setPath(p);

		p5 = papplet;

		img = p5.loadImage( p5.sketchPath + pfad);
		img.loadPixels();

		colors = new int[img.width*img.height];
		
		int index = 0;
		//top to bottom
		//System.out.println("TOP_BOTTOM");
		for (int i=0; i<img.width; i++) {
			for (int j=0; j<img.height; j++) {
				//-----------------------------------------------------------------------------
				//get the color and location of images pixels
				//-----------------------------------------------------------------------------
				loc = i + j*img.width; 
				colors[index] = img.pixels[loc];
				
				index++;
			}  
		}

	
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * @param w
	 *		  value to keep within the range of colors created
	 */
	private int inBounds(int w) {
		w = PApplet.constrain(w, 0,getCount());
		return w;
	}

	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param pfad
	 *		  the path of the palette image to load
	 */
	public void setPath(String pfad) {
		this.pfad = pfad;
	}


	/**
	 * @param wert
	 *		  set the direction of color gathering TOP_BOTTOM or LEFT_RIGHT
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
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * return the palette base image path  
	 * 
	 * @return pfad
	 */
	public String getPath() {
		return pfad;
	}

	/**
	 * return all of the created colors as an array 
	 * 
	 * @return colors
	 */
	public int[] getColors() {
		return colors;
	}

	/**
	 * return selected color of the created colors 
	 * 
	 * @return colors[]
	 */
	public int getColor(int w) {
		return colors[w];
	}

	/**
	 * return number of colors available 
	 * 
	 * @return f
	 */
	public int getCount() {
		int f = colors.length;
		return f;
	}

	/**
	 * @param w
	 *		  number of color to use
	 * @param wert
	 *		  percentage of transparency 0.0 - 1.0
	 */
	public int getColorTrans(int w, float wert) {
		w = inBounds(w);
		Color color = new Color( colors[w] );

		int f = color.getRGB();
		float r =  ( f >> 16 ) & 0xFF;
		float g = ( f >> 8 ) & 0xFF;
		float b = f & 0xFF;

		Color colorHold = new Color(r,g,b, 255*wert);
		int farbeTrans = colorHold.getRGB();
		return farbeTrans;
	}

}

