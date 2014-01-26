package openmaple.data.pkgnx;

import io.netty.buffer.ByteBuf;
import openmaple.data.DataEntry;
import openmaple.data.DataEntryType;
import us.aaronweiss.pkgnx.NXNode;
import us.aaronweiss.pkgnx.nodes.*;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * An entry of data representing an {@code NXNode} within an {@code NXFile}.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/26/13
 */
public class NXDataEntry implements DataEntry {
	private final DataEntryType type;
	private final NXNode node;
	private final String path;
	private DataEntry[] children;

	public NXDataEntry(NXNode node, String path) {
		this.node = node;
		this.path = path;
		children = null;

		if (node instanceof NXNullNode) {
			type = DataEntryType.None;
		} else if (node instanceof NXLongNode) {
			type = DataEntryType.Int64;
		} else if (node instanceof NXDoubleNode) {
			type = DataEntryType.Double;
		} else if (node instanceof NXStringNode) {
			type = DataEntryType.String;
		} else if (node instanceof NXPointNode) {
			type = DataEntryType.Vector;
		} else if (node instanceof NXBitmapNode) {
			type = DataEntryType.Bitmap;
		} else if (node instanceof NXAudioNode) {
			type = DataEntryType.Audio;
		} else {
			type = DataEntryType.Unknown;
		}
	}

	@Override
	public String getName() {
		return node.getName();
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public Object getData() {
		return node.get();
	}

	@Override
	public long getDataAsLong() {
		if (node instanceof NXLongNode)
			return (long) node.get();
		else
			throw new UnsupportedOperationException("Cannot cast non-long node data as long.");
	}

	@Override
	public double getDataAsDouble() {
		if (node instanceof NXDoubleNode)
			return (double) node.get();
		else
			throw new UnsupportedOperationException("Cannot cast non-double node data as double.");
	}

	@Override
	public String getDataAsString() {
		if (node instanceof NXStringNode)
			return (String) node.get();
		else
			throw new UnsupportedOperationException("Cannot cast non-String node data as String.");
	}

	@Override
	public Point getDataAsPoint() {
		if (node instanceof NXPointNode)
			return (Point) node.get();
		else
			throw new UnsupportedOperationException("Cannot cast non-Point node data as Point.");
	}

	@Override
	public BufferedImage getDataAsBufferedImage() {
		if (node instanceof NXBitmapNode)
			return (BufferedImage) node.get();
		else
			throw new UnsupportedOperationException("Cannot cast non-BufferedImage node data as BufferedImage.");
	}

	@Override
	public ByteBuf getDataAsByteBuf() {
		if (node instanceof NXAudioNode)
			return (ByteBuf) node.get();
		else
			throw new UnsupportedOperationException("Cannot cast non-ByteBuf node data as ByteBuf.");
	}

	@Override
	public DataEntryType getType() {
		return type;
	}

	@Override
	public DataEntry[] getChildEntries() {
		if (children == null) {
			children = new DataEntry[node.getChildCount()];
			int index = 0;
			for (NXNode child : node) {
				children[index++] = new NXDataEntry(child, path + "/" + child.getName());
			}
		}
		return children;
	}
}
