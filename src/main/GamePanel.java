package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import ui.Board;
import utility.KeyHandler;
import utility.MouseHandler;

public class GamePanel extends JPanel implements Runnable {
	
	private final KeyHandler keyHandler = new KeyHandler(this);
	private final MouseHandler mouseHandler = new MouseHandler(this);
	
	private final int tileSize = 64;
	private final int maxScreenCol = 8;
	private final int maxScreenRow = 8;
	private final int screenWidth = getTileSize() * maxScreenCol;
	private final int screenHeight = getTileSize() * maxScreenRow;
	
	private final int FPS = 60;
	
	private Board board = new Board(this);
	
	Thread gameThread;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true); //Enables better rendering performance
		
		this.setBackground(Color.black);
		
		this.addKeyListener(keyHandler);
		this.addMouseListener(mouseHandler); 
		this.addMouseMotionListener(mouseHandler);
		
		this.setFocusable(true);
		
	}
	
	public void setUpGame() {
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime(); //Checks current time
			delta += (currentTime - lastTime) / drawInterval; 
			lastTime = currentTime;
			
			if(delta >= 1) {
				//Update
				update();
				//Draw
				repaint(); //Will call the paintComponent()
				delta--;
			}
		}	
	}
	
	public void update() {
	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Change graphics to graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
		board.draw(g2);
		
		g2.dispose(); //Save memory after the drawing is created
	}

	public KeyHandler getKeyHandler() {
		return keyHandler;
	}

	public int getMaxScreenCol() {
		return maxScreenCol;
	}

	public int getMaxScreenRow() {
		return maxScreenRow;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getTileSize() {
		return tileSize;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	
}
