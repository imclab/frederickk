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
import java.awt.event.MouseEvent;


public class FKnopf extends Frederickk {

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	protected float x, y, b, h;
	protected float MOUSEX_NEW, MOUSEY_NEW;
	
	public float width, height;
	public String labelText;

	//-----------------------------------------------------------------------------
	//constructor
	//-----------------------------------------------------------------------------
	/**
	 * @param p5
	 * 			PApplet
	 * @param x
	 *          the x position of FKnopf
	 * @param y
	 *          the y position of FKnopf
	 * @param b
	 *          the width of FKnopf
	 * @param h
	 *          the height of FKnopf
	 */
	public FKnopf(
			PApplet p5,
			float x,
			float y,
			float b,
			float h) {

		super(p5);
		setCoord(x,y,b,h);
		p5.registerMouseEvent(this);

		MOUSEX_NEW = x;
		MOUSEY_NEW = y;
	}

	//-----------------------------------------------------------------------------
	//helpers
	//-----------------------------------------------------------------------------
	/**
	 * draw knopf to screen
	 * 
	 */
	public void create() {
		update();
		//drag();

		if( getOver() || LOCKED ) p5.fill( farbeActive );
		else p5.fill( farbeResting );

		p5.rect(x,y, b,h);
		p5.noFill();

		if(labelText != null) createLabel();
	}

	protected void createLabel() {
		p5.fill( farbeResting );
	    p5.textFont (typeface);
	    p5.text(labelText, (x+b)+5, (y+h)-1);
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

		if (theMouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
			CLICK = true;
			RELEASE = false;
		} 

		if (theMouseEvent.getID() == MouseEvent.MOUSE_PRESSED) {
			PRESSED = true;
			RELEASE = false;
		} 

		if (theMouseEvent.getID() == MouseEvent.MOUSE_RELEASED) {
			PRESSED = false;
			RELEASE = true;
			LOCKED = false;
		} 

	}

	/**
	 */
	public boolean getOver() {
		if(MOUSEX > x && MOUSEX < x+b && 
		   MOUSEY > y && MOUSEY < y+h) {
			OVER = true;
		} else {
			OVER = false;
		}
		return OVER;
	}
	
	/**
	 */
	public void update() {
		if( getOver() && PRESSED ) LOCKED = true;
	}


	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param x
	 *          the x position of FKnopf
	 * @param y
	 *          the y position of FKnopf
	 * @param b
	 *          the width of FKnopf
	 * @param h
	 *          the height of FKnopf
	 */
	public void setCoord(
			float x,
			float y,
			float b,
			float h) {

		this.setX(x);
		this.setY(y);
		this.setB(b);
		this.setH(h);
	}

	/**
	 * @param b
	 *          the width of FKnopf
	 * @param h
	 *          the height of FKnopf
	 */
	public void setSize(
			float b,
			float h) {

		this.setB(b);
		this.setH(h);
	}

	/**
	 * @param val
	 *          set the x position of FKnopf
	 */
	public void setX(float val) {
		x = val;
	}

	/**
	 * @param val
	 *          set the y position of FKnopf
	 */
	public void setY(float val) {
		y = val;
	}

	/**
	 * @param val
	 *          set the width of FKnopf
	 */
	public void setB(float val) {
		b = val;
		width = b;
	}

	/**
	 * @param val
	 *          set the height of FKnopf
	 */
	public void setH(float val) {
		h = val;
		height = h;
	}

	/**
	 * @param labelText
	 *          set the text to be shown for the label
	 */
	public void setLabel(String labelText) {
		this.labelText = labelText;
	}
	
	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return x
	 * 
	 * @return x
	 */
	public float getX() {
		return x;
	}

	/**
	 * return y
	 * 
	 * @return y
	 */
	public float getY() {
		return y;
	}

	/**
	 * return width
	 * 
	 * @return b
	 */
	public float getB() {
		return b;
	}

	/**
	 * return height
	 * 
	 * @return h
	 */
	public float getH() {
		return h;
	}

}
