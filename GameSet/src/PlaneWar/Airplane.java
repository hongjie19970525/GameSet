package PlaneWar;

public class Airplane extends FlyingObject implements Enemy {
	public Airplane(){
		image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		x = (int) (Math.random()*(ShootGame.WIDTH - width));
//		x = 100;
//		y = 200;
	}

	private int speed = 2;
	
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		y += speed;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return y>ShootGame.HEIGHT;
	}
}
