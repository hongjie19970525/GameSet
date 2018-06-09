package game2048;

import java.awt.Color;
import java.awt.Component;
import main.MainFrame;


import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main2048 {
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
					Main2048 window = new Main2048();
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
	public Main2048() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException{
		//frame参数设置
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("2048");
		frame.setSize(450,500);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	
		/**
		 * Main2048的布局
		 */
		JPanel panel = new JPanel();
		JPanel jpanelhelp=new JPanel();
		panel.setBounds(0, 0, 450, 500);
		panel.setLayout(null);
		jpanelhelp.setLayout(null);
		panel.setBackground(null);
		panel.setOpaque(false);
		jpanelhelp.setBackground(null);
		jpanelhelp.setOpaque(false);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(jpanelhelp);
		Mybutton ButtonStart = new Mybutton("GameStart",Color.ORANGE);
		
		ButtonStart.setBounds(164, 91, 97, 23);
		panel.add(ButtonStart);
		Mybutton ButtonHelp = new Mybutton("HELP",Color.ORANGE);
		ButtonHelp.setBounds(191, 237, 62, 23);
		panel.add(ButtonHelp);		
		Mybutton ButtonBack = new Mybutton("BACK",Color.ORANGE);
		ButtonBack.setBounds(191, 380, 62, 23);
		panel.add(ButtonBack);
		
		/**
		 * jpanelhelp的布局
		 */
		Mybutton jhelpback=new Mybutton("BACK",Color.ORANGE);
		jhelpback.setBounds(175, 400, 100, 50);
		jpanelhelp.setBounds(0, 0, 450, 500);
		jpanelhelp.setVisible(false);  //在初始状态，panel的visiable为false,为的是能让主panel显示，在鼠标监听事件中更改visiable值
		JTextArea jta=new JTextArea();
		jta.setText(str);
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		jta.setBackground(null);
		jta.setOpaque(false);
		Font f=new Font("Ariel",Font.BOLD,15);
		jta.setBounds(140, 120, 190, 190);
		jta.setForeground(new Color(0,0,128));
		jta.setFont(f);
		jpanelhelp.add(jta);
		jpanelhelp.add(jhelpback);
		
		//添加位于最底层的GIF背景
		Gif gif=new Gif(450,500);		
		frame.getContentPane().add(gif);		
		
		
		/**
		 * 鼠标监听事件
		 */
		jhelpback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jpanelhelp.setVisible(false);
				panel.setVisible(true);
			}
		});
		
		ButtonHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(false);
				jpanelhelp.setVisible(true);
				
			}

			
		});
		ButtonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Game game=new Game();
				game.main(null);
				frame.dispose();

			}
		});
		
		ButtonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					MainFrame mf=new MainFrame();
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
