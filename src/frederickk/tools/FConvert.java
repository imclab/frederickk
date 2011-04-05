package frederickk.tools;

/*
 *  Frederickk.Tools 003
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
 *  http://code.google.com/p/frederickk/
 *
 */


import java.util.ArrayList;
import processing.core.PVector;

public class FConvert {

	/**
	 * Reverse
	 * 
	 * Reverses the order of the given array. There is no special handling for
	 * multi-dimensional arrays.
	 * 
	 * @param array
	 *            the array to reverse, may be <code>null</code>
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
	 *            a <code>Integer</code> array, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *             if array content is <code>null</code>
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
	 *            a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *             if array content is <code>null</code>
	 *
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
	*/

	/**
	 * Converts an ArrayList<Object> to float[][]
	 * 
	 * @param array
	 *            a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *             if array content is <code>null</code>
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
	 *            a <code>Integer</code> ArrayList, may be <code>null</code>
	 * @return an <code>int</code> array, <code>null</code> if null array input
	 * @throws NullPointerException
	 *             if array content is <code>null</code>
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

}
