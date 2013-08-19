package openmaple.crypto.legacy;

/**
 * A set of universal byte utilities for the legacy protocol.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public class LegacyUtils {
	/**
	 * Rolls a byte to the left by the specific amount.
	 *
	 * @param b the byte to be rolled
	 * @param c the amount to roll by
	 * @return the rolled byte
	 */
	public static byte rollLeft(byte b, int c) {
		int tmp = (b & 0xFF) << (c % 8);
		return (byte) ((tmp & 0xFF) | (tmp >> 8));
	}

	/**
	 * Rolls a byte to the right by the specific amount.
	 *
	 * @param b the byte to be rolled
	 * @param c the amount to roll by
	 * @return the rolled byte
	 */
	public static byte rollRight(byte b, int c) {
		int tmp = ((b & 0xFF) << 8) >>> (c % 8);
		return (byte) ((tmp & 0xFF) | (tmp >> 8));
	}

	/**
	 * Scales an array of bytes up by the desired {@code scale} factor.
	 *
	 * @param bs    the array of bytes to scale
	 * @param scale the scale factor
	 * @return the scaled byte array
	 */
	public static byte[] upscale(byte[] bs, int scale) {
		byte[] ret = new byte[bs.length * scale];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = bs[i % bs.length];
		}
		return ret;
	}
}
