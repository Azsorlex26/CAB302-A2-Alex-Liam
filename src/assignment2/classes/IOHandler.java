package assignment2.classes;

import java.io.BufferedReader;
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
	private static List<Item> storeItems;
	private static BufferedReader csvReader;
	
	private static final int ITEM_NAME_INDEX = 0;
	private static final int ITEM_COST_INDEX = 1;
	private static final int ITEM_PRICE_INDEX = 2;
	private static final int ITEM_ORDPOINT_INDEX = 3;
	private static final int ITEM_ORDAMT_INDEX = 4;
	private static final int ITEM_TEMP_INDEX = 5; // Optional
	private static final int MANIFEST_ITEM_INDEX = 0;
	private static final int MANIFEST_QUANT_INDEX = 1;
	private static final int SALESLOG_ITEM_INDEX = 0;
	private static final int SALESLOG_QUANT_INDEX = 1;

	/**
	 * Iterates through all items in store to locate the Item by that name
	 * 
	 * @param Name of the item to retrieve
	 * @return the item specified
	 * @throws StockException if item doesn't exist
	 */
	public static Item getItem(String name) throws StockException {
		for (Item item : storeItems) {
			if (name.equalsIgnoreCase(item.getName())) {
				return item;
			}
		}
		System.err.println(name);
		throw new StockException();
	}
	
	/**
	 * Reads the Item Properties from the given filePath provided via the GUI
	 */
	public static void readItemProperties(String filePath) throws CSVFormatException {
		storeItems = new ArrayList<Item>();
		String line;
		csvReader = null;
		
		try {
			csvReader = new BufferedReader(new FileReader(filePath));

			while ((line = csvReader.readLine()) != null) {
				System.out.println(line);
				String[] properties = line.split(COMMA_DELIMITER);

				if (properties.length == 5) { // If a non-refrigerated item, create an item
					Item item = (new Item(properties[ITEM_NAME_INDEX],
							Double.parseDouble(properties[ITEM_COST_INDEX]),
							Double.parseDouble(properties[ITEM_PRICE_INDEX]),
							Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
							Integer.parseInt(properties[ITEM_ORDAMT_INDEX])));
					storeItems.add(item);
					Store.getInventory().add(item, 0);
					
				} else if (properties.length == 6) { // If a refrigerated item, create an item
					Item item = (new Item(properties[ITEM_NAME_INDEX],
							Double.parseDouble(properties[ITEM_COST_INDEX]),
							Double.parseDouble(properties[ITEM_PRICE_INDEX]),
							Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
							Integer.parseInt(properties[ITEM_ORDAMT_INDEX]),
							Double.parseDouble(properties[ITEM_TEMP_INDEX])));
					storeItems.add(item);
					Store.getInventory().add(item, 0);
					
				} else {
					throw new CSVFormatException();
				}
			}
			csvReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void readManifest(String filePath) throws CSVFormatException, StockException {
		
		String line;
		csvReader = null;
		
		try {
			csvReader = new BufferedReader(new FileReader(filePath));

			while ((line = csvReader.readLine()) != null) {
				String[] manifestLine = line.split(COMMA_DELIMITER);
				System.out.println(line);
				
				if(!manifestLine[MANIFEST_ITEM_INDEX].startsWith(">")) {
						Item item = getItem(manifestLine[MANIFEST_ITEM_INDEX]);
						Store.getInventory().add(item, Integer.parseInt(manifestLine[MANIFEST_QUANT_INDEX]));
						Store.adjustCapital(-item.getManufactureCost());
				} else if(manifestLine.length > 2) {
					throw new CSVFormatException();
				}
			}
			csvReader.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public static void readSalesLog(String filePath) throws CSVFormatException, StockException {
		String line;
		csvReader = null;
		
		try {
			csvReader = new BufferedReader(new FileReader(filePath));

			while ((line = csvReader.readLine()) != null) {
				String[] salesLogLine = line.split(COMMA_DELIMITER);
				System.out.println(line);
				
				if(salesLogLine.length == 2) {
						Item item = getItem(salesLogLine[SALESLOG_ITEM_INDEX]);
						Store.getInventory().remove(item, Integer.parseInt(salesLogLine[SALESLOG_QUANT_INDEX]));
						Store.adjustCapital(item.getSellCost());
				} else {
					throw new CSVFormatException();
				}
			}
			csvReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
