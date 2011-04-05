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
import processing.core.PVector;
import processing.core.PFont;

public class FKnob extends FControlBase {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected String name;
	private float x,y,z, x_new,y_new, b,h;
	private PVector val = new PVector();

	boolean dragOff = false;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FKnob(PApplet p5) {
		super(p5);
	}

	public FKnob(PApplet p5, String _name, float _x, float _y, float _b, float _h, PFont _typeface, int _labelType) {
		super(p5);
		setName(_name);
		setCoord(_x,_y);
		setSize(_b,_h);

		typeface = _typeface;
		labelType = _labelType;
		label.set( (x+b)+5,y, typeface);
	}

	public FKnob(PApplet p5, String _name, float _x, float _y, float _z, float _b, float _h, PFont _typeface, int _labelType) {
		super(p5);
		setName(_name);
		setCoord(_x,_y,_z);
		setSize(_b,_h);

		typeface = _typeface;
		labelType = _labelType;
		label.set( (x+b)+5,y, typeface);
	}

	
	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void create() {
		update();
		if(!dragOff) drag();

		val.x = x;
		val.y = y;
		
		if(showLabels) {
			label.setCoord( (x+b)+5,y + ( p5.textAscent() ) );
			if(labelType == LABEL_FLOAT) label.create( getStrValue( val.x,val.y, 2 ) );
			else if(labelType == LABEL_INT) label.create( getStrValue( val.x, val.y ) );
		}
		
		p5.noStroke();
		if( getOver() || LOCKED ) p5.fill( getColorActive() );
		else p5.fill( getColorInactive() );
		p5.rect(x, y, b, h);

	}

	//-----------------------------------------------------------------------------
	//interaction
	//-----------------------------------------------------------------------------
	private void update() {
		if( getOver() && PRESSED ) {
			LOCKED = true;
		}
	}

	protected boolean getOver() {
		if(MOUSE_X > x && MOUSE_X < x+b && 
		   MOUSE_Y > y && MOUSE_Y < y+h) {
			OVER = true;
		} else {
			OVER = false;
		}
		return OVER;
	}
	
	private void drag() {
		if( LOCKED ) {
			if( SNAP ) {
				x_new = snap( (float) (MOUSE_X - (b * 0.5)), SNAP_INC, 0);
				y_new = snap( (float) (MOUSE_Y - (h * 0.5)), SNAP_INC, 0);
			} else {
				x_new = (float) (MOUSE_X - (b * 0.5));
				y_new = (float) (MOUSE_Y - (h * 0.5));
			}
		}

		if(PApplet.abs(x_new - x) > 1) x = x_new;
		if(PApplet.abs(y_new - y) > 1) y = y_new;
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	public void setCoord(float _x, float _y) {
		x = _x;
		y = _y;

		x_new = x;
		y_new = y;

		val.x = x; 
		val.y = y;
	}

	public void setCoord(float _x, float _y, float _z) {
		x = _x;
		y = _y;
		z = _z;

		x_new = x;
		y_new = y;

		val.x = x; 
		val.y = y;
		val.z = z;
	}

	public void setSize(float _b, float _h) {
		b = _b;
		h = _h;
	}

	public void setName(String _name) {
		name = _name;
	}
	
	public void disableDrag(boolean _dragOff) {
		dragOff = _dragOff;
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	protected PVector getCoord() {
		PVector point = new PVector(0,0);
		point.x = x;
		point.y = y;
		return point;
	}
	
	public PVector getValue() {
		return val;
	}	

}
