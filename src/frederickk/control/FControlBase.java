package frederickk.control;

/*
 *  Frederickk.Control 0.0.4
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



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;



abstract public class FControlBase extends Rectangle implements FControlConstants {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;
	protected static PApplet p5;

	// constants
	protected boolean OVER, CLICK, PRESSED, LOCKED, DRAG, MOVED, RELEASE, SNAP;
	protected int MOUSE_X, MOUSE_Y, MOUSE_BUTTON;
	protected int MOUSE_X_NEW, MOUSE_Y_NEW;
	protected float SNAP_INC;

	// labels
	protected String name;

	protected FLabel labelVal;
	protected FLabel labelName;
	protected boolean showLabels;
	protected int labelType;

	// colors
//	protected static int colorInactive;
//	protected static int colorOver;
//	protected static int colorPressed;
//	protected static int white;
	protected int colorInactive;
	protected int colorOver;
	protected int colorPressed;
	protected int white;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FControlBase(PApplet thePApplet) {
		p5 = thePApplet;
		p5.registerMouseEvent(this);
		
		Color _white = new Color(255,255,255, 204);
		white = _white.getRGB();

		OVER = false;
		PRESSED = false;
		LOCKED = false;
		RELEASE = true;
		SNAP = false;
	}	



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	//quick and dirty, eventually move this to a different spot
	protected double roundDecimal(float orig, int deci) {
		double multi = Math.pow(10,deci);
		double num = Math.round(orig * multi)/multi;
		return num;
	}	

	protected int snap(float _val, float _inc, float _offset) {
		float sf = ((int) (_val/_inc) * _inc) + _offset;
		return (int) sf;
	}

	public void showLabels(boolean _showLabels) {
		showLabels = _showLabels;
	}
	public void showLabels(boolean _showLabels, int _labelType) {
		showLabels = _showLabels;
		setLabelType(_labelType);
	}

	protected abstract void update();
	abstract public void draw();



	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	/**
	 * capture mouse events
	 * 
	 */
	public void mouseEvent(MouseEvent event) {
		MOUSE_X = event.getX();
		MOUSE_Y = event.getY();
		MOUSE_BUTTON = event.getButton();


		// mouse over
		/*
		if(hitTest(MOUSE_X,MOUSE_Y)) {
			if(!OVER) {
				OVER = true;
			}
		} else if(OVER) {
			OVER = false;
		}
		*/


		// clicked
		if( event.getID() == MouseEvent.MOUSE_CLICKED) {
			if(hitTest(MOUSE_X,MOUSE_Y)) {
				CLICK = true;
			} else {
				// nothing
				//CLICK = false;
			}
		}


		// pressed
		if( event.getID() == MouseEvent.MOUSE_PRESSED) {
			if(hitTest(MOUSE_X,MOUSE_Y)) {
				PRESSED = true;
			} else {
				// nothing
				//PRESSED = false;
			}
		}


		// released
		if( event.getID() == MouseEvent.MOUSE_RELEASED) {
			if(hitTest(MOUSE_X,MOUSE_Y)) {
				// release inside
				RELEASE = true;
			} else {
				// release outside
				RELEASE = true;
			}
			LOCKED = false;
			PRESSED = false;
			CLICK = false;
		}
		
		
		// dragged
		if( event.getID() == MouseEvent.MOUSE_DRAGGED) {
			if(hitTest(MOUSE_X,MOUSE_Y)) {
				if(!OVER) {
					OVER = true;
				}
			} else {
				if(OVER) {
					OVER = false;
				}
				if(PRESSED) {
					// drag outside
					//LOCKED = false;

				}
			}
		}
		
		
		// moved
		if( event.getID() == MouseEvent.MOUSE_MOVED ) {
			if(hitTest(MOUSE_X,MOUSE_Y)) {
				if(!OVER) {
					OVER = true;
				}
			} else if(OVER) {
				OVER = false;
			}
		}
		
	}


	//-----------------------------------------------------------------------------
	protected boolean hitTest(int mx, int my) {
		return ((mx > x) && (mx < x + width) && (my > y) && (my < y + height));
	}


	//-----------------------------------------------------------------------------
	protected boolean getOver() {
		/*
		if(MOUSE_X > x && MOUSE_X < x+width && 
		   MOUSE_Y > y && MOUSE_Y < y+height) {
			OVER = true;
		} else {
			OVER = false;
		}
		*/
		return OVER;
	}
	protected boolean getClicked() {
		return CLICK;
	}
	protected boolean getPressed() {
		return PRESSED;
	}
	protected boolean getLocked() {
		return LOCKED;
	}
	protected boolean getMoved() {
		return MOVED;
	}
	protected boolean getReleased() {
		return RELEASE;
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _name
	 *		  the name of the gui element
	 */
	public void setName(String _name) {
		name = _name;
	}
	

	//-----------------------------------------------------------------------------
	/**
	 * @param _w
	 *		  width
	 * @param _h
	 *		  height
	 */
	public void setSize(int _w, int _h) {
		this.width = _w;
		this.height = _h;

	}
	public void setCoord(float _x, float _y) {
		this.x = (int) _x;
		this.y = (int) _y;
	}
	

	//-----------------------------------------------------------------------------
	/**
	 * @param _labelVal
	 *		  sets label for showing value
	 * @param _labelName
	 *		  sets label for showing name/info
	 */
	public void setLabels(FLabel _labelVal, FLabel _labelName) {
		labelVal = _labelVal;
		labelVal.setSize( width,height );
		labelVal.setCoord( x+5,y );

		labelName = _labelName;
		labelName.setSize( width,height );
		labelName.setCoord( x+(width+5),y );
	}

	/**
	 * @param _label
	 *		  sets generic label for showing value and name/info
	 */
	public void setLabels(FLabel _label) {
		setLabels(_label,_label);
	}

	public void setLabelType(int _labelType) {
		labelType = _labelType;
	}
	
	
//	/**
//	 * @param _typefaceLabel
//	 *		  sets regular typeface weight for labels
//	 * @param _typefaceLabelBold
//	 *		  sets bold typeface weight for labels
//	 */
//	public void setTypeface(PFont _typefaceLabel, PFont _typefaceLabelBold) {
//		labelVal.setTypeface( _typefaceLabel,_typefaceLabelBold );
//		labelName.setTypeface( _typefaceLabel,_typefaceLabelBold );
//	}
//
//	/**
//	 * @param _typefaceLabel
//	 *		  sets typeface weight for labels
//	 */
//	public void setTypeface(PFont _typefaceLabel) {
//		labelVal.setTypeface( _typefaceLabel );
//		labelName.setTypeface( _typefaceLabel );
//	}

	
	//-----------------------------------------------------------------------------
	/**
	 * @param _colorInactive
	 *		  the inactive color of all gui elements
	 */
	public void setColorInactive(int _colorInactive) {
		colorInactive = _colorInactive;
	}
	/**
	 * @param _colorOver
	 *		  the mouseOver color of all gui elements
	 */
	public void setColorOver(int _colorOver) {
		colorOver = _colorOver;
	}
	/**
	 * @param _colorPressed
	 *		  the mousePressed color of all gui elements
	 */
	public void setColorPressed(int _colorPressed) {
		colorPressed = _colorPressed;
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param _val
	 *		  enable element value snap 
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
	// gets
	//-----------------------------------------------------------------------------
	protected PVector getCoord() {
		return new PVector(x,y);
	}
	
	public int getColorInactive() {
		return colorInactive;
	}
	public int getColorOver() {
		return colorOver;
	}
	public int getColorPressed() {
		return colorPressed;
	}

	//-----------------------------------------------------------------------------
	// singular
	protected String getStrValue(float _input, int deci) {
		String val = "";
		val = java.lang.Float.toString( (float) roundDecimal(_input,deci) );
		return val;
	}
	protected String getStrValue(float _input) {
		String val = "";
		val = Integer.toString( (int) _input );
		return val;
	}


	//-----------------------------------------------------------------------------
	// plural
	protected String getStrValue(float _x, float _y, int deci) {
		String val = "";
		val += "x " + java.lang.Float.toString( (float) roundDecimal(_x,deci) ) + "\n";
		val += "y " + java.lang.Float.toString( (float) roundDecimal(_y,deci) ) + "\n";
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
