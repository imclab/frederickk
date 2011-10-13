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


import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

public class FDropDown extends FControlBase {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	private ArrayList<FKnob> FItems = new ArrayList<FKnob>();
	protected String name;
	private float x,y,w,h;
	private int val = -1;

	private boolean OPEN = true;
	
	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	public FDropDown(PApplet p5) {
		super(p5);
	}
	
	public FDropDown(PApplet p5, String _name, float _x, float _y, float _w, float _h, PFont _typeface[]) {
		super(p5);
		typeface = _typeface;
		labelInfo.setTypeface(typeface);

		setName(_name);
		setSize(_w,_h);
		setCoord(_x,_y);
	}

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void create() {
		update();
		toggle();
		
		//-----------------------------------------
		if( OPEN ) {
			p5.noFill();
			p5.stroke( getColorInactive() );
			p5.rect(x,y, w,h*(FItems.size()+1) );

			for(int i=0; i<FItems.size(); i++) {
				//items
				FKnob fk = (FKnob) FItems.get(i);
				fk.setCoord( x,y+(h*(i+1)) );
				fk.setSize(w,h);
				fk.setColorActive( colorActive );
				fk.setColorInactive( colorInactive );
				fk.showLabels( true );
				fk.createItem();
	
				//background
				p5.noFill();
				p5.stroke( getColorInactive() );
				p5.rect(x,y+(h*(i+1)), w,h);
			}
		}

		//-----------------------------------------
		p5.fill( getColorActive() );
		p5.rect(x,y, w,h);

		//-----------------------------------------
		if(showLabels) {
			p5.fill( white );
			labelInfo.create( name, BOLD );
		}
	}	

	public void addItem(String _name) {
		FItems.add(new FKnob(p5, _name, x,y, w,h, typeface, LABEL_INT) );
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
			OPEN = !OPEN;
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

		float typeOff = PApplet.abs( h - typeface[0].getFont().getSize() );
		labelInfo.setCoord( x+5,y+(typeOff/2) );
	}

	public void setSize(float _w, float _h) {
		w = _w;
		h = _h;
	}

	public void setName(String _name) {
		name = _name;
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
	
	protected int getSelection() {
		for(int i=0; i<FItems.size(); i++) {
			FKnob fk = (FKnob) FItems.get(i);
			if(fk.LOCKED) val = i;
		}
		return val;
	}

}
