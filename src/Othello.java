
/**
 * Othello class that keeps track of the position of pieces on the game board.
 * @author Bryan Chung
 */
public class Othello {
    private int[][] board;
    private boolean curPlayer;
    private final int WHITE = 1;
    private final int BLACK = -1;

    /**
     * Default constructor for an Othello board with height 8 and width 8.
     */
    public Othello() {
        board = new int[8][8];
        curPlayer = true;
        board[3][3] = WHITE;
        board[4][4] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
    }

    /**
     * Resets the Othello board back to its default position.
     */
    public void resetBoard() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        curPlayer = true;
        board[3][3] = WHITE;
        board[4][4] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
    }
    /**
     * Gives the final scores for each player, assuming that there are no blank squares remaining.
     * @return An array of size 2 with the first element being white's score and the second element being black's score.
     */
    public int[] returnFinalScores() {
        int[] finalScores = new int[2];
        int whiteCount = 0;
        int blackCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    whiteCount++;
                }
                else if (board[i][j] == -1) {
                    blackCount++;
                }
            }
        }
        finalScores[0] = whiteCount;
        finalScores[1] = blackCount;
        return (finalScores);
    }

    /**
     * Returns the current player.
     * @return true if it is white's turn, false if it is black's turn.
     */
    public boolean returnPlayer() {
        return curPlayer;
    }

    /**
     * Returns if there are any legal moves for the current player.
     * @return true if there exist valid moves for the player, false otherwise.
     */
    public boolean playerHasMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValid(i, j)) {
                    return true;
                }
            }
        }
        curPlayer = !curPlayer;
        return false;
    }

    /**
     * Returns if there are any legal moves for either player.
     * @return true if there exist valid moves, false otherwise.
     */
    public boolean areThereAnyMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValid(i, j)) {
                    return true;
                }
            }
        }
        curPlayer = !curPlayer;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValid(i, j)) {
                    return true;
                }
            }
        }
        curPlayer = !curPlayer;
        return false;
    }

    /**
     * Returns the color of the piece at a given index.
     * @param row The row the piece is in.
     * @param column The column the piece is in.
     * @return The color of the piece at that index, -1 meaning black, 0 meaning nothing, and 1 meaning white.
     */
    public int returnColor(int row, int column) {
        return board[row][column];
    }

    /**
     * Assuming the move is valid, executes a move, which swaps all opposing color squares
     * between the newly placed square and existing ones of the same color.
     * @param row The row of the square to make the move.
     * @param column The column of the square to make the move.
     */
    public void makeMove(int row, int column) {
        int index;
        if (curPlayer) {
            index = 1;
        }
        else {
            index = -1;
        }
        if (!isValid(row,column)) {
            return;
        }
        else {
            board[row][column] = index;
        }
        if (checkDirection(row,column,1)) {
            for (int i = 1; i <= row; i++) {
                if (board[row-i][column] == index) {
                    break;
                }
                else {
                    board[row-i][column] = index;
                }
            }
        }
        if (checkDirection(row,column,2)) {
            int maxPossible = Math.min(row, board.length - 1 - column);
            for (int i = 1; i <= maxPossible; i++) {
                if (board[row-i][column+i] == index) {
                    break;
                }
                else {
                    board[row-i][column+i] = index;
                }
            }
        }
        if (checkDirection(row,column,3)) {
            for (int i = 1; i <= board[0].length - column - 1; i++) {
                if (board[row][column+i] == index) {
                    break;
                }
                else {
                    board[row][column+i] = index;
                }
            }
        }
        if (checkDirection(row,column,4)) {
            int maxPossible = Math.min(board.length - 1 - row, board.length - 1 - column);
            for (int i = 1; i <= maxPossible; i++) {
                if (board[row+i][column+i] == index) {
                    break;
                }
                else {
                    board[row+i][column+i] = index;
                }
            }
        }
        if (checkDirection(row,column,5)) {
            for (int i = 1; i <= board.length - row - 1; i++) {
                if (board[row+i][column] == index) {
                    break;
                }
                else {
                    board[row+i][column] = index;
                }
            }
        }
        if (checkDirection(row,column,6)) {
            int maxPossible = Math.min(board.length - 1 - row, column);
            for (int i = 1; i <= maxPossible; i++) {
                if (board[row+i][column-i] == index) {
                    break;
                }
                else {
                    board[row+i][column-i] = index;
                }
            }
        }
        if (checkDirection(row,column,7)) {
            for (int i = 1; i <= column; i++) {
                if (board[row][column-i] == index) {
                    break;
                }
                else {
                    board[row][column-i] = index;
                }
            }
        }
        if (checkDirection(row,column,8)) {
            int maxPossible = Math.min(row, column);
            for (int i = 1; i <= maxPossible; i++) {
                if (board[row-i][column-i] == index) {
                    break;
                }
                else {
                    board[row-i][column-i] = index;
                }
            }
        }
        curPlayer = !curPlayer;
    }

    /**
     * Checks if a move at a certain index is valid.
     * @param row The row of the square to check.
     * @param column The column of the square to check.
     * @return True if the move is valid, false if the move is invalid.
     */
    public boolean isValid(int row, int column) {
        if (board[row][column] != 0) {
            return false;
        }
        for (int i = 1; i <= 8; i++) {
            if (checkDirection(row,column,i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a move is valid at a given index in one specific direction.
     * @param row The row of the square to check.
     * @param column The column of the square to check.
     * @param direction The direction to check (1 = up, 2 = up-right, 3 = right, 4 = down-right, 5 = down, 6 = down-left,
     *                  7 = left, 8 = up-left).
     * @return True if the move is valid in the given direction, false otherwise.
     */
    public boolean checkDirection(int row, int column, int direction) {
        int index;
        if (curPlayer) {
            index = 1;
        }
        else {
            index = -1;
        }
        if (direction == 1) {
            if (row == 0) {
                return false;
            }
            if (board[row-1][column] != -index) {
                return false;
            }
            for (int i = 2; i <= row; i++) {
                if (board[row-i][column] == -index) {
                    continue;
                }
                if (board[row-i][column] == index) {
                    return true;
                }
                if (board[row-i][column] == 0) {
                    return false;
                }
            }
            return false;
        }
        else if (direction == 2) {
            int maxPossible = Math.min(row, board.length - 1 - column);
            if (row == 0 || column == board.length-1) {
                return false;
            }
            if (board[row-1][column+1] != -index) {
                return false;
            }
            for (int i = 2; i <= maxPossible; i++) {
                if (board[row-i][column+i] == -index) {
                    continue;
                }
                if (board[row-i][column+i] == index) {
                    return true;
                }
                if (board[row-i][column+i] == 0) {
                    return false;
                }
            }
            return false;
        }
        else if (direction == 3) {
            if (column == board.length - 1) {
                return false;
            }
            if (board[row][column+1] != -index) {
                return false;
            }
            for (int i = 2; i <= board.length - 1 - column; i++) {
                if (board[row][column+i] == -index) {
                    continue;
                }
                if (board[row][column+i] == index) {
                    return true;
                }
                if (board[row][column+i] == 0) {
                    return false;
                }
            }
            return false;
        }
        else if (direction == 4) {
            int maxPossible = Math.min(board.length - 1 - row, board.length - 1 - column);
            if (row == board.length - 1 || column == board.length - 1) {
                return false;
            }
            if (board[row+1][column+1] != -index) {
                return false;
            }
            for (int i = 2; i <= maxPossible; i++) {
                if (board[row + i][column + i] == -index) {
                    continue;
                }
                if (board[row + i][column + i] == index) {
                    return true;
                }
                if (board[row + i][column + i] == 0) {
                    return false;
                }
            }
            return false;
        }
        else if (direction == 5) {
            if (row == board.length - 1) {
                return false;
            }
            if (board[row+1][column] != -index) {
                return false;
            }
            for (int i = 2; i <= board.length - 1 - row; i++) {
                if (board[row+i][column] == -index) {
                    continue;
                }
                if (board[row+i][column] == index) {
                    return true;
                }
                if (board[row+i][column] == 0) {
                    return false;
                }
            }
            return false;
        }
        else if (direction == 6) {
            int maxPossible = Math.min(board.length - 1 - row, column);
            if (row == board.length - 1 || column == 0) {
                return false;
            }
            if (board[row+1][column-1] != -index) {
                return false;
            }
            for (int i = 2; i <= maxPossible; i++) {
                if (board[row + i][column - i] == -index) {
                    continue;
                }
                if (board[row + i][column - i] == index) {
                    return true;
                }
                if (board[row + i][column - i] == 0) {
                    return false;
                }
            }
            return false;
        }
        else if (direction == 7) {
            if (column == 0) {
                return false;
            }
            if (board[row][column-1] != -index) {
                return false;
            }
            for (int i = 2; i <= column; i++) {
                if (board[row][column-i] == -index) {
                    continue;
                }
                if (board[row][column-i] == index) {
                    return true;
                }
                if (board[row][column-i] == 0) {
                    return false;
                }
            }
            return false;
        }
        else if (direction == 8) {
            int maxPossible = Math.min(row, column);
            if (row == 0 || column == 0) {
                return false;
            }
            if (board[row-1][column-1] != -index) {
                return false;
            }
            for (int i = 2; i <= maxPossible; i++) {
                if (board[row-i][column-i] == -index) {
                    continue;
                }
                if (board[row-i][column-i] == index) {
                    return true;
                }
                if (board[row-i][column-i] == 0) {
                    return false;
                }
            }
            return false;
        }
        return false;
    }
}