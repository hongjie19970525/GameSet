package PlaneWar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootGame extends JPanel{
	
	private int state;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 654;
	
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	
	private FlyingObject[] flyings = {};
	private Bullet bullets[] = {};
	private Hero hero = new Hero();
	private Timer timer = new Timer();
	private int intervel = 1000/100;//时间间隔
	private int score = 0;//得分
	
	public void paintState(Graphics g){
		switch(state){
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	public ShootGame(){}
	
	//随机生成飞行物
	public static FlyingObject nextOne(){
		Random random = new Random();
		int type = random.nextInt(20);
		if(type == 0){
			return new Bee();
		}else{
			return new Airplane();
		}
	}
	
	static{
		try{
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
			airplane = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	int shootIndex = 0;//射击计数
	public void shootAction(){
		shootIndex++;
		if(shootIndex % 30 == 0){
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length, bs.length);
		}
	}
	
	 int flyEnteredIndex = 0;//飞行物计数
	 /**
	  * 添加飞行物
	  */
	 public void enterAction(){
		 flyEnteredIndex++;
		 if(flyEnteredIndex % 40 == 0){
			 FlyingObject obj = nextOne();
			 flyings = 	Arrays.copyOf(flyings, flyings.length +1);//扩容
			 flyings[flyings.length-1] = obj;
		 }
	 }
	 
	 public void stepAction(){
		 for(FlyingObject fo:flyings){
			 fo.step();
		 }
		 //移动子弹
		 for(Bullet b:bullets){
			 b.step();
		 }
		 hero.step();
	 }
	public void paint(Graphics g){
		g.drawImage(background,0,0,null);
		paintHero(g);//画英雄机
		paintBullets(g);
		paintFlyingObjects(g);
		paintScore(g);
		paintState(g);
	}
	
	public void paintHero(Graphics g){
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	
	public void paintBullets(Graphics g){
		for(Bullet b:bullets){
			g.drawImage(b.getImage(), b.getX(), b.getY(), null);
		}
	}
	
	public void paintFlyingObjects(Graphics g){
		for(FlyingObject fo:flyings){
			g.drawImage(fo.getImage(), fo.getX(), fo.getY(), null);
		}
	}
	
	//子弹与飞行物碰撞检测
	public void bangAction(){
		for(Bullet b:bullets){
			bang(b);
		}
	}
	
	public void bang(Bullet bullet){
		int index = -1;//飞行物的左因
		for(int i=0;i<flyings.length;i++){
			FlyingObject 	obj = flyings[i];
			if(obj.shootBy(bullet)){
				index = i;
				break;
			}
		}
		if(index != -1){
			FlyingObject one = flyings[index];
			FlyingObject temp = flyings[index];
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length-1]=temp;
			flyings = Arrays.copyOf(flyings, flyings.length-1);
			
			//检查one类型：敌人？
			if(one instanceof Enemy){
				Enemy e = (Enemy) one;
				score += e.getScore();
			}
			
			if(one instanceof Award){
				Award a = (Award) one;
				switch(a.getType()){
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();
					break;
				}
			}
		}
	}
	
	public void paintScore(Graphics g){
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 14);
		g.setColor(new Color(0x3A3B3B));
		g.setFont(font);
		g.drawString("SCORE:"+score, x, y);
		y+=20;
		g.drawString("LIFE:"+hero.getLife(), x, y);
	}	
	
	//删除越界飞行物
	public void outOfBoundsAction(){
		int index = 0;
		
		//存储或者的飞行物
		FlyingObject[] flyingLives = new FlyingObject[flyings.length];
		for(FlyingObject f:flyings){
			if(!f.outOfBounds())
				flyingLives[index++] = f;
		}
		flyings = Arrays.copyOf(flyingLives, index);
		
		index = 0;
		
		Bullet[] bulletLives = new Bullet[bullets.length];
		for(Bullet b:bullets){
			if(!b.outOfBounds()){
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index);
	}
	
	public boolean isGameOver(){
		for(int i = 0;i<flyings.length;i++){
			int index = -1;
			FlyingObject obj = flyings[i];
			if(hero.hit(obj)){
				hero.subtractLife();
				hero.setDoubleFire(0);
				index = i;
			}
			if(index != -1){
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length-1];
				flyings[flyings.length -1] = flyings[index];
				
				flyings = Arrays.copyOf(flyings, flyings.length -1);
			}
		}
		return hero.getLife() <= 0;
	}
	
	public void checkGameOverAction(){
		if(isGameOver()){
			state = GAME_OVER;
		}
	}
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Fly");
		ShootGame game = new ShootGame();
		
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.action();
	}
	
	public void action(){
		//鼠标滑动方法
		MouseAdapter l = new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				if(state == RUNNING){
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x, y);
				}
			}
			
			public void mouseEntered(MouseEvent e){//鼠标进入
				if(state== PAUSE)
					state = RUNNING;
			}
			
			public void mouseExited(MouseEvent e){
				if(state != GAME_OVER)
						state =  PAUSE;
			}
			
			public void mouseClicked(MouseEvent e){
				switch(state){
				case START:
					state = RUNNING;
					break;
				case GAME_OVER:
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					hero = new Hero();
					score = 0;
					state = START;
					break;
				}
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		
//		timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				if(state == RUNNING){
					enterAction();
					stepAction();
					shootAction();
					bangAction();
					
					outOfBoundsAction();//删除越界飞行物
					checkGameOverAction();	
				}
				repaint();
			}
		}, intervel, intervel);
	}
	
}
