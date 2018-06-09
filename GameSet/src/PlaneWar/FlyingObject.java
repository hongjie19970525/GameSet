package PlaneWar;

import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	//处理越界
	public abstract boolean outOfBounds();
	
	//物体移动1步
	public abstract void step();
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	//被击中
		public boolean shootBy(Bullet bullet){
			int x = bullet.x;
			int y = bullet.y;
			return this.x<x && x<this.x+width && this.y<y && y<this.y+height;
		}
}
