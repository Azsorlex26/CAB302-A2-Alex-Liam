package assignment2.classes;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class Interface extends JFrame {

	private JPanel storeManagementPane;
	private JTable tblInventory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Interface("test");
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface(String storeName){
		//Create the main JFrame GUI with title, close properties and bounds
		setTitle(storeName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 536);
		
		//Create a tabbedPane and add layouts
		JTabbedPane storeTabs = new JTabbedPane();
		storeTabs.setFont(new Font("Sylfaen", Font.PLAIN, 35));
		
		//Creates two separate objects for the tabbed pane.
		storeManagementPane = new JPanel(); //Create a panel to allow buttons and objects
		tblInventory = new JTable(); // Creates a table component to be put into its own tab
		
		
		//Create labels and buttons to be put into the store management tab
		JLabel lblStoreCapital = new JLabel("Store Capital: $*****");
		JButton btnImportItems = new JButton("Import Item Properties");
		JButton btnImportManifest = new JButton("Import Manifest");
		JButton btnImportSalesLog = new JButton("Import Sales Log");
		JButton btnExportManifest = new JButton("Export Manifest");
		
		//Configure the layout with components
		//The chosen layout is a GroupLayout as it allows flexibility with resizing objects
		//as well as with relativity within the page
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/groupExample.html
		GroupLayout stockLayout = new GroupLayout(storeManagementPane);
		stockLayout.setHorizontalGroup( //Set horizontal parameters
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
					.addContainerGap())
		);
		stockLayout.setVerticalGroup( //Set vertical parameters
			stockLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(stockLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(stockLayout.createParallelGroup(Alignment.BASELINE)
						//.addComponent(lblStoreCapital)
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
		
		//Add the tabs the the tabbedPane and display GUI
		storeManagementPane.setLayout(stockLayout);
		storeTabs.addTab("Store Management", null, storeManagementPane, null);
		storeTabs.addTab("Inventory", null, tblInventory, null);
		storeTabs.setVisible(true);
		setVisible(true);
		getContentPane().add(storeTabs);
	}
}
