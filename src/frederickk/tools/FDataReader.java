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
import java.io.File;



public class FDataReader {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	public static PApplet p5;

	private File ordner;
	private String[] daten;
	//private String ordnerName;



	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	/**
	 * instantiate FDataReader
	 * 
	 * @param _p5
	 * 			PApplet
	 * @param ordnerName
	 *		  the path of the files to load
	 */
	public FDataReader(
			PApplet _p5,
			String ordnerName) {

		p5 = _p5;
		setFolder(ordnerName);
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * @param ordnerName
	 *		  the path of the files to load
	 */
	public void setFolder(
			String ordnerName) {

		setFolderName(p5.sketchPath + ordnerName);

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

			if(ds) {
				for(int i=0; i != ordner.list().length-1; i++) {
					daten[i] = ordner.list()[i+1];
					System.out.println("file " + daten[i]);
				}

			} else if(!ds) {
				for(int i=0; i != ordner.list().length; i++) {
					daten[i] = ordner.list()[i];
					System.out.println("file " + daten[i]);
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
	 *		  set the file to read
	 */
	private void setFolderName(String ordnerName) {
		ordner = new File(ordnerName);
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
		w = PApplet.constrain(w, 0,getFileNum());
		return w;
	}

}


