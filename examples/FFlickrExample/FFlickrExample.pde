import frederickk.api.flickr.*;

//-----------------------------------------------------------------------------
//classes
//-----------------------------------------------------------------------------
FFlickr flickr;
int index = 0;

void setup() {
  size(500,500);
  smooth();

  flickr = new FFlickr(this);
  flickr.setKey("681a16a0f5448bb7c7db02431e59c7fa");
  flickr.setSecret("919c1384422fa4aa");

  flickr.setMaxItems(10); //default is 1
  flickr.getSearchTag( "munich" );
}

void draw() {
  background(0);
  fill(255);

  try {
    image( flickr.getImage(index), 0,0 );
  } 
  catch(Exception e) {
  }
}

//-----------------------------------------------------------------------------
//interactive
//-----------------------------------------------------------------------------
void keyPressed() {

  if(key == CODED) {
    if(keyCode == RIGHT) index++;
    if(keyCode == LEFT) index--;

    if( index >= flickr.getImageNum() ) index = 0;
    if( index < 0 ) index = flickr.getImageNum()-1;
  }
}

