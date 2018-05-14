package assignment2.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import assignment2.exceptions.CSVFormatException;

/**
 * This class handles the importing and exporting of CSV files
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class IOHandler {
	
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	
	private static final int ITEM_NAME_INDEX = 0;
	private static final int ITEM_COST_INDEX = 1;
	private static final int ITEM_PRICE_INDEX = 2;
	private static final int ITEM_ORDPOINT_INDEX = 3;
	private static final int ITEM_ORDAMT_INDEX = 4;
	private static final int ITEM_TEMP_INDEX = 5; // Optional
	
	/*
	 * Reads the Item Properties from the given filePath provided via the GUI
	 * 
	 * @param Item Properties filepath
	 */
	public static void readItemProperties(String filePath) throws CSVFormatException {
		BufferedReader csvReader = null;
		
		try {
			List Items = new ArrayList();
			csvReader = new BufferedReader(new FileReader(filePath));
			String itemLine = "";
			
			while((itemLine = csvReader.readLine()) != null) {
				String[] properties = itemLine.split(COMMA_DELIMITER);
				
				if (properties.length == 5) { //If a non-refrigerated item
					Item item = new Item(
							properties[ITEM_NAME_INDEX],
							Double.parseDouble(properties[ITEM_COST_INDEX]),
							Double.parseDouble(properties[ITEM_PRICE_INDEX]),
							Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
							Integer.parseInt(properties[ITEM_ORDAMT_INDEX]));
				} else if (properties.length == 6) { //If a refrigerated item
					Item item = new Item(
							properties[ITEM_NAME_INDEX],
							Double.parseDouble(properties[ITEM_COST_INDEX]),
							Double.parseDouble(properties[ITEM_PRICE_INDEX]),
							Integer.parseInt(properties[ITEM_ORDPOINT_INDEX]),
							Integer.parseInt(properties[ITEM_ORDAMT_INDEX]),
							Double.parseDouble(properties[ITEM_TEMP_INDEX]));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
