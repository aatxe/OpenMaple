package openmaple.data;

/**
 * An interface describing a basic data source for {@code om-datalib}.
 *
 * @author Aaron Weiss
 * @version 1.0.0
 * @since 7/25/13
 */
public interface DataSource {
	/**
	 * Gets the name of the data source.
	 * This can be anything as long as it describes the source uniquely.
	 * For file formats, this will often be the file name.
	 *
	 * @return the name of the source
	 */
	public String getName();

	/**
	 * Gets the root data entry from this data source.
	 *
	 * @return the root data entry
	 */
	public DataEntry getRoot();

	/**
	 * Looks up a data entry in the source by full path.
	 *
	 * @param path the path to the entry
	 * @return the entry
	 * @throws NoSuchDataEntryException if entry cannot be found
	 */
	public DataEntry lookUp(String path);


	/**
	 * Tries to look up a data entry by full path, returning null if the entry is not found.
	 *
	 * @param path the path to the entry
	 * @return the entry or null if it cannot be found
	 */
	public DataEntry tryLookUp(String path);
}
