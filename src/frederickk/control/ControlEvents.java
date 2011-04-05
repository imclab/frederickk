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

import java.awt.event.MouseEvent;

import processing.core.PApplet;
//import java.awt.event.MouseEvent;
//import java.awt.event.KeyEvent;

public class ControlEvents {

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected PApplet papplet;

	public boolean OVER, PRESSED, CLICK, RELEASE, LOCKED, DRAG;
	public float MOUSEX, MOUSEY;
	public float MOUSEX_NEW, MOUSEY_NEW;

	public boolean DEBUG = false;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * @param p
	 *            PApplet
	 */
	public ControlEvents(
			PApplet p) {

		papplet = p;
		papplet.registerMouseEvent(this);
	}

	//-----------------------------------------------------------------------------
	//interaction
	//-----------------------------------------------------------------------------
	/**
	 * capture mouse events
	 * 
	 */
	public void mouseEvent(
	        MouseEvent theMouseEvent) {
		MOUSEX = theMouseEvent.getX();
		MOUSEY = theMouseEvent.getY();

		if (theMouseEvent.getID() == MouseEvent.MOUSE_PRESSED) {
			setPressed(true);
			PRESSED = true;
			//setReleased(false);
		} 
		/*
		if (theMouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
			setClicked(true);
			setReleased(false);
		}
		*/
		/*
		if (theMouseEvent.getID() == MouseEvent.MOUSE_RELEASED) {
			setPressed(false);
			setClicked(false);
			setReleased(true);
			setLocked(false);
		}
		*/

		/*
		if (getPressed() && theMouseEvent.getID() == MouseEvent.MOUSE_MOVED) {
			DRAG = true;
		} else {
			DRAG = false;
		}
		*/

		if( DEBUG ) {
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("x: " + MOUSEX + ", y: " + MOUSEY );
			System.out.println("-----------------------------------------------------------------------------");
			//System.out.println("over---: " + getOver() );
			System.out.println( "pressed: " + getPressed() );
			System.out.println( "click--: " + getClicked() );
			System.out.println( "release: " + getReleased() );
			System.out.println( "locked-: " + getLocked() );
		}
	}


	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param theFlag
	 *          set mouse PRESSED
	 */
	public void setPressed(boolean theFlag) {
		PRESSED = theFlag;
	}

	/**
	 * @param theFlag
	 *          set mouse CLICK
	 */
	public void setClicked(boolean theFlag) {
		CLICK = theFlag;
	}

	/**
	 * @param theFlag
	 *          set mouse RELEASE
	 */
	public void setReleased(boolean theFlag) {
		RELEASE = theFlag;
	}


	/**
	 * @param theFlag
	 *          set mouse LOCKED
	 */
	public void setLocked(boolean theFlag) {
		LOCKED = theFlag;
	}

	/**
	 * @param theFlag
	 *          toggle debug mode
	 */
	public void setDebug(boolean theFlag) {
		DEBUG = theFlag;
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return mouse press
	 * 
	 * @return PRESSED
	 */
	public boolean getPressed() {
		return PRESSED;
	}

	/**
	 * return mouse click
	 * 
	 * @return CLICK
	 */
	public boolean getClicked() {
		return CLICK;
	}

	/**
	 * return mouse release
	 * 
	 * @return RELEASE
	 */
	public boolean getReleased() {
		return RELEASE;
	}

	/**
	 * return clicked
	 * 
	 * @return LOCKED
	 */
	public boolean getLocked() {
		return LOCKED;
	}

}



