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
import processing.core.PConstants;

//import java.awt.Font;

public class FLabel implements PConstants {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected static PApplet p5;

	protected String text;
	private float x,y;

	protected static PFont typefaceLabel[] = new PFont[2];
	protected static float typefaceSize;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	FLabel(PApplet thePApplet) {
		p5 = thePApplet; 
	}

	FLabel(PApplet p5, float _x, float _y, PFont _typefaceLabel) {
		setTypeface(_typefaceLabel);
		setCoord(_x, _y);
	}

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void create(String _text) {
		p5.textFont(typefaceLabel[1]);
		p5.textAlign(LEFT);
		setText(_text);
		p5.text(text, x,y);
	}

	public void create(String _text, int _style) {
		p5.textFont(typefaceLabel[ _style ]);
		p5.textAlign(LEFT);
		setText(_text);
		p5.text(text, x,y);
	}
	
	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	public void set(float _x, float _y, PFont _typefaceLabel) {
		setTypeface(_typefaceLabel);
		setCoord(_x, _y);
	}

	public void set(float _x, float _y, PFont _typefaceLabel[]) {
		setTypeface(_typefaceLabel);
		setCoord(_x, _y);
	}

	public void set(float _x, float _y, PFont _typefaceLabel, PFont _typefaceLabelBold) {
		setTypeface(_typefaceLabel,_typefaceLabelBold);
		setCoord(_x, _y);
	}

	protected void setTypeface(PFont _typefaceLabel) {
		typefaceLabel[0] = _typefaceLabel;
		typefaceLabel[1] = _typefaceLabel;
		typefaceSize = typefaceLabel[0].getFont().getSize();
	}

	protected void setTypeface(PFont _typefaceLabel[]) {
		typefaceLabel[0] = _typefaceLabel[0];
		typefaceLabel[1] = _typefaceLabel[1];
		typefaceSize = typefaceLabel[0].getFont().getSize();
	}

	protected void setTypeface(PFont _typefaceLabel, PFont _typefaceLabelBold) {
		typefaceLabel[0] = _typefaceLabel;
		typefaceLabel[1] = _typefaceLabelBold;
		typefaceSize = typefaceLabel[0].getFont().getSize();
	}
	
	public void setText(String _text) {
		text = _text;
	}

	public void setCoord(float _x, float _y) {
		x = _x;
		y = (float) ((float) _y + ( getTypeSize() * 0.8 ));
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	public float getTypeSize() {
		float val = typefaceSize;
		return val;
	}

}
