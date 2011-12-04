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
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;



public class FDropDown extends FControlBase {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private ArrayList<FButton> FItems = new ArrayList<FButton>();
	private int val = -1;

	private boolean OPEN = true;


	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FDropDown(PApplet p5) {
		super(p5);
	}
	
	public FDropDown(PApplet p5, String _name, float _x, float _y, int _w, int _h) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setCoord(_x,_y);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	protected void update() {
		if( getOver() && PRESSED ) LOCKED = true;
	}


	//-----------------------------------------------------------------------------
	public void draw() {
		update();
		toggle();
		
		//-----------------------------------------
		// controller
		//-----------------------------------------
		p5.pushStyle();
		if( OPEN ) {
			p5.noFill();
			p5.stroke( getColorInactive() );
			p5.rect(x,y, width,height*(FItems.size()+1) );

			for(int i=0; i<FItems.size(); i++) {
				//items
				FButton fb = (FButton) FItems.get(i);
				fb.setCoord( x,y+(height*(i+1)) );
				fb.setSize(width,height);
				fb.setColorOver( colorOver );
				fb.setColorInactive( colorInactive );
				fb.showLabels( true );
				//fb.drawItem();
	
				//background
				p5.noFill();
				p5.stroke( getColorInactive() );
				p5.rect(x,y+(height*(i+1)), width,height);
			}
		}

		//-----------------------------------------
		p5.fill( getColorOver() );
		p5.rect(x,y, width,height);

		
		//-----------------------------------------
		// label
		//-----------------------------------------
		if(showLabels) {
			int a = (getColorInactive() >> 24) & 0xFF;
			p5.fill( white, a );
			labelName.draw( name ); // PApplet.CENTER, BOLD );
		}
		p5.popStyle();
	}	


	//-----------------------------------------------------------------------------
	private void toggle() {
		if( LOCKED ) {
			OPEN = !OPEN;
			LOCKED = !LOCKED;
			OVER = !OVER;
			PRESSED = !PRESSED;
		}
	}


	//-----------------------------------------------------------------------------
	public void addItem(String _name) {
		FItems.add(new FButton(p5, _name, x,y, width,height, LABEL_INT) );
	}


	
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	protected int getSelection() {
		for(int i=0; i<FItems.size(); i++) {
			FButton fb = (FButton) FItems.get(i);
			if(fb.LOCKED) val = i;
		}
		return val;
	}

}
