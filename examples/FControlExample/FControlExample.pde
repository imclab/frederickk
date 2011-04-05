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
  typeface = loadFont("FuturaT-Bold-10.vlw");

  //initiate FControl
  gui = new FControl(this);

  //set typeface (optional)
  //defaul is "LucidaGrande-Bold"
  gui.setTypeface( typeface );
  gui.setColorActive( color(255,200,0) );

  //add gui elements
  gui.addKnob("knob", random(width),random(height), 8,8);
  gui.addCheck("check", 15,45, 8, true);
  gui.addSlider("slider", 15,65, 100,8, 0,100, 25);
  gui.addMeter("meter", 15,85, 100,8, 0,100, 0);

  //enable snap to grid i.e. 20x20
  gui.enableSnap("knob", 20);
}

void draw() {
  background(0);
  
  //draw gui elements to screen
  gui.create();

  //show/hide labels with value from check
  gui.showLabels( gui.getBoolValue("check"));
    
}

