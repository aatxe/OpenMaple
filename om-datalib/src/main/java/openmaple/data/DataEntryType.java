package openmaple.data;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An enumeration of types for {@code DataEntries}.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/25/13
 */
public enum DataEntryType {
	None(null),
	Int64(long.class),
	Double(double.class),
	String(String.class),
	Vector(Point.class),
	Bitmap(BufferedImage.class),
	Audio(byte[].class),
	Unknown(Object.class);
	private final Class dataType;

	/**
	 * Constructs a data entry type with the specified {@code dataType}.
	 *
	 * @param dataType a {@code Class} for the specific data type.
	 */
	private DataEntryType(Class dataType) {
		this.dataType = dataType;
	}

	/**
	 * Gets the {@code Class} of the data type.
	 *
	 * @return the data type
	 */
	public Class getDataType() {
		return dataType;
	}
}
