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
	protected float x,y,z, x_new,y_new, w,h;
	protected PVector val = new PVector();
	protected boolean valSel;

	protected boolean DRAG_OFF = false;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FKnob(PApplet p5) {
		super(p5);
	}

	public FKnob(PApplet p5, String _name, float _x, float _y, float _w, float _h, PFont _typeface[], int _labelType) {
		super(p5);
		typeface = _typeface;
		labelVal.setTypeface(typeface);

		setName(_name);
		setSize(_w,_h);
		setCoord(_x,_y);

		labelType = _labelType;
	}

	public FKnob(PApplet p5, String _name, float _x, float _y, float _z, float _w, float _h, PFont _typeface[], int _labelType) {
		super(p5);
		typeface = _typeface;
		labelVal.setTypeface(typeface);

		setSize(_w,_h);
		setCoord(_x,_y,_z);
		setName(_name);

		labelType = _labelType;
	}

	
	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void create() {
		update();
		if(!DRAG_OFF) drag();

		//-----------------------------------------
		val.x = x;
		val.y = y;
		
		//-----------------------------------------
		if(showLabels) {
			if(labelType == LABEL_FLOAT) labelVal.create( getStrValue( val.x,val.y, 2 ) );
			else if(labelType == LABEL_INT) labelVal.create( getStrValue( val.x, val.y ) );
		}
		
		//-----------------------------------------
		p5.noStroke();
		if( getOver() || LOCKED ) p5.fill( getColorActive() );
		else p5.fill( getColorInactive() );
		p5.rect(x,y, w, h);
	}
	
	protected void createItem() {
		update();
		toggle();

		//-----------------------------------------
		p5.stroke( getColorInactive() );
		if( getOver() || LOCKED ) p5.fill( getColorActive() );
		else p5.fill( getColorInactive() );
		p5.rect(x,y, w, h);

		//-----------------------------------------
		if(valSel) {
			p5.fill( getColorActive() );
			p5.rect(x,y, 3,h);
		}
		
		//-----------------------------------------
		if(showLabels) {
			if( getOver() || LOCKED ) p5.fill( white );
			else p5.fill( getColorActive() );
			labelInfo.create( name );
			updateLabelCoord();
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

	private void drag() {
		if( LOCKED ) {
			if( SNAP ) {
				x_new = snap( (float) (MOUSE_X - (w * 0.5)), SNAP_INC, 0);
				y_new = snap( (float) (MOUSE_Y - (h * 0.5)), SNAP_INC, 0);
			} else {
				x_new = (float) (MOUSE_X - (w * 0.5));
				y_new = (float) (MOUSE_Y - (h * 0.5));
			}
		}

		if(PApplet.abs(x_new - x) > 1) x = x_new;
		if(PApplet.abs(y_new - y) > 1) y = y_new;
		if(PApplet.abs(x_new - x) > 1 || PApplet.abs(y_new - y) > 1) {
			MOVED = true;
		} else {
			MOVED = false;
		}
		
	}

	private void toggle() {
		if( LOCKED ) {
			valSel = !valSel;
			LOCKED = !LOCKED;
			OVER = !OVER;
			PRESSED = !PRESSED;
		}
	}
	
	private void updateLabelCoord() {
		float typeOff = PApplet.abs( h - labelVal.typefaceSize );
		labelVal.setCoord( x+(w+5),y+(typeOff/2) );
		labelInfo.setCoord( x+5,y+(typeOff/2) );
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

		float typeOff = PApplet.abs( h - labelVal.typefaceSize );
		labelVal.setCoord( x+(w+5),y+(typeOff/2) );
		labelInfo.setCoord( x+5,y+(typeOff/2) );
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

		float typeOff = PApplet.abs( h - labelVal.typefaceSize );
		labelVal.setCoord( x+(w+5),y+(typeOff/2) );
		labelInfo.setCoord( x+5,y+(typeOff/2) );
	}

	public void setSize(float _w, float _h) {
		w = _w;
		h = _h;
	}

	public void setName(String _name) {
		name = _name;
	}
	
	public void disableDrag(boolean _DRAG_OFF) {
		DRAG_OFF = _DRAG_OFF;
	}

	protected void setToggle() {
		valSel = !valSel;
	}
	protected void setToggle(boolean _valSel) {
		valSel = _valSel;
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

	protected boolean getToggle() {
		return valSel;
	}
}
