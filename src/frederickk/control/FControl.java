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


import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;
import processing.core.PConstants;

//import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.ArrayList;

public class FControl implements PConstants,FControlConstants {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected static PApplet p5;

	private ArrayList<FKnob> FKnobs = new ArrayList<FKnob>();
	private ArrayList<FKnob3D> FKnob3Ds = new ArrayList<FKnob3D>();
	private ArrayList<FCheck> FChecks = new ArrayList<FCheck>();
	private ArrayList<FSlider> FSliders = new ArrayList<FSlider>();
	private ArrayList<FMeter> FMeters = new ArrayList<FMeter>();
	private ArrayList<FDropDown> FDropDowns = new ArrayList<FDropDown>();

	protected static PFont typeface[] = new PFont[2];

	private boolean boolVal;
	private int intVal;
	private float floatVal;
	private int selVal;

	private static int colorInactive;
	private static int colorActive;
	
	private boolean showLabels = true;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param thePApplet
	 *          instance of the applet
	 * 
	 */
	public FControl(PApplet thePApplet) {
		welcome();

		p5 = thePApplet;

		//set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 96);
		colorInactive = _colorInactive.getRGB();
		
		//set default active color
		Color _colorActive = new Color(210,210,210, 230);
		colorActive = _colorActive.getRGB();

		//set default typeface
		try {
			setTypeface( p5.createFont("LucidaGrande",9), p5.createFont("LucidaGrande-Bold",9) );

		} catch (Exception e) {
			setTypeface( p5.createFont("SansSerif",9) );
			
			System.out.println( "-----------------------------------------------------------------------------" );
			System.out.println( "FControl Error: " + e );
			System.out.println( "it appers you don't have \"LucidaGrande-Bold\" on your system,");
			System.out.println( "so \"SansSerif\" has been loaded as a substitute, however" );
			System.out.println( "setTypeface(PFont typeface) can be use to change the typeface");
		}
	}


	/**
	 * initialize the FControl gui elements class
	 * 
	 * @param thePApplet
	 *          instance of the applet
	 * @param _typeface
	 *          typeface for use with interface elements
	 * 
	 */
	public FControl(PApplet thePApplet, PFont _typeface) {
		welcome();

		p5 = thePApplet;

		//set default resting (inactive) color
		Color _colorInactive = new Color(215,215,215, 64);
		colorInactive = _colorInactive.getRGB();
		
		//set default active color
		Color _colorActive = new Color(210,210,210, 230);
		colorActive = _colorActive.getRGB();

		//set typeface
		setTypeface( _typeface );
	}

	private void welcome() {
		System.out.println( "" );
		System.out.println( "-----------------------------------------------------------------------------" );
		System.out.println( "Frederickk Library 003" );
		System.out.println( "FControl" );
		System.out.println( "http://github.com/frederickk/frederickk" );
		System.out.println( "http://kenfrederick.blogspot.com/\n" );
	}
	
	
	//-----------------------------------------------------------------------------
	//controllers
	//-----------------------------------------------------------------------------
	
	//-----------------------------------------
	//FKnob
	//-----------------------------------------
	/**
	 * @param fk
	 *          FKnob instance
	 * 
	 */
	public void add(FKnob fk) {
		FKnobs.add( fk );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addKnob(String _name, float _x, float _y, float _w, float _h) {
		FKnobs.add(new FKnob(p5, _name, _x,_y, _w,_h, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _z
	 *          z value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addKnob(String _name, float _x, float _y, float _z, float _w, float _h) {
		FKnobs.add(new FKnob(p5, _name, _x,_y,_z, _w,_h, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addKnob(String _name, int _series, float _x, float _y, float _w, float _h) {
		FKnobs.add(new FKnob(p5, _name + Integer.toString(_series), _x,_y, _w,_h, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _z
	 *          z value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addKnob(String _name, int _series, float _x, float _y, float _z, float _w, float _h) {
		FKnobs.add(new FKnob(p5, _name + Integer.toString(_series), _x,_y,_z, _w,_h, typeface, LABEL_FLOAT) );
	}	

	
	//-----------------------------------------
	//FKnob3D
	//-----------------------------------------
	/**
	 * @param fk3
	 *          FKnob3D instance
	 * 
	 */
	public void add(FKnob3D fk3) {
		FKnob3Ds.add( fk3 );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _z
	 *          z value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addKnob3D(String _name, float _x, float _y, float _z, float _w) {
		FKnob3Ds.add(new FKnob3D(p5, _name, _x,_y,_z, _w,_w, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _z
	 *          z value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addKnob3D(String _name, int _series, float _x, float _y, float _z, float _w) {
		FKnob3Ds.add(new FKnob3D(p5, _name + Integer.toString(_series), _x,_y,_z, _w,_w, typeface, LABEL_FLOAT) );
	}
	

	//-----------------------------------------
	//FCheck
	//-----------------------------------------
	/**
	 * @param fc
	 *          FCheck instance
	 * 
	 */
	public void add(FCheck fc) {
		FChecks.add( fc );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _sz
	 *          width & height of gui element
	 * @param _val
	 *         initial state of gui element
	 *         
	 */
	public void addCheck(String _name, float _x, float _y, float _sz, boolean _val) {
		FChecks.add(new FCheck(p5, _name, _x,_y, _sz, _val, typeface) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _sz
	 *          width & height of gui element
	 *         
	 */
	public void addCheck(String _name, float _x, float _y, float _sz) {
		FChecks.add(new FCheck(p5, _name, _x,_y, _sz, false, typeface) );
	}
	
	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _sz
	 *          width & height of gui element
	 * @param _val
	 *         initial state of gui element
	 *         
	 */
	public void addCheck(String _name, int _series, float _x, float _y, float _sz, boolean _val) {
		FChecks.add(new FCheck(p5, _name + Integer.toString(_series), _x,_y, _sz, _val, typeface) );
	}

	
	//-----------------------------------------
	//FSlider
	//-----------------------------------------
	/**
	 * @param fs
	 *          FSlider instance
	 * 
	 */
	public void add(FSlider fs) {
		FSliders.add( fs );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addSlider(String _name, float _x, float _y, float _w, float _h) {
		FSliders.add(new FSlider(p5, _name, _x,_y, _w,_h, 0,100, 0, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 *          
	 */
	public void addSlider(String _name, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val) {
		FSliders.add(new FSlider(p5, _name, _x,_y, _w,_h, _vMin,_vMax, _val, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 * @param _labelType
	 *          display type of value LABEL_FLOAT or LABEL_INT
	 *          
	 */
	public void addSlider(String _name, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val, int _labelType) {
		FSliders.add(new FSlider(p5, _name, _x,_y, _w,_h, _vMin,_vMax, _val, typeface, _labelType) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 *          
	 */
	public void addSlider(String _name, int _series, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val) {
		FSliders.add(new FSlider(p5, _name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 * @param _labelType
	 *          display type of value LABEL_FLOAT or LABEL_INT
	 *          
	 */
	public void addSlider(String _name, int _series, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val, int _labelType) {
		FSliders.add(new FSlider(p5, _name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, typeface, _labelType) );
	}

	
	//-----------------------------------------
	//FMeter
	//-----------------------------------------
	/**
	 * @param fm
	 *          FMeter instance
	 * 
	 */
	public void add(FMeter fm) {
		FMeters.add( fm );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 *          
	 */
	public void addMeter(String _name, float _x, float _y, float _w, float _h) {
		FMeters.add(new FMeter(p5, _name, _x,_y, _w,_h, 0,100, 0, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 *          
	 */
	public void addMeter(String _name, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val) {
		FMeters.add(new FMeter(p5, _name, _x,_y, _w,_h, _vMin,_vMax, _val, typeface, LABEL_FLOAT) );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 * @param _labelType
	 *          display type of value LABEL_FLOAT or LABEL_INT
	 *          
	 */
	public void addMeter(String _name, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val, int _labelType) {
		FMeters.add(new FMeter(p5, _name, _x,_y, _w,_h, _vMin,_vMax, _val, typeface, _labelType) );
	}

	
	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 *          
	 */
	public void addMeter(String _name, int _series, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val) {
		FMeters.add(new FMeter(p5, _name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, typeface, LABEL_FLOAT) );
	}
	
	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *          height of gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 * @param _val
	 *          initial value of gui element
	 * @param _labelType
	 *          display type of value LABEL_FLOAT or LABEL_INT
	 *          
	 */
	public void addMeter(String _name, int _series, float _x, float _y, float _w, float _h, float _vMin, float _vMax, float _val, int _labelType) {
		FMeters.add(new FMeter(p5, _name + Integer.toString(_series), _x,_y, _w,_h, _vMin,_vMax, _val, typeface, _labelType) );
	}
	

	//-----------------------------------------
	//FCheck
	//-----------------------------------------
	/**
	 * @param fd
	 *          FDropDown instance
	 * 
	 */
	public void add(FDropDown fd) {
		FDropDowns.add( fd );
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _x
	 *          x value of gui element
	 * @param _y
	 *          y value of gui element
	 * @param _w
	 *          width of gui element
	 * @param _h
	 *         height of gui element
	 *         
	 */
	public void addDropDown(String _name, float _x, float _y, float _w, float _h) {
		FDropDowns.add(new FDropDown(p5, _name, _x,_y, _w, _h, typeface) );
	}
	
	
	//-----------------------------------------
	//FLabel
	//-----------------------------------------
	/**
	 * @param fl
	 *          FLabel instance
	 * 
	 */
	public void add(FLabel fl) {
	}
	
	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	/**
	 * create (draw to screen) initiated gui elements
	 * 
	 */
	public void create() {
		p5.rectMode(CORNER);
		p5.strokeWeight(1);

		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			fk.setColorActive( colorActive );
			fk.setColorInactive( colorInactive );
			fk.showLabels( showLabels );
			fk.create();
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			fk3.setColorActive( colorActive );
			fk3.setColorInactive( colorInactive );
			fk3.showLabels( showLabels );
			fk3.create();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			fc.setColorActive( colorActive );
			fc.setColorInactive( colorInactive );
			fc.showLabels( showLabels );
			fc.create();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			fs.setColorActive( colorActive );
			fs.setColorInactive( colorInactive );
			fs.showLabels( showLabels );
			fs.create();
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			fm.setColorActive( colorActive );
			fm.setColorInactive( colorInactive );
			fm.showLabels( showLabels );
			fm.create();
		}
		for(int i=0; i<FDropDowns.size(); i++) {
			FDropDown fd = (FDropDown) FDropDowns.get(i);
			fd.setColorActive( colorActive );
			fd.setColorInactive( colorInactive );
			fd.showLabels( showLabels );
			fd.create();
		}

	}

	/**
	 * @param _showLabels
	 *          toggle showing value labels for all gui elements
	 */
	public void showLabels(boolean _showLabels) {
		showLabels = _showLabels;
	}
	
	/**
	 * @param _showLabels
	 *          toggle showing value label for the gui element
	 * @param _name
	 *          name of the gui element
	 */
	public void showLabels(boolean _showLabels, String _name) {
		/*
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.showLabels( _showLabels );
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) fc.showLabels( _showLabels );
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.showLabels( _showLabels );
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) fm.showLabels( _showLabels );
		}
		*/
	}
	
	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param _name
	 *          name of the gui element
	 * @param _val
	 *          set element PVector value 
	 */
	public void setValue(String _name, PVector _val) {
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.setCoord(_val.x,_val.y,_val.z);
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			if(_name.compareTo(fk3.name) == 0) fk3.setCoord(_val.x,_val.y,_val.z);
		}
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *          set element PVector value 
	 */
	public void setValue(String _name, int _series, PVector _val) {
		String s_name = _name + Integer.toString(_series);
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(s_name.compareTo(fk.name) == 0) fk.setCoord(_val.x,_val.y,_val.z);
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			if(s_name.compareTo(fk3.name) == 0) fk3.setCoord(_val.x,_val.y,_val.z);
		}
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _val
	 *          set element boolean value 
	 */
	public void setValue(String _name, boolean _val) {
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) fc.setValue(_val);
		}
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *          set element boolean value 
	 */
	public void setValue(String _name, int _series, boolean _val) {
		String s_name = _name + Integer.toString(_series);
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(s_name.compareTo(fc.name) == 0) fc.setValue(_val);
		}
	}

	
	/**
	 * @param _name
	 *          name of the gui element
	 * @param _val
	 *          set element float/int value 
	 */
	public void setValue(String _name, float _val) {
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}

		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) fm.setValue(_val);
		}
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _val
	 *          set element float/int value 
	 */
	public void setValue(String _name, int _series, float _val) {
		String s_name = _name + Integer.toString(_series);
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(s_name.compareTo(fs.name) == 0) fs.setValue(_val);
		}

		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(s_name.compareTo(fm.name) == 0) fm.setValue(_val);
		}
	}

	
	/**
	 * @param _name
	 *          name of the gui element
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 */
	public void setValueRange(String _name, float _vMin, float _vMax) {
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.setValueRange(_vMin,_vMax);
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) fm.setValueRange(_vMin,_vMax);
		}
	}

	/**
	 * @param _name
	 *          name of the gui element
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 * @param _vMin
	 *          minimum value of gui element
	 * @param _vMax
	 *          maximum value of gui element
	 */
	public void setValueRange(String _name, int _series, float _vMin, float _vMax) {
		String s_name = _name + Integer.toString(_series);
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(s_name.compareTo(fs.name) == 0) fs.setValueRange(_vMin,_vMax);
		}

		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(s_name.compareTo(fm.name) == 0) fm.setValueRange(_vMin,_vMax);
		}
	}


	/**
	 * @param _name
	 *          name of the gui element
	 * @param _nameItem
	 *          name of the added menu item
	 *          
	 */
	public void addItem(String _name, String _nameItem) {
		for(int i=0; i<FDropDowns.size(); i++) {
			FDropDown fd = (FDropDown) FDropDowns.get(i);
			if(_name.compareTo(fd.name) == 0) fd.addItem(_nameItem);
		}
	}

	
	/**
	 * @param _name
	 *          name of the gui element
	 * @param _val
	 *          increment value
	 */
	public void enableSnap(String _name, float _val) {
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.enableSnap(_val);
		}

		/*
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.enableSnap(_val);
		}
		*/

		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) fm.enableSnap(_val);
		}
	}
	
	/**
	 * @param _name
	 *          name of the gui element
	 */
	public void disableSnap(String _name) {
		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) fk.disableSnap();
		}

		/*
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) fs.disableSnap();
		}
		*/

		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) fm.disableSnap();
		}
	}


	/**
	 * @param _typeface
	 *          set global typeface for all gui elements
	 */
	public void setTypeface(PFont _typeface) {
		typeface[0] = _typeface;
		typeface[1] = _typeface;
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}

	public void setTypeface(PFont _typeface[]) {
		typeface[0] = _typeface[0];
		typeface[1] = _typeface[1];
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}

	public void setTypeface(PFont _typeface, PFont _typefaceBold) {
		typeface[0] = _typeface;
		typeface[1] = _typefaceBold;
		//System.out.println( "FControl typeface " + typeface.getFont().getName() );
	}

	/**
	 * @param _colorInactive
	 *          set global inactive color for all gui elements
	 */
	public void setColorInactive(int _colorInactive) {
		colorInactive = _colorInactive;
	}

	/**
	 * @param _colorActive
	 *          set global active color for all gui elements
	 */
	public void setColorActive(int _colorActive) {
		colorActive = _colorActive;
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return boolean value of gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
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
	 *          name of the gui element 
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 *          
	 */
	public boolean getBoolValue(String _name, int _series) {
		String s_name = _name + Integer.toString(_series);
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(s_name.compareTo(fc.name) == 0) boolVal = fc.getValue();
			//else bool = null;
		}
		return boolVal;
	}

	/**
	 * return int value of gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
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
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) intVal = fm.getIntValue();
			//else return null;
		}

		return intVal;
	}

	/**
	 * return int value of gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 *          
	 */
	public int getIntValue(String _name, int _series) {
		String s_name = _name + Integer.toString(_series);
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(s_name.compareTo(fc.name) == 0) intVal = ( fc.getValue() )?1:0;
			//else bool = null;
		}

		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(s_name.compareTo(fs.name) == 0) intVal = fs.getIntValue();
			//else return null;
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(s_name.compareTo(fm.name) == 0) intVal = fm.getIntValue();
			//else return null;
		}

		return intVal;
	}	
	
	
	/**
	 * return float value of gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
	 *          
	 */
	public float getFloatValue(String _name) {
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) floatVal = fs.getFloatValue();
			//else return null;
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) floatVal = fm.getFloatValue();
			//else return null;
		}

		return floatVal;
	}

	/**
	 * return float value of gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 *          
	 */
	public float getFloatValue(String _name, int _series) {
		String s_name = _name + Integer.toString(_series);
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(s_name.compareTo(fs.name) == 0) floatVal = fs.getFloatValue();
			//else return null;
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(s_name.compareTo(fm.name) == 0) floatVal = fm.getFloatValue();
			//else return null;
		}

		return floatVal;
	}

	
	/**
	 * return PVector value of gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
	 *          
	 */
	//public PVector getCoord(String _name) {
	public PVector getPointValue(String _name) {
		PVector point = new PVector();

		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) point = fk.getCoord();
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			if(_name.compareTo(fk3.name) == 0) point = fk3.getCoord();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) point = fc.getCoord();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) point = fs.getCoord();
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) point = fm.getCoord();
		}

		return point;
	}

	/**
	 * return PVector value of gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
	 * @param _series
	 *          number marker for multiple elements (i.e. name + series = "name0")
	 *          
	 */
	//public PVector getCoord(String _name) {
	public PVector getPointValue(String _name, int _series) {
		PVector point = new PVector();
		String s_name = _name + Integer.toString(_series);

		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(s_name.compareTo(fk.name) == 0) point = fk.getCoord();
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			if(s_name.compareTo(fk3.name) == 0) point = fk3.getCoord();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(s_name.compareTo(fc.name) == 0) point = fc.getCoord();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(s_name.compareTo(fs.name) == 0) point = fs.getCoord();
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(s_name.compareTo(fm.name) == 0) point = fm.getCoord();
		}

		return point;
	}

	
	/**
	 * return whether mouse is over the gui element 
	 * 
	 * @param _name
	 *          name of the gui element 
	 *          
	 */
	public boolean getOver(String _name) {
		boolean _over = false;

		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) _over = fk.getOver();
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			if(_name.compareTo(fk3.name) == 0) _over = fk3.getOver();
		}
		for(int i=0; i<FChecks.size(); i++) {
			FCheck fc = (FCheck) FChecks.get(i);
			if(_name.compareTo(fc.name) == 0) _over = fc.getOver();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) _over = fs.getOver();
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) _over = fm.getOver();
		}		
		
		return _over;
	}

	
	/**
	 * return whether the element has been moved/adjusted 
	 * 
	 * @param _name
	 *          name of the gui element 
	 *          
	 */
	public boolean getMoved() {
		boolean _moved = false;

		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			_moved = fk.getMoved();
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			_moved = fk3.getMoved();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			_moved = fs.getMoved();
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			_moved = fm.getMoved();
		}		
		
		return _moved;
	}
	
	
	/**
	 * return whether the element has been moved/adjusted 
	 * 
	 * @param _name
	 *          name of the gui element 
	 *          
	 */
	public boolean getMoved(String _name) {
		boolean _moved = false;

		for(int i=0; i<FKnobs.size(); i++) {
			FKnob fk = (FKnob) FKnobs.get(i);
			if(_name.compareTo(fk.name) == 0) _moved = fk.getMoved();
		}
		for(int i=0; i<FKnob3Ds.size(); i++) {
			FKnob3D fk3 = (FKnob3D) FKnob3Ds.get(i);
			if(_name.compareTo(fk3.name) == 0) _moved = fk3.getMoved();
		}
		for(int i=0; i<FSliders.size(); i++) {
			FSlider fs = (FSlider) FSliders.get(i);
			if(_name.compareTo(fs.name) == 0) _moved = fs.getMoved();
		}
		for(int i=0; i<FMeters.size(); i++) {
			FMeter fm = (FMeter) FMeters.get(i);
			if(_name.compareTo(fm.name) == 0) _moved = fm.getMoved();
		}		
		
		return _moved;
	}
	
	
	/**
	 * return the selection from FDropDown 
	 * 
	 * @param _name
	 *          name of the gui element 
	 *          
	 */
	public int getSelection(String _name) {
		for(int i=0; i<FDropDowns.size(); i++) {
			FDropDown fd = (FDropDown) FDropDowns.get(i);
			if(_name.compareTo(fd.name) == 0) selVal = fd.getSelection();
		}
		return selVal;
	}
	
	/**
	 * return inactive color (as int) 
	 * 
	 */
	public int getColorInactive() {
		return colorInactive;
	}
	/**
	 * return active color (as int) 
	 * 
	 */
	public int getColorActive() {
		return colorActive;
	}

}
