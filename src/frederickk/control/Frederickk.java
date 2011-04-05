package frederickk.control;

/*
you can put a one sentence description of your library here.

(c) copyright

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General
Public License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place, Suite 330,
Boston, MA  02111-1307  USA
 */

import processing.core.PApplet;
import processing.core.PFont;
import java.awt.Color;
//import java.awt.event.MouseEvent;

abstract class Frederickk {// extends ControlEvents {

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	public final String VERSION = "0.0.1";

	protected int farbeResting;
	protected int farbeActive;

	protected static PFont typeface;
	protected static int typesize = 9;

	//public ControlEvents cEvents;

	public boolean OVER, CLICK = false, PRESSED, LOCKED, DRAG, RELEASE;
	public float MOUSEX, MOUSEY;
	public float MOUSEX_NEW, MOUSEY_NEW;
	
	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate Frederickk
	 * 
	 * @param thePApplet
	 * 			PApplet
	 */
	public Frederickk(
			PApplet thePApplet) {

		//super(thePApplet);
		p5 = thePApplet;
		//cEvents = new ControlEvents(p5);

		//set default resting (inactive) color
		Color _farbeResting = new Color(255,255,255, 64);
		farbeResting = _farbeResting.getRGB();
		
		//set default resting (inactive) color
		Color _farbeActive = new Color(0,255,255);
		farbeActive = _farbeActive.getRGB();

		//set default typeface
		typeface = p5.createFont("LucidaGrande-Bold", 9);
		setTypeface(typeface);

		//wilkommen();
	}

	abstract void create();

	//-----------------------------------------------------------------------------
	//helpers
	//-----------------------------------------------------------------------------
	public void wilkommen() {
		System.out.println(
			"-----------------------------------------------------------------------------\n" +
			"frederickk controls Library " + VERSION + "\n" +
			"-----------------------------------------------------------------------------\n"
		);
	}

	/**
	 * @param val
	 *          set the value to constrain
	 *          
	 * @param minv
	 *          set the minimum constrain value
	 *          
	 * @param maxv
	 *          set the maximum contrain value
	 */
	protected int constrain(int val, int minv, int maxv) {
		return PApplet.min(PApplet.max(val, minv), maxv);
	}

	/**
	 * @param value
	 *          The incoming value to be converted
	 *          
	 * @param low1
	 *          Lower bound of the value's current range
	 *          
	 * @param high1
	 *          Upper bound of the value's current range
	 *          
	 * @param low2
	 *          Lower bound of the value's target range
	 *          
	 * @param high2
	 *          Upper bound of the value's target range
	 */
	public float map(
			float value,
			float low1,
			float high1, 
			float low2,
			float high2) {

		float m = low2 + (high2 - low2) * ((value - low1) / (high1 - low1));
		return m;
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param farbeResting
	 *          set the resting color
	 */
	public void setFarbeResting(int farbeResting) {
		this.farbeResting = farbeResting;
	}

	/**
	 * @param farbeActive
	 *          set the active color
	 */
	public void setFarbeActive(int farbeActive) {
		this.farbeActive = farbeActive;
	}

	/**
	 * @param type
	 *          set the global typeface for all components
	 */
	public static void setTypeface(PFont type) {
		typeface = type;
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return resting (inactive) color
	 * 
	 * @return farbeResting
	 */
	public int getFarbeResting() {
		return farbeResting;
	}

	/**
	 * return rollover color
	 * 
	 * @return farbeActive
	 */
	public int getFarbeActive() {
		return farbeActive;
	}

	/**
	 * return typeface
	 * 
	 * @return typeface
	 */
	public static PFont getTypeface() {
		return typeface;
	}

	/**
	 * return typeface size
	 * 
	 * @return typesize
	 */
	public static int getTypesize() {
		return typesize;
	}

	/**
	 * return version number
	 */
	public void getVersion() {
		System.out.println(VERSION);
	}

}