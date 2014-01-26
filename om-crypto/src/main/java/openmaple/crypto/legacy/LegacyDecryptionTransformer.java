package openmaple.crypto.legacy;

import io.netty.buffer.ByteBuf;
import openmaple.crypto.ByteTransformer;

import static openmaple.crypto.legacy.LegacyUtils.*;

/**
 * A unidirectional legacy transformer for the decryption end of the arbitrary MapleStory protocol.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public class LegacyDecryptionTransformer extends LegacyBaseTransformer {
	@Override
	public void transform(byte[] data) {
		byte cursor, prior;
		for (int i = 0; i < 3; i++) {
			prior = 0;
			for (int j = data.length; j > 0; j--) {
				cursor = data[j - 1];
				cursor = (byte) (rollLeft(cursor, 3) ^ 0x13);
				byte tmp = cursor;
				cursor = (byte) ((cursor ^ prior) - j);
				cursor = rollRight(cursor, 4);
				prior = tmp;
				data[j - 1] = cursor;
			}
			prior = 0;
			for (int j = data.length; j > 0; j--) {
				cursor = data[data.length - j];
				cursor = (byte) (~(cursor - 0x48));
				cursor = rollLeft(cursor, j);
				byte tmp = cursor;
				cursor = (byte) ((cursor ^ prior) - j);
				cursor = rollRight(cursor, 3);
				prior = tmp;
				data[data.length - j] = cursor;
			}
		}
	}
}
