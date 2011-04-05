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
import processing.core.PVector;

public class FMeter extends FControlBase {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected String name;
	private float x,y,b,h;
	private int posMin, posMax, loose;
	private float pos, posNew, vMin, vMax;
	private float val;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FMeter(PApplet p5) {
		super(p5);
		loose = 3;
	}

	public FMeter(PApplet p5, String _name, float _x, float _y, float _b, float _h, float _vMin, float _vMax, float _val, PFont _typeface, int _labelType) {
		super(p5);
		setCoord(_x,_y);
		setSize(_b,_h);
		setName(_name);
		
		loose = 3;
		pos = (float) (x + b*0.5 - h*0.5);
		posNew = pos;
		posMin = (int) x;
		posMax = (int) (x + b);
		
		setValueRange(_vMin,_vMax);
		setValue(_val);

		typeface = _typeface;
		labelType = _labelType;
		label.set( (x+b)+5,y, typeface);
	}

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void create() {
		update();
		drag();
		
		p5.noStroke();
		p5.fill( getColorInactive() );
		p5.rect(x,y, b,h);
		
		p5.fill( getColorActive() );
		p5.rect(x, y, pos-x, h);

		if( LOCKED ) p5.fill( getColorActive() );
		else p5.fill( getColorInactive() );

		p5.rect(pos,y, 3,h);
		
		if(showLabels) {
			if(labelType == LABEL_FLOAT) label.create( getStrValue( getFloatValue(),2 ) );
			else if(labelType == LABEL_INT) label.create( getStrValue( getIntValue() ) );
		}
	}

	//-----------------------------------------------------------------------------
	//interaction
	//-----------------------------------------------------------------------------
	private void update() {
		if( getOver() && PRESSED ) LOCKED = true;
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
				posNew = PApplet.constrain( snap( (float) (MOUSE_X - (h * 0.5)), SNAP_INC, -5), posMin, posMax);;
			} else {
				posNew = PApplet.constrain((float) (MOUSE_X - (h * 0.5)), posMin, posMax);
			}
		}
		if(PApplet.abs(posNew - pos) > 1) {
			pos = pos + (posNew-pos)/loose;
		}
	}


	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	public void setName(String _name) {
		name = _name;
	}

	public void setCoord(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public void setSize(float _b, float _h) {
		b = _b;
		h = _h;
	}

	public void setValue(float _val) {
		val = _val;
		posNew = PApplet.map(val, vMin,vMax, posMin,posMax);
	}

	public void setValueRange(float _vMin, float _vMax) {
		vMin = _vMin;
		vMax = _vMax;
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

	public float getFloatValue() {
		val = PApplet.map(posNew, posMin,posMax, vMin,vMax);
		//return f;
		return val;
	}

	public int getIntValue() {
		val = (int) PApplet.map(posNew, posMin,posMax, vMin,vMax);
		//return f;
		return (int) val;
	}

	public float getValueMin() {
		return vMin;
	}

	public float getValueMax() {
		return vMax;
	}
}
