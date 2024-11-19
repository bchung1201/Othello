import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * TileButton class to attach a button to a tile.
 * @author Bryan Chung
 */
public class TileButton extends JButton {
    private Tile parent;

    /**
     * Default constructor for a tile button attached to a tile.
     * @param t The tile to attach the button to.
     */
    public TileButton(Tile t) {
        super();
        parent = t;;
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clicked();
            }
        } );
    }

    /**
     * Executes a command for the tile if the button is clicked.
     */
    public void clicked(){
        parent.clicked();
    }
}