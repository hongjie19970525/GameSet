package game2048;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Help {

	private JFrame frame;
	private JTextField txtTheseAreThe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help window = new Help();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Help() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtTheseAreThe = new JTextField();
		txtTheseAreThe.setText("These are the voyage of the starship ENTERPRICE\nHer continue mission \nis to explore strange new worlds\nto seek out the new lif and new civilization\nto bodly go where no one\nhas gone before!");
		txtTheseAreThe.setBounds(53, 50, 333, 183);
		frame.getContentPane().add(txtTheseAreThe);
		txtTheseAreThe.setColumns(10);
	}
}
