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

import processing.core.*;
import processing.core.PConstants;

public class FKnob3D extends FKnob implements PConstants {
	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	/*
	 * button in 3d space with help from mustermann
	 * http://processing.org/discourse/yabb2/YaBB.pl?num=1251823003
	 */
	private PGraphics3D g = new PGraphics3D();
	private PGraphics3D p3d = (PGraphics3D)g;
	private PMatrix3D proj = new PMatrix3D();
	private PMatrix3D cam = new PMatrix3D();
	private PMatrix3D modvw = new PMatrix3D();
	private PMatrix3D modvwInv = new PMatrix3D();
	private PMatrix3D screen2Model = new PMatrix3D();

	protected float x3D,y3D,z3D;
	PVector loc2D = new PVector();

	//-----------------------------------------------------------------------------
	//properties
	//-----------------------------------------------------------------------------
	public FKnob3D(PApplet p5) {
		super(p5);
		setup3D(p5);
	}

	public FKnob3D(PApplet p5, String _name, float _x, float _y, float _z, float _w, float _h, PFont _typeface[], int _labelType) {
		super(p5, _name, _x,_y,_z, _w,_h, _typeface, _labelType);
		setup3D(p5);
	}


	//-----------------------------------------------------------------------------
	//methods
	//-----------------------------------------------------------------------------
	private void setup3D(PApplet papplet) {
		g.setParent(papplet);
		p3d.setParent(papplet);
	}
	
	public void create() {
		update();
		//if(!DRAG_OFF) drag();

		System.out.println("g\t" + g.zbuffer);
		System.out.println("projection\t" + p3d.projection);
		System.out.println("camera\t" + p3d.camera);
		System.out.println("modelview\t" + p3d.modelview);
		System.out.println("modelviewIn\t" + p3d.modelviewInv);
		//proj = p3d.projection.get();
	    //cam = p3d.camera.get();
	    //modvw = p3d.modelview.get();
	    //modvwInv = p3d.modelviewInv.get();
	    

		//-----------------------------------------
		val.x = x;
		val.y = y;
		val.z = z;
		
		//-----------------------------------------
		if(showLabels) {
			if(labelType == LABEL_FLOAT) labelVal.create( getStrValue( val.x,val.y, 2 ) );
			else if(labelType == LABEL_INT) labelVal.create( getStrValue( val.x, val.y ) );
		}
		
		//-----------------------------------------
		p5.noStroke();
		if( getOver() || LOCKED ) p5.fill( getColorActive() );
		else p5.fill( getColorInactive() );
	    p5.pushMatrix();
	    p5.translate(x, y, z);
	    p5.sphere(w);
	    p5.popMatrix();
	}

	//-----------------------------------------------------------------------------
	//interaction
	//-----------------------------------------------------------------------------
	protected void update() {
		if( getOver() && PRESSED ) LOCKED = true;

		if(LOCKED) {
			// calculate transformation matrix for projecting mouse coords
			// to the plane where the current selected vertex is
			// this doesn't work!
			screen2Model = modvwInv;
			screen2Model.apply(cam);

			float scrn[] = {MOUSE_X, MOUSE_Y, 0};
			float model[] = new float[3];
			screen2Model.mult(scrn, model);

			x = model[0];
			y = model[1];
			z = model[2];
		}

	}

	protected boolean getOver() {
		int x2D = (int) p5.screenX(x, y, z);
		int y2D = (int) p5.screenY(x, y, z);

		if(MOUSE_X-w*1.5 > x2D && MOUSE_X < x2D+w*1.5 && 
		   MOUSE_Y-h*1.5 > y2D && MOUSE_Y < y2D+h*1.5) {
			OVER = true;
		} else {
			OVER = false;
		}
		return OVER;
	}

	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------


}
