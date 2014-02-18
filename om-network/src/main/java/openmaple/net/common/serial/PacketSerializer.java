package openmaple.net.common.serial;

import openmaple.net.common.Packet;

import java.nio.ByteBuffer;

/**
 * An interface describing an object for serializing a packet.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/16/14
 */
public interface PacketSerializer {
	/**
	 * Gets the opcode for this serializer to handle.
	 *
	 * @return the packet opcode
	 */
	public int getOpcode();


	/**
	 * Serializes the {@code Packet} into a {@code ByteBuffer}.
	 *
	 * @param packet a proper packet to serialize
	 * @return data to be sent over the network
	 */
	public ByteBuffer serialize(Packet packet);
}
