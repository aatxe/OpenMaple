package openmaple.net.common;

import com.google.common.reflect.ClassPath;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import openmaple.net.common.serial.BaseDeserializer;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.List;

/**
 *
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/10/14
 */
public class PacketDeserializer extends ReplayingDecoder<ByteBuf> {
	private Constructor<?>[] constructors;

	public PacketDeserializer() {
		constructors = new Constructor<?>[0xFFFF];
		try {
			ClassPath cp = ClassPath.from(PacketDeserializer.class.getClassLoader());
			for (ClassPath.ClassInfo info : cp.getAllClasses()) {
				Class<?> c = info.load();
				if (Packet.class.isAssignableFrom(c)) {
					for (Constructor<?> constructor : c.getConstructors()) {
						if (constructor.isAnnotationPresent(Opcode.class)) {
							constructors[constructor.getAnnotation(Opcode.class).value()] = constructor;
						}
					}
				}
			}
		} catch (IOException e) {
			throw new Error("OpenMaple was unable to access classpath resources.", e);
		}
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int opcode = in.readUnsignedShort();
		Constructor<?> constructor = constructors[opcode];
		Class<?>[] types = constructor.getParameterTypes();
		Annotation[][] annotations = constructor.getParameterAnnotations();
		Object[] data = new Object[types.length];
		for (int i = 0; i < data.length; i++) {
			data[i] = BaseDeserializer.deserialize(in, types[i], annotations[i]);
		}
		out.add(constructor.newInstance(data));
	}
}
