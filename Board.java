import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Board {

    int WIDTH = 7;
    
    int HEIGHT = 6;
    
    /**
     * Returns true if the specified cell is within the board's boundaries.
     * @param x
     * @param y
     * @return
     */
    public boolean isInBoard(int x,int y);
    
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
    public int countDiscs(int x,int y,int dx,int dy);
    
    /**
     * Returns 0 if there is no 4-disc sequence, 1 if the 1st player has connected 
     * 4 discs, or 2 if the 2nd player has connected 4 discs.
     * @return
     */
    public int getWinner();
    
    /**
     * Returns non-full columns.
     * @return
     */
    public Collection<Integer> getAllowedMoves();
    
    /**
     * Puts a disc belonging to the specified player into the specified column.
     * 
     * @param column
     * @param player
     */
    public void putDisc(int column, int player);

    /**
     * Returns a string representation of the board.
     * @return
     */
    public String toString();
    
    /**
     * Returns a read-only interface to this board.
     * @return
     */
    public Board getReader();
    
    /**
     * Returns true if the're are no empty cells left.
     * @return
     */
    public boolean isFull();
    
    /**
     * Returns the disc in the specified cell. If there's a disc, the returned
     * number indicates whose player that disc is. Otherwise returns 0. 
     * @param x
     * @param y
     * @return
     */
    public int get(int x, int y);
    
}
