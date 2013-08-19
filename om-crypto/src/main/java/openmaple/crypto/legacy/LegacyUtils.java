package openmaple.crypto.legacy;

/**
 * A set of universal byte utilities for the legacy protocol.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public class LegacyUtils {
	public static byte rollLeft(byte b, int c) {
		int tmp = (b & 0xFF) << (c % 8);
		return (byte) ((tmp & 0xFF) | (tmp >> 8));
	}

	public static byte rollRight(byte b, int c) {
		int tmp = ((b & 0xFF) << 8) >>> (c % 8);
		return (byte) ((tmp & 0xFF) | (tmp >> 8));
	}
}
