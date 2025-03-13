package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Stack;

import javax.swing.JPanel;

import gameLogic.Move;
import pieces.Piece;
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
	private boolean isWhiteTurn = true;
	
	private Stack<Move> moveHistory = new Stack<>();
	
	private boolean playerIsWhite = true;
	private Piece selectedPiece = null;
	
	
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
		board.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Change graphics to graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
		board.draw(g2);
		
		g2.dispose(); //Save memory after the drawing is created
	}

	
	
	public void attemptChangeSelectedPiece(int col, int row) {
		if(board.checkTileForPiece(col, row) != null) {
        	Piece potentialPiece = board.checkTileForPiece(col, row);
        	if(potentialPiece.getColour() == "white" && isPlayerIsWhite()) {
        		setSelectedPiece(potentialPiece);
        	}
        	else if(potentialPiece.getColour() == "black" && isPlayerIsWhite() == false) {
        		setSelectedPiece(potentialPiece);
        	}
        }
	}
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
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

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece selectedPiece) {
		this.selectedPiece = selectedPiece;
	}

	public boolean isPlayerIsWhite() {
		return playerIsWhite;
	}

	public void setPlayerIsWhite(boolean playerIsWhite) {
		this.playerIsWhite = playerIsWhite;
	}

	public Stack<Move> getMoveHistory() {
		return moveHistory;
	}

	public void setMoveHistory(Stack<Move> moveHistory) {
		this.moveHistory = moveHistory;
	}

	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}

	public void setWhiteTurn(boolean isWhiteTurn) {
		this.isWhiteTurn = isWhiteTurn;
	}
	
	
	
}
