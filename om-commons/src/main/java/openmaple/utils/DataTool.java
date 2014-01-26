package openmaple.utils;

/**
 * A set of tools for working with and transforming data.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 1/26/14
 */
public class DataTool {
	/**
	 * Converts an array of bytes into a human-readable 64-bit {@code String}.
	 *
	 * @param array the data to convert
	 * @return the converted data as a 64-bit string
	 */
	public static String toHexString(byte[] array) {
		String ret = "";
		for (byte b : array) {
			String s = Integer.toHexString(b);
			ret += ((s.length() == 1) ? "0" : "") + s + " ";
		}
		return ret;
	}
}
