import frederickk.control.*;

/**
 * FControlExample
 * Ken Frederick
 * 
 * Example showing the usage of the FControl GUI elements:
 * FKnob, FCheck, FSlider, FMeter and being able to set your
 * own custom typeface, by default LucidaGrande-Bold is used
 * 
 */
 
FControl gui;
PFont typeface;

void setup() {
  size(200,200);
  typeface = createFont("FuturaT-Bold",10);

  //initiate FControl
  gui = new FControl(this);

  //set typeface (optional)
  //default is "LucidaGrande-Bold"
  gui.setTypeface( typeface );
  gui.setColorOver( color(255,200,0) );

  //add gui elements
  gui.addDropDown("dropdown", 15,15, 100,15);
  for(int i=0; i<5; i++) {
    gui.addItem("dropdown", "item " + str(i));
  }
}

void draw() {
  background(0);
  
  //draw gui elements to screen
  println( gui.getSelection("dropdown") );
  gui.draw();

}

