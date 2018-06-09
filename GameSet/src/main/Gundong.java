package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gundong extends JPanel {
	private String message="";
	private int xZuoBiao=0;
	private int yZuoBiao=450;
	
	public Gundong(String message) {
		this.message=message;
		Timer timer=new Timer(100,new TimerListener());
		timer.start();
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		if(xZuoBiao>this.getWidth()){
			xZuoBiao=-20;
			}
		
			xZuoBiao+=5;
			g.drawString(message, xZuoBiao, yZuoBiao);
		}
	
	class TimerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			repaint();
			}
		}	
}
