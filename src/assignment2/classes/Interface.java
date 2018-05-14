package assignment2.classes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import assignment2.exceptions.StockException;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {

	public static final int WIDTH = 953;
	public static final int HEIGHT = 536;
	private static Store store;
	private JPanel storeManagementPane;
	private JTable tblInventory;

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
		super(storeName);
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
		JLabel lblStoreCapital = new JLabel("Store Capital: $" + store.capital());
		JButton btnImportItems = new JButton("Import Item Properties");
		JButton btnImportManifest = new JButton("Import Manifest");
		JButton btnImportSalesLog = new JButton("Import Sales Log");
		JButton btnExportManifest = new JButton("Export Manifest");
		
		// Add action listeners onto buttons
		// Set names for buttons to prevent duplication of code when opening file dialogue box
		btnImportItems.addActionListener(this);
		btnImportItems.setName("import");
		btnImportManifest.addActionListener(this);
		btnImportManifest.setName("import");
		btnImportSalesLog.addActionListener(this);
		btnImportSalesLog.setName("import");
		btnExportManifest.addActionListener(this);
		
		// Configure the layout with components
		// The chosen layout is a GroupLayout as it allows flexibility with resizing / moving
		// objects
		// as well as with relativity within the page
		// https://docs.oracle.com/javase/tutorial/uiswing/layout/groupExample.html
		GroupLayout stockLayout = new GroupLayout(storeManagementPane);
		stockLayout.setHorizontalGroup( // Set horizontal parameters
			stockLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(stockLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(stockLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnImportItems)
						.addGroup(stockLayout.createSequentialGroup()
							.addComponent(btnImportManifest)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExportManifest))
						.addComponent(btnImportSalesLog)
						.addComponent(lblStoreCapital))
					.addContainerGap()));
		
		stockLayout.setVerticalGroup( // Set vertical parameters
			stockLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(stockLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(stockLayout.createParallelGroup(Alignment.BASELINE)
						// .addComponent(lblStoreCapital)
						.addComponent(btnImportItems))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(stockLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnImportManifest)
						.addComponent(btnExportManifest))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnImportSalesLog)
					.addGap(150)
					.addComponent(lblStoreCapital)
					.addContainerGap(204, 204)));		
			
		// Add the tabs the the tabbedPane and display GUI
		storeManagementPane.setLayout(stockLayout);
		storeTabs.addTab("Store Management", null, storeManagementPane, null);
		storeTabs.addTab("Inventory", null, tblInventory, null);
		storeTabs.setVisible(true);
		setVisible(true);
		getContentPane().add(storeTabs);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonClicked = (JButton) e.getSource();
		if(buttonClicked.getName() == "import") {
			System.out.println("Import button");
		}
	}
}
