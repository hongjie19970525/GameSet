package PlaneWar;

public class Bullet extends FlyingObject{
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		this.image = ShootGame.bullet;
	}
	
	private int speed = 3;

	@Override
	public void step() {
		// TODO Auto-generated method stub
		y -= speed;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return y<-height;
	}

}
