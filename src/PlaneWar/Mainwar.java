package PlaneWar;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import main.MainFrame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Mainwar {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainwar window = new Mainwar();
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
	public Mainwar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("PlaneWar");
		frame.setSize(400,654);
		frame.setLocationRelativeTo(null);
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		JPanel panelhelp=new JPanel();
		panelhelp.setBounds(0,0,400,654);
		panelhelp.setVisible(false);
		panelhelp.setLayout(null);
		panel.setBounds(0, 0, 400, 654);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panelhelp);
		panel.setLayout(null);
		
		
		/**
		 * panelhelp的布局
		 */
		
		JButton helpback=new JButton("BACK");
		JTextArea jta=new JTextArea();
		jta.setText("these are the voyage of \n the starship ENTERPRICE");
		jta.setBounds(110, 100, 150, 150);
		helpback.setBounds(150, 550, 100, 50);
		panelhelp.add(helpback);
		panelhelp.add(jta);
		
		
		JButton btnGamestart = new JButton("GameStart");
		btnGamestart.setBounds(145, 96, 104, 23);
		panel.add(btnGamestart);
		
		JButton btnHelp = new JButton("HELP");
		btnHelp.setBounds(145, 285, 104, 23);
		panel.add(btnHelp);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(145, 453, 104, 23);
		panel.add(btnBack);
		
		
		/**
		 * 触发事件
		 */
		helpback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(true);
				panelhelp.setVisible(false);
			}
		});
		
		
		btnGamestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ShootGame game=new ShootGame();
				game.main(null);
				frame.dispose();
			}
		});
		
		btnHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(false);
				panelhelp.setVisible(true);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainFrame mf;
				try {
					mf = new MainFrame();
					mf.main(null);
					frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

}
