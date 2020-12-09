import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of a connect-4 game.
 */
public class Connect4State extends GameState {
    
    /**
     * The board
     */
    private Board board;
    
    /**
     * Creates a new state given the board, the player to make a move, and the last move.
     * @param board
     * @param player
     * @param move
     */
    public Connect4State(Board board, int player, int move) {
        super(player, move);
        this.board = new BoardImpl(board);
    }
    
    /**
     * Creates a state without the move information.
     * @param board
     * @param player
     */
    public Connect4State(Board board, int player) {
        this(board, player, -1);
    }
    
    @Override
    /**
     * Returns the list of child states.
     */
    public List<GameState> children() {
        /*
         * 1. Create an empty list of child states.
         * List<GameState> children = new ArrayList<GameState>();
         * 
         * 2. board.getAllowedMoves() will return List<Integer> containing the 
         * allowed moves (for the case of connect-four, the allowed column numbers from 0 to 6, that is, the ones that are not yet full).
         * 
         * 3. for each move M, create a copy of the board like this
         * Board b = new BoardImpl(board);
         * 
         * Then put the current player's disc there
         * b.putDisc(int move, int player) is the method you need.
         * To get the player number, use getPlayer()
         * 
         * Create a new state using 
         * new Connect4State(b, P, M)
         * here P is the next player (equal to 3 - getPlayer())
         * M is the move
         * 
         * Add the created state to the list.
         * 
         * 4. In the end, return the list.
         */
        // TODO: your code here
    }
    
    @Override
    /**
     * If there are 4 discs in a row, returns the winner. 
     * Returns 3 if it's a draw. Otherwise returns 0.
     */
    public int getWinner() {
        if (board.isFull())
            return 3;
        return board.getWinner();
    }
    
    private static int[] dx = {-1,-1,0,1};
    private static int[] dy = {0,1,1,1};
    
    /**
     * Utility method to calculate the heuristic.
     * @param x
     * @param y
     * @param dx
     * @param dy
     * @param n
     * @return
     */
    private int[] countXO(int x,int y,int dx,int dy, int n) {
        if (n==0)
            return new int[] {0,0};
        
        int[] nxt = countXO(x+dx,y+dy,dx,dy,n-1);
        if (board.get(x,y)==1)
            nxt[0]++;
        else if (board.get(x,y)==2)
            nxt[1]++;
        
        return nxt;
    }
    
    @Override
    /**
     * Returns the heuristic.
     */
    public double heuristic() {

        double h = 0;
        
        for (int i = 0; i < board.HEIGHT; i++) {
            for (int j = 0; j < board.WIDTH; j++) {
                for (int k = 0; k < 4; k++) {
                    if (board.isInBoard(i+dx[k]*3, j+dy[k]*3)) {
                        int[] num = countXO(i,j,dx[k],dy[k],4);
                        if (num[0]==0) {
                            // no X
                            if (num[1]==1)
                                h -= 1;
                            else if (num[1]==2)
                                h -= 10;
                            else if (num[1]==3)
                                h -= 50;
                        }
                        if (num[1]==0) {
                            // no O
                            if (num[0]==1)
                                h += 1;
                            else if (num[0]==2)
                                h += 10;
                            else if (num[0]==3)
                                h += 50;
                        }
                    }
                }
            }
        }
        
        if (getPlayer() == 1) {
            h += 16;
        } else {
            h -= 16;
        }
        
        return (h + 5000) / 10000.;
    }
}
