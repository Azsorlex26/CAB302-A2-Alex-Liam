package assignment2.classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JLabel;

public class Interface extends JFrame {

	private JPanel stockManagementPane;
	private JTable tblInventory;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface("test");
					
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
		//Create the main JFrame GUI
		JFrame GUI = new JFrame();
		GUI.setTitle(storeName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.setBounds(100, 100, 953, 536);
		
		//Create a tabbedPane and add layouts
		JTabbedPane storeTabs = new JTabbedPane();
		storeTabs.setFont(new Font("Sylfaen", Font.PLAIN, 35));
		
		tblInventory = new JTable();
		
		stockManagementPane = new JPanel();
		GroupLayout stockLayout = new GroupLayout(stockManagementPane);
		stockManagementPane.setLayout(stockLayout);
		
		storeTabs.addTab("Stock Management", null, stockManagementPane, null);
		storeTabs.addTab("Inventory", null, tblInventory, null);
		
		storeTabs.setVisible(true);
		GUI.setVisible(true);
		GUI.add(storeTabs);
	}

}
