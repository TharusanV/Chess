package pieces;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public abstract class Piece {
    private  int pieceID;
    private int positionX;
    private int positionY;
    private Color color;
    private Image image;

    private ImageView imageView;

    public Piece(int pieceID, int positionX, int positionY, Color color, Image image) {
        this.pieceID = pieceID;
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
        this.image = image;
        this.imageView = new ImageView(image);
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView(){
        return this.imageView;
    }

    public void setImageViewPosition(double x, double y, GridPane grid) {
        this.setPositionX((int) x);
        this.setPositionY((int) y);
        GridPane.setRowIndex(this.imageView, (int) y);
        GridPane.setColumnIndex(this.imageView, (int) x);
    }

    public abstract boolean canMove(int x, int y, GridPane board);

    public abstract void possibleMoves(GridPane board);

    public ImageView addImageViewToGridPane(GridPane gridPane, int column, int row) {

        this.getImageView().setFitWidth(100);
        this.getImageView().setFitHeight(100);
        GridPane.setConstraints(this.getImageView(), column, row);
        gridPane.getChildren().add(this.getImageView());
        this.getImageView().setUserData(this);
        return this.getImageView();
    }


    public Piece getPieceAt(int x, int y, GridPane board) {
        for (Node node : board.getChildren()) {
            if (board.getColumnIndex(node) == x && board.getRowIndex(node) == y) {
                if (node instanceof ImageView) {
                    Piece piece = (Piece) node.getUserData();
                    return piece;
                }
            }
        }
        return null;
    }
}
