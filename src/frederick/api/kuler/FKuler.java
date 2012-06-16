package frederick.api.kuler;

/*
 *  Frederickk.api.kuler 001
 *
 *  Ken Frederick
 *  ken.frederick@gmx.de
 *
 *  http://cargocollective.com/kenfrederick/
 *  http://kenfrederick.blogspot.com/
 *
 *  a library for easier use of the kuler api
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import processing.core.*;
import processing.xml.*;
import java.util.ArrayList;



public class FKuler {
	//-----------------------------------------------------------------------------
	// properties
	//-----------------------------------------------------------------------------
	private PApplet p;

	private String serverPage = "http://kuler-api.adobe.com/rss/";
	private String typ;
	private String pageTyp = ".cfm";

	private int maxItems = 1;
	private int startIndex = 0;

	private String query;
	private String key = "5F5D21FE5CA6CBE00A40BD4457BAF3BA";

	private String[] FKulerThemes;

	private XMLElement[] themeItems;
	private XMLElement themeItem;

	private XMLElement[] themeSwatches;
	private XMLElement[] themeTag;

	private boolean bVerbose = false;


	//-----------------------------------------------------------------------------
	// constructor
	//-----------------------------------------------------------------------------
	public FKuler(PApplet parent) {
		p = parent;
	}



	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	void init(String query, String typ) { 
		String url = serverPage + typ + pageTyp + "?itemsPerPage=" + maxItems + "&startIndex=" + startIndex + query + "&key=" + key;
		String urlPrint = typ + pageTyp + "?itemsPerPage=" + maxItems + "&startIndex=" + startIndex + query;

		ArrayList themes = new ArrayList();
		XMLElement xml;

		try {
			xml = new XMLElement( p, url.toString() );

			if(bVerbose) System.out.println("\n" + urlPrint.toString());
			if(bVerbose) System.out.println("-----------------------------------------------------------------------------");

			if (xml.getChild("success") != null && xml.getChild("success").getContent().equals("false")) {
				if(bVerbose) System.out.println("The following error appears while calling kuler service:");
				if(bVerbose) System.out.println(xml.getChild("error/errorText").getContent());
				if(bVerbose) System.out.println("-----------------------------------------------------------------------------");

			} else {
				themeItems = xml.getChildren("channel/item/kuler:themeItem");
				if(bVerbose) System.out.println(themeItems.length + " theme results were returned");

				/*
	        for(int i=0; i<themeItems.length; i++) {
	          themeSwatches = themeItems[i].getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");

	          int[] swatches = new int[themeSwatches.length];
	          for (int j=0; j < themeSwatches.length; j++) {
	            swatches[j] = unhex("FF" + themeSwatches[j].getContent());
	            if(bVerbose) System.out.println("swatch color " + j + " " + swatches[j]);
	          }

	        }
	        if(bVerbose) System.out.println("-----------------------------------------------------------------------------");
				 */
			}

		} catch(Exception e) {
			if(bVerbose) System.out.println("FKuler XML Error " + e);
			if(bVerbose) System.out.println("-----------------------------------------------------------------------------");
		}

	}



	//-----------------------------------------------------------------------------
	//sets
	//-----------------------------------------------------------------------------
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	//-----------------------------------------------------------------------------
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	//-----------------------------------------------------------------------------
	public void setKey(String key) {
		this.key = key;
	}

	//-----------------------------------------------------------------------------
	public void setTheme(int num) {
		themeItem = themeItems[num];
		if(num > themeItems.length) {
			if(bVerbose) System.out.println("there are only " + themeItems.length);
			if(bVerbose) System.out.println("-----------------------------------------------------------------------------");
		} else {
			themeSwatches = themeItem.getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");
			//themeTag = themeItem.getChildren("kuler:themeTags"); //)[0].getContent());
			themeTag = themeItem.getChildren("kuler:themeTags");//[0].getContent();
		}
	}

	//-----------------------------------------------------------------------------
	public void setVerbose(boolean val) {
		bVerbose = val;
	}



	//-----------------------------------------------------------------------------
	//gets
	//-----------------------------------------------------------------------------
	public void getSearchTag(String suche) {
		query = "&searchQuery=tag:" + suche;
		typ = "search";

		init(query, typ);
	}

	//-----------------------------------------------------------------------------
	public void getRecent() {
		query = "&listType=recent";
		typ = "get";

		init(query, typ);
	}

	public void getRecent(int num) {
		setMaxItems(num);
		getRecent();
	}

	//-----------------------------------------------------------------------------
	public int getThemeCount() {
		try {
			return themeItems.length;
		} catch (Exception e) {
			if(bVerbose) System.out.println("getThemeCount() error " + e);
			return 0;
		}
	}

	//-----------------------------------------------------------------------------
	public int[] getSwatches() {
		themeSwatches = themeItems[0].getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");

		int[] swatches = new int[themeSwatches.length];
		for (int j=0; j < themeSwatches.length; j++) {
			swatches[j] = p.unhex("FF" + themeSwatches[j].getContent());
		}

		return swatches;
	}


	public int[] getSwatches(int t) {
		try {
			themeSwatches = themeItems[t].getChildren("kuler:themeSwatches/kuler:swatch/kuler:swatchHexColor");

			int[] swatches = new int[themeSwatches.length];
			for (int j=0; j < themeSwatches.length; j++) {
				swatches[j] = p.unhex("FF" + themeSwatches[j].getContent());
			}
			return swatches;

		} catch(Exception e) {
			//if(bVerbose) System.out.println("getSwatches() error " + e);
			int[] swatches = {};
			return swatches;
		}

	}

	//-----------------------------------------------------------------------------
	public String[] getThemeTag() {
		String[] themeTags = new String[themeTag.length];
		try {
			String holder = themeTag[0].getContent().toString();
			String[] tagsList = p.split(holder, ", ");

			return tagsList;
		} catch(Exception e) {
			//if(bVerbose) System.out.println("error reading tags: " + e);
			themeTags[0] = "";
		}
		return themeTags;
	}



}





