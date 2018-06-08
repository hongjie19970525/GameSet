package main;
import game2048.Main2048;
import PlaneWar.ShootGame;
import PlaneWar.Mainwar;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainFrame {
	private JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() throws IOException {
		initialize();
	}

	public void initialize() throws IOException {
		
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setTitle("GameSelect");
		frame.getContentPane().setForeground(Color.CYAN);
		frame.setSize(450,580);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    Image image = ImageIO.read(new File("Images/background1.jpeg"));
		ImageIcon background = new ImageIcon(image);
        
        
        
        
		/**2048 */
		MyPanel panel=new MyPanel(0,0,104,67,"Images/2048.png");		
		panel.setBounds(314, 31, 104, 67);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		//frame.getContentPane().add(panel);
		
		MyButton Button1 = new MyButton("StartGame");
		Font f1=new Font("华文行楷",Font.BOLD,13);
		Button1.setFont(f1);
		Button1.setForeground(Color.CYAN);
		
		Button1.setBounds(314, 110, 104, 23);
		Button1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main2048 m2048;
				try {
					m2048 = new Main2048();
					m2048.main(null);	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}								
			    frame.dispose();				
			}
		});
		frame.getContentPane().add(Button1);
		
		
		/** PlaneWar */
		MyButton Button2 = new MyButton("StartGame");
		
		Font f2=new Font("华文行楷",Font.BOLD,13);
		Button2.setFont(f2);
		Button2.setForeground(Color.CYAN);
		Button2.setBounds(58, 110, 104, 23);
		Button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Mainwar mainwar;
				try {
					mainwar = new Mainwar();
					mainwar.main(null);
					
				    frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				
			}
		});
		frame.getContentPane().add(Button2);
		
		MyPanel panel_1 = new MyPanel(0,0,104,67,"Images/airplane.png");
		panel_1.setBounds(58, 31, 104, 67);
		frame.getContentPane().add(panel_1);
		
		JLabel backgroundLabel = new JLabel("");
		JPanel panelback=new JPanel();
		panelback.setBounds(0,0,450,580);
		panelback.setLayout(null);
		backgroundLabel.setBounds(0, 0, 450,580);
		backgroundLabel.setIcon(background);      
		panelback.add(backgroundLabel);
		frame.getContentPane().add(panelback);
		
		
	}		
}