package frederickk.api.flickr;

import processing.core.*;
import processing.core.PApplet;

class FFlickrSource {
	private static PApplet p5;
	
	protected PImage img;
	protected boolean LOADED = false;
	protected boolean ERROR = false;
	protected String url;

	public FFlickrSource(PApplet papplet, String _url) {
		url = _url;
	}

	void load() {
		System.out.println("nLoading image: " + url);
		img = p5.loadImage(url);

		if(img == null) ERROR = true;
		LOADED = true;

		if(ERROR) System.out.println("Error occurred when loading image.");
		else System.out.println("Load successful. " + 
				"Image size is " + img.width + "x" + img.height);

	}
}