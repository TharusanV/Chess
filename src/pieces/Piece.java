package pieces;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Piece {
    private  int pieceID;
    private int positionX;
    private int positionY;
    private Color color;
    private boolean isKilled;
    private Image image;

    public Piece(int pieceID, int positionX, int positionY, Color color, Image image) {
        this.pieceID = pieceID;
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
        this.isKilled = false;
        this.image = image;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public abstract boolean canMove(int x, int y);

}
