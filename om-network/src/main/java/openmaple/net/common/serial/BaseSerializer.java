package openmaple.net.common.serial;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import openmaple.net.common.annotate.Unsigned;

import java.lang.annotation.Annotation;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * @author Aaron
 * @version 1.0
 * @since 2/10/14
 */
public class BaseSerializer {
	private static final ThreadLocal<CharsetEncoder> utfEncoder = new ThreadLocal<CharsetEncoder>() {
		@Override
		protected CharsetEncoder initialValue() {
			return Charset.forName("UTF-8").newEncoder();
		}
	};

	public static ByteBuf serialize(Object o, Class<?> type, Annotation[] annotations) {
		boolean unsigned = false;
		for (Annotation annotation : annotations)
			if (annotation.getClass().equals(Unsigned.class))
				unsigned = true;
		if (type.equals(int.class)) {
			return (unsigned) ? Unpooled.buffer(2).writeShort((short) o) : Unpooled.buffer(4).writeInt((int) o);
		} else if (type.equals(short.class)) {
			return (unsigned) ? Unpooled.buffer(1).writeByte((byte) o) : Unpooled.buffer(2).writeShort((short) o);
		} else if (type.equals(long.class)) {
			return (unsigned) ? Unpooled.buffer(4).writeInt((int) o) : Unpooled.buffer(8).writeLong((long) o);
		} else if (type.equals(byte.class)) {
			return Unpooled.buffer(1).writeByte((byte) o);
		} else if (type.equals(double.class)) {
			return Unpooled.buffer(8).writeDouble((double) o);
		} else if (type.equals(float.class)) {
			return Unpooled.buffer(4).writeFloat((float) o);
		} else if (type.equals(String.class)) {
			// TODO: serialize string;
			String s = (String) o;
			try {
				return Unpooled.wrappedBuffer(utfEncoder.get().encode(CharBuffer.wrap(s.toCharArray()))).order(ByteOrder.LITTLE_ENDIAN);
			} catch (CharacterCodingException e) {
				throw new Error("Failed to encode string (" + s + ") into buffer.", e);
			}
		} else {
			throw new Error("OpenMaple found an unknown base type (" + type.getSimpleName() + ") in the serialization process.");
		}
	}
}
