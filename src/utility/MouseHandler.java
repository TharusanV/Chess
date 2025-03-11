package utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

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
            System.out.println("Left Click at: (" + x + ", " + y + ")");
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
