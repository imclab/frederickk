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



public class FButton extends FControlBase {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 004L;

	private int mode;
	private String filePath = "";

	
	
	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FButton(PApplet p5) {
		super(p5);
		setMode(BUTTON_NORMAL);
	}

	public FButton(PApplet p5, String _name, float _x, float _y, int _w, int _h) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setCoord(_x,_y);
		//setLabelType(_labelType);
		setMode(BUTTON_NORMAL);
	}

	public FButton(PApplet p5, String _name, float _x, float _y, int _w, int _h, int _mode) {
		super(p5);
		setName(_name);
		setSize(_w,_h);
		setCoord(_x,_y);
		//setLabelType(_labelType);
		setMode(_mode);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	protected void update() {
		if( getOver() && PRESSED ) {
			LOCKED = true;
			if(mode == BUTTON_LOAD) filePath = p5.selectInput(name);
		}
	}


	//-----------------------------------------------------------------------------
	public void draw() {
		update();


		//-----------------------------------------
		// controller
		//-----------------------------------------
		p5.pushStyle();
		p5.noStroke();

		if( getOver() ) p5.fill( getColorOver() );
		else if( getOver() && LOCKED ) p5.fill( getColorPressed() );
		else p5.fill( getColorInactive() );
		
		p5.rect(x,y, width,height);

		
		//-----------------------------------------
		// label
		//-----------------------------------------
		if(showLabels) {
			int a = (getColorInactive() >> 24) & 0xFF;
			p5.fill( white, a );
			labelName.setCoord(x,y);
			labelName.draw(name, PApplet.CENTER, BOLD);
		}
		p5.popStyle();
	}


	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	public void setMode(int _val) {
		mode = _val;
	}

	
	
	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	public int getMode() {
		return mode;
	}

	public String getFilePath() {
		return filePath;
	}
	
}
