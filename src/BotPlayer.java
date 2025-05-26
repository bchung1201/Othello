import java.util.*;

/**
 * BotPlayer for Othello. Plays as black (-1).
 * Heuristics: prefers corners, avoids X-squares (adjacent to corners), otherwise random valid move.
 */
public class BotPlayer {
    private static final int[][] CORNERS = { {0,0}, {0,7}, {7,0}, {7,7} };
    private static final int[][] X_SQUARES = { {1,1}, {1,6}, {6,1}, {6,6}, {0,1}, {1,0}, {0,6}, {1,7}, {6,0}, {7,1}, {6,7}, {7,6} };

    public int[] chooseMove(Othello othello) {
        List<int[]> validMoves = new ArrayList<>();
        List<int[]> cornerMoves = new ArrayList<>();
        List<int[]> safeMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (othello.isValid(i, j)) {
                    validMoves.add(new int[]{i, j});
                    if (isCorner(i, j)) cornerMoves.add(new int[]{i, j});
                    else if (!isXSquare(i, j)) safeMoves.add(new int[]{i, j});
                }
            }
        }
        if (!cornerMoves.isEmpty()) {
            return cornerMoves.get(0);
        } else if (!safeMoves.isEmpty()) {
            return safeMoves.get(new Random().nextInt(safeMoves.size()));
        } else if (!validMoves.isEmpty()) {
            return validMoves.get(new Random().nextInt(validMoves.size()));
        }
        return null; // No valid moves
    }

    private boolean isCorner(int i, int j) {
        for (int[] c : CORNERS) if (c[0] == i && c[1] == j) return true;
        return false;
    }
    private boolean isXSquare(int i, int j) {
        for (int[] x : X_SQUARES) if (x[0] == i && x[1] == j) return true;
        return false;
    }
} 