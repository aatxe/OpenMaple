package openmaple.crypto.legacy;

import io.netty.buffer.ByteBuf;
import openmaple.crypto.ByteTransformer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static openmaple.crypto.legacy.LegacyUtils.upscale;

/**
 * A AES transformer for the legacy protocol using {@code javax.crypto} internally.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public class AESTransformer extends LegacyBaseTransformer {
	/**
	 * The default AES key to use.
	 */
	private static final byte[] defaultKey = {
			0x13, 0x00, 0x00, 0x00, 0x08, 0x00, 0x00, 0x00, 0x06, 0x00, 0x00, 0x00, (byte) 0xB4, 0x00, 0x00, 0x00,
			0x1B, 0x00, 0x00, 0x00, 0x0F, 0x00, 0x00, 0x00, 0x33, 0x00, 0x00, 0x00, 0x52, 0x00, 0x00, 0x00,
	};

	private InitializationVector iv;
	private final short version;
	private final Cipher cipher;

	public AESTransformer(byte[] iv, short version) {
		this.iv = new InitializationVector(iv, 3);
		this.version = (short) (((version >> 8) & 0xFF) | ((version << 8) & 0xFF00));
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(defaultKey, "AES"));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw new LegacyCryptographyException("Failed to find AES cipher.", e);
		} catch (InvalidKeyException e) {
			throw new LegacyCryptographyException("Failed to initialize AES cipher with proper keyspec.", e);
		}
	}

	@Override
	public void transform(byte[] data) {
		int blockSize = 0x5B0;
		int start = 0;
		for (int i = data.length; i > 0 || i < blockSize; i -= blockSize) {
			byte[] iv = upscale(this.iv.get(), 4);
			if (i < 0) {
				blockSize += i;
			} else if (i != data.length) {
				blockSize = 0x5B4;
			}
			for (int j = start; j < (start + blockSize); j++) {
				if ((j - start) % iv.length == 0) {
					try {
						byte[] cIv = cipher.doFinal(iv);
						for (int k = 0; k < iv.length; k++) {
							iv[k] = cIv[k];
						}
					} catch (BadPaddingException | IllegalBlockSizeException e) {
						throw new LegacyCryptographyException("Failed to perform cipher action.", e);
					}
				}
				data[j] ^= iv[(j - start) % iv.length];
			}
			start += blockSize;
		}
		iv.roll();
	}
}
