package openmaple.data;

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
