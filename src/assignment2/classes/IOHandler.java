package assignment2.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

	public Stock storeStock = new Stock();
	public static List<Item> storeItems;
	
	private static final int ITEM_NAME_INDEX = 0;
	private static final int ITEM_COST_INDEX = 1;
	private static final int ITEM_PRICE_INDEX = 2;
	private static final int ITEM_ORDPOINT_INDEX = 3;
	private static final int ITEM_ORDAMT_INDEX = 4;
	private static final int ITEM_TEMP_INDEX = 5; // Optional
	private static final int MANIFEST_ITEM_INDEX = 0;
	private static final int MANIFEST_QUANT_INDEX = 1;

	/**
	 * Iterates through all items in store to locate the Item by that name
	 * 
	 * @param Name of the item to retrieve
	 * @return the item specified
	 */
	public static Item getItem(String name) {
		for (Item item : storeItems) {
			if (item.getName() == name) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Reads the Item Properties from the given filePath provided via the GUI
	 */
	public static void readItemProperties(String filePath) throws CSVFormatException {
		List<Item> Items = new ArrayList<Item>();
		storeItems = new ArrayList<Item>();
		Stock storeStock = new Stock();
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

			while (csvReader.readLine() != null) {
				String[] properties = csvReader.readLine().split(COMMA_DELIMITER);

				if (properties.length == 5) { // If a non-refrigerated item, create an item
					Item item = (new Item(properties[ITEM_NAME_INDEX],
							Double.parseDouble(properties[ITEM_COST_INDEX]),
							Double.parseDouble(properties[ITEM_PRICE_INDEX]),
							Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
							Integer.parseInt(properties[ITEM_ORDAMT_INDEX])));
					storeItems.add(item);
					Store.inventory.add(item, 0);
					
				} else if (properties.length == 6) { // If a refrigerated item, create an item
					Item item = (new Item(properties[ITEM_NAME_INDEX],
							Double.parseDouble(properties[ITEM_COST_INDEX]),
							Double.parseDouble(properties[ITEM_PRICE_INDEX]),
							Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
							Integer.parseInt(properties[ITEM_ORDAMT_INDEX]),
							Double.parseDouble(properties[ITEM_TEMP_INDEX])));
					storeItems.add(item);
					Store.inventory.add(item, 0);
					
				} else {
					throw new CSVFormatException(null);
				}
			}
			csvReader.close();
			//storeItems = Items;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void readManifest(String filePath) throws CSVFormatException {
		List<Item> Items = new ArrayList<Item>();
		
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(filePath));

			while (csvReader.readLine() != null) {
				String[] manifestLine = csvReader.readLine().split(COMMA_DELIMITER);
				
				if(manifestLine.length == 2) {
					try {
						Item item = getItem(manifestLine[MANIFEST_ITEM_INDEX]);
						Store.inventory.add(item, Integer.parseInt(manifestLine[MANIFEST_QUANT_INDEX]));
						Store.store.adjustCapital(item.getManufactureCost());
					} catch (StockException e) {
						throw new CSVFormatException(null);
					}
				}
			}
			csvReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
