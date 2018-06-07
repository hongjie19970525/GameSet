package main;

import java.awt.Dimension;

public class Mid {
	java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
	Dimension screenSize=toolkit.getScreenSize();
	int screenWidth = screenSize.width/2; // 获取屏幕的宽
	int screenHeight = screenSize.height/2; // 获取屏幕的高
	public int returnWidth() {
		return screenWidth;
	}
	public int returnHeight() {
		return screenHeight;
	}
}
