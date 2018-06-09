package PlaneWar;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Gif extends JPanel {
	int x,y;
	public Gif(int x,int y) {
		this.setBounds(0,0,x,y);
		this.x=x;
		this.y=y;
		this.setLayout(null);
		this.setOpaque(false);
	}
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon=new ImageIcon("Images/background1.gif");
		g.drawImage(icon.getImage(), 0, 0, x,y,this);
	}
}
