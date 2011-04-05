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

public class FSlider extends FKnopf {

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	private int posMin, posMax, loose = 3;
	private float pos, posNew, vMin, vMax;

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
	public FSlider(
			PApplet p5,
			float x,
			float y,
			float b,
			float h) {

		super(p5,x,y,b,h);

		//int widthtoheight = (int) ((int) b - h);
		//float ratio = b / widthtoheight;
		y = y-h/2;

		pos = x + b/2 - h/2;
		posNew = pos;
		posMin = (int) x;
		posMax = (int) ((int) x + (b - h));

		//default range -width,width
		setValueRange(-b,b);
	}

	//-----------------------------------------------------------------------------
	//helpers
	//-----------------------------------------------------------------------------
	/**
	 * draw slider to screen
	 * 
	 */
	public void create() {
		update();
		drag();

		p5.noStroke();
		p5.fill( farbeResting );
		p5.rect(x, y, b,h); 

		if( LOCKED ) p5.fill( farbeActive );
		else p5.fill( farbeResting );
		p5.rect(pos,y, h,h);
		p5.noFill();

		if(labelText != null) createLabel();
	}

	/**
	 */
	public void drag() {
		if( LOCKED ) posNew = constrain((int) (MOUSEX-h/2), posMin, posMax);
		if(PApplet.abs(posNew - pos) > 1) pos = pos + (posNew-pos)/loose;
	}
	
	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 * @param value
	 *          set the position of the FSlider
	 *          
	 */
	public void setValue(float value) {
		posNew = map(value, vMin,vMax, posMin,posMax);
	}

	/**
	 * @param vMin
	 *          set the minimum value range of FSlider
	 *          
	 * @param vMax
	 *          set the maximum value range of FSlider
	 */
	public void setValueRange(float vMin, float vMax) {
		this.vMin = vMin;
		this.vMax = vMax;
	}

	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 * return value of slider position
	 * 
	 * @return f
	 */
	public float getValue() {
		float f = map(posNew, posMin,posMax, vMin,vMax);
		return f;
	}

	/**
	 * return minimum value of slider 
	 * 
	 * @return vMin
	 */
	public float getValueMin() {
		return vMin;
	}

	/**
	 * return maximum value of slider 
	 * 
	 * @return vMax
	 */
	public float getValueMax() {
		return vMax;
	}


}
