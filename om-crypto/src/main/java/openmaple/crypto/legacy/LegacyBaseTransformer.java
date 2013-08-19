package openmaple.crypto.legacy;

import io.netty.buffer.ByteBuf;
import openmaple.crypto.ByteTransformer;

/**
 * An abstract base for all legacy transformers that handles {@code ByteBuf}s automatically.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 8/19/13
 */
public abstract class LegacyBaseTransformer implements ByteTransformer {
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
