package frederickk.control;

/*
 *  Frederickk.Control 002
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
import processing.core.PFont;
//import java.awt.Color;
import java.awt.event.MouseEvent;

public class FControlBase implements FControlConstants {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected static PApplet p5;

	protected boolean OVER, CLICK, PRESSED, LOCKED, DRAG, RELEASE, SNAP;
	protected float MOUSE_X, MOUSE_Y;
	protected float MOUSE_X_NEW, MOUSE_Y_NEW;
	protected float SNAP_INC;

	protected static PFont typeface;
	protected FLabel label;
	protected boolean showLabels;
	protected int labelType;

	protected static int colorInactive;
	protected static int colorActive;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FControlBase(PApplet thePApplet) {
		p5 = thePApplet;

		p5.registerMouseEvent(this);
		label = new FLabel(p5);

		OVER = false;
		PRESSED = false;
		LOCKED = false;
		RELEASE = true;
		SNAP = false;
	}	

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	//quick and dirty, eventually move this to a different spot
	protected double roundDecimal(float orig, int deci) {
		  double multi = Math.pow(10,deci);
		  double num = Math.round(orig * multi)/multi;
		  return num;
	}	

	protected int snap(float _val, float _inc, float _offset) {
		  float sf = ((int) (_val/_inc) * _inc) + _offset;

		  int s = (int) sf;
		  return s;
	}
	
	public void showLabels(boolean _showLabels) {
		showLabels = _showLabels;
	}
	public void showLabels(boolean _showLabels, int _labelType) {
		showLabels = _showLabels;
		labelType = _labelType;
	}

	//-----------------------------------------------------------------------------
	//interaction
	//-----------------------------------------------------------------------------
	/**
	 * capture mouse events
	 * 
	 */
	public void mouseEvent(
	        MouseEvent event) {
		MOUSE_X = event.getX();
		MOUSE_Y = event.getY();

		if (event.getID() == MouseEvent.MOUSE_CLICKED) {
			CLICK = true;
			RELEASE = false;
		} 

		if (event.getID() == MouseEvent.MOUSE_PRESSED) {
			PRESSED = true;
			RELEASE = false;
		} 

		if (event.getID() == MouseEvent.MOUSE_RELEASED) {
			PRESSED = false;
			RELEASE = true;
			LOCKED = false;
		} 

	}

		
	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _colorInactive
	 *          the inactive color of all gui elements
	 */
	public void setColorInactive(int _colorInactive) {
		colorInactive = _colorInactive;
	}
	/**
	 * @param _colorActive
	 *          the active color of all gui elements
	 */
	public void setColorActive(int _colorActive) {
		colorActive = _colorActive;
	}

	/**
	 * @param _val
	 *          enable element value snap 
	 */
	public void enableSnap(float _val) {
		SNAP_INC = _val;
		SNAP = true;
	}
	/**
	 * disable element value snap 
	 *          
	 */
	public void disableSnap() {
		SNAP = false;
	}		

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	public int getColorInactive() {
		return colorInactive;
	}
	public int getColorActive() {
		return colorActive;
	}

	//singular
	protected String getStrValue(float _input, int deci) {
		String val = "";
		val = Float.toString( (float) roundDecimal( _input,deci ) );
		return val;
	}
	protected String getStrValue(float _input) {
		String val = "";
		val = Integer.toString( (int) _input );
		return val;
	}
	
	//plural
	protected String getStrValue(float _x, float _y, int deci) {
		String val = "";
		val += "x " + Float.toString( (float) roundDecimal( _x,deci ) ) + "\n";
		val += "y " + Float.toString( (float) roundDecimal( _y,deci ) ) + "\n";
		//val += "z " + Float.toString( (float) roundDecimal( getValue().x,deci ) );
		return val;
	}
	protected String getStrValue(float _x, float _y) {
		String val = "";
		val += "x " + Integer.toString( (int) _x ) + "\n";
		val += "y " + Integer.toString( (int) _y ) + "\n";
		//val += "z " + Float.toString( (float) roundDecimal( getValue().x,deci ) );
		return val;
	}
	
	
}
