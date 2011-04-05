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

public class FCheck extends FKnopf {

	// -----------------------------------------------------------------------------
	// properties
	// -----------------------------------------------------------------------------
	private float sz;
	private boolean on_off = false;
	//protected float _myValue;

	// -----------------------------------------------------------------------------
	// constructor
	// -----------------------------------------------------------------------------
	/**
	 * @param x
	 *            the x position of FCheck
	 * @param y
	 *            the y position of FCheck
	 * @param sz
	 *            the size of FCheck
	 */
	public FCheck(PApplet p5, float x, float y, float sz) {
		super(p5, x, y, sz, sz);
		setSize(sz);
		setB(sz);
		setH(sz);
	}

	// -----------------------------------------------------------------------------
	// helpers
	// -----------------------------------------------------------------------------
	/**
	 * draw check to screen
	 * 
	 */
	public void create() {
		update();
		on_off();

		p5.noFill();
		if ( LOCKED ) {
			p5.stroke(farbeActive);
		} else {
			p5.stroke(farbeResting);
		}

		p5.fill(farbeResting);
		p5.rect(x, y, sz, sz);
		p5.noStroke();

		// x'd
		if (on_off) {
			p5.fill(farbeActive);
			p5.rect(x, y, sz, sz);
		}

		if (labelText != null) createLabel();
	}

	/**
	 */
	public void on_off() {
		if( LOCKED ) {
			on_off = !on_off;

			LOCKED = !LOCKED;
			OVER = !OVER;
			PRESSED = !PRESSED;
		}
	}


	// -----------------------------------------------------------------------------
	// sets
	// -----------------------------------------------------------------------------
	/**
	 * @param sz
	 *            set the size of FCheck
	 */
	public void setSize(float sz) {
		this.sz = sz;
	}

	/**
	 * @param on_off
	 *            set the value on_off
	 */
	 public void setValue(boolean on_off) { 
		 this.on_off = on_off;
		 //_myValue = (on_off == false) ? 0 : 1;
	 }

	// -----------------------------------------------------------------------------
	// gets
	// -----------------------------------------------------------------------------
	/**
	 * return size of check (height & width are same)
	 * 
	 * @return sz
	 */
	public float getSize() {
		return sz;
	}

	/**
	 * return whether check is ticked or not
	 * 
	 * @return on_off
	 */
	public boolean getValue() {
		return on_off;
	}

}
