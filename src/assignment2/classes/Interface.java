package assignment2.classes;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import assignment2.exceptions.CSVFormatException;
import assignment2.exceptions.StockException;

/**
 * This class is both the program entry point and GUI
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {

	public static final int WIDTH = 953;
	public static final int HEIGHT = 536;
	private static final String[] COLUMN_NAMES = {
			"Name",
			"Quantity",
			"Manufacturing Cost ($)",
			"Sell Price ($)",
			"Reorder Point",
			"Reorder Amount", 
			"Temperature (C)" };
	private DecimalFormat capFormat = new DecimalFormat("#, ###.00");
	private JPanel storeManagementPane, storeInventoryPane;
	private JTable tblInventory;
	private JButton btnImportItems, btnImportManifest, btnImportSalesLog, btnExportManifest;
	private JScrollPane tblScroll;
	private JLabel lblStoreCapital;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Interface("SuperMart");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the interface.
	 * 
	 * @param storeName
	 */
	public Interface(String storeName) {
		// Create the main JFrame GUI with title, close properties and bounds
		super(storeName + " Inventory Management");
		Store.makeStore(storeName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);

		// Create a tabbedPane and add layouts
		JTabbedPane storeTabs = new JTabbedPane();
		storeTabs.setFont(new Font("Sylfaen", Font.PLAIN, 35));

		// Creates two separate objects for the tabbed pane.
		storeManagementPane = new JPanel(); // Create a panel to allow buttons and objects
		storeInventoryPane = new JPanel();

		tblInventory = new JTable(); // Creates a table component to be put into its own tab
		tblScroll = new JScrollPane(tblInventory);

		// Create labels and buttons to be put into the store management tab
		lblStoreCapital = new JLabel("Store Capital: $" + capFormat.format(Store.getCapital()));
		btnImportItems = new JButton("Import Item Properties");
		btnImportManifest = new JButton("Import Manifest");
		btnImportManifest.setEnabled(false);
		btnImportSalesLog = new JButton("Import Sales Log");
		btnImportSalesLog.setEnabled(false);
		btnExportManifest = new JButton("Generate Manifest");
		btnExportManifest.setEnabled(false);
		tblInventory.setVisible(true);

		// Add action listeners onto buttons
		// Set names for buttons to prevent duplication of code when opening file
		// dialogue box
		btnImportItems.addActionListener(this);
		btnImportManifest.addActionListener(this);
		btnImportSalesLog.addActionListener(this);
		btnExportManifest.addActionListener(this);

		// Configure the layout with components
		// The chosen layout is a GroupLayout as it allows flexibility with resizing /
		// moving
		// objects
		// as well as with relativity within the page
		// https://docs.oracle.com/javase/tutorial/uiswing/layout/groupExample.html
		GroupLayout stockLayout = new GroupLayout(storeManagementPane);
		stockLayout.setHorizontalGroup( // Set horizontal parameters
				stockLayout.createParallelGroup(Alignment.LEADING).addGroup(stockLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(stockLayout.createParallelGroup(Alignment.LEADING).addComponent(btnImportItems)
								.addGroup(stockLayout.createSequentialGroup().addComponent(btnExportManifest)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnImportManifest))
								.addComponent(btnImportSalesLog).addComponent(lblStoreCapital))
						.addContainerGap()));

		stockLayout.setVerticalGroup( // Set vertical parameters
				stockLayout.createParallelGroup(Alignment.LEADING).addGroup(stockLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(stockLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnImportItems))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(stockLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnExportManifest)
								.addComponent(btnImportManifest))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnImportSalesLog).addGap(150)
						.addComponent(lblStoreCapital).addContainerGap(204, 204)));

		// Add the tabs the the tabbedPane and display GUI
		storeManagementPane.setLayout(stockLayout);
		storeInventoryPane.setLayout(new BorderLayout());
		storeTabs.addTab("Store Management", null, storeManagementPane, null);
		storeTabs.addTab("Inventory", null, storeInventoryPane, null);

		storeTabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (storeTabs.getSelectedIndex() == 1) {
					updateInventory();
				}
			}
		});

		storeTabs.setVisible(true);
		setVisible(true);
		getContentPane().add(storeTabs);
	}

	private void updateInventory() {
		storeInventoryPane.remove(tblScroll);
		Object[][] tableData = new Object[Store.getInventory().totalItems()][7];
		int count = 0;

		for (Item item : Store.getInventory()) {
			tableData[count] = new Object[] { item.getName(), Store.getInventory().getItemQuantity(item.getName()),
					item.getManufactureCost(), item.getSellCost(), item.getReorderPoint(), item.getReorderAmount(),
					item.getTempThreshold() };
			count++;
		}
		tblInventory = new JTable(tableData, COLUMN_NAMES);
		tblScroll = new JScrollPane(tblInventory);
		storeInventoryPane.add(tblScroll, BorderLayout.CENTER);
	}

	/**
	 * Allows different actions to happen depending on the button pressed
	 * 
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath;
		if (e.getSource() == btnImportItems) {
			if ((filePath = IOHandler.fileChooser(false)) != null) {

				try {
					IOHandler.readItemProperties(filePath);
				} catch (CSVFormatException exception) {
					JOptionPane.showMessageDialog(null, "This is not a valid CSV file", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} catch (IOException exception) {
				}
				;
				btnImportItems.setEnabled(false); // Disable item properties importing as this is only done once
				btnImportManifest.setEnabled(true);
				btnExportManifest.setEnabled(true);
				btnImportSalesLog.setEnabled(true);
			}
		} else if (e.getSource() == btnImportManifest) {
			if ((filePath = IOHandler.fileChooser(false)) != null) {

				try {
					IOHandler.readManifest(filePath);
				} catch (CSVFormatException exception) {
					System.err.println("Warning: This is not a valid CSV file");
					JOptionPane.showMessageDialog(null, "This is not a valid CSV file", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} catch (StockException exception) {
					System.err.println("There is an invalid item in this manifest");
					JOptionPane.showMessageDialog(null, "There is an invalid item in this manifest", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException exception) {
				}
			}
		} else if (e.getSource() == btnImportSalesLog) {
			if ((filePath = IOHandler.fileChooser(false)) != null) {

				try {
					IOHandler.readSalesLog(filePath);
				} catch (CSVFormatException exception) {
					JOptionPane.showMessageDialog(null, "Warning: This is not a valid CSV file", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (StockException exception) {
					JOptionPane.showMessageDialog(null, "There is an invalid item/quantity in this sales log", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException exception) {
				}
			}
		} else if (e.getSource() == btnExportManifest) {
			if ((filePath = IOHandler.fileChooser(true)) != null) {
				try {
					IOHandler.exportManifest(filePath);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "There was an error exporting the manifest", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		lblStoreCapital.setText("Store Capital: $" + capFormat.format((Store.getCapital())));
	}

}
