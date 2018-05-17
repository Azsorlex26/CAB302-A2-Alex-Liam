package assignment2.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import assignment2.exceptions.CSVFormatException;
import assignment2.exceptions.StockException;

/**
 * This class handles the importing and exporting of CSV files
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class IOHandler {

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final int ITEM_NAME_INDEX = 0;
	private static final int ITEM_COST_INDEX = 1;
	private static final int ITEM_PRICE_INDEX = 2;
	private static final int ITEM_ORDPOINT_INDEX = 3;
	private static final int ITEM_ORDAMT_INDEX = 4;
	private static final int ITEM_TEMP_INDEX = 5; // Optional
	private static final int MANIFEST_ITEM_INDEX = 0;
	private static final int MANIFEST_QUANT_INDEX = 1;

	private static BufferedReader csvReader;
	private static String line;

	private static void closecsvReader() {
		try {
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the Item Properties from the given filePath provided via the GUI
	 * 
	 * @param filePath
	 * @throws CSVFormatException
	 */
	public static void readItemProperties(String filePath) throws CSVFormatException {
		try {
			csvReader = new BufferedReader(new FileReader(filePath));
			while ((line = csvReader.readLine()) != null) {
				String[] properties = line.split(COMMA_DELIMITER);

				if (properties.length == 5) { // If a non-refrigerated item, create an item
					Store.getInventory()
							.add(new Item(properties[ITEM_NAME_INDEX], Double.parseDouble(properties[ITEM_COST_INDEX]),
									Double.parseDouble(properties[ITEM_PRICE_INDEX]),
									Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
									Integer.parseInt(properties[ITEM_ORDAMT_INDEX])), 0);

				} else if (properties.length == 6) { // If a refrigerated item, create an item
					Store.getInventory()
							.add(new Item(properties[ITEM_NAME_INDEX], Double.parseDouble(properties[ITEM_COST_INDEX]),
									Double.parseDouble(properties[ITEM_PRICE_INDEX]),
									Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
									Integer.parseInt(properties[ITEM_ORDAMT_INDEX]),
									Double.parseDouble(properties[ITEM_TEMP_INDEX])), 0);
				} else {
					throw new CSVFormatException("Warning: This is not a valid Item Properties CSV file");
				}
			}

		} catch (NumberFormatException | IOException | StockException e) {
			e.printStackTrace();
		} finally {
			closecsvReader();
		}
	}

	/**
	 * Reads the manifest and does something
	 * 
	 * @param filePath
	 * @throws CSVFormatException
	 */
	public static void readManifest(String filePath) throws CSVFormatException {
		try {
			csvReader = new BufferedReader(new FileReader(filePath));
			while ((line = csvReader.readLine()) != null) {
				String[] manifestLine = line.split(COMMA_DELIMITER);
				
				try {
					if (manifestLine.length == 2) {
						Item item = Store.getInventory().getItem(manifestLine[MANIFEST_ITEM_INDEX]);
						Store.getInventory().add(item, Integer.parseInt(manifestLine[MANIFEST_QUANT_INDEX]));
						Store.getStore().adjustCapital(-item.getManufactureCost());
					} else throw new StockException(null);
				} catch (StockException e) {
					throw new CSVFormatException("Warning: This is not a valid Manifest CSV file");
				}
			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			closecsvReader();
		}
	}
}
