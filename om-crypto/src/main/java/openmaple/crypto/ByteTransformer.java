package openmaple.crypto;

import io.netty.buffer.ByteBuf;

/**
 * An interface describing a byte transformer for cryptography.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/28/13
 */
public interface ByteTransformer {
	/**
	 * Performs an in-place transformation on an array of bytes.
	 *
	 * @param data the array to be transformed
	 */
	public void transform(byte[] data);

	/**
	 * Performs an in-place transformation on a {@code ByteBuf}.
	 *
	 * @param data the buffer to be transformed
	 */
	public void transform(ByteBuf data);

	/**
	 * Performs an in-place transformation on a segment of an array of bytes.
	 *
	 * @param data       the array to be transformed
	 * @param startIndex the starting index of the segment to be transformed
	 * @param endIndex   the ending index of the segment to be transformed
	 */
	public void transformSegment(byte[] data, int startIndex, int endIndex);


	/**
	 * Performs an in-place transformation on a segment of a {@code ByteBuf}.
	 *
	 * @param data       the buffer to be transformed
	 * @param startIndex the starting index of the segment to be transformed
	 * @param endIndex   the ending index of the segment to be transformed
	 */
	public void transformSegment(ByteBuf data, int startIndex, int endIndex);
}
