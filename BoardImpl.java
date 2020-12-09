import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class {@link Board} contains information about the board state only.
 * It doesn't contain the information about which player is to make a move.
 * Information about the player to move is in {@link GameState}
 * 
 */
public class BoardImpl implements Board {
    /**
     * Each cell contains 0 if it's empty, 1 if there's a disc belonging
     * to the 1st player, or 2 if it's second player's.
     */
    private int[][] board = new int[HEIGHT][WIDTH];

    /**
     * Creates a new empty board.
     */
    public BoardImpl() {
    }
    
    /**
     * Creates a new board that's a copy of the provided board.
     * @param b
     */
    public BoardImpl(Board b) {
        this.board = new int[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.board[i][j] = b.get(i,j);
            }
        }
    }
    
    /**
     * Returns true if the specified cell is within the board's boundaries.
     * @param x
     * @param y
     * @return
     */
    public boolean isInBoard(int x,int y) {
        return x>=0 && x<board.length && y>=0 && y<board[0].length;
    }
    
    /**
     * Counts discs of the same color starting from the specified cell and 
     * going in the specified direction.
     * 
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @return
     */
    public int countDiscs(int x,int y,int dx,int dy) {
        int nx = x + dx;
        int ny = y + dy;
        int ret = 1;
        if (isInBoard(nx,ny) && board[nx][ny] == board[x][y])
            ret += countDiscs(nx,ny,dx,dy);
        return ret;
    }
    
    /**
     * Returns 0 if there is no 4-disc sequence, 1 if the 1st player has connected 
     * 4 discs, or 2 if the 2nd player has connected 4 discs.
     * @return
     */
    public int getWinner() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((board[i][j] != 0) && (
                                (countDiscs(i,j,-1,1) >= 4)
                                || (countDiscs(i,j,0,1) >= 4)
                                || (countDiscs(i,j,1,1) >= 4)
                                || (countDiscs(i,j,1,0) >= 4)
                                )) {
                    return board[i][j];
                }
            }
        }
        return 0;
    }
    
    /**
     * Returns non-full columns.
     * @return
     */
    public Collection<Integer> getAllowedMoves() {
        List<Integer> columns = new ArrayList<Integer>();
        for (int j = 0; j < board[0].length; j++) {
            if (board[0][j] == 0) {
                columns.add(j);
            }
        }
        return columns;
    }
    
    /**
     * Puts a disc belonging to the specified player into the specified column.
     * 
     * @param column
     * @param player
     */
    public void putDisc(int column, int player) {
        if (board[0][column] != 0) {
            throw new IllegalArgumentException("Illegal move - column " + column);
        }
        int row = 0;
        while (row + 1 < board.length && board[row + 1][column] == 0) {
            row++;
        }
        board[row][column] = player;
    }

    /**
     * Returns a string representation of the board.
     * @return
     */
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                switch (board[i][j]) {
                case 0:
                    buf.append(".");
                    break;
                case 1:
                    buf.append("X");
                    break;
                case 2:
                    buf.append("O");
                    break;
                default:
                    throw new IllegalStateException(
                            "The board configuration contains an " +
                            "illegal number: " + board[i][j]);
                }
            }
            buf.append("\n");
        }
        for (int j = 0; j < board[0].length; j++)
            buf.append(j+"");
        buf.append("\n");
        return buf.toString();
    }
    
    /**
     * Returns true if the're are no empty cells left.
     * @return
     */
    public boolean isFull() {
        return getAllowedMoves().size() == 0;
    }
    
    /**
     * Returns the disc in the specified cell. If there's a disc, the returned
     * number indicates whose player that disc is. Otherwise returns 0. 
     * @param x
     * @param y
     * @return
     */
    public int get(int x, int y) {
        return board[x][y];
    }
    
    /**
     * Returns a read-only interface to this board.
     * @return
     */
    public Board getReader() {
        return new Board() {
            public int countDiscs(int x, int y, int dx, int dy) {
                return BoardImpl.this.countDiscs(x, y, dx, dy);
            }
            
            public Collection<Integer> getAllowedMoves() {
                return BoardImpl.this.getAllowedMoves();
            }
            
            public int getWinner() {
                return BoardImpl.this.getWinner();
            }
            
            public boolean isInBoard(int x, int y) {
                return BoardImpl.this.isInBoard(x, y);
            }

            public void putDisc(int column, int player) {
                // We won't throw OperationNotSupportedException because we don't
                // want to deal with checked exceptions.
                throw new IllegalStateException();
            }
            
            @Override
            public String toString() {
                return BoardImpl.this.toString();
            }
            
            public Board getReader() {
                // this is already a reader
                return this;
            }
            
            public boolean isFull() {
                return BoardImpl.this.isFull();
            }
            
            public int get(int x, int y) {
                return BoardImpl.this.get(x,y);
            }
            
        };
    }
}
