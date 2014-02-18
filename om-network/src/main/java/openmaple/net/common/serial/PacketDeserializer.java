package openmaple.net.common.serial;

import openmaple.net.common.Packet;

import java.nio.ByteBuffer;

/**
 * An interface describing an object for de-serializing a packet.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/16/14
 */
public interface PacketDeserializer {
	/**
	 * Gets the opcode for this serializer to handle.
	 *
	 * @return the packet opcode
	 */
	public int getOpcode();


	/**
	 * De-serializes the {@code ByteBuffer} into a {@code Packet}.
	 *
	 * @param data the data to de-serialize
	 * @return the proper packet implementation
	 */
	public Packet deserialize(ByteBuffer data);
}
