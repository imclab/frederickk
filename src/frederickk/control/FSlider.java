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
import processing.core.PFont;
import processing.core.PVector;

public class FSlider extends FControlBase {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected String name;
	private float x,y,w,h;
	private int posMin, posMax, loose;
	private float pos, posNew, vMin, vMax;
	private float val;
	private int dir;
	
	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FSlider(PApplet p5) {
		super(p5);
		loose = 3;
	}

	public FSlider(PApplet p5, String _name, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val, PFont _typeface[], int _labelType) {
		super(p5);
		typeface = _typeface;
		labelVal.setTypeface(typeface);

		setSize(_w,_h);
		setCoord(_x,_y);
		setName(_name);
		
		loose = 3;
		if(_h > _w) setDirection(VERTICAL);
		else setDirection(HORIZONTAL);
		posNew = pos;
		
		setValueRange(_vMin,_vMax);
		setValue(_val);

		labelType = _labelType;
	}


	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void create() {
		update();
		drag();

		
		//-----------------------------------------
		p5.noStroke();
		p5.fill( getColorInactive() );
		p5.rect(x,y, w,h);

		//-----------------------------------------
		if( LOCKED ) p5.fill( getColorActive() );
		else p5.fill( getColorInactive() );

		if(dir == HORIZONTAL)	p5.rect(pos,y, 3,h);
		if(dir == VERTICAL)		p5.rect(x,pos, w,3);

		//-----------------------------------------
		if(showLabels) {
			labelInfo.create( name );

			p5.fill( white );
			if(labelType == LABEL_FLOAT) labelVal.create( getStrValue( getFloatValue(),2 ) );
			else if(labelType == LABEL_INT) labelVal.create( getStrValue( getIntValue() ) );

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
			if(dir == HORIZONTAL) {
				posNew = PApplet.constrain((float) (MOUSE_X - (h * 0.5)), posMin, posMax); 
				if( SNAP ) posNew = PApplet.constrain( snap( (float) (MOUSE_X - (h * 0.5)), SNAP_INC, -5), posMin, posMax);;
			} else if(dir == VERTICAL) {
				posNew = PApplet.constrain((float) (MOUSE_Y - (w * 0.5)), posMin, posMax); 
				if( SNAP ) posNew = PApplet.constrain( snap( (float) (MOUSE_Y - (w * 0.5)), SNAP_INC, -5), posMin, posMax);;
			}
		}

		if(PApplet.abs(posNew - pos) > 1) {
			pos = pos + (posNew-pos)/loose;
			MOVED = true;
		} else {
			MOVED = false;
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

		float typeOff = PApplet.abs( h - typeface[0].getFont().getSize() );
		labelVal.setCoord( x+5,y+(typeOff/2) );
		labelInfo.setCoord( x+(w+5),y+(typeOff/2) );
	}

	public void setSize(float _w, float _h) {
		w = _w;
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

	private void setDirection(int _dir) {
		dir = _dir;

		if(dir == HORIZONTAL) {
			pos = (float) (x + w*0.5 - h*0.5);
			posMin = (int) x;
			posMax = (int) (x + w);

		} else if(dir == VERTICAL) {
			pos = (float) (x + h*0.5 - w*0.5);
			posMin = (int) y;
			posMax = (int) (y + h);
		}

		posNew = pos;
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

