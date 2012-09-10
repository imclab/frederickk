package frederickk.control;

/*
 *  Frederickk.Control 0.0.5
 *  FControl.java
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
//import java.awt.Frame;
import java.util.ArrayList;



public class FControl implements PConstants,FControlConstants {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	protected static PApplet p5;
	//protected static Frame frame;

	private int sleepTime = 150;

	
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
	private String selStrVal = new String();
	private int selIntVal;

	
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
		System.out.println( "Frederickk Library 0.0.5" );
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
		fb.setPos(_x, _y);
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
	 * @param _mode
	 *		  BUTTON_NORMAL creates a generic button
	 *		  BUTTON_LOAD creates a button which opens a file dialog use getFilePath() to retrieve the path to the file
	 *
	 */
	public void addButton(String _name, float _x, float _y, int _w, int _h, int _mode) {
		FButton fb = new FButton(p5);
		fb.setName(_name);
		fb.setPos(_x, _y);
		fb.setSize(_w, _h);
		fb.setMode(_mode);

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
	 * @param _mode
	 *		  BUTTON_NORMAL creates a generic button
	 *		  BUTTON_LOAD creates a button which opens a file dialog use getFilePath() to retrieve the path to the file
	 *		  
	 */
	public void addButton(String _name, int _series, float _x, float _y, int _w, int _h, int _mode) {
		addButton(_name + Integer.toString(_series), _x,_y, _w,_h, _mode);
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
		fh.setPos(_x, _y);
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
	 *		  
	 */
	public void addHandle3D(String _name, float _x, float _y, float _z, int _w, int _labelType) {
		FHandle3D fh3 = new FHandle3D(p5);
		fh3.setName(_name);
		fh3.setPos(_x, _y, _z);
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
	 *		  
	 */
	public void addHandle3D(String _name, int _series, float _x, float _y, float _z, int _w) {
		addHandle3D(_name + Integer.toString(_series), _x,_y,_z, _w);
	}
	

	//-----------------------------------------
	// FCheck
	//-----------------------------------------
	/**
	 * @param fcheck
	 *		  fcheck element
	 */
	public void addCheck(FCheck fcheck) {
		FLabel LabelName = new FLabel(p5);
		LabelName.setTypeface(typefaceBold);
		fcheck.setLabels(LabelName);

		FChecks.add(fcheck);
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
	 * @param _val
	 *		 initial state of gui element
	 *		 
	 */
	public void addCheck(String _name, float _x, float _y, int _sz, boolean _val) {
		FCheck fc = new FCheck(p5);
		fc.setName(_name);
		fc.setPos(_x, _y);
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
		fs.setPos(_x, _y);
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
		fk.setPos(_x, _y);
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
	 * @param items
	 *		 array of menu items
	 *		 
	 */
	public void addDropDown(String _name, float _x, float _y, int _w, int _h, String[] items) {
		FDropDown fd = new FDropDown(p5);
		fd.setName(_name);
		fd.setPos(_x, _y);
		fd.setSize(_w, _h);

		fd.addItem(items);
		
		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);
		fd.setLabels(LabelValue, LabelInfo);
		fd.setLabelType( LABEL_FLOAT );
		
		FDropDowns.add(fd);
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
	 *		 height of gui element
	 * @param items
	 *		 array of menu items
	 * @param selected
	 *		 initial value
	 *		 
	 */
	public void addDropDown(String _name, float _x, float _y, int _w, int _h, String[] items, String selected) {
		FDropDown fd = new FDropDown(p5, _name, _x,_y, _w,_h, items, selected);	
		
		FLabel LabelValue = new FLabel(p5);
		FLabel LabelInfo = new FLabel(p5);
		LabelValue.setTypeface(typefaceReg,typefaceBold);
		LabelInfo.setTypeface(typefaceReg,typefaceBold);
		fd.setLabels(LabelValue, LabelInfo);
		fd.setLabelType( LABEL_STRING );
		
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
		for(FButton fb : FButtons) {
			fb.setColorOver( colorOver );
			fb.setColorPressed( colorPressed );
			fb.setColorInactive( colorInactive );
			fb.showLabels( bShowLabels );
			fb.draw();
		}
		for(FHandle fh : FHandles) {
			fh.setColorOver( colorOver );
			fh.setColorPressed( colorPressed );
			fh.setColorInactive( colorInactive );
			fh.showLabels( bShowLabels );
			fh.draw();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			fh3.setColorOver( colorOver );
			fh3.setColorPressed( colorPressed );
			fh3.setColorInactive( colorInactive );
			fh3.showLabels( bShowLabels );
			fh3.draw();
		}
		for(FCheck fc : FChecks) {
			fc.setColorOver( colorOver );
			fc.setColorPressed( colorPressed );
			fc.setColorInactive( colorInactive );
			fc.showLabels( bShowLabels );
			fc.draw();
		}
		for(FSlider fs : FSliders) {
			fs.setColorOver( colorOver );
			fs.setColorPressed( colorPressed );
			fs.setColorInactive( colorInactive );
			fs.showLabels( bShowLabels );
			fs.draw();
		}
		for(FKnob fk : FKnobs) {
			fk.setColorOver( colorOver );
			fk.setColorPressed( colorPressed );
			fk.setColorInactive( colorInactive );
			fk.showLabels( bShowLabels );
			fk.draw();
		}
		for(FDropDown fd : FDropDowns) {
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
		for(FButton fb : FButtons) {
			FButton fb = (FButton) FButtons.get(i);
			if(_name.compareTo(fb.name) == 0) fb.showLabels( val );
		}
		for(FHandle fh : FHandles) {
			FHandle fh = (FHandle) FHandles.get(i);
			if(_name.compareTo(fh.name) == 0) fh.showLabels( val );
		}
		for(FCheck fc : FChecks) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) fc.showLabels( val );
		}
		for(FSlider fs : FSliders) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.showLabels( val );
		}
		for(FKnob fk : FKnobs) {
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
	 * FButton
	 * FHandle
	 * FHandle3D
	 * FSliders
	 * FDropDowns
	 * 
	 * set width,height of specific element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _width
	 *		  set width of gui element 
	 * @param _height
	 *		  set height of gui element 
	 */
	public void setSize(String _name, float _width, float _height) {
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) fb.setSize((int)_width, (int)_height);
		}
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setSize((int)_width, (int)_height);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setSize((int)_width, (int)_height);
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setSize((int)_width, (int)_height);
		}
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) fd.setSize((int)_width, (int)_height);
		}
	}

	/**
	 * FCheck
	 * FKnob
	 * 
	 * set radius of specfic element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _radius
	 *		  radius of element 
	 */
	public void setSize(String _name, float _radius) {
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) fc.setSize((int)_radius,(int)_radius);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setSize((int)_radius,(int)_radius);
		}
	}
	
	
	//-----------------------------------------------------------------------------
	/**
	 * FButton
	 * FHandle
	 * FSliders
	 * FDropDowns
	 * FCheck
	 * FKnob
	 * 
	 * set x,y position of specfic element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  set x position of gui element 
	 * @param _y
	 *		  set y position of gui element 
	 */
	public void setPos(String _name, float _x, float _y) {
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) fb.setPos((int)_x, (int)_y);
		}
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setPos((int)_x, (int)_y);
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setPos((int)_x, (int)_y);
		}
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) fd.setPos((int)_x, (int)_y);
		}
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) fc.setPos((int)_x,(int)_y);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setPos((int)_x,(int)_y);
		}
	}

	/**
	 * FHandle3D
	 * 
	 * set x,y,z position of specific element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _x
	 *		  set x position of gui element 
	 * @param _y
	 *		  set y position of gui element 
	 * @param _z
	 *		  set z position of gui element 
	 */
	public void setSize(String _name, float _x, float _y, float _z) {
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setPos((int)_x, (int)_y, (int)_z);
		}
	}
	
	
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
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setPos(_val.x,_val.y);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setPos(_val.x,_val.y,_val.z);
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
		for(FHandle fh : FHandles) {
			if(seriesName.compareTo(fh.name) == 0) fh.setPos(_val.x,_val.y);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(seriesName.compareTo(fh3.name) == 0) fh3.setPos(_val.x,_val.y,_val.z);
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
		for(FCheck fc : FChecks) {
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
		for(FCheck fc : FChecks) {
			if(seriesName.compareTo(fc.name) == 0) fc.setValue(_val);
		}
	}


	
	//-----------------------------------------------------------------------------
	/**
	 * FSlider
	 * FKnob
	 * 
	 * single & series elements
	 * 
	 * @param _val
	 *		  set loosenes of the sliding mechanism (globally for every slider and knob) 
	 */
	public void setLoose(int _val) {
		for(FSlider fs : FSliders) {
			fs.setLoose(_val);
		}
		for(FKnob fk : FKnobs) {
			fk.setLoose(_val);
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
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(FKnob fk : FKnobs) {
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
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(FKnob fk : FKnobs) {
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
		for(FSlider fs : FSliders) {
			if(seriesName.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(FKnob fk : FKnobs) {
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
		for(FSlider fs : FSliders) {
			if(seriesName.compareTo(fs.name) == 0) fs.setValue(_val);
		}
		for(FKnob fk : FKnobs) {
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
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setValueRange(_vMin,_vMax);
		}
		for(FKnob fk : FKnobs) {
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
		for(FSlider fs : FSliders) {
			if(seriesName.compareTo(fs.name) == 0) fs.setValueRange(_vMin,_vMax);
		}
		for(FKnob fk : FKnobs) {
			if(seriesName.compareTo(fk.name) == 0) fk.setValueRange(_vMin,_vMax);
		}
	}


	//-----------------------------------------------------------------------------
	/**
	 * FDropDown
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _nameItem
	 *		  name of the added menu item
	 *		  
	 */
	public void addItem(String _name, String _nameItem) {
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) fd.addItem(_nameItem);
		}
	}

	
	//-----------------------------------------------------------------------------
	/**
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element
	 * @param _val
	 *		  increment value
	 */
	public void enableSnap(String _name, float _val) {
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.enableSnap(_val);
		}

		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.enableSnap(_val);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.enableSnap(_val);
		}
	}
	
	/**
	 * global
	 * 
	 * @param _name
	 *		  name of the gui element
	 */
	public void disableSnap(String _name) {
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.disableSnap();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.disableSnap();
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.disableSnap();
		}
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
	 * FHandles
	 * FHandles3D
	 * FSlider
	 * FKnob
	 * 
	 * global
	 * 
	 * @param _labelType
	 *		  increment value
	 *		  LABEL_FLOAT displays value as a 2 decimal float
	 *		  LABEL_INT displays value as an integer
	 *		  LABEL_STRING displays values as a string
	 */
	public void setLabelType(int _labelType) {
		for(FHandle fh : FHandles) {
			fh.setLabelType(_labelType);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			fh3.setLabelType(_labelType);
		}
		for(FSlider fs : FSliders) {
			fs.setLabelType(_labelType);
		}
		for(FKnob fk : FKnobs) {
			fk.setLabelType(_labelType);
		}
	}

	/**
	 * FHandles
	 * FHandles3D
	 * FSlider
	 * FKnob
	 * 
	 * single element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _labelType
	 *		  increment value
	 *		  LABEL_FLOAT displays value as a 2 decimal float
	 *		  LABEL_INT displays value as an integer
	 *		  LABEL_STRING displays values as a string
	 */
	public void setLabelType(String _name, int _labelType) {
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) fh.setLabelType(_labelType);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) fh3.setLabelType(_labelType);
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) fs.setLabelType(_labelType);
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) fk.setLabelType(_labelType);
		}
	}
	
	/**
	 * FHandles
	 * FHandles3D
	 * FSlider
	 * FKnob
	 * 
	 * series element
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 * @param _labelType
	 *		  increment value
	 *		  LABEL_FLOAT displays value as a 2 decimal float
	 *		  LABEL_INT displays value as an integer
	 *		  LABEL_STRING displays values as a string
	 */
	public void setLabelType(String _name, int _series, int _labelType) {
		String seriesName = _name + Integer.toString(_series);
		for(FHandle fh : FHandles) {
			if(seriesName.compareTo(fh.name) == 0) fh.setLabelType(_labelType);
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(seriesName.compareTo(fh3.name) == 0) fh3.setLabelType(_labelType);
		}
		for(FSlider fs : FSliders) {
			if(seriesName.compareTo(fs.name) == 0) fs.setLabelType(_labelType);
		}
		for(FKnob fk : FKnobs) {
			if(seriesName.compareTo(fk.name) == 0) fk.setLabelType(_labelType);
		}
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
	 * @param _colorOver
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
		for(FCheck fc : FChecks) {
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
		for(FCheck fc : FChecks) {
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
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) intVal = ( fc.getValue() )?1:0;
			//else bool = null;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) intVal = fs.getIntValue();
			//else return null;
		}
		for(FKnob fk : FKnobs) {
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
		for(FCheck fc : FChecks) {
			if(seriesName.compareTo(fc.name) == 0) intVal = ( fc.getValue() )?1:0;
			//else bool = null;
		}
		for(FSlider fs : FSliders) {
			if(seriesName.compareTo(fs.name) == 0) intVal = fs.getIntValue();
			//else return null;
		}
		for(FKnob fk : FKnobs) {
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
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) floatVal = fs.getFloatValue();
			//else return null;
		}
		for(FKnob fk : FKnobs) {
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
		for(FSlider fs : FSliders) {
			if(seriesName.compareTo(fs.name) == 0) floatVal = fs.getFloatValue();
			//else return null;
		}
		for(FKnob fk : FKnobs) {
			if(seriesName.compareTo(fk.name) == 0) floatVal = fk.getFloatValue();
			//else return null;
		}

		return floatVal;
	}


	//-----------------------------------------------------------------------------
	/**
	 * single element
	 * 
	 * return filePath of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public String getFilePath(String _name) {
		String filePath = "";
		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) filePath = fb.getFilePath();
			//else return null;
		}

		return filePath;
	}
	
	/**
	 * return filePath of gui element 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 * @param _series
	 *		  number marker for multiple elements (i.e. name + series = "name0")
	 *		  
	 */
	public String getFilePath(String _name, int _series) {
		String filePath = "";
		String seriesName = _name + Integer.toString(_series);
		for(FButton fb : FButtons) {
			if(seriesName.compareTo(fb.name) == 0) filePath = fb.getFilePath();
			//else return null;
		}

		return filePath;
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

		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) vector = fh.getCoord();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) vector = fh3.getCoord();
		}
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) vector = fc.getCoord();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) vector = fs.getCoord();
		}
		for(FKnob fk : FKnobs) {
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

		for(FHandle fh : FHandles) {
			if(seriesName.compareTo(fh.name) == 0) vector = fh.getCoord();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(seriesName.compareTo(fh3.name) == 0) vector = fh3.getCoord();
		}
		for(FCheck fc : FChecks) {
			if(seriesName.compareTo(fc.name) == 0) vector = fc.getCoord();
		}
		for(FSlider fs : FSliders) {
			if(seriesName.compareTo(fs.name) == 0) vector = fs.getCoord();
		}
		for(FKnob fk : FKnobs) {
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

		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) _over = fh.getOver();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) _over = fh3.getOver();
		}
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) _over = fc.getOver();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) _over = fs.getOver();
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) _over = fk.getOver();
		}
		
		return _over;
	}

	
	/**
	 * return whether the element has been clicked 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getClicked(String _name) {
		boolean _clicked = false;

		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) _clicked = fb.getClicked();
			else continue;
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) _clicked = fc.getClicked();
			else continue;
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) _clicked = fh.getClicked();
			else continue;
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) _clicked = fh3.getClicked();
			else continue;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) _clicked = fs.getClicked();
			else continue;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) _clicked = fk.getClicked();
			else continue;
		}
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return _clicked;
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

		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) _pressed = fb.getPressed();
			else continue;
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) _pressed = fc.getPressed();
			else continue;
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) _pressed = fh.getPressed();
			else continue;
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) _pressed = fh3.getPressed();
			else continue;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) _pressed = fs.getPressed();
			else continue;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) _pressed = fk.getPressed();
			else continue;
		}
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return _pressed;
	}
	
	
	/**
	 * return whether the element is locked on 
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getLocked(String _name) {
		boolean _locked = false;

		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) _locked = fb.getLocked();
			else continue;
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) _locked = fc.getLocked();
			else continue;
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) _locked = fh.getLocked();
			else continue;
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) _locked = fh3.getLocked();
			else continue;
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) _locked = fs.getLocked();
			else continue;
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) _locked = fk.getLocked();
			else continue;
		}
		
		return _locked;
	}

	
	/**
	 * return whether any element has been moved/adjusted 
	 */
	public boolean getMoved() {
		boolean _moved = false;

		for(FHandle fh : FHandles) {
			_moved = fh.getMoved();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			_moved = fh3.getMoved();
		}
		for(FSlider fs : FSliders) {
			_moved = fs.getMoved();
		}
		for(FKnob fk : FKnobs) {
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

		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) _moved = fh.getMoved();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) _moved = fh3.getMoved();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) _moved = fs.getMoved();
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) _moved = fk.getMoved();
		}
		
		return _moved;
	}
	

	/**
	 * return whether the element has been released
	 * 
	 * @param _name
	 *		  name of the gui element 
	 *		  
	 */
	public boolean getReleased(String _name) {
		boolean _released = false;

		for(FButton fb : FButtons) {
			if(_name.compareTo(fb.name) == 0) _released = fb.getReleased();
		}		
		for(FCheck fc : FChecks) {
			if(_name.compareTo(fc.name) == 0) _released = fc.getReleased();
		}		
		for(FHandle fh : FHandles) {
			if(_name.compareTo(fh.name) == 0) _released = fh.getReleased();
		}
		for(FHandle3D fh3 : FHandle3Ds) {
			if(_name.compareTo(fh3.name) == 0) _released = fh3.getReleased();
		}
		for(FSlider fs : FSliders) {
			if(_name.compareTo(fs.name) == 0) _released = fs.getReleased();
		}
		for(FKnob fk : FKnobs) {
			if(_name.compareTo(fk.name) == 0) _released = fk.getReleased();
		}
		
		return _released;
	}
	
	
	//-----------------------------------------------------------------------------
	/**
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *		  name of the dropdown gui element
	 *
	 * @return
	 * 		  String (name) of the element selected
	 *		  
	 */
	public String getSelection(String _name) {
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) selStrVal = fd.getSelection();
		}
		return selStrVal;
	}
	
	/**
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *		  name of the dropdown gui element
	 *
	 * @return
	 * 		  int (index) of the element selected
	 *		  
	 */
	public int getSelectionIndex(String _name) {
		for(FDropDown fd : FDropDowns) {
			if(_name.compareTo(fd.name) == 0) selIntVal = fd.getSelectionIndex();
		}
		return selIntVal;
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
