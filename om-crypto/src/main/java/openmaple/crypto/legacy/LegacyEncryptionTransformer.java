package openmaple.crypto.legacy;

import io.netty.buffer.ByteBuf;
import openmaple.crypto.ByteTransformer;

import static openmaple.crypto.legacy.LegacyUtils.*;

/**
 * A unidirectional legacy transformer for the encryption end of the arbitrary MapleStory protocol.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public class LegacyEncryptionTransformer implements ByteTransformer {
	@Override
	public void transform(byte[] data) {
		byte cursor, prior;
		for (int i = 0; i < 3; i++) {
			prior = 0;
			for (int j = data.length; j > 0; j--) {
				cursor = data[data.length - j];
				cursor = rollLeft(cursor, 3);
				cursor = (byte) ((cursor + j) ^ prior);
				prior = cursor;
				cursor = rollRight(cursor, j);
				cursor = (byte) (~cursor + 0x48);
				data[data.length - j] = cursor;
			}
			prior = 0;
			for (int j = data.length; j > 0; j--) {
				cursor = data[j - 1];
				cursor = rollLeft(cursor, 4);
				cursor = (byte) ((cursor + j) ^ prior);
				prior = cursor;
				cursor ^= 0x13;
				cursor = rollRight(cursor, j);
				data[j - 1] = cursor;
			}
		}
	}

	@Override
	public void transform(ByteBuf data) {
		data.markReaderIndex();
		data.markWriterIndex();
		data.clear();
		byte[] trans = new byte[data.readableBytes()];
		data.readBytes(trans);
		transform(trans);
		data.setBytes(0, trans);
		data.resetReaderIndex();
		data.resetWriterIndex();
	}
}
