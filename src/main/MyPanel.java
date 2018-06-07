package main;



import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private String URL="";
	private int pointX=0;
	private int pointY=0;
	private int X=0;
	private int Y=0;
	Image image=null;
	public MyPanel(int pointX, int pointY, int x, int y, String uRL) {
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		X = x;
		Y = y;
		URL = uRL;
	}
	
    public void paint(Graphics g){
        try {
            image=ImageIO.read(new File(URL));
            g.drawImage(image, pointX, pointY, X, Y, null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
