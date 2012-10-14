import frederickk.control.*;

/**
 *  FControlGroup Example
 *  Ken Frederick
 *  
 *  Example showing the usage of the FControl GUI elements:
 *  FButton, FKnob, FCheck, FSlider, FMeter and being able to set your
 *  own custom typeface, by default LucidaGrande-Bold is used
 *  
 */



//-----------------------------------------------------------------------------
// properties
//-----------------------------------------------------------------------------
FControlGroup cgroup;

PFont typeface;

int[] rgbVals = { (int)random(255), (int)random(255), (int)random(255) };



//-----------------------------------------------------------------------------
// methods
//-----------------------------------------------------------------------------
void setup() {
  size(300, 300);
  //smooth();
  typeface = loadFont("FuturaT-Bold-10.vlw");

  // initiate FControlGroup
  // pushes gui elements into a seperate window
  cgroup = new FControlGroup("gui window", 450,450);
  cgroup.setRowsCols(3,3);
  cgroup.showGrid(true);

  // initiate FControl
  FControl elements = cgroup.getElements();
//  elements = new FControl( cgroup.getApplet() );
//  elements.setTypeface( typeface );
  elements.setColorOver( color(0, 200, 255) );
  elements.addKnob("R", cgroup.getCol(0), cgroup.getRow(1), 100, 0.0f,255.0f, rgbVals[0]);
  elements.addKnob("G", cgroup.getCol(1), cgroup.getRow(1), 100, 0.0f,255.0f, rgbVals[1]);
  elements.addKnob("B", cgroup.getCol(2), cgroup.getRow(1), 100, 0.0f,255.0f, rgbVals[2]);

  // put the elements in the window
//  cgroup.setElements( elements );
}

//-----------------------------------------------------------------------------
void draw() {
  background( color(rgbVals[0], rgbVals[1], rgbVals[2]) );

  // rgb values
//  println( cgroup.getIntValue("R") );
//  rgbVals[0] = cgroup.getIntValue("R");
//  rgbVals[1] = cgroup.getIntValue("G");
//  rgbVals[2] = cgroup.getIntValue("B");

}



void keyPressed() {
  // put the elements in the window
//  cgroup.setElements( elements );
}

