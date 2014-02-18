package openmaple.net.common;

/**
 * The interface for all packets that use the simple serialization system.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 2/16/14
 */
public interface SimplePacket extends Packet {
	/**
	 * Gets the packet's opcode as an unsigned short.
	 *
	 * @return the opcode
	 */
	public int opcode();
}
