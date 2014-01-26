package openmaple.data;

import io.netty.buffer.ByteBuf;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * An interface describing an entry of data within a {@code DataSource}.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/25/13
 */
public interface DataEntry {
	/**
	 * Gets the name of the data entry.
	 *
	 * @return the entry name
	 */
	public String getName();

	/**
	 * Gets the path to the data entry.
	 *
	 * @return the entry path
	 */
	public String getPath();

	/**
	 * Gets the data from the data entry.
	 *
	 * @return the actual entry data
	 */
	public Object getData();


	/**
	 * Gets the data from the data entry as a {@code long}.
	 *
	 * @return the actual entry data as a long
	 */
	public long getDataAsLong();

	/**
	 * Gets the data from the data entry as a {@code double}.
	 *
	 * @return the actual entry data as a double
	 */
	public double getDataAsDouble();

	/**
	 * Gets the data from the data entry as a {@code String}.
	 *
	 * @return the actual entry data as a String
	 */
	public String getDataAsString();

	/**
	 * Gets the data from the data entry as a {@code Point}.
	 *
	 * @return the actual entry data as a Point
	 */
	public Point getDataAsPoint();

	/**
	 * Gets the data from the data entry as a {@code BufferedImage}.
	 *
	 * @return the actual entry data as a BufferedImage
	 */
	public BufferedImage getDataAsBufferedImage();

	/**
	 * Gets the data from the data entry as a {@code ByteBuf}.
	 *
	 * @return the actual entry data as a ByteBuf
	 */
	public ByteBuf getDataAsByteBuf();

	/**
	 * Gets the {@code DataEntryType} of the data entry.
	 *
	 * @return the entry type
	 */
	public DataEntryType getType();

	/**
	 * Gets an array of child data entries.
	 *
	 * @return the child entries
	 */
	public DataEntry[] getChildEntries();
}
