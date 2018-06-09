package game2048;
import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Game extends JPanel{
	static JFrame game = new JFrame();
	private static final long serialVersionUID = -2147680159621095414L;
	static final Color BG_COLOR = new Color(0xbbada0);
	static final String FONT_NAME = "Arial";
	static final int TILE_SIZE = 64;
	static final int TILES_MARGIN = 16;
	static final int MOVE_TIME = 80;
	static final int MOVE_TIMES = 20;
	static final int ENLARGE_SIZE = 15;
	static final int ENLARGE_TIME = 50;
	static final int ENLARGE_TIMES = 10;
	boolean isWin,isLose,move,merge;
	boolean animateOver = true;
	Tile[][] tiles,resultTiles;
	List<Tile> moveList,mergeList;
	Image image;
	int max;
	int score,xx,yy;
	
	public Game(){
	    setFocusable(true);
	    addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		super.keyPressed(e);
	    		moveList = new ArrayList<Tile>(16);
	    		mergeList = new ArrayList<Tile>(16);
	    		System.out.println(" "+KeyEvent.getKeyText(e.getKeyCode())+"\n") ;
	    		//System.out.println(KeyEvent.getKeyText(e.getKeyCode())+"\n") ;
	    		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					resetGame();
				}
	    		 
	    		if (e.getKeyCode() == KeyEvent.VK_SPACE && !isLose) {
					isWin = false;
				}
	    		if(e.getKeyCode()==KeyEvent.VK_E) {
	    			game.dispose();
	    			Main2048.main(null);;
	    		}

				if (!isWin && !isLose) {
					resultTiles = newTile(tiles);
					move = false;merge = false;
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						moveLeft();
						break;
					case KeyEvent.VK_RIGHT:
						moveRight();
						break;
					case KeyEvent.VK_UP:
						moveUp();
						break;
					case KeyEvent.VK_DOWN:
						moveDown();
						break;
					}	
					setDefaultTile(resultTiles);
					tiles = newTile(resultTiles);
					
				}

				if (!canMove()) {
					isLose = true;
				}

				repaint();
	    	}
	    	});
	    resetGame();
	}
	boolean canMove(){
		if (!isFull())
			return true;
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				if ((i < 3 && tiles[i][j].value == tiles[i+1][j].value)
						|| ((j < 3) && tiles[i][j].value == tiles[i][j+1].value)) 
					return true;
		return false;
	}
	
	
	protected void addTile() {
		List<Tile> list = availableSpace();
		if (!availableSpace().isEmpty()) {
			int index = (int) (Math.random() * list.size()) % list.size();
			Tile emptyTile = list.get(index);
			emptyTile.value = Math.random() < 0.5 ? 2 : 4;
		}
	}
	protected List<Tile> availableSpace() {
		List<Tile> list = new ArrayList<Tile>(16);
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				if (resultTiles[i][j].isEmpty())
					list.add(resultTiles[i][j]);
		return list;
	}
	protected boolean isFull() {
		return availableSpace().size() == 0;
	}
	protected void resetGame(){
		score = 0;
		isWin = false;
		isLose = false;
		max = 0;
		resultTiles = newTile(null);
		addTile();
		addTile();
		tiles = newTile(resultTiles);
	}
	protected void moveLeft(){
		boolean isMoved;
		for (int x=1;x<4;x++)
			for (int y=0;y<4;y++)
				if (!resultTiles[x][y].isEmpty())
				{
					xx = x;yy = y;isMoved = false;
					while (xx > 0 && resultTiles[xx-1][yy].isEmpty())
					{
						xx --;
						isMoved = true;
					}
					if (isMoved && (xx == 0 || !canMerge(x, y, "left")))
					{
						doMove(x, y, "left");
						tiles[x][y].setMove(xx-x);
					}
					else if (canMerge(x, y, "left"))
						{
							doMerge(x, y, -1, 0, "left");
							tiles[x][y].setMove(xx-x-1);
						}
				}
		showAnimate();
	}
	protected void moveRight(){
		boolean isMoved;
		for (int x=2;x>=0;x--)
			for (int y=0;y<4;y++)
				if (!resultTiles[x][y].isEmpty())
				{
					xx = x;yy = y;isMoved = false;
					while (xx < 3 && resultTiles[xx+1][yy].isEmpty())
					{
						xx ++;
						isMoved = true;
					}
					if (isMoved && (xx == 3 || !canMerge(x, y, "right")))
					{
						doMove(x, y, "right");
						tiles[x][y].setMove(xx-x);
					}
					else if (canMerge(x, y, "right"))
						{
							doMerge(x, y, 1, 0, "right");
							tiles[x][y].setMove(xx-x-1);
						}
				}
		showAnimate();
	}
	protected void moveUp(){
		boolean isMoved;
		for (int y=1;y<4;y++)
			for (int x=0;x<4;x++)
				if (!resultTiles[x][y].isEmpty())
				{
					xx = x;yy = y;isMoved = false;
					while (yy > 0 && resultTiles[xx][yy-1].isEmpty())
					{
						yy --;
						isMoved = true;
					}
					if (isMoved && (yy == 0 || !canMerge(x, y, "up")))
					{
						doMove(x, y, "up");
						tiles[x][y].setMove(yy-y);
					}
					else if (canMerge(x, y, "up"))
						{
							doMerge(x, y, 0, -1, "up");
							tiles[x][y].setMove(yy-y+1);
						}
				}
		showAnimate();
	}
	protected void moveDown(){
		boolean isMoved;
		for (int y=2;y>=0;y--)
			for (int x=0;x<4;x++)
				if (!resultTiles[x][y].isEmpty())
				{
					xx = x;yy = y;isMoved = false;
					while (yy < 3 && resultTiles[xx][yy+1].isEmpty())
					{
						yy ++;
						isMoved = true;
					}
					if (isMoved && (yy == 3 || !canMerge(x, y, "down")))
					{
						doMove(x, y, "down");
						tiles[x][y].setMove(yy-y);
					}
					else if (canMerge(x, y, "down"))
						{
							doMerge(x, y, 0, 1, "down");
							tiles[x][y].setMove(yy-y+1);
						}
				}
		showAnimate();
	}
	protected void doMove(int x,int y,String direction) {
		resultTiles[xx][yy] = new Tile(resultTiles[x][y].value);
		resultTiles[x][y] = new Tile();
		tiles[x][y].setDirection(direction);
		moveList.add(tiles[x][y]);
		move = true;
	}
	protected void doMerge(int x,int y,int tX,int tY,String direction) {
		resultTiles[xx+tX][yy+tY].setMerged();
		resultTiles[xx+tX][yy+tY].doubleValue();
		score += resultTiles[xx+tX][yy+tY].value;
		resultTiles[x][y] = new Tile();
		tiles[x][y].setDirection(direction);
		mergeList.add(resultTiles[xx+tX][yy+tY]);
		merge = true;
		if (resultTiles[xx+tX][yy+tY].value > max) 
		{
			max = resultTiles[xx+tX][yy+tY].value;
			if (max >=2048)
				isWin = true;
		}	
	}
	protected void showAnimate() {
		if (merge)
		{
			new AePlayWave("Audio/merge.wav").start();
			showMove();
			showMerge();
		}
		else if (move)
		{
			new AePlayWave("Audio/move.wav").start();
			showMove();
		}
		if (move || merge) addTile();
	}
	protected boolean canMerge(int x,int y,String direction) {
		switch (direction) {
		case "left":
			if (resultTiles[x][y].value == resultTiles[xx-1][yy].value && !resultTiles[xx-1][yy].isMerged)
				return true;
			break;
		case "right":
			if (resultTiles[x][y].value == resultTiles[xx+1][yy].value && !resultTiles[xx+1][yy].isMerged)
				return true;
			break;
		case "up":
			if (resultTiles[x][y].value == resultTiles[xx][yy-1].value && !resultTiles[xx][yy-1].isMerged)
				return true;
			break;
		case "down":
			if (resultTiles[x][y].value == resultTiles[xx][yy+1].value && !resultTiles[xx][yy+1].isMerged)
				return true;
			break;
		}
		return false;
	}
	protected Point getPoint(Tile[][] f,Tile t) {
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				if (f[i][j] == t)
					return new Point(i,j);
		return new Point(0,0);
	}
	protected void setDefaultTile(Tile[][] t){
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				t[i][j].setDefault();
	}
	protected Tile[][] newTile(Tile[][] old){
		Tile[][] t = new Tile[4][4];
		for (int i=0;i<4;i++)
			t[i] = new Tile[4];
		for (int i=0;i<4;i++)
			for (int j=0;j<4;j++)
				if (old == null)
					t[i][j] = new Tile();
				else
					t[i][j] = new Tile(old[i][j].value);
		return t;
	}
	@Override
	public void paint(Graphics g) {//鍒跺浘
		super.paint(g);
		if (animateOver)//娓告垙缁撴潫
		{
			g.setColor(BG_COLOR);
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);//濉厖鐭╁舰
			for (int i=0;i<4;i++)
				for (int j=0;j<4;j++)
					drawTile(g,resultTiles[i][j],i,j,0,0,0);
		}
	}
	protected void showMove(){
		animateOver = false;
		Graphics gg = getGraphics();
		image = this.createImage(this.getSize().width, this.getSize().height);
		Graphics g = image.getGraphics();
		int k = 0;
		long time1 = new Date().getTime(),time2;
		while(k < MOVE_TIMES)
		{
			time2 = new Date().getTime();
			if (time2 - time1 < MOVE_TIME / MOVE_TIMES) continue;
			else time1 = time2;
			g.setColor(BG_COLOR);
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);
			for (int i=0;i<4;i++)
				for (int j=0;j<4;j++)
					if (tiles[i][j].getDirection().equals("none"))
						drawTile(g,tiles[i][j],i,j,0,0,0);
					else 
						drawTile(g,new Tile(),i,j,0,0,0);
			for (int i=0;i<moveList.size();i++)
			{
				Tile t = moveList.get(i);
				switch (t.getDirection()) {
					case "left":
					case "right":
						drawTile(g,t,getPoint(tiles, t).x,getPoint(tiles, t).y,t.getMove() * k,0,0);
						break;
					case "up":
					case "down":
						drawTile(g,t,getPoint(tiles, t).x,getPoint(tiles, t).y,0,t.getMove() * k,0);
						break;
				}
			}
			gg.drawImage(image, 0, 0, null);
			k ++;
		}
		animateOver = true;
	}
	protected void showMerge(){

		animateOver = false;
		Graphics gg = getGraphics();
		image = this.createImage(this.getSize().width, this.getSize().height);
		Graphics g = image.getGraphics();
		int larger = ENLARGE_SIZE * 2 / ENLARGE_TIMES;
		int expand;
		int k = 0;
		long time1 = new Date().getTime(),time2;
		while (k < ENLARGE_TIMES)
		{
			time2 = new Date().getTime();
			if (time2 - time1 < ENLARGE_TIME / ENLARGE_TIMES) continue;
			else time1 = time2;
			if (k >= ENLARGE_TIMES / 2)
				expand = ENLARGE_TIMES - k - 1;
			else
				expand = k;
			g.setColor(BG_COLOR);
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);
			for (int i=0;i<4;i++)
				for (int j=0;j<4;j++)
					if (!resultTiles[i][j].isMerged)
						drawTile(g,resultTiles[i][j],i,j,0,0,0);
					else 
						drawTile(g,new Tile(),i,j,0,0,0);
			for (int i=0;i<mergeList.size();i++)
			{
				Tile t = mergeList.get(i);
				drawTile(g,t,getPoint(resultTiles, t).x,getPoint(resultTiles, t).y,
						0,0,expand * larger);
				
			}
			gg.drawImage(image, 0, 0, null);
			k ++;
		}
		animateOver = true;
	}
	protected void drawTile(Graphics g2,Tile tile,int x,int y,int moveX,int moveY,int enlarge) {
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		int value = tile.value;
		int xOffset = offsetCoors(x);
		int yOffset = offsetCoors(y);
		g.setColor(tile.getBackground());
		g.fillRoundRect(xOffset + moveX - enlarge, yOffset + moveY - enlarge,
				TILE_SIZE + enlarge * 2, TILE_SIZE + enlarge * 2, 14, 14);
		g.setColor(tile.getForeground());
		final int size = value < 100 ? 36 : value < 1000 ? 32 : value < 10000 ? 24 : 18;
		final Font font = new Font(FONT_NAME, Font.BOLD, size);
		g.setFont(font);

		String s = String.valueOf(value);
		final FontMetrics fm = getFontMetrics(font);

		final int w = fm.stringWidth(s);
		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

		if (value != 0)
			g.drawString(s, xOffset + (TILE_SIZE - w) / 2 + moveX, yOffset + TILE_SIZE
					- (TILE_SIZE - h) / 2 - 2 + moveY);
		
		if (isWin || isLose) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(78, 139, 202));
			g.setFont(new Font(FONT_NAME, Font.BOLD, 48));
			if (isWin) {
				Tile t = new Tile(max);
				g.setColor(t.getBackground());
				if (max < 10000)
					g.drawString(max+"", 115, 130);
				else 
					g.drawString(max+"", 105, 130);
				g.setColor(new Color(78, 139, 202));
				g.drawString("You won!", 68, 200);
				g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
				g.setColor(new Color(128, 128, 128, 128));
				g.drawString("Press Space to contine", 80, getHeight() - 40);
			}
			if (isLose) {
				g.drawString("Game over!", 35, 130);
				g.drawString("You lose!", 64, 200);
				g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
				g.setColor(new Color(128, 128, 128, 128));
				g.drawString("Press ESC to play again", 80, getHeight() - 40);
			}
		}
		g.setColor(new Color(0x776e65));
		g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
		g.drawString("Score: " + score, 200, 365);
	}
	protected static int offsetCoors(int arg) {
		return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
	}
	public class Tile {
		int value;
		boolean isMerged;
		String moveDirection;
		int movePixels;
		public Tile() {
			this(0);
		}

		public Tile(int num) {
			value = num;
			setDefault();
		}

		public boolean isEmpty() {
			return value == 0;
		}

		public Color getForeground() {
			return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
		}
		
		public void setMove(int s) {
			movePixels = s * (TILE_SIZE + TILES_MARGIN) / MOVE_TIMES;
		}
		
		public int getMove() {
			return movePixels;
		}
		
		public void setDirection(String d) {
			moveDirection = new String(d);
		}
		
		public String getDirection() {
			return moveDirection;
		}
		
		public void setMerged() {
			isMerged = true;
		}
		
		public void doubleValue() {
			value *= 2;
		}
		
		public void setDefault() {
			isMerged = false;
			moveDirection = new String("none");
			movePixels = 0;
		}
		
		public Color getBackground() {//鏀瑰彉棰滆壊
			switch (value) {
				case 2:
					return new Color(0xeee4da);
				case 4:
					return new Color(0xede0c8);
				case 8:
					return new Color(0xf2b179);
				case 16:
					return new Color(0xf59563);
				case 32:
					return new Color(0xf67c5f);
				case 64:
					return new Color(0xf65e3b);
				case 128:
					return new Color(0xedcf72);
				case 256:
					return new Color(0xedcc61);
				case 512:
					return new Color(0xedc850);
				case 1024:
					return new Color(0xedc53f);
				case 2048:
					return new Color(0xedc22e);
				case 4096:
					return new Color(0x65da92);
				case 8192:
					return new Color(0x5abc65);
				case 16384:
					return new Color(0x248c51);
				default:
					return new Color(0xcdc1b4);
			}
		}
	}
	public class AePlayWave extends Thread { 	 
	    private String filename;
	    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 

	    public AePlayWave(String wavfile) { 
	        filename = wavfile;
	    } 
	    	    
	    public void run() { 
	        File soundFile = new File(filename); 
	        AudioInputStream audioInputStream = null;
	        try { 
	            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
	        } catch (UnsupportedAudioFileException e1) { 
	            e1.printStackTrace();
	            return;
	        } catch (IOException e1) { 
	            e1.printStackTrace();
	            return;
	        } 
	 
	        AudioFormat format = audioInputStream.getFormat();
	        SourceDataLine auline = null;
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
	 
	        try { 
	            auline = (SourceDataLine) AudioSystem.getLine(info);
	            auline.open(format);
	        } catch (LineUnavailableException e) { 
	            e.printStackTrace();
	            return;
	        } catch (Exception e) { 
	            e.printStackTrace();
	            return;
	        } 

	        auline.start();
	        int nBytesRead = 0;
	        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
	 
	        try { 
	            while (nBytesRead != -1) { 
	                nBytesRead = audioInputStream.read(abData, 0, abData.length);
	                if (nBytesRead >= 0) 
	                    auline.write(abData, 0, nBytesRead);
	            } 
	        } catch (IOException e) { 
	            e.printStackTrace();
	            return;
	        } finally { 
	            auline.drain();
	            auline.close();
	        } 
	    } 
	} 
	
		
	public static void main(String[] args) {
	    game.setTitle("2048");
	    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    game.setSize(340, 400);
	    game.setResizable(false);
	    game.setLocationRelativeTo(null);
	    game.add(new Game());
	    game.setVisible(true);
//	   
	    
	}
}
