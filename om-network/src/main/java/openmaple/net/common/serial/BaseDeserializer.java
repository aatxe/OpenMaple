package openmaple.net.common.serial;

import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author Aaron
 * @version 1.0
 * @since 2/10/14
 */
public class BaseDeserializer {
	private static final ThreadLocal<CharsetDecoder> utfDecoder = new ThreadLocal<CharsetDecoder>() {
		@Override
		protected CharsetDecoder initialValue() {
			return Charset.forName("UTF-8").newDecoder();
		}
	};

	public Object deserialize(ByteBuf data, Class<?> type) {
		if (type.equals(int.class)) {
			return data.readInt();
		} else if (type.equals(short.class)) {
			return data.readShort();
		} else if (type.equals(long.class)) {
			return data.readLong();
		} else if (type.equals(byte.class)) {
			return data.readByte();
		} else if (type.equals(double.class)) {
			return data.readDouble();
		} else if (type.equals(float.class)) {
			return data.readFloat();
		} else if (type.equals(String.class)) {
			try {
				return utfDecoder.get().decode(data.readBytes(data.readUnsignedShort()).order(ByteOrder.LITTLE_ENDIAN).nioBuffer()).toString();
			} catch (CharacterCodingException e) {
				throw new Error("Failed to load UTF String in buffer.", e);
			}
		} else {
			throw new Error("OpenMaple found an unknown base type in the de-serialization process.");
		}
	}
}
