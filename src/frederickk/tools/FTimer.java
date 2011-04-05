package frederickk.tools;

/*
 *  Frederickk.Tools 002
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a collection of tools that i tend to use frequently
 *  http://code.google.com/p/frederickk/
 *
 *	FTimer code improvement by 
 *	Eugen Kern-Emden
 *	eugn@kern-emden.de
 *
 */

import processing.core.PApplet;
//import java.awt.Color;

public class FTimer {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	private PApplet p5;

	private float startTime;
	private float freq;

	private boolean firstCall;
	private boolean trigger;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FTimer (work in progess)
	 * 
	 * @param thePApplet
	 * 			PApplet
	 */
	public FTimer(PApplet papplet) {
		p5 = papplet;
		firstCall = true;
	}

	/**
	 * instantiate FTimer (work in progess)
	 * 
	 * @param thePApplet
	 * 			PApplet
	 * @param _freq
	 * 			frequency (in millis) the timer reacts
	 * 
	 */
	public FTimer(PApplet papplet, float _freq) {
		p5 = papplet;
		firstCall = true;
		setFreq(_freq);
	}

	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	public void start() {
		startTime = p5.millis();
		//startTime = System.currentTimeMillis();
		firstCall = false;
	}

	public void stop() {
		firstCall = true;
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	public void setFreq(float _freq) {
		freq = _freq;
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	public boolean getTrigger() {
		if(firstCall) start();

		if( (p5.millis() - startTime) < freq) {
		//if( (System.currentTimeMillis() - startTime) < freq) {
			trigger = true;
		} else {
			stop();
			trigger = false;
		}

		return trigger;
	}

}

/*
 * public class FTimer {
 * //------------------------------------------------------
 * ----------------------- //properties
 * //----------------------------------------
 * ------------------------------------- private PApplet p5;
 * 
 * private boolean trigger; private boolean timerRun;
 * 
 * private int fc; private float freq;
 * 
 * private int count;
 * 
 * private int lineX; private int lineColor; private int boxColor;
 * 
 * 
 * //----------------------------------------------------------------------------
 * - //constructor
 * //------------------------------------------------------------
 * ----------------- public FTimer(PApplet thePApplet) { p5 = thePApplet;
 * 
 * fc = p5.millis(); setTime(0);
 * 
 * Color _lineColor = new Color(0,255,255); setLineColor( _lineColor.getRGB() );
 * 
 * Color _boxColor = new Color(255,255,255, 64); boxColor = _boxColor.getRGB();
 * }
 * 
 * public FTimer(PApplet thePApplet, float freq) { p5 = thePApplet;
 * 
 * fc = p5.millis(); setTime(freq);
 * 
 * //Color _lineColor = new Color(0,255,255); //setLineColor(
 * _lineColor.getRGB() );
 * 
 * //Color _boxColor = new Color(255,255,255, 64); //boxColor =
 * _boxColor.getRGB(); }
 * 
 * 
 * //----------------------------------------------------------------------------
 * - //helpers
 * //----------------------------------------------------------------
 * ------------- public void run() { timerRun = true; }
 * 
 * public void run(boolean _timerRun) { timerRun = _timerRun; timing(); }
 * 
 * private void stop() { timerRun = false; trigger = false; fc = p5.millis();
 * //System.out.println("fc: " + fc); }
 * 
 * private void timing() { if(timerRun) { if( p5.millis() >= fc + freq) { fc =
 * p5.millis(); trigger = true; count++; } } else { stop(); } }
 * 
 * public void visual(float _x, float _y, float _h) { timing();
 * 
 * p5.noStroke(); p5.fill( boxColor ); p5.rect(_x,_y, freq, _h);
 * 
 * p5.stroke( lineColor ); p5.noFill(); p5.line(_x + (int) p5.constrain(lineX,
 * 0,freq),_y, _x + (int) p5.constrain(lineX, 0,freq),_y + _h ); }
 * 
 * 
 * //----------------------------------------------------------------------------
 * - //sets
 * //--------------------------------------------------------------------
 * --------- public void reset() { trigger = false; }
 * 
 * public void setTime(float _freq) { freq = _freq; }
 * 
 * public void setLineColor( int val ) { lineColor = val; }
 * 
 * public void setBoxColor( int val ) { boxColor = val; }
 * 
 * 
 * //----------------------------------------------------------------------------
 * - //gets
 * //--------------------------------------------------------------------
 * --------- public float getTime() { return freq; }
 * 
 * public boolean getTrigger() { return trigger; }
 * 
 * public int getCount() { return count; }
 * 
 * }
 */