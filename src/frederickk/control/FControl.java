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
import processing.core.PConstants;

//import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Frame;
import java.util.ArrayList;



public class FControl implements PConstants,FControlConstants {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	protected static PApplet p5;
	//protected static Frame frame;

	
	// controllers
	private ArrayList<FButton> FButtons = new ArrayList<FButton>();
	private ArrayList<FHandle> FHandles = new ArrayList<FHandle>();
	private ArrayList<FHandle3D> FHandle3Ds = new ArrayList<FHandle3D>();
	private ArrayList<FCheck> FChecks = new ArrayList<FCheck>();
	private ArrayList<FSlider> FSliders = new ArrayList<FSlider>();
	private ArrayList<FKnob> FKnobs = new ArrayList<FKnob>();
	private ArrayList<FDropDown> FDropDowns = new ArrayList<FDropDown>();

	
	// values
	private boolean boolVal;
	private int intVal;
	private float floatVal;
	private int selVal;


	// colors
//	private static int colorInactive;
//	private static int colorOver;
//	private static int colorPressed;
	private int colorInactive;
	private int colorOver;
	private int colorPressed;

	
	// labels
	protected static PFont typefaceReg;
	protected static PFont typefaceBold;
	private boolean bShowLabels = true;
	private boolean bShowFrameRate = true;

	
	// debug
	private boolean bVerbose = false;

	

	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param _papplet
	 *		  instance of the applet
	 * 
	 */
	public FControl(PApplet _papplet) {
		welcome();

		p5 = _papplet;
		//frame = p5.frame;
		
		// set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 96);
		colorInactive = _colorInactive.getRGB();
		
		// set default over color
		Color _colorOver = new Color(210,210,210, 230);
		colorOver = _colorOver.getRGB();

		// set default pressed color
		Color _colorPressed = new Color(255,255,255, 230);
		colorPressed = _colorPressed.getRGB();

		// set default typeface
		try {
			//setTypeface( p5.createFont("LucidaGrande",9), p5.createFont("LucidaGrande-Bold",9) );
			setTypeface( p5.createFont("LucidaGrande-Bold",10) );

		} catch (Exception e) {
			setTypeface( p5.createFont("SansSerif",9) );
			
			System.out.println( "-----------------------------------------------------------------------------" );
			System.out.println( "FControl Error: " + e );
			System.out.println( "it appers you don't have \"LucidaGrande-Bold\" on your system,");
			System.out.println( "so \"SansSerif\" has been loaded as a substitute, however" );
			System.out.println( "setTypeface() can be use to change the typeface");
		}
	
	}


	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param _papplet
	 *		  instance of the applet
	 * @param _typeface
	 *		  typeface for use with interface elements
	 * 
	 */
	public FControl(PApplet _papplet, PFont _typeface) {
		welcome();

		p5 = _papplet;
		//frame = p5.frame;

		// set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 96);
		colorInactive = _colorInactive.getRGB();
		
		// set default over color
		Color _colorOver = new Color(210,210,210, 230);
		colorOver = _colorOver.getRGB();

		// set default pressed color
		Color _colorPressed = new Color(255,255,255, 230);
		colorPressed = _colorPressed.getRGB();

		//set typeface
		setTypeface( _typeface );
	}


	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param _papplet
	 *		  instance of the applet
	 * @param _typeface
	 *		  typeface for use with interface elements
	 * @param _typefaceBold
	 *		  bold typeface for use with interface elements
	 * 
	 */
	public FControl(PApplet _papplet, PFont _typeface, PFont _typefaceBold) {
		welcome();

		p5 = _papplet;
		//frame = p5.frame;

		// set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 96);
		colorInactive = _colorInactive.getRGB();
		
		// set default over color
		Color _colorOver = new Color(210,210,210, 230);
		colorOver = _colorOver.getRGB();

		// set default pressed color
		Color _colorPressed = new Color(255,255,255, 230);
		colorPressed = _colorPressed.getRGB();

		//set typeface
		setTypeface( _typeface, _typefaceBold );
	}
	
	
	//-----------------------------------------------------------------------------
	private void welcome() {
		System.out.println( "\n" );
		System.out.println( "-----------------------------------------------------------------------------" );
		System.out.println( "Frederickk Library 0.0.4" );
		System.out.println( "FControl" );
		System.out.println( "http://github.com/frederickk/frederickk" );
		System.out.println( "http://kenfrederick.blogspot.com/\n" );
	}

	
	
	//-----------------------------------------------------------------------------
	// controllers
	//-----------------------------------------------------------------------------
	
	//-----------------------------------------
	// FButton
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addButton(String _name, float _x, float _y, int _w, int _h) {
		FButton fb = new FButton(p5);
		fb.setName(_name);
		fb.setCoord(_x, _y);
		fb.setSize(_w, _h);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);

		fb.setLabels(LabelValue, LabelInfo);
		fb.setLabelType( LABEL_STRING );

		FButtons.add(fb);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addButton(String _name, int _series, float _x, float _y, int _w, int _h) {
		addButton(_name + Integer.toString(_series), _x,_y, _w,_h);
	}

	
	//-----------------------------------------
	// FHandle
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle(String _name, float _x, float _y, int _w, int _h, int _labelType) {
		FHandle fh = new FHandle(p5);
		fh.setName(_name);
		fh.setCoord(_x, _y);
		fh.setSize(_w, _h);

		FLabel LabelValue = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);

		FLabel LabelInfo = new FLabel(p5);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);
		
		fh.setLabels(LabelValue, LabelInfo);
		fh.setLabelType( _labelType );

		FHandles.add(fh);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle(String _name, float _x, float _y, int _w, int _h) {
		addHandle(_name, _x,_y, _w,_h, LABEL_FLOAT );
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle(String _name, int _series, float _x, float _y, int _w, int _h) {
		addHandle(_name + Integer.toString(_series), _x,_y, _w,_h);
	}

	
	//-----------------------------------------
	// FHandle3D
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _z
	 *		  z value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle3D(String _name, float _x, float _y, float _z, int _w, int _labelType) {
		FHandle3D fh3 = new FHandle3D(p5);
		fh3.setName(_name);
		fh3.setCoord(_x, _y, _z);
		fh3.setSize(_w, _w);

		FLabel LabelValue = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);

		FLabel LabelInfo = new FLabel(p5);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);
		
		fh3.setLabels(LabelValue, LabelInfo);
		fh3.setLabelType( _labelType );

		FHandle3Ds.add(fh3);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _z
	 *		  z value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle3D(String _name, float _x, float _y, float _z, int _w) {
		addHandle3D(_name, _x,_y,_z, _w, LABEL_FLOAT );
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _z
	 *		  z value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addHandle3D(String _name, int _series, float _x, float _y, float _z, int _w) {
		addHandle3D(_name + Integer.toString(_series), _x,_y,_z, _w);
	}
	

	//-----------------------------------------
	// FCheck
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _sz
	 *		  width & height of gui element
	 * @param _val
	 *		 initial state of gui element
	 *		 
	 */
	public void addCheck(String _name, float _x, float _y, int _sz, boolean _val) {
		FCheck fc = new FCheck(p5);
		fc.setName(_name);
		fc.setCoord(_x, _y);
		fc.setSize(_sz,_sz);
		fc.setValue(_val);

		FLabel LabelName = new FLabel(p5);
		LabelName.setTypeface(typefaceBold);
		fc.setLabels(LabelName);

		FChecks.add(fc);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _sz
	 *		  width & height of gui element
	 *		 
	 */
	public void addCheck(String _name, float _x, float _y, int _sz) {
		addCheck(_name, _x,_y, _sz, false);
	}
	
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _sz
	 *		  width & height of gui element
	 * @param _val
	 *		 initial state of gui element
	 *		 
	 */
	public void addCheck(String _name, int _series, float _x, float _y, int _sz, boolean _val) {
		addCheck(_name + Integer.toString(_series), _x,_y, _sz, _val);
	}

	
	//-----------------------------------------
	// FSlider
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addSlider(String _name, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val, int _labelType) {
		FSlider fs = new FSlider(p5);
		fs.setName(_name);
		fs.setCoord(_x, _y);
		fs.setSize(_w,_h);
		fs.setValueRange(_vMin,_vMax);
		fs.setValue(_val);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);
		fs.setLabels(LabelValue, LabelInfo);
		fs.setLabelType( _labelType );

		FSliders.add(fs);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addSlider(String _name, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val) {
		addSlider(_name, _x,_y, _w,_h, _vMin,_vMax, _val, LABEL_FLOAT);
	}
	
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 *		  
	 */
	public void addSlider(String _name, float _x, float _y, int _w, int _h) {
		addSlider(_name, _x,_y, _w,_h, 0.0f,1.0f, 0.0f, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addSlider(String _name, int _series, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val) {
		addSlider(_name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		  height of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addSlider(String _name, int _series, float _x, float _y, int _w, int _h, float _vMin, float _vMax, float _val, int _labelType) {
		addSlider(_name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, _labelType);
	}


	//-----------------------------------------
	// FKnob
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addKnob(String _name, float _x, float _y, int _r, float _vMin, float _vMax, float _val, int _labelType) {
		FKnob fk = new FKnob(p5);
		fk.setName(_name);
		fk.setCoord(_x, _y);
		fk.setRadius(_r);
		fk.setValueRange(_vMin,_vMax);
		fk.setValue(_val);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);
		fk.setLabels(LabelValue, LabelInfo);
		fk.setLabelType( LABEL_FLOAT );
		
		FKnobs.add(fk);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addKnob(String _name, float _x, float _y, int _r, float _vMin, float _vMax, float _val) {
		addKnob(_name, _x,_y, _r, _vMin,_vMax, _val, LABEL_FLOAT);
	}
	
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 *		  
	 */
	public void addKnob(String _name, float _x, float _y, int _r) {
		addKnob(_name, _x,_y, _r, 0.0f,1.0f, 0.0f, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 *		  
	 */
	public void addKnob(String _name, int _series, float _x, float _y, int _r, float _vMin, float _vMax, float _val) {
		addKnob(_name + Integer.toString(_series), _x,_y, _r, _vMin,_vMax, _val, LABEL_FLOAT);
	}

	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _r
	 *		  radius of gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 * @param _val
	 *		  initial value of gui element
	 * @param _labelType
	 *		  display type of value LABEL_FLOAT or LABEL_INT
	 *		  
	 */
	public void addKnob(String _name, int _series, float _x, float _y, int _r, float _vMin, float _vMax, float _val, int _labelType) {
		addKnob(_name + Integer.toString(_series), _x,_y, _r, _vMin,_vMax, _val, _labelType);
	}

	
	
	//-----------------------------------------
	// FDropDown
	//-----------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  x value of gui element
	 * @param _y
	 *		  y value of gui element
	 * @param _w
	 *		  width of gui element
	 * @param _h
	 *		 height of gui element
	 *		 
	 */
	public void addDropDown(String _name, float _x, float _y, int _w, int _h) {
		FDropDown fd = new FDropDown(p5);
		fd.setName(_name);
		fd.setCoord(_x, _y);
		fd.setSize(_w, _h);

		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);
		fd.setLabels(LabelValue, LabelInfo);
		fd.setLabelType( LABEL_FLOAT );
		
		FDropDowns.add(fd);
	}
	
	
	//-----------------------------------------
	// FLabel
	//-----------------------------------------


	
	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * draw initiated gui elements
	 * 
	 */
	public void draw() {
		if(bVerbose) {
			// debug info
			System.out.println( "FButtons " + FButtons.size() );
			System.out.println( "FHandles " + FHandles.size() );
			System.out.println( "FHandle3Ds " + FHandle3Ds.size() );
			System.out.println( "FChecks " + FChecks.size() );
			System.out.println( "FSliders " + FSliders.size() );
			System.out.println( "FKnobs " + FKnobs.size() );
			System.out.println( "FDropDowns " + FDropDowns.size() );
		}
		

		// frameRate in title
		if(bShowFrameRate) {
			//frame.setTitle( Float.toString(p5.frameRate) );
		}

		
		// draw controllers
		p5.pushStyle();
		p5.rectMode(CORNER);
		p5.strokeWeight(1);
		for(int i=0; i<FButtons.size(); i++) {
			FButton fb = (FButton) FButtons.get(i);
			fb.setColorOver( colorOver );
			fb.setColorPressed( colorPressed );
			fb.setColorInactive( colorInactive );
			fb.showLabels( bShowLabels );
			fb.draw();
		}
		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			fh.setColorOver( colorOver );
			fh.setColorPressed( colorPressed );
			fh.setColorInactive( colorInactive );
			fh.showLabels( bShowLabels );
			fh.draw();
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			fh3.setColorOver( colorOver );
			fh3.setColorPressed( colorPressed );
			fh3.setColorInactive( colorInactive );
			fh3.showLabels( bShowLabels );
			fh3.draw();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			fc.setColorOver( colorOver );
			fc.setColorPressed( colorPressed );
			fc.setColorInactive( colorInactive );
			fc.showLabels( bShowLabels );
			fc.draw();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			fs.setColorOver( colorOver );
			fs.setColorPressed( colorPressed );
			fs.setColorInactive( colorInactive );
			fs.showLabels( bShowLabels );
			fs.draw();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			fk.setColorOver( colorOver );
			fk.setColorPressed( colorPressed );
			fk.setColorInactive( colorInactive );
			fk.showLabels( bShowLabels );
			fk.draw();
		}
		for(int i=0; i<FDropDowns.size(); i++) {
			FDropDown fd = (FDropDown) FDropDowns.get(i);
			fd.setColorOver( colorOver );
			fd.setColorPressed( colorPressed );
			fd.setColorInactive( colorInactive );
			fd.showLabels( bShowLabels );
			fd.draw();
		}
		p5.popStyle();
		// end controllers
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param val
	 *		  toggle showing value labels for all gui elements
	 */
	public void showLabels(boolean val) {
		bShowLabels = val;
	}
	
	/**
	 * @param val
	 *		  toggle showing value label for the gui element
	 * @param _name
	 *		  name of the gui element
	 */
	public void showLabels(boolean val, String _name) {
		/*
		for(int i=0; i<FButtons.size(); i++) {
			FButton fb = (FButton) FButtons.get(i);
			if(_name.compareTo(fb.name) == 0) fb.showLabels( val );
		}
		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) fh.showLabels( val );
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) fc.showLabels( val );
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.showLabels( val );
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.showLabels( val );
		}
		*/
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * show frame rate in the application title
	 */
	public void showFrameRateTitle() {
		bShowFrameRate = true;
	}

	/**
	 * @param val
	 *		  toggle showing frame rate in the application title
	 */
	public void showFrameRateTitle(boolean val) {
		bShowFrameRate = val;
	}
	

	//-----------------------------------------------------------------------------
	/**
	 * @param val
	 *		  toggle verbose
	 */
	public void verbose(boolean val) {
		bVerbose = val;
	}
	
	
	
	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * FHandle
	 * FHandle3D
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element PVector value 
	 */
	public void setValue(String _name, PVector _val) {
		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) fh.setCoord(_val.x,_val.y);
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			if(_name.compareTo(fh3.name) == 0) fh3.setCoord(_val.x,_val.y,_val.z);
		}
	}


	/**
	 * FHandle
	 * FHandle3D
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element PVector value 
	 */
	public void setValue(String _name, int _series, PVector _val) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(seriesName.compareTo(fh.name) == 0) fh.setCoord(_val.x,_val.y);
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			if(seriesName.compareTo(fh3.name) == 0) fh3.setCoord(_val.x,_val.y,_val.z);
		}
	}


	//-----------------------------------------------------------------------------
	/**
	 * FCheck
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element boolean value 
	 */
	public void setValue(String _name, boolean _val) {
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) fc.setValue(_val);
		}
	}

	/**
	 * FCheck
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element boolean value 
	 */
	public void setValue(String _name, int _series, boolean _val) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(seriesName.compareTo(fc.name) == 0) fc.setValue(_val);
		}
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element int value 
	 */
	public void setValue(String _name, int _val) {
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.setValue(_val);
		}
	}
	
	/**
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  set element float value 
	 */
	public void setValue(String _name, float _val) {
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.setValue(_val);
		}
	}	
	

	
	/**
	 * FSlider
	 * FKnob
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element int value 
	 */
	public void setValue(String _name, int _series, int _val) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(seriesName.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(seriesName.compareTo(fk.name) == 0) fk.setValue(_val);
		}
	}
	
	/**
	 * FSlider
	 * FKnob
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *		  set element float/int value 
	 */
	public void setValue(String _name, int _series, float _val) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(seriesName.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(seriesName.compareTo(fk.name) == 0) fk.setValue(_val);
		}
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 */
	public void setValueRange(String _name, float _vMin, float _vMax) {
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.setValueRange(_vMin,_vMax);
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.setValueRange(_vMin,_vMax);
		}
	}

	/**
	 * FSlider
	 * FKnob
	 * 
	 * series elements
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _vMin
	 *		  minimum value of gui element
	 * @param _vMax
	 *		  maximum value of gui element
	 */
	public void setValueRange(String _name, int _series, float _vMin, float _vMax) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(seriesName.compareTo(fs.name) == 0) fs.setValueRange(_vMin,_vMax);
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(seriesName.compareTo(fk.name) == 0) fk.setValueRange(_vMin,_vMax);
		}
	}


	//-----------------------------------------------------------------------------
	/**
	 * FDropDown
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _nameItem
	 *		  name of the added menu item
	 *		  
	 */
	public void addItem(String _name, String _nameItem) {
		for(int i=0; i<FDropDowns.size(); i++) {
			FDropDown fd = (FDropDown) FDropDowns.get(i);
			if(_name.compareTo(fd.name) == 0) fd.addItem(_nameItem);
		}
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  increment value
	 */
	public void enableSnap(String _name, float _val) {
		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) fh.enableSnap(_val);
		}
		/*
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.enableSnap(_val);
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.enableSnap(_val);
		}
		*/
	}
	
	/**
	 * @param _name
	 *		  name of the gui element
	 */
	public void disableSnap(String _name) {
		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) fh.disableSnap();
		}
		/*
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.disableSnap();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.disableSnap();
		}
		*/
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param _typeface
	 *		  set global typeface for all gui elements
	 */
	public void setTypeface(PFont _typeface) {
		typefaceReg = _typeface;
		typefaceBold = _typeface;
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}

	public void setTypeface(PFont _typeface[]) {
		typefaceReg = _typeface[0];
		typefaceBold = _typeface[1];
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}

	public void setTypeface(PFont _typeface, PFont _typefaceBold) {
		typefaceReg = _typeface;
		typefaceBold = _typefaceBold;
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}


	//-----------------------------------------------------------------------------
	/**
	 * @param _colorInactive
	 *		  set global inactive color for all gui elements
	 */
	public void setColorInactive(int _colorInactive) {
		colorInactive = _colorInactive;
	}

	/**
	 * @param _colorActive
	 *		  set global over color for all gui elements
	 */
	public void setColorOver(int _colorOver) {
		colorOver = _colorOver;
	}
	
	/**
	 * @param _colorPressed
	 * 		  set global pressed color for all gui elemetns
	 */
	public void setColorPressed(int _colorPressed) {
		colorPressed = _colorPressed;
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * single element
	 * 
	 * return boolean value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getBoolValue(String _name) {
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) boolVal = fc.getValue();
			//else bool = null;
		}
		return boolVal;
	}

	/**
	 * return boolean value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public boolean getBoolValue(String _name, int _series) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(seriesName.compareTo(fc.name) == 0) boolVal = fc.getValue();
			//else bool = null;
		}
		return boolVal;
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * single element
	 * 
	 * return int value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public int getIntValue(String _name) {
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) intVal = ( fc.getValue() )?1:0;
			//else bool = null;
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) intVal = fs.getIntValue();
			//else return null;
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) intVal = fk.getIntValue();
			//else return null;
		}

		return intVal;
	}

	/**
	 * return int value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public int getIntValue(String _name, int _series) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(seriesName.compareTo(fc.name) == 0) intVal = ( fc.getValue() )?1:0;
			//else bool = null;
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(seriesName.compareTo(fs.name) == 0) intVal = fs.getIntValue();
			//else return null;
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(seriesName.compareTo(fk.name) == 0) intVal = fk.getIntValue();
			//else return null;
		}

		return intVal;
	}	
	
	
	//-----------------------------------------------------------------------------
	/**
	 * single element
	 * 
	 * return float value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public float getFloatValue(String _name) {
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) floatVal = fs.getFloatValue();
			//else return null;
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) floatVal = fk.getFloatValue();
			//else return null;
		}

		return floatVal;
	}

	/**
	 * return float value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public float getFloatValue(String _name, int _series) {
		String seriesName = _name + Integer.toString(_series);
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(seriesName.compareTo(fs.name) == 0) floatVal = fs.getFloatValue();
			//else return null;
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(seriesName.compareTo(fk.name) == 0) floatVal = fk.getFloatValue();
			//else return null;
		}

		return floatVal;
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * single element
	 * 
	 * return PVector value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public PVector getCoord(String _name) {
		PVector vector = new PVector();

		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) vector = fh.getCoord();
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			if(_name.compareTo(fh3.name) == 0) vector = fh3.getCoord();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) vector = fc.getCoord();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) vector = fs.getCoord();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) vector = fk.getCoord();
		}

		return vector;
	}


	/**
	 * series elements
	 * 
	 * return PVector value of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public PVector getCoord(String _name, int _series) {
		PVector vector = new PVector();
		String seriesName = _name + Integer.toString(_series);

		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(seriesName.compareTo(fh.name) == 0) vector = fh.getCoord();
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			if(seriesName.compareTo(fh3.name) == 0) vector = fh3.getCoord();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(seriesName.compareTo(fc.name) == 0) vector = fc.getCoord();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(seriesName.compareTo(fs.name) == 0) vector = fs.getCoord();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(seriesName.compareTo(fk.name) == 0) vector = fk.getCoord();
		}

		return vector;
	}

	
	
	//-----------------------------------------------------------------------------
	// events
	//-----------------------------------------------------------------------------
	/**
	 * return whether mouse is over the gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getOver(String _name) {
		boolean _over = false;

		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) _over = fh.getOver();
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			if(_name.compareTo(fh3.name) == 0) _over = fh3.getOver();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) _over = fc.getOver();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) _over = fs.getOver();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) _over = fk.getOver();
		}
		
		return _over;
	}

	
	/**
	 * return whether the element has been moved/adjusted 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getMoved() {
		boolean _moved = false;

		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			_moved = fh.getMoved();
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			_moved = fh3.getMoved();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			_moved = fs.getMoved();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			_moved = fk.getMoved();
		}
		
		return _moved;
	}
	
	
	/**
	 * return whether the element has been moved/adjusted 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getMoved(String _name) {
		boolean _moved = false;

		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) _moved = fh.getMoved();
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			if(_name.compareTo(fh3.name) == 0) _moved = fh3.getMoved();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) _moved = fs.getMoved();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) _moved = fk.getMoved();
		}
		
		return _moved;
	}
	

	/**
	 * return whether the element has been pressed 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getPressed(String _name) {
		boolean _pressed = false;

		for(int i=0; i<FButtons.size(); i++) {
			FButton fb = (FButton) FButtons.get(i);
			if(_name.compareTo(fb.name) == 0) _pressed = fb.getPressed();
		}		
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) _pressed = fc.getPressed();
		}		
		for(int i=0; i<FHandles.size(); i++) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) _pressed = fh.getPressed();
		}
		for(int i=0; i<FHandle3Ds.size(); i++) {
			FHandle3D fh3 = (FHandle3D) FHandle3Ds.get(i);
			if(_name.compareTo(fh3.name) == 0) _pressed = fh3.getPressed();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) _pressed = fs.getPressed();
		}
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) _pressed = fk.getPressed();
		}
		
		return _pressed;
	}
	
	
	
	//-----------------------------------------------------------------------------
	/**
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public int getSelection(String _name) {
		for(int i=0; i<FDropDowns.size(); i++) {
			FDropDown fd = (FDropDown) FDropDowns.get(i);
			if(_name.compareTo(fd.name) == 0) selVal = fd.getSelection();
		}
		return selVal;
	}
	

	//-----------------------------------------------------------------------------
	/**
	 * return inactive color (as int) 
	 * 
	 */
	public int getColorInactive() {
		return colorInactive;
	}
	/**
	 * return over color (as int) 
	 * 
	 */
	public int getColorOver() {
		return colorOver;
	}
	/**
	 * return pressed color (as int) 
	 * 
	 */
	public int getColorPressed() {
		return colorPressed;
	}

}
