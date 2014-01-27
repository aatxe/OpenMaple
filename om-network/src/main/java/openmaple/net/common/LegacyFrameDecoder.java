package openmaple.net.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import openmaple.crypto.legacy.LegacyCryptoKit;
import openmaple.net.common.utils.NetworkUtils;

import java.util.List;

/**
 * A decoder that splits the received {@code ByteBuf}s according to the legacy MapleStory protocol.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 1/26/14
 */
public class LegacyFrameDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		LegacyCryptoKit cryptoKit = (LegacyCryptoKit) ctx.attr(NetworkUtils.CRYPTO_KIT_ATTRIBUTE_KEY).get();
		if (in.readableBytes() < 4)
			return;
		int length = in.readInt();
		if (!cryptoKit.validatePacket(length)) {
			ctx.close();
			return;
		}
		length = cryptoKit.getPacketLength(length);
		if (in.readableBytes() < length)
			return;
		ByteBuf data = in.readBytes(length);
		cryptoKit.decrypt(data);
		out.add(data);
	}
}
