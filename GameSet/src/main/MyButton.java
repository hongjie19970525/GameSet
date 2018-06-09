package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.JButton;

class MyButton extends JButton{
	private static final long serialVersionUID = 1L;
	private Shape shape = null;
	private Color quit = new Color(0, 0, 228);// 离开时颜色
	public MyButton(String s) {
		super(s);
	//addMouseListener(this);
		setContentAreaFilled(false);// 是否显示外围矩形区域 选否
 	}
	public void paintComponent(Graphics g) {
		g.setColor(quit);
  //填充圆角矩形区域 也可以为其它的图形
		g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
				20, 20);
  //必须放在最后 否则画不出来
		super.paintComponent(g);
 }
 public void paintBorder(Graphics g) {
  //画边界区域
	 	g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1,
	 			20, 20);
 	}
}