package openmaple.crypto.legacy;

import io.netty.buffer.ByteBuf;
import openmaple.crypto.ByteTransformer;
import openmaple.crypto.CryptoKit;

/**
 * The complete {@code CryptoKit} for the legacy MapleStory protocol.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public class LegacyCryptoKit implements CryptoKit {
	private final static ByteTransformer ENCRYPT = new LegacyEncryptionTransformer();
	private final static ByteTransformer DECRYPT = new LegacyDecryptionTransformer();
	private final AESTransformer aes;
	private final short version;

	/**
	 * Constructs a {@code CryptoKit} for the legacy protocol.
	 *
	 * @param iv      the default value for the initialization vector
	 * @param version the target version of MapleStory
	 */
	public LegacyCryptoKit(byte[] iv, short version) {
		aes = new AESTransformer(iv, version);
		this.version = version;
	}


	@Override
	public void encrypt(byte[] data) {
		ENCRYPT.transform(data);
		aes.transform(data);
	}

	@Override
	public void decrypt(byte[] data) {
		aes.transform(data);
		DECRYPT.transform(data);
	}

	@Override
	public void encrypt(ByteBuf data) {
		data.markReaderIndex();
		data.markWriterIndex();
		data.clear();
		byte[] enc = new byte[data.readableBytes()];
		data.readBytes(enc);
		encrypt(enc);
		data.setBytes(0, enc);
		data.resetReaderIndex();
		data.resetWriterIndex();
	}

	@Override
	public void decrypt(ByteBuf data) {
		data.markReaderIndex();
		data.markWriterIndex();
		data.clear();
		byte[] dec = new byte[data.readableBytes()];
		data.readBytes(dec);
		decrypt(dec);
		data.setBytes(0, dec);
		data.resetReaderIndex();
		data.resetWriterIndex();
	}

	@Override
	public boolean validatePacket(byte[] data) {
		byte[] iv = aes.getIv().get();
		return (
				(((data[0] ^ iv[2]) & 0xFF) == ((version >> 8) & 0xFF))
					&&
				(((data[1] ^ iv[3]) & 0xFF) == (version & 0xFF))
			   );
	}

	/**
	 * Validates the header information from a packet
	 *
	 * @param header the header to check
	 * @return whether or not the header is from a valid packet
	 */
	public boolean validatePacket(int header) {
		byte[] ret = new byte[2];
		ret[0] = (byte) ((header >> 24) & 0xFF);
		ret[1] = (byte) ((header >> 16) & 0xFF);
		return validatePacket(ret);
	}

	/**
	 * Reads the packet length from the header.
	 *
	 * @param header the header to read from
	 * @return the whole packet length
	 */
	public int getPacketLength(int header) {
		int ret = ((header >>> 16) ^ (header & 0xFFFF));
		ret = ((ret << 8) & 0xFF00) | ((ret >>> 8) & 0xFF);
		return ret;
	}

	/**
	 * Determines the header of a packet of the desired length.
	 *
	 * @param length the length of the packet
	 * @return the header of the packet
	 */
	public byte[] getPacketHeader(int length) {
		byte[] iv = aes.getIv().get();
		int i = ((iv[3] & 0xFF) | ((iv[2] << 8) & 0xFF00)) ^ version;
		length = ((length << 8) & 0xFF00) | (length >>> 8);
		int k = i ^ length;
		byte[] ret = new byte[4];
		ret[0] = (byte) ((i >>> 8) & 0xFF);
		ret[1] = (byte) (i & 0xFF);
		ret[2] = (byte) ((k >>> 8) & 0xFF);
		ret[3] = (byte) (k & 0xFF);
		return ret;
	}
}
