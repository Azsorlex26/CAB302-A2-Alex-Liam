package assignment2.classes;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {	
	public static void main(String[] args) {
		JFrame appWindow = new JFrame("Inventory Manager Tycoon");
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.setVisible(true);
		
		JLabel label = new JLabel("Hello World");
		appWindow.add(label);
		
		appWindow.setSize(640, 480);
	}
}