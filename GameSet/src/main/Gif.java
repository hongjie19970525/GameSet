package main;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Gif extends JPanel {
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon=new ImageIcon("Images/background.gif");
		g.drawImage(icon.getImage(), 0, 0, 450,580,this);
	}
}
