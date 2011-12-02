package frederickk.tools;

/*
 *  Frederickk.Tools 0.0.4
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a collection of tools that i tend to use frequently
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;



public class FImageLoader {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	private File ordner;
	private PImage[] bild;

	private String[] daten;
	//private String ordnerName;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FImageLoader
	 * 
	 * @param _p5
	 * 			PApplet
	 * @param ordnerName
	 *		  the path of the images to load
	 */
	public FImageLoader(
			PApplet _p5,
			String ordnerName) {

		p5 = _p5;
		setFolder(p5.sketchPath + ordnerName);
	}



	//-----------------------------------------------------------------------------
	// sets
	//-----------------------------------------------------------------------------
	/**
	 * @param ordnerName
	 *		  the path to load
	 */
	public void setFolder(
			String ordnerName) {

		//setFolderName(p5.sketchPath + ordnerName);
		setFolderName(ordnerName);

		//if(ordner.isFile()) {
		boolean ds;
		String dsTest = ordner.list()[0];

		if(dsTest.equals(".DS_Store") == true) {
			daten = new String[ordner.list().length-1];
			ds = true;
		} else {
			daten = new String[ordner.list().length];
			ds = false;
		}

		if(daten != null || daten.length != 0) {
			bild = new PImage[daten.length];

			if(ds) {
				for(int i=0; i != ordner.list().length-1; i++) {
					daten[i] = ordner.list()[i+1];
					bild[i] = p5.loadImage(ordnerName + "/" + daten[i]);
					System.out.println("loading image " + daten[i]);
				}

			} else if(!ds) {
				for(int i=0; i != ordner.list().length; i++) {
					daten[i] = ordner.list()[i];
					bild[i] = p5.loadImage(ordnerName + "/" + daten[i]);
					System.out.println("loading image " + daten[i]);
				}
			}

		}

		/*
		} else {
			System.out.println(ordner + " is not a file");
		}
		 */
	}

	/**
	 * @param ordnerName
	 *		  set the folder to read
	 */
	private void setFolderName(String ordnerName) {
		ordner = new File(ordnerName);
	}

	/**
	 * @param dateiName
	 *		  set the file to read
	 */
	public void setFile(String dateiName) {
		bild = new PImage[1];
		daten = new String[1];

		for(int i=0; i !=bild.length; i++) {
			bild[i] = p5.loadImage(dateiName);
			daten[i] = dateiName;

			//String[] n_daten = p5.split(daten[i], '/');
			String[] n_daten = daten[i].split("/");
			daten[i] = n_daten[n_daten.length-1];

			System.out.println("loading image " + daten[i]);
		}
	}



	//-----------------------------------------------------------------------------
	// gets
	//-----------------------------------------------------------------------------
	/**
	 * return number of files
	 * 
	 * @return d
	 */
	public int getFileNum() {
		int d = daten.length;
		return d;

	}

	/**
	 * return number of images 
	 * 
	 * @return b
	 */
	public int getImageNum() {
		int b = bild.length;
		return b;
	}

	/**
	 * @param w
	 *		  index of image to return
	 *		  
	 * return a specific image
	 * 
	 * @return bild[w]
	 */
	public PImage getImage(int w) {
		w = inBounds(w);
		return bild[w];
	}

	/**
	 * @param w
	 *		  number of file to use
	 *		  
	 * return a specific file 
	 * 
	 * @return daten[w]
	 */
	public String getFile(int w) {
		w = inBounds(w);
		return daten[w];
	}

	/**
	 * @param w
	 *		  value to keep within the range of colors created
	 */
	private int inBounds(int w) {
		w = PApplet.constrain(w, 0,getImageNum());
		return w;
	}

}


