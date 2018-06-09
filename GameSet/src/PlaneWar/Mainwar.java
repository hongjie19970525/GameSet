package PlaneWar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.Collator;

import main.MainFrame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Mainwar {
	String str="These are the voyage of the starship Enterprice. Her continie mission is "
			+"to explore strange new worlds to seek out the new life and new civilizations "
			+"to boldy go where no one has gone before";
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
	 * @throws IOException 
	 */
	public Mainwar() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("PlaneWar");
		frame.setSize(400,654);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Image image = ImageIO.read(new File("Images/background3.jpg"));
		ImageIcon background = new ImageIcon(image);
		
		
		JPanel panel = new JPanel();
		JPanel panelhelp=new JPanel();
		panelhelp.setBounds(0,0,400,654);
		panelhelp.setVisible(false);//在初始状态，panel的visiable为false,为的是能让主panel显示，在鼠标监听事件中更改visiable值
		panelhelp.setLayout(null);
		panel.setBounds(0, 0, 400, 654);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(panelhelp);

		
		panel.setBackground(null);
		panel.setOpaque(false);
		panelhelp.setBackground(null);
		panelhelp.setOpaque(false);
		
		//添加动态效果
		Gif gif=new Gif(frame.getWidth(),frame.getHeight());
		frame.getContentPane().add(gif);
		
		
		/**
		 * panelhelp的布局
		 */
		
		Mybutton helpback=new Mybutton("BACK",Color.GRAY);
		JTextArea jta=new JTextArea();
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		jta.setBackground(null);
		jta.setOpaque(false);
		jta.setText(str);
		jta.setBounds(140, 120, 130, 190);
		Font f=new Font("黑体",Font.BOLD,13);
		jta.setFont(f);
		jta.setForeground(new Color(127,255,170));
		helpback.setBounds(150, 550, 100, 50);
		panelhelp.add(helpback);
		panelhelp.add(jta);
		
		
		Mybutton btnGamestart = new Mybutton("GameStart",Color.GRAY);
		btnGamestart.setForeground(Color.ORANGE);
		btnGamestart.setBounds(145, 96, 104, 23);
		panel.add(btnGamestart);
		
		Mybutton btnHelp = new Mybutton("HELP",Color.GRAY);
		btnHelp.setForeground(Color.ORANGE);
		btnHelp.setBounds(145, 285, 104, 23);
		panel.add(btnHelp);
		
		Mybutton btnBack = new Mybutton("BACK",Color.GRAY);
		btnBack.setForeground(Color.ORANGE);
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
