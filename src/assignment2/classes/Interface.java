package assignment2.classes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import assignment2.exceptions.CSVFormatException;
import assignment2.exceptions.StockException;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {

	public static final int WIDTH = 953;
	public static final int HEIGHT = 536;
	private static Store store;
	private JPanel storeManagementPane;
	private JTable tblInventory;
	private JButton btnImportItems, btnImportManifest, btnImportSalesLog, btnExportManifest;
	private JFileChooser fileChooser;
	JLabel lblStoreCapital;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Interface("test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the interface.
	 * 
	 * @param storeName
	 */
	public Interface(String storeName) {
		// Create the main JFrame GUI with title, close properties and bounds
		super(storeName + " Inventory Management");
		store = Store.makeStore(storeName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);

		// Create a tabbedPane and add layouts
		JTabbedPane storeTabs = new JTabbedPane();
		storeTabs.setFont(new Font("Sylfaen", Font.PLAIN, 35));

		// Creates two separate objects for the tabbed pane.
		storeManagementPane = new JPanel(); // Create a panel to allow buttons and objects
		tblInventory = new JTable(); // Creates a table component to be put into its own tab

		// Create labels and buttons to be put into the store management tab
		lblStoreCapital = new JLabel("Store Capital: $0");
		btnImportItems = new JButton("Import Item Properties");
		btnImportManifest = new JButton("Import Manifest");
		btnImportManifest.setEnabled(false);
		btnImportSalesLog = new JButton("Import Sales Log");
		btnImportSalesLog.setEnabled(false);
		btnExportManifest = new JButton("Export Manifest");
		btnExportManifest.setEnabled(false);

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
								.addGroup(stockLayout.createSequentialGroup().addComponent(btnImportManifest)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExportManifest))
								.addComponent(btnImportSalesLog).addComponent(lblStoreCapital))
						.addContainerGap()));

		stockLayout.setVerticalGroup( // Set vertical parameters
				stockLayout.createParallelGroup(Alignment.LEADING).addGroup(stockLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(stockLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnImportItems))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(stockLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnImportManifest)
								.addComponent(btnExportManifest))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnImportSalesLog).addGap(150)
						.addComponent(lblStoreCapital).addContainerGap(204, 204)));

		// Add the tabs the the tabbedPane and display GUI
		storeManagementPane.setLayout(stockLayout);
		storeTabs.addTab("Store Management", null, storeManagementPane, null);
		storeTabs.addTab("Inventory", null, tblInventory, null);
		storeTabs.setVisible(true);
		setVisible(true);
		getContentPane().add(storeTabs);
	}

	public String fileChooser() {
		fileChooser = new JFileChooser();
		int optionSelected = fileChooser.showOpenDialog(this);

		if (optionSelected == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	/**
	 * Allows different actions to happen depending on the button pressed
	 * 
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = fileChooser();
		if (e.getSource() == btnImportItems) {
			try {
				IOHandler.readItemProperties(filePath);
				
				btnImportItems.setEnabled(false); // Disable item properties importing as this is only done once
				btnImportManifest.setEnabled(true);
				System.out.println("item_properties.csv imported successfully");
			} catch (CSVFormatException c) {
				System.err.println(c.getMessage());
			}

		} else if (e.getSource() == btnImportManifest) {
			try {
				IOHandler.readManifest(filePath);
				
				lblStoreCapital.setText("Store Capital: $" + store.getCapital());
				System.out.println("manifest.csv imported successfully");
			} catch (CSVFormatException c) {
				System.err.println(c.getMessage());
			}
		}
	}
}