package openmaple.crypto;

import io.netty.buffer.ByteBuf;

/**
 * An interface describing a cryptographic utility for performing encryption and decryption.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public interface CryptoKit {
	/**
	 * Encrypts an array of bytes in-place.
	 *
	 * @param data the array to be encrypted
	 */
	public void encrypt(byte[] data);

	/**
	 * Encrypts a {@code ByteBuf} in-place.
	 *
	 * @param data the buffer to be encrypted
	 */
	public void encrypt(ByteBuf data);

	/**
	 * Decrypts an array of bytes in-place.
	 *
	 * @param data the array to be encrypted
	 */
	public void decrypt(byte[] data);

	/**
	 * Decrypts a {@code ByteBuf} in-place.
	 *
	 * @param data the buffer to be decrypted
	 */
	public void decrypt(ByteBuf data);
}
