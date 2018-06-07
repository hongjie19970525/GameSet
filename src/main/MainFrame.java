package main;
import game2048.Game;
import PlaneWar.ShootGame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
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

	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("GameSelect");
		frame.getContentPane().setForeground(Color.CYAN);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**2048 */
		MyPanel panel=new MyPanel(0,0,104,67,"Images/2048.png");		
		panel.setBounds(314, 31, 104, 67);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		MyButton Button1 = new MyButton("StartGame");
		Font f1=new Font("华文行楷",Font.BOLD,13);
		Button1.setFont(f1);
		Button1.setForeground(Color.CYAN);
		
		Button1.setBounds(314, 110, 104, 23);
		//Button1.setOpaque(false);
		//Button1.setBorder(null);
		Button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Button1.setBackground(Color.BLUE);
				JFrame game = new JFrame();
			    game.setTitle("2048");
			    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    game.setSize(340, 400);
			    game.setResizable(false);
			    game.setLocationRelativeTo(null);
			    game.getContentPane().add(new Game());
			    game.setVisible(true);
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
				JFrame game = new JFrame();
				ShootGame plane=new ShootGame();
			    game.setTitle("fly");
			    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    game.setSize(400, 654);
			    game.setResizable(false);
			    game.setLocationRelativeTo(null);
			    game.getContentPane().add(plane);
			    game.setVisible(true);
			    frame.dispose();
			    plane.action();
				
			}
		});
		frame.getContentPane().add(Button2);
		
		MyPanel panel_1 = new MyPanel(0,0,104,67,"Images/airplane.png");
		panel_1.setBounds(58, 31, 104, 67);
		frame.getContentPane().add(panel_1);
		
		
	}		
}