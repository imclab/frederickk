package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FMeter.java
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



public class FMeter extends FSlider {
	/**
	 * 
	 * FMeter depreceated use FSlider
	 *
	 */

	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FMeter(PApplet p5) {
		super(p5);
	}

	public FMeter(PApplet p5, String _name, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val, int _labelType) {
		super(p5, _name, _x,_y, _w,_h, _vMin,_vMax, _val, _labelType);
	}

}
