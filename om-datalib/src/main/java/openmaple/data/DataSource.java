package openmaple.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An interface describing a basic data source for {@code om-datalib}.
 *
 * @author Aaron Weiss
 * @version 2.0.0
 * @since 7/25/13
 */
public abstract class DataSource {
	protected Map<String, DataEntry> dataEntries;
	private Lock entriesLock;

	public DataSource() {
		entriesLock = new ReentrantLock();
		dataEntries = new HashMap<>();
	}

	/**
	 * Gets the name of the data source. This can be anything as long as it describes the source uniquely. For file
	 * formats, this will often be the file name.
	 *
	 * @return the name of the source
	 */
	public abstract String getName();

	/**
	 * Gets the root data entry from this data source.
	 *
	 * @return the root data entry
	 */
	public abstract DataEntry getRoot();

	/**
	 * Looks up a data entry in the source by full path.
	 *
	 * @param path the path to the entry
	 * @return the entry
	 *
	 * @throws NoSuchDataEntryException if entry cannot be found
	 */
	public DataEntry lookUp(String path) {
		DataEntry ret = tryLookUp(path);
		if (ret == null)
			throw new NoSuchDataEntryException(this, path);
		return ret;
	}

	/**
	 * Tries to look up a data entry by full path, returning null if the entry is not found.
	 *
	 * @param path the path to the entry
	 * @return the entry or null if it cannot be found
	 */
	public DataEntry tryLookUp(String path) {
		if (entriesLock.tryLock() && !dataEntries.isEmpty()) {
			return dataEntries.get(path);
		} else {
			return internalLookUp(path);
		}
	}

	/**
	 * Populates an internal {@code Map} with data entries for full-path lookup. This should result in a significant
	 * speed-up in accessing data entries by full path.
	 */
	public void populateEntryMap() {
		entriesLock.lock();
		try {
			internalPopulateEntryMap();
		} finally {
			entriesLock.unlock();
		}
	}

	/**
	 * Performs an internal population of the data entry {@code Map} to speed-up full-path lookups.
	 */
	protected abstract void internalPopulateEntryMap();

	/**
	 * Performs an internal data entry lookup. This is to be specified by the particular implementation.
	 *
	 * @param path the path to the entry
	 * @return the entry or null if it cannot be found
	 */
	protected abstract DataEntry internalLookUp(String path);
}
