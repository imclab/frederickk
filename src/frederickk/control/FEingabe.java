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
import processing.core.PConstants;
import java.awt.event.KeyEvent;

public class FEingabe extends FKnopf implements PConstants{

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	private String inputEnter = new String();
	private String inputText = "FEingabe";//new String();
	private boolean enter;

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
	 */
	public FEingabe(
			PApplet p5,
			float x,
			float y) {

		super(p5, x, y, 75,20);

		setB(75);
		setH(20);
	}

	//-----------------------------------------------------------------------------
	//helpers
	//-----------------------------------------------------------------------------
	/**
	 * draw check to screen
	 * 
	 */
	public void create() {
		update();

		p5.noStroke();
		p5.fill( 255, 65 );
		p5.rect(x,y, b,h);

		p5.noFill();
		if( LOCKED ) p5.stroke( farbeActive );
		else p5.stroke( farbeResting );

		p5.rect(x,y, b,h);

		//text
		p5.noStroke();
		p5.fill(0,255,255);
		p5.textFont(typeface, typesize);
		p5.text(inputText + "|",
				(float) (x - ((b*0.5) + 5)), y - 5,
				b,h);
	}

	public void keyEvent(
			KeyEvent theKeyEvent) {

		if (KeyEvent.VK_ALT != KeyEvent.KEY_PRESSED && theKeyEvent.getID() == KeyEvent.KEY_PRESSED) {
			if (theKeyEvent.getID() == DELETE || theKeyEvent.getID() == BACKSPACE) {
				if (inputText.length() > 0) {
					inputText = inputText.substring(0, inputText.length() - 1);
				}
			} else if (theKeyEvent.getID() == ENTER) {
				submit();
			} else if (theKeyEvent.getID() != SHIFT && theKeyEvent.getID() != ALT &&
					theKeyEvent.getID() != TAB && theKeyEvent.getID() != CONTROL) {
				inputText = inputText + KeyEvent.KEY_PRESSED;
			}

		}
	}


	/**
	 * submit 
	 * 
	 */
	public void submit() {
		inputEnter = inputText;
		inputText = "";
		enter = true;
	}	
	
	/**
	 * determine whether a return/enter was sent 
	 * 
	 * @return enter
	 */
	public boolean isEnter() {
		return enter;
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	/**
	 *	function sets the text when entered
	 *
	 */
	public void setText() {
		/*
		if(keyPressed) {
			if(key == ENTER || key == RETURN) {
				inputEnter = inputText;
				inputText = "";
				enter = true;
			} else if(key == BACKSPACE && inputText.length() > 0) {
				inputText = inputText.substring(0, inputText.length() - 1);
			} else if(keyCode == KeyEvent.VK_CONTROL) {
				inputText = inputText;
			} else {
				inputText = inputText + key;
			}
		}
		 */
	}

	/**
	 * @param inputEnter
	 * 			PApplet
	 */
	public void setInputEnter(String inputEnter) {
		this.inputEnter = inputEnter;
	}

	/**
	 */
	public void setEnter(boolean enter) {
		this.enter = enter;
	}


	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	/**
	 *	return whether ENTER key was pressed
	 *
	 *	@return inputEnter
	 *
	 */
	public String getInputEnter() {
		return inputEnter;
	}

	
}
