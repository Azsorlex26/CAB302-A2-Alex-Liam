package assignment2.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import assignment2.classes.truck.OrdinaryTruck;
import assignment2.classes.truck.RefrigeratedTruck;
import assignment2.classes.truck.Truck;
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
	private static BufferedReader csvReader;
	private static String line;
	private static Stock refrigeratedItems, ordinaryItems;
	private static List<Item> sortedColdItems, listOrdinaryItems, storeItems;

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
	 * Opens a window and returns the filepath of the selected file.
	 * 
	 * @return file path if approve (yes, ok) is chosen. null otherwise.
	 */
	public static String fileChooser() {
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	/**
	 * Reads the Item Properties from the given filePath provided via the GUI
	 * 
	 * @param filePath
	 *            of item to be read
	 * @throws CSVFormatException
	 * @throws IOException
	 */
	public static void readItemProperties(String filePath) throws CSVFormatException, IOException {
		storeItems = new ArrayList<Item>();

		try {
			csvReader = new BufferedReader(new FileReader(filePath));

			while ((line = csvReader.readLine()) != null) {
				// System.out.println(line);
				String[] properties = line.split(COMMA_DELIMITER);

				if (properties.length == 5) { // If a non-refrigerated item, create an item
					Item item = (new Item(properties[ITEM_NAME_INDEX], Double.parseDouble(properties[ITEM_COST_INDEX]),
							Double.parseDouble(properties[ITEM_PRICE_INDEX]),
							Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
							Integer.parseInt(properties[ITEM_ORDAMT_INDEX])));
					storeItems.add(item);
					Store.getInventory().add(item, 0);

				} else if (properties.length == 6) { // If a refrigerated item, create an item
					Item item = (new Item(properties[ITEM_NAME_INDEX], Double.parseDouble(properties[ITEM_COST_INDEX]),
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

		} catch (Exception e) {
			throw new CSVFormatException();
		} finally {
			csvReader.close();
		}
	}

	/**
	 * Reads the manifest from the given filePath provided via the GUI
	 * 
	 * @param filePath
	 *            of item to be read
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws IOException
	 */
	public static void readManifest(String filePath) throws CSVFormatException, StockException, IOException {
		Truck truck = null;

		try {
			csvReader = new BufferedReader(new FileReader(filePath));

			while ((line = csvReader.readLine()) != null) {
				String[] manifestLine = line.split(COMMA_DELIMITER);
				System.out.println(line);

				/**
				 * As trucks are specified first in a manifest, create a truck accordingly
				 * Refrigerated trucks can have temperature changed, so start with max temp
				 */
				if (manifestLine[MANIFEST_ITEM_INDEX].startsWith(">")) {
					if (truck != null) {
						Store.adjustCapital(-(truck.getCost()));
					}
					if (manifestLine[MANIFEST_ITEM_INDEX].startsWith(">Ordinary")) {
						truck = new OrdinaryTruck();
					} else if (manifestLine[MANIFEST_ITEM_INDEX].startsWith(">Refrigerated")) {
						truck = new RefrigeratedTruck(10);
					} else {
						throw new CSVFormatException();
					}

					/**
					 * Process items individually. If item has a temperature threshold, compare to
					 * existing temperature If temperature is less than existing truck, change
					 * temperature of truck accordingly
					 */
				} else if (!manifestLine[MANIFEST_ITEM_INDEX].startsWith(">") && manifestLine.length == 2) {
					Item item = Store.getInventory().getItem(manifestLine[MANIFEST_ITEM_INDEX]);

					if (truck.getClass() == RefrigeratedTruck.class) {
						((RefrigeratedTruck) truck).setTemp(item.getTempThreshold());
					}
					Store.getInventory().add(item, Integer.parseInt(manifestLine[MANIFEST_QUANT_INDEX]));
					Store.adjustCapital(
							-(item.getManufactureCost() * Integer.parseInt(manifestLine[MANIFEST_QUANT_INDEX])));

				} else if (manifestLine.length != 2 && !manifestLine[MANIFEST_ITEM_INDEX].startsWith(">")) {
					throw new CSVFormatException();
				}
			}

			if (truck != null) {
				Store.adjustCapital(-(truck.getCost()));
			}

		} catch (CSVFormatException e) {
			throw new CSVFormatException();
		} catch (StockException e) {
			throw new StockException();
		} catch (Exception e) {
		} finally {
			csvReader.close();
		}
	}

	/**
	 * Reads the sales log from the given filePath provided via the GUI
	 * 
	 * @param filePath
	 *            of item to be read
	 * @throws CSVFormatException
	 * @throws StockException
	 * @throws IOException
	 */
	public static void readSalesLog(String filePath) throws CSVFormatException, StockException, IOException {
		try {
			csvReader = new BufferedReader(new FileReader(filePath));

			while ((line = csvReader.readLine()) != null) {
				String[] salesLogLine = line.split(COMMA_DELIMITER);
				System.out.println(line);

				if (salesLogLine.length == 2) {
					Item item = Store.getInventory().getItem(salesLogLine[SALESLOG_ITEM_INDEX]);
					Store.getInventory().remove(item, Integer.parseInt(salesLogLine[SALESLOG_QUANT_INDEX]));
					Store.adjustCapital(item.getSellCost() * Integer.parseInt(salesLogLine[SALESLOG_QUANT_INDEX]));
				} else {
					throw new CSVFormatException();
				}
			}

		} catch (CSVFormatException e) {
			throw new CSVFormatException();
		} catch (StockException e) {
			throw new StockException();
		} catch (Exception e) {
		} finally {
			csvReader.close();
		}
	}

	/**
	 * Exports a manifest for all items that need to be reordered
	 * 
	 * @throws StockException
	 */
	public static void exportManifest() throws StockException {
		Manifest manifest = new Manifest();
		sortedColdItems = new ArrayList<Item>();
		listOrdinaryItems = new ArrayList<Item>();
		RefrigeratedTruck rTruck;
		OrdinaryTruck oTruck;
		ordinaryItems = new Stock();
		refrigeratedItems = new Stock();

		// Store all items in appropriate stock lists that need to be reordered
		for (Item item : Store.getInventory()) {
			if (Store.getInventory().reorder(item)) {
				if (item.getTempThreshold() == null) {
					ordinaryItems.add(item, item.getReorderAmount());
				} else {
					refrigeratedItems.add(item, item.getReorderAmount());
					System.out.println(item.getName());
				}
			}
		}

		// Create a list of all the refrigerated items from coldest to warmest
		for (Item item : refrigeratedItems) {
			sortedColdItems.add(coldestItem());
		}
		for (Item item : ordinaryItems) {
			listOrdinaryItems.add(item);
		}

		// Create the refrigerated trucks
		rTruck = new RefrigeratedTruck(10);
		// Loop until there are no outstanding cold items to be reordered
		while (refrigeratedItems.totalQuantity() > 0) {
			int remainingCargo = rTruck.remainingCapacity();
			System.out.println(remainingCargo);
			String itemName = sortedColdItems.get(0).getName();
			Item item = refrigeratedItems.getItem(itemName);

			// Set the temperature of the truck
			rTruck.setTemp(sortedColdItems.get(0).getTempThreshold());

			// Remove the appropriate amount of stock by checking remaining amount of cargo
			if (refrigeratedItems.getItemQuantity(itemName) >= remainingCargo) {
				rTruck.add(item, remainingCargo);
				refrigeratedItems.remove(item, remainingCargo);
				System.out.println("Removed: " + remainingCargo + " of " + itemName);
			} else { // If truck has enough space to remove all of remaining item
				rTruck.add(item, refrigeratedItems.getItemQuantity(item));
				refrigeratedItems.remove(item, refrigeratedItems.getItemQuantity(item));
				sortedColdItems.remove(0); // Shift the sortedItems left 1
				System.out.println("Removed: " + itemName);
			}

			// Add truck to manifest if full and start again
			if (rTruck.remainingCapacity() == 0) {
				manifest.add(rTruck);
				rTruck = new RefrigeratedTruck(10);
			}
		}

		// Fill the remainder of the last remaining refrigerated truck with ordinary
		// items
		while (rTruck.remainingCapacity() > 0 && !listOrdinaryItems.isEmpty()) {

			int remainingCargo = rTruck.remainingCapacity();
			String itemName = listOrdinaryItems.get(0).getName();
			Item item = ordinaryItems.getItem(itemName);

			if (ordinaryItems.getItemQuantity(itemName) >= remainingCargo) {
				rTruck.add(item, remainingCargo);
				ordinaryItems.remove(item, remainingCargo);
				System.out.println("Removed: " + remainingCargo + " of " + itemName);
			} else { // If truck has enough space to remove all of remaining item
				rTruck.add(item, ordinaryItems.getItemQuantity(item));
				ordinaryItems.remove(item, ordinaryItems.getItemQuantity(item));
				listOrdinaryItems.remove(0); // Shift the sortedItems left 1
				System.out.println("Removed: " + itemName);
			}
		}
		manifest.add(rTruck);

		// Loop until there are no outstanding ordinary items to be reordered
		oTruck = new OrdinaryTruck();
		while (ordinaryItems.totalQuantity() > 0) {
			int remainingCargo = oTruck.remainingCapacity();
			System.out.println(remainingCargo);
			String itemName = listOrdinaryItems.get(0).getName();
			Item item = ordinaryItems.getItem(itemName);

			// Remove the appropriate amount of stock by checking remaining amount of cargo
			if (ordinaryItems.getItemQuantity(itemName) >= remainingCargo) {
				oTruck.add(item, remainingCargo);
				ordinaryItems.remove(item, remainingCargo);
				System.out.println("Removed: " + remainingCargo + " of " + itemName);
			} else { // If truck has enough space to remove all of remaining item
				oTruck.add(item, ordinaryItems.getItemQuantity(item));
				ordinaryItems.remove(item, ordinaryItems.getItemQuantity(item));
				listOrdinaryItems.remove(0); // Shift the sortedItems left 1
				System.out.println("Removed: " + itemName);
			}

			// Add truck to manifest if full and start again
			if (oTruck.remainingCapacity() == 0) {
				manifest.add(oTruck);
				oTruck = new OrdinaryTruck();
			}
		}
		manifest.add(oTruck);

		System.out.println(ordinaryItems.totalQuantity());
		System.out.println(refrigeratedItems.totalQuantity());
	}

	/**
	 * Returns the coldest item in the refrigerated items stock
	 * 
	 * @returns an item
	 */
	public static Item coldestItem() {
		double temp = 10;
		Item coldItem = null;
		for (Item item : refrigeratedItems) {
			if (item.getTempThreshold() <= temp && !sortedColdItems.contains(item)) {
				temp = item.getTempThreshold();
				coldItem = item;
			}
		}
		return coldItem;
	}
}
