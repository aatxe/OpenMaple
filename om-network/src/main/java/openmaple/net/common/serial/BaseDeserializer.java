package openmaple.net.common.serial;

import io.netty.buffer.ByteBuf;
import openmaple.net.common.annotate.Unsigned;

import java.lang.annotation.Annotation;
import java.nio.ByteOrder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/10/14
 */
public class BaseDeserializer {
	private static final ThreadLocal<CharsetDecoder> utfDecoder = new ThreadLocal<CharsetDecoder>() {
		@Override
		protected CharsetDecoder initialValue() {
			return Charset.forName("UTF-8").newDecoder();
		}
	};

	public static Object deserialize(ByteBuf data, Class<?> type, Annotation[] annotations) {
		boolean unsigned = false;
		for (Annotation annotation : annotations)
			if (annotation.getClass().equals(Unsigned.class))
				unsigned = true;
		if (type.equals(int.class)) {
			return (unsigned) ? data.readUnsignedShort() : data.readInt();
		} else if (type.equals(short.class)) {
			return (unsigned) ? data.readUnsignedByte() : data.readShort();
		} else if (type.equals(long.class)) {
			return (unsigned) ? data.readUnsignedInt() : data.readLong();
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
			throw new Error("OpenMaple found an unknown base type (" + type.getSimpleName() + ") in the de-serialization process.");
		}
	}
}
