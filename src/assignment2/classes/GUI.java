package assignment2.classes;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The interface for the program
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class GUI extends JFrame implements ActionListener, Runnable {

	private static final long serialVersionUID = -8954008955048531845L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private static Store store;
	private JButton btnName, btnCapital;

	/**
	 * Class constructor
	 * 
	 * @param title
	 * @throws HeadlessException
	 */
	public GUI(String title) throws HeadlessException {
		super(title);
		store = Store.makeStore("UMart");
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
		JButton button = new JButton();
		button.setText(text);
		button.setBounds(x, y, width, height);
		button.addActionListener(this);
		add(button);
		return button;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		setLayout(null); // This allows manual adjustment of the elements.
							// Without this the buttons take up the whole screen no matter what
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(WIDTH, HEIGHT);

		btnName = newButton("Store name", 0, 0, 100, 45);
		btnCapital = newButton("Store capital", 0, 50, 120, 30);
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
			System.out.println(store.getCapital());
		}
	}

	/**
	 * Program entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new GUI("Inventory Manager Tycoon"));
	}

}
