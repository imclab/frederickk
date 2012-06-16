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



public class FCheck extends FControlBase {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;

	protected boolean val;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FCheck(PApplet p5) {
		super(p5);
		val = false;
	}

	public FCheck(PApplet p5, String _name, float _x, float _y, int _sz, boolean _val) {
		super(p5);
		setName(_name);
		setSize(_sz,_sz);
		setCoord(_x,_y);
		setValue(_val);
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
		p5.noFill();
		if( getOver() ) p5.stroke( getColorOver() );
		else if( LOCKED ) p5.stroke( getColorPressed() );
		else p5.stroke( getColorInactive() );
		p5.rect(x,y, width,height);

		
		//-----------------------------------------
		// x'd
		//-----------------------------------------
		if(val) {
			PVector xsz = new PVector((float) (width*0.6), (float) (height*0.6));
			p5.noStroke();
			p5.fill( getColorOver() );
			p5.ellipse(x+width/2,y+height/2, xsz.x,xsz.y);
		}


		//-----------------------------------------
		// label
		//-----------------------------------------
		if(showLabels) {
			p5.fill( getColorOver() );
			labelName.setSize(width*3,height);
			labelName.uncontained();
			labelName.draw(name, PApplet.LEFT, BOLD);
			//labelVal.draw("");
		}
		p5.popStyle();
	}


	//-----------------------------------------------------------------------------
	protected void toggle() {
		if( LOCKED ) {
			val = !val;
			
			LOCKED = !LOCKED;
			OVER = !OVER;
			PRESSED = !PRESSED;
		}
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setValue(boolean _val) {
		val = _val;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public boolean getValue() {
		return val;
	}

}
