package openmaple.net.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import openmaple.net.common.serial.PacketSerializer;

/**
 * A simple registration-based {@code MessageToByteEncoder} for serializing {@code SimplePacket}s using manually registered {@code PacketSerializer}s.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/16/14
 */
public class SimplePacketSerializer extends MessageToByteEncoder {
	private PacketSerializer[] serializers;

	public SimplePacketSerializer() {
		this(0xFFFF);
	}

	public SimplePacketSerializer(int maxOpcode) {
		serializers = new PacketSerializer[maxOpcode];
	}

	public void register(PacketSerializer serializer) {
		serializers[serializer.getOpcode()] = serializer;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		SimplePacket packet = (SimplePacket) msg;
		PacketSerializer serializer = serializers[packet.opcode()];
		out.writeBytes(serializer.serialize(packet));
	}
}
