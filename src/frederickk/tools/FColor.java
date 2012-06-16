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
// libaries
//-----------------------------------------------------------------------------
import java.awt.Color;



public class FColor {
	//-----------------------------------------------------------------------------
	// methods
	//-----------------------------------------------------------------------------
	/**
	 * return the RGBA break down of an int/color (0.0 - 1.0) as float[4]   

	 * @param _col
	 * 			input color as int 
	 * 
	 * @return rgba
	 */
	public static float[] getColorF(int _col) {
		int r = (_col >> 16) & 0xFF;
		int g = (_col >> 8) & 0xFF;
		int b = _col & 0xFF;
		int a = (_col >> 24) & 0xFF;

		float[] rgba = { 
				r/255,g/255, b/255, a/255
		};
		return rgba;
	}


	/**
	 * return the RGBA break down of an int/color (0 - 255) as int[4]  
	 * 
	 * @param _col
	 * 			input color as int 
	 * 
	 * @return rgba
	 */
	public static int[] getColor(int _col) {
		int r = (_col >> 16) & 0xFF;
		int g = (_col >> 8) & 0xFF;
		int b = _col & 0xFF;
		int a = (_col >> 24) & 0xFF;

		int[] rgba = { r,g,b,a }; 
		return rgba;
	}

	
	/**
	 * return desaturated color as int  
	 * 
	 * @param _col
	 * 			input color as int 
	 * @param pct
	 * 			percentage of desaturation as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int desaturate(int _col, float pct) {
		int r = (_col >> 16) & 0xFF;
		int g = (_col >> 8) & 0xFF;
		int b = _col & 0xFF;

		float[] hsb = Color.RGBtoHSB(r, g, b, null); 
		
		int rgba = Color.HSBtoRGB(hsb[0], pct, hsb[1]);
		return rgba;
	}

	/**
	 * return desaturated color as int  
	 * 
	 * @param _col
	 * 			input color as int 
	 * @param sat
	 * 			percentage of desaturation as a floating-point value (0.0 - 1.0) 
	 * @param bright
	 * 			percentage of brightness as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int desaturate(int _col, float sat, float bright) {
		int r = (_col >> 16) & 0xFF;
		int g = (_col >> 8) & 0xFF;
		int b = _col & 0xFF;

		float[] hsb = Color.RGBtoHSB(r, g, b, null); 
		
		int rgba = Color.HSBtoRGB(hsb[0], sat, bright);
		return rgba;
	}

	
	/**
	 * return darkened color as int  
	 * 
	 * @param _col
	 * 			input color as int 
	 * @param pct
	 * 			percentage of darkening as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int darken(int _col, float pct) {
		float[] rgbaf = getColorF(_col);

		rgbaf[0] -= pct;
		if(rgbaf[0] < 0.0) rgbaf[0] = 0.0f;
		rgbaf[1] -= pct;
		if(rgbaf[1] < 0.0) rgbaf[1] = 0.0f;
		rgbaf[2] -= pct;
		if(rgbaf[2] < 0.0) rgbaf[2] = 0.0f;
		
		Color c = new Color( Math.abs(rgbaf[0]), Math.abs(rgbaf[1]), Math.abs(rgbaf[2]), rgbaf[3]);
		int rgba = c.getRGB();
		return rgba;
	}
	

	/**
	 * return lightened color as int  
	 * 
	 * @param _col
	 * 			input color as int 
	 * @param pct
	 * 			percentage lightening as a floating-point value (0.0 - 1.0) 
	 * 
	 * @return rgba
	 */
	public static int lighten(int _col, float pct) {
		float[] rgbaf = getColorF(_col);

		rgbaf[0] += pct;
		if(rgbaf[0] > 1.0) rgbaf[0] = 1.0f;
		rgbaf[1] += pct;
		if(rgbaf[1] > 1.0) rgbaf[1] = 1.0f;
		rgbaf[2] += pct;
		if(rgbaf[2] > 1.0) rgbaf[2] = 1.0f;
		
		Color c = new Color( Math.abs(rgbaf[0]), Math.abs(rgbaf[1]), Math.abs(rgbaf[2]), rgbaf[3]);
		int rgba = c.getRGB();
		return rgba;
	}

	
	/**
	 * bitwise luminance
	 * http://processing.org/discourse/yabb2/YaBB.pl?num=1164286894
	 * 
	 * @param col
	 * 			color value 
	 * 
	 */
	public static int luminance(int _col){
		return ( ((_col>>16)&0xff)*9 + ((_col>>8)&0xff)*19 + ((_col&0xff)<<2) ) >> 5;
	}

	/*
	public static int brightness(int col){
		int a = (col >> 16) & 0xFF;
		int b = (col >> 8) & 0xFF;
		int c = col & 0xFF;

		//max function taken from processing 
		return (a > b) ? ((a > c) ? a : c) : ((b > c) ? b : c);
	}
	*/
	
	
	/**
	 * return random RGB color as int  
	 * 
	 * @return rgb
	 */
	public static int randomRGBColor() {
		Color c = new Color( (int) Math.random()*255,
							 (int) Math.random()*255,
							 (int) Math.random()*255,
							 255
							);
		int rgb = c.getRGB();
		return rgb;
	}


	/**
	 * return random RGBA color as int  
	 * 
	 * @return rgba
	 */
	public static int randomRGBAColor() {
		Color c = new Color( (int) Math.random()*255,
							 (int) Math.random()*255,
							 (int) Math.random()*255,
							 (int) Math.random()*255
							);
		int rgba = c.getRGB();
		return rgba;
	}


	/**
	 * return random RGBA color as int  
	 * 
	 * @return rgba
	 */
	public static int randomGrayColor() {
		int g = (int) Math.random()*255;
		Color c = new Color(g,g,g, 255);
		int rgba = c.getRGB();
		return rgba;
	}

}
