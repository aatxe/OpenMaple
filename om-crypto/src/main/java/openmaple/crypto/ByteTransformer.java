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
}
