/**
 *  FEdgeDetect Example 
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 */

// -----------------------------------------------------------------------------
// libraries
// -----------------------------------------------------------------------------
import javax.media.opengl.*;
import processing.opengl.*;
import org.processing.wiki.triangulate.*;

import frederickk.tools.*;
import frederickk.control.*;



// -----------------------------------------------------------------------------
// constants
// -----------------------------------------------------------------------------
final int MODE_GRADIENT = 0;
final int MODE_FLAT = 1;



// -----------------------------------------------------------------------------
// properties
// -----------------------------------------------------------------------------
// edges
FEdgeDetect edges;

// triangulation
ArrayList triangles = new ArrayList();
float[] TriangleVBOPoints;
float[] TriangleVBOColors;

// image
PImage src;

// opengl
PGraphicsOpenGL pgl;
GL gl;

// interface
FControl gui;
float thresh;
int density;
float b_low;
float b_high;

int mode = MODE_FLAT;



// -----------------------------------------------------------------------------
void setup() {
  size(536,666, OPENGL);

  pgl = (PGraphicsOpenGL)g;
  gl = pgl.gl;
  hint(ENABLE_OPENGL_4X_SMOOTH);


  /**
   *  edge detect
   */
  gui = new FControl(this);
  thresh = 10.0;
  density = 20;   // every 20pixels we look for an edge
  b_low = 50;    // any pixel of the original image with a brightness above 50 ...
  b_high = 255;  // and below 255 is valid

  /**
   *  image
   */
  src = loadImage("http://mikaeleliasson.com/images/Mikael_Eliasson_066.jpg");

  /**
   *  edge detect
   */
  edges = new FEdgeDetect();
  edges.process(src, thresh, true);
  edges.findEdges(density, b_low,b_high);

}


// -----------------------------------------------------------------------------
void draw() {
  background(0);
  gui.draw();
  image(src, 0,0);

  density = (int) map(mouseX, 0,width, 5,40);


  // -----------------------------------------------------------------------------
  // render
  // -----------------------------------------------------------------------------
  pgl.beginGL();
  lights();

  //gl.glTranslatef( (width-src.width)/2, (height-src.height)/2, 0);
  // vbo.render(GL.GL_TRIANGLES);

  if(triangles.size() != 0) {
    if(mode == 0 || mode == 1) {
      gl.glBegin(GL.GL_TRIANGLES);

      float x1,y1,z1, x2,y2,z2, x3,y3,z3;
      float px1,py1,pz1, px2,py2,pz2, px3,py3,pz3;;
      float r,g,b,a, r2,g2,b2;

      if(mode == MODE_GRADIENT) {
        /**
         *  smooth gradients
         */
        for(int i=0; i<TriangleVBOPoints.length; i+=3) {
          x1 = TriangleVBOPoints[i];
          y1 = TriangleVBOPoints[i+1];
          z1 = TriangleVBOPoints[i+2];
  
          int c1 = src.pixels[ ((int)y1*src.width) + ((int)x1) ];
          r = norm((c1 >> 16) & 0xff, 0,255);
          g = norm((c1 >> 8) & 0xff, 0,255);
          b = norm(c1 & 0xff, 0,255);
          a = 0.9; //norm(FTools.luminance(c1), 0,255);
  
          gl.glColor4f(r,g,b, a);
          gl.glVertex3f(x1, y1, z1);
        }
  
      } else if(mode == MODE_FLAT) {
        /**
         *  flat colors
         */
        for(int i=0; i<TriangleVBOPoints.length; i+=9) {
          x1 = TriangleVBOPoints[i];
          y1 = TriangleVBOPoints[i+1];
          z1 = TriangleVBOPoints[i+2];
  
          int c1 = src.pixels[ ((int)y1*src.width) + ((int)x1) ];
          r = norm((c1 >> 16) & 0xff, 0,255);
          g = norm((c1 >> 8) & 0xff, 0,255);
          b = norm(c1 & 0xff, 0,255);
          a = 0.9; //norm(FTools.luminance(c1), 0,255);
        
          gl.glColor4f(r,g,b, a);
          gl.glVertex3f(x1, y1, z1);
  
          x2 = TriangleVBOPoints[i+3];
          y2 = TriangleVBOPoints[i+4];
          z2 = TriangleVBOPoints[i+5];
          gl.glVertex3f(x2, y2, z2);
  
  
          x3 = TriangleVBOPoints[i+6];
          y3 = TriangleVBOPoints[i+7];
          z3 = TriangleVBOPoints[i+8];
          gl.glVertex3f(x3, y3, z3);
  
        }
  
      } 
  
      gl.glEnd();
    }
  }

  pgl.endGL();


  noStroke();
  fill(0,255,255);

  // edges.getEdgePVector() returns an arrayList of PVectors
  // http://download.oracle.com/javase/1.4.2/docs/api/java/util/ArrayList.html
  for(int i=0; i<edges.getEdgePVector().size(); i++) {
    PVector pt = (PVector) edges.getEdgePVector().get(i);
    
    // draw a cirlce on every edge detected
    ellipse(pt.x,pt.y, 5,5);
  }
}


// -----------------------------------------------------------------------------
void triangulate() {
  // start triangulation
  triangles = Triangulate.triangulate( edges.getEdgePVector() );
  
  ArrayList<Float> tpts = new ArrayList<Float>();
  for(int i=0; i<triangles.size(); i++) {
    Triangle t = (Triangle) triangles.get(i);
    tpts.add( t.p1.x );
    tpts.add( t.p1.y );
    tpts.add( t.p1.z );

    tpts.add( t.p2.x );
    tpts.add( t.p2.y );
    tpts.add( t.p2.z );

    tpts.add( t.p3.x );
    tpts.add( t.p3.y );
    tpts.add( t.p3.z );
  }

  // add the four corners of the images
//  tpts.add( 0.0f );
//  tpts.add( 0.0f );
//  tpts.add( 0.0f );
//
//  tpts.add( (float) src.width );
//  tpts.add( 0.0f );
//  tpts.add( 0.0f );
//
//  tpts.add( (float) src.width );
//  tpts.add( (float) src.height );
//  tpts.add( 0.0f );
//
//  tpts.add( 0.0f );
//  tpts.add( (float) src.height );
//  tpts.add( 0.0f );


  TriangleVBOPoints = new float[tpts.size()];
  for(int i=0; i<TriangleVBOPoints.length; i++) {
    TriangleVBOPoints[i] = (float) tpts.get(i);
  }

  TriangleVBOColors = new float[TriangleVBOPoints.length];
  for(int i=0; i<TriangleVBOColors.length; i+=3) {
    TriangleVBOColors[i] = (1.0/TriangleVBOPoints.length)*i;
    TriangleVBOColors[i+1] = 0;
    TriangleVBOColors[i+2] = (1.0/TriangleVBOPoints.length)*i; 
  }
}


// -----------------------------------------------------------------------------
// Events
// -----------------------------------------------------------------------------
void mouseMoved() {
  edges.process(src, thresh, true);
  edges.findEdges(density, b_low,b_high);

  triangulate();
}

// -----------------------------------------------------------------------------
void keyPressed() {
  if(key == 'f') {
    mode = MODE_FLAT;
  }
  if(key == 'g') {
    mode = MODE_GRADIENT;
  }
}

