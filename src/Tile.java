import java.awt.*;
import java.awt.Frame;
import javax.swing.*;

/**
 * Tile class that draws one square on the Othello board.
 * @author Bryan Chung
 */
public class Tile extends JPanel {
    private final TileButton button;
    private final OthelloFrame parent;
    private final int i;
    private final int j;
    private int color;

    /**
     * Default constructor for a Tile attached to a frame.
     * @param posI The row it exists in in the frame.
     * @param posJ The column it exists in in the frame.
     * @param p The frame it is attached to.
     */
    public Tile(int posI, int posJ, OthelloFrame p) {
        super();
        i = posI;
        j = posJ;
        parent = p;
        button = new TileButton(this);
        button.setPreferredSize(new Dimension(75, 75));
        button.setBackground(new Color(255,255,255,0));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        add(button);
    }

    /**
     * Performs an action when the TileButton attached to the Tile is clicked.
     */
    public void clicked(){
        parent.performMove(i,j);
    }

    /**
     * Changes the color of the Tile.
     * @param newColor The new color to set (-2 = a valid move for black, -1 = a black square, 0 = blank, 1 = a white
     * square, 2 = a valid move for white)
     */
    public void setColor(int newColor){
        color = newColor;
    }

    /**
     * Paints the Tile to show what value it has on the board.
     * @param g The graphics object to protect.
     */
    public void paintComponent(Graphics g) {
        Color myGreen = new Color(50, 115, 69, 255);
        Color validWhiteMove = new Color (112, 162, 117, 150);
        Color validBlackMove = new Color (27, 61, 37, 150);
        g.setColor(myGreen);
        g.fillRect(-1, -1, getWidth(), getHeight());
        if (color == -1) {
            g.setColor(Color.black);
            g.fillOval(14, 14, 60, 60);
            g.setColor(Color.black);
            g.drawOval(14, 14, 60, 60);
        }
        if (color == 0) {
            g.setColor(myGreen);
            g.fillOval(14, 14, 60, 60);
            g.setColor(myGreen);
            g.drawOval(14, 14, 60, 60);
        }
        if (color == 1) {
            g.setColor(Color.white);
            g.fillOval(14, 14, 60, 60);
            g.setColor(Color.black);
            g.drawOval(14, 14, 60, 60);
        }
        if (color == 2) {
            g.setColor(validWhiteMove);
            g.fillOval(14, 14, 60, 60);
            g.setColor(validWhiteMove);
            g.drawOval(14, 14, 60, 60);
        }
        if (color == -2) {
            g.setColor(validBlackMove);
            g.fillOval(14, 14, 60, 60);
            g.setColor(validBlackMove);
            g.drawOval(14, 14, 60, 60);
        }
    }
}