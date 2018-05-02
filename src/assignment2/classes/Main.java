package assignment2.classes;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JFrame {	
	public Main() {
		super("Inventory Manager Tycoon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(640, 480);
		
		JButton button = new JButton();
		button.setText("Click here");
		add(button);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You clicked me!");
			}
		});
	}
	
	public static void main(String[] args) {
		new Main();
	}
}