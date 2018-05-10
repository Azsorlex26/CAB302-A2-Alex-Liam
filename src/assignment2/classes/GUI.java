package assignment2.classes;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * The interface for the program
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8954008955048531845L;
	public static final int WIDTH = 953;
	public static final int HEIGHT = 536;
	private static Store store;
	private JButton btnName, btnCapital;
	private JPanel stockManagementPane;
	private JTable tblInventory;
	private JLabel lblNewLabel;

	/**
	 * Program entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI("UMart");
	}

	/**
	 * GUI constructor
	 * 
	 * @param title
	 * @throws HeadlessException
	 */
	public GUI(String title) throws HeadlessException {
		super(title);
		store = Store.makeStore(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, WIDTH, HEIGHT);

		// Create a tabbedPane and add layouts
		JTabbedPane storeTabs = new JTabbedPane();
		storeTabs.setFont(new Font("Sylfaen", Font.PLAIN, 35));

		tblInventory = new JTable();

		stockManagementPane = new JPanel();
		GroupLayout stockLayout = new GroupLayout(stockManagementPane);
		stockManagementPane.setLayout(stockLayout);

		storeTabs.addTab("Stock Management", null, stockManagementPane, null);
		storeTabs.addTab("Inventory", null, tblInventory, null);

		storeTabs.setVisible(true);
		setVisible(true);
		add(storeTabs);
	}

	/**
	 * Creates a new button, sets its properties and adds this as its listener
	 * 
	 * @param text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return button
	 */
	private JButton newButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.addActionListener(this);
		add(button);
		return button;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnName) {
			System.out.println(store.getName());
		} else if (e.getSource() == btnCapital) {
			System.out.println(store.capital());
		}
	}
}