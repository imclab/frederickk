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
 *  A selection of ArrayList Utility Methods modified for processing from
 *  org.apache.commons.lang (ArrayUtils.java)
 *  http://commons.apache.org/lang/
 *  
 *  http://github.com/frederickk/frederickk
 *
 */



//-----------------------------------------------------------------------------
// libraries
//-----------------------------------------------------------------------------
import java.util.ArrayList;
import processing.core.PVector;



public class FTools {
	//------------------------------------------------------------------------
	// properties
	//------------------------------------------------------------------------
	public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
	public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];



	//------------------------------------------------------------------------
	// methods
	//------------------------------------------------------------------------
	/**
	 * Reverse
	 * 
	 * Reverses the order of the given array. There is no special handling for
	 * multi-dimensional arrays.
	 * 
	 * @param array
	 *			the array to reverse, may be <code>null</code>
	 */
	public static void reverse(Object[] _array) {
		if (_array == null)
			return;

		int i = 0;
		int j = _array.length - 1;
		Object tmp;
		while (j > i) {
			tmp = _array[j];
			_array[j] = _array[i];
			_array[i] = tmp;
			j--;
			i++;
		}
	}

	/**
	 * Converts an array of object Integers to int[]
	 * 
	 * @param array
	 *			a <code>Integer</code> array, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 */
	public static int[] toPrimitive(Integer[] _array) {
		if (_array == null) {
			return null;
		}

		final int[] result = new int[_array.length];
		for (int i = 0; i < _array.length; i++) {
			result[i] = _array[i].intValue();
		}
		return result;
	}

	/**
	 * Converts an ArrayList to int[]
	 * 
	 * @param array
	 *			a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 */
	public static int[] toPrimitive(ArrayList<Object> _array) {
		if (_array == null) {
			return null;
		}

		final int[] result = new int[_array.size()];
		for (int i = 0; i < _array.size(); i++) {
			Integer temp = (Integer) _array.get(i);
			result[i] = temp.intValue();
		}
		return result;
	}



	/**
	 *	Float array converters
	 */
	public static float[] toPrimitive(int[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_ARRAY;
		}
		final float[] result = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = (float) array[i];
		}
		return result;
	}

	
	/**
	 * <p>Converts an array of object Floats to primitives.</p>
	 *
	 * <p>This method returns <code>null</code> for a <code>null</code> input array.</p>
	 *
	 * @param array  a <code>Float</code> array, may be <code>null</code>
	 * @return a <code>float</code> array, <code>null</code> if null array input
	 * @throws NullPointerException if array content is <code>null</code>
	 */
	public static float[] toPrimitive(Float[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_ARRAY;
		}
		final float[] result = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = array[i].floatValue();
		}
		return result;
	}

	/**
	 * <p>Converts an array of object Floats to primitives handling <code>null</code>.</p>
	 *
	 * <p>This method returns <code>null</code> for a <code>null</code> input array.</p>
	 *
	 * @param array  a <code>Float</code> array, may be <code>null</code>
	 * @param valueForNull  the value to insert if <code>null</code> found
	 * @return a <code>float</code> array, <code>null</code> if null array input
	 */
	public static float[] toPrimitive(Float[] array, float valueForNull) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_ARRAY;
		}
		final float[] result = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			Float b = array[i];
			result[i] = (b == null ? valueForNull : b.floatValue());
		}
		return result;
	}

	/**
	 * <p>Converts an array of primitive floats to objects.</p>
	 *
	 * <p>This method returns <code>null</code> for a <code>null</code> input array.</p>
	 *
	 * @param array  a <code>float</code> array
	 * @return a <code>Float</code> array, <code>null</code> if null array input
	 */
	public static Float[] toObject(float[] array) {
		if (array == null) {
			return null;
		} else if (array.length == 0) {
			return EMPTY_FLOAT_OBJECT_ARRAY;
		}
		final Float[] result = new Float[array.length];
		for (int i = 0; i < array.length; i++) {
			result[i] = new Float(array[i]);
		}
		return result;
	}
	
	
	/**
	 * Converts an ArrayList<Object> to float[][]
	 * 
	 * @param array
	 *			a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 *
	public static float[][] to2DArray(ArrayList _array) {
		float[][] floatArray = new float[_array.size()][3];

		for (int i = 0; i < _array.size(); i++) {
			float[] temp = (float[]) _array.get(i);
			floatArray[i] = temp;
		}

		return floatArray;
	}
	*/

	/**
	 * Converts an ArrayList<PVector> to float[][]
	 * 
	 * @param array
	 *			a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *			 if array content is <code>null</code>
	 */
	public static float[][] to2DArray(ArrayList<PVector> _array) {
		float[][] floatArray = new float[_array.size()][3];

		// Class type = _array.getClass().getComponentType();
		// println("type\t" + type);

		for (int i = 0; i < _array.size(); i++) {
			float[] temp = (float[]) _array.get(i).array();
			floatArray[i] = temp;
		}

		return floatArray;
	}

	
	//------------------------------------------------------------------------
	//additional tools
	//------------------------------------------------------------------------
	public static int snap(int value, int snapAmt) {
		return snapAmt * Math.round(value / snapAmt);
	}
	public static float snap(float value, float snapAmt) {
		return snapAmt * Math.round(value / snapAmt);
	}


	//------------------------------------------------------------------------
	/**
	 * method which return the index of
	 * the index of the largest value in the array
	 * 
	 * int[] list = { 5, 1, 2, -3 };
	 * int h = FTools.maxIndex(list); // sets h to "0"
	 *  
	 * @param array
	 * 			the array to find the max index of
	 * 
	 */
	public static int maxIndex(float[] array) {
		int max = 0;
		int len = array.length;
		for(int i=1; i<len; i++) if(array[i] > max) max = i;
		return max;
	}
	public static int maxIndex(int[] array) {
		int max = 0;
		int len = array.length;
		for(int i=1; i<len; i++) if(array[i] > max) max = i;
		return max;
	}

	
	/**
	 * method which return the index of
	 * the index of the smallest value in the array
	 * 
	 * int[] list = { 5, 1, 2, -3 };
	 * int h = FTools.minIndex(list); // sets h to "3"
	 *  
	 * @param array
	 * 			the array to find the max index of
	 * 
	 */
	public static int minIndex(float[] array) {
		int min = 0;
		int len = array.length;
		for (int i=1; i<len; i++) if(array[i] < min) min = i;
		return min;
	}
	
	public static int minIndex(int[] array) {
		int min = array[0];
		int len = array.length;
		//for (int i=1; i<len; i++) if(array[i] < min) min = array[i];
		for (int i=1; i<len; i++) if(array[i] < min) min = i;
		return min;
	}

	
	/**
	 * return boolean value as int
	 * 
	 */
	public static int boolToInt(boolean val) {
		return (val) ? 1:0;
	}

	
}
