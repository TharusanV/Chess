package utility;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;
import pieces.Piece;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private GamePanel gamePanel;

    public MouseHandler(GamePanel gp) {
        this.gamePanel = gp;
    }
	
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Left mouse button
            int x = e.getX();
            int y = e.getY();
            
            
            int col = (int) Math.floor(x / gamePanel.getTileSize());
            int row = (int) Math.floor(y / gamePanel.getTileSize());
            
            if(gamePanel.getSelectedPiece() == null) {
            	gamePanel.attemptChangeSelectedPiece(col, row);
            }
            else {
            	//Move Piece
            	if(gamePanel.getSelectedPiece().getAllPossibleMovesList().contains(new Point(col, row))){
            		gamePanel.getBoard().movePiece(col, row);
            	}
            	else{ //Change Piece
            		gamePanel.attemptChangeSelectedPiece(col, row);
            	}
            	
            }
            
            //System.out.println(gamePanel.getSelectedPiece());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // MouseMotionListener Methods (optional, if you want mouse movement tracking)
    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

}
