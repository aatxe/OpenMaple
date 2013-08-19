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
}
