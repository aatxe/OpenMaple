package openmaple.net.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import openmaple.net.common.annotate.Opcode;
import openmaple.net.common.annotate.Serial;
import openmaple.net.common.serial.BaseSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A reflections-based {@code MessageToByteEncoder} that automatically serializes packets based on the {@code Opcode}
 * constructor annotation and the {@code Serial} field annotation.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/10/14
 */
public class PacketSerializer extends MessageToByteEncoder {
	private final static Logger logger = LoggerFactory.getLogger(PacketSerializer.class);
	private Map<Class<?>, Constructor<?>> classConstructorMap = new HashMap<>();

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		int opcode = classToOpcodeValue(msg.getClass());
		SerializeData[] data = fieldValues(msg);
		out.writeShort((short) opcode);
		for (SerializeData d : data) {
			out.writeBytes(BaseSerializer.serialize(d.get(), d.type(), d.annotations()));
		}
	}

	private Constructor<?> classToConstructor(Class<?> c) {
		if (classConstructorMap.containsKey(c))
			return classConstructorMap.get(c);
		for (Constructor<?> constructor : c.getConstructors()) {
			if (constructor.isAnnotationPresent(Opcode.class)) {
				classConstructorMap.put(c, constructor);
				return constructor;
			}
		}
		throw new Error("OpenMaple was unable to find a constructor for the class " + c.getSimpleName());
	}

	private int classToOpcodeValue(Class<?> c) {
		return classToConstructor(c).getAnnotation(Opcode.class).value();
	}

	private SerializeData[] fieldValues(Object o) {
		Field[] fields = o.getClass().getFields();
		SerializeData[] serializeData = new SerializeData[fields.length];
		int index = 0;
		for (Field field : fields)
			if (field.isAnnotationPresent(Serial.class))
				serializeData[index++] = new SerializeData(o, field);
		Arrays.sort(serializeData);
		return serializeData;
	}

	private static class SerializeData implements Comparable<SerializeData> {
		private final Object msg;
		private final Field field;
		private final int position;

		public SerializeData(Object msg, Field field) {
			this(msg, field, field.getAnnotation(Serial.class).value());
		}

		public SerializeData(Object msg, Field field, int position) {
			this.msg = msg;
			this.field = field;
			this.position = position;
		}

		public Object get() {
			try {
				return field.get(msg);
			} catch (IllegalAccessException e) {
				logger.error("OpenMaple cannot handle private packet fields that honor accessor modifiers.", e);
			}
			return null;
		}

		public Class<?> type() {
			return field.getType();
		}

		public Annotation[] annotations() {
			return field.getAnnotations();
		}

		@Override
		public int compareTo(SerializeData o) {
			return position - o.position;
		}
	}
}
