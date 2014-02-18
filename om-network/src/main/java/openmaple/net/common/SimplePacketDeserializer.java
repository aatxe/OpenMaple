package openmaple.net.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import openmaple.net.common.serial.PacketDeserializer;

import java.util.List;

/**
 * A simple registration-based {@code ReplayingDecoder} for de-serializing {@code SimplePacket}s using manually registered {@code PacketDeserializer}s.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/16/14
 */
public class SimplePacketDeserializer extends ReplayingDecoder<ByteBuf> {
	private PacketDeserializer[] deserializers;

	public SimplePacketDeserializer() {
		this(0xFFFF);
	}

	public SimplePacketDeserializer(int maxOpcode) {
		deserializers = new PacketDeserializer[maxOpcode];
	}

	public void register(PacketDeserializer deserializer) {
		deserializers[deserializer.getOpcode()] = deserializer;
	}


	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int opcode = in.readUnsignedShort();
		out.add(deserializers[opcode].deserialize(in.nioBuffer()));
	}
}
