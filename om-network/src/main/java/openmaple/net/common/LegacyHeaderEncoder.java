package openmaple.net.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import openmaple.crypto.legacy.LegacyCryptoKit;
import openmaple.net.common.utils.NetworkUtils;

import java.nio.ByteOrder;

/**
 * An encoder that prepends the encoded header and encrypts the packet as per the legacy MapleStory protocol.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 1/26/14
 */
public class LegacyHeaderEncoder extends MessageToByteEncoder<ByteBuf> {
	@Override
	protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
		LegacyCryptoKit cryptoKit = (LegacyCryptoKit) ctx.attr(NetworkUtils.CRYPTO_KIT_ATTRIBUTE_KEY).get();
		ByteBuf data = msg.duplicate();
		byte[] header = cryptoKit.getPacketHeader(data.readableBytes());
		cryptoKit.encrypt(data);
		out.order(ByteOrder.LITTLE_ENDIAN).writeBytes(header).writeBytes(data);
	}
}
