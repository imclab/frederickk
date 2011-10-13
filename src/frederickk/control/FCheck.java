package frederickk.control;

/*
 *  Frederickk.Control 003
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

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

public class FCheck extends FControlBase {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected String name;
	private float sz;
	private boolean val;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FCheck(PApplet p5) {
		super(p5);
		val = false;
	}

	public FCheck(PApplet p5, String _name, float _x, float _y, float _sz, boolean _val, PFont _typeface[]) {
		super(p5);
		typeface = _typeface;
		labelInfo.setTypeface(typeface);

		setName(_name);
		setCoord(_x,_y);
		setSize(_sz);
		setValue(_val);
	}

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void create() {
		update();
		toggle();
		
		p5.noFill();
		if( LOCKED ) p5.stroke( getColorActive() );
		else p5.stroke( getColorInactive() );
		p5.rect(x,y, sz, sz);

		// x'd
		//-----------------------------------------
		if(val) {
			p5.stroke( getColorActive() );
			p5.line(x,y, x+sz, y+sz);
			p5.line(x+sz,y, x, y+sz);
		}

		//-----------------------------------------
		if(showLabels) {
			labelInfo.create( name );
		}
	}

	
	//-----------------------------------------------------------------------------
	//interaction
	//-----------------------------------------------------------------------------
	protected void update() {
		if( getOver() && PRESSED ) LOCKED = true;
	}

	protected boolean getOver() {
		if(MOUSE_X > x && MOUSE_X < x+w && 
		   MOUSE_Y > y && MOUSE_Y < y+h) {
			OVER = true;
		} else {
			OVER = false;
		}
		return OVER;
	}

	private void toggle() {
		if( LOCKED ) {
			val = !val;
			
			LOCKED = !LOCKED;
			OVER = !OVER;
			PRESSED = !PRESSED;
		}
	}


	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	public void setCoord(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public void setSize(float _sz) {
		sz = _sz;
		w = _sz;
		h = _sz;
	}

	public void setName(String _name) {
		name = _name;
	}

	public void setValue(boolean _val) {
		val = _val;
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

	public boolean getValue() {
		return val;
	}

}
