import javax.swing.*;
import java.awt.*;

/**
 * Tile class that is responsible for creating the visual representation for an othello board.
 * @author Bryan Chung
 */
public class OthelloFrame extends JFrame{
    private Tile[][] board;
    private Othello othello = new Othello();
    private final int WHITE = 1;
    private final int BLACK = -1;

    /**
     * Default constructor for an object of type Frame, with an 8 by 8 grid of Tile objects.
     */
    public OthelloFrame() {
        super("Othello");
        board = new Tile[8][8];
        setVisible(true);
        setResizable(false);
        setSize(750,750);
        setLayout(new GridLayout(8, 8, 2, 2));
        getContentPane().setBackground(Color.black);
        int n = 8;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Tile newTile = new Tile(i, j, this);
                board[i][j] = newTile;
                getContentPane().add(newTile);
            }
        }
        board[3][3].setColor(WHITE);
        board[4][4].setColor(WHITE);
        board[3][4].setColor(BLACK);
        board[4][3].setColor(BLACK);
        board[4][2].setColor(2);
        board[5][3].setColor(2);
        board[2][4].setColor(2);
        board[3][5].setColor(2);
        invalidate();
        validate();
        repaint();
    }

    /**
     * Resets the board back to its default position
     */
    public void resetBoard() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setColor(0);
                board[3][3].setColor(WHITE);
                board[4][4].setColor(WHITE);
                board[3][4].setColor(BLACK);
                board[4][3].setColor(BLACK);
                board[4][2].setColor(2);
                board[5][3].setColor(2);
                board[2][4].setColor(2);
                board[3][5].setColor(2);
                invalidate();
                validate();
                repaint();
            }
        }
    }

    /**
     * Performs a move based on the position of the Tile that is clicked. Is responsible for managing all game methods
     * and redrawing the game board.
     * @param row The row of the Tile that is clicked.
     * @param column The column of the Tile is clicked.
     */
    public void performMove(int row, int column){
        if (!othello.isValid(row, column)) {
            return;
        }
        else {
            othello.makeMove(row, column);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].setColor(othello.returnColor(i, j));
                if (othello.returnPlayer() && othello.isValid(i, j)) {
                    board[i][j].setColor(2);
                }
                else if (!othello.returnPlayer() && othello.isValid(i, j)) {
                    board[i][j].setColor(-2);
                }
            }
        }

        if (!othello.playerHasMoves()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (othello.returnPlayer() && othello.isValid(i, j)) {
                        board[i][j].setColor(2);
                    }
                    else if (!othello.returnPlayer() && othello.isValid(i, j)) {
                        board[i][j].setColor(-2);
                    }
                }
            }
        }
        repaint();

        if (!othello.areThereAnyMoves()) {
            int[] finalScores = othello.returnFinalScores();
            int whiteScore = finalScores[0];
            int blackScore = finalScores[1];
            if (whiteScore > blackScore) {
                System.out.println("White wins!\nWhite's Score = " + whiteScore + "\nBlack's score = " + blackScore);
            }
            if (whiteScore == blackScore) {
                System.out.println("It's a tie!");
            }
            if (whiteScore<blackScore) {
                System.out.println("Black wins!\nWhite's score = " + whiteScore + "\nBlack's score = " + blackScore);
            }
            othello.resetBoard();
            resetBoard();
        }
    }
}