import java.util.List;

/**
 * The game state includes information about the board and the player to make a move.
 */
public abstract class GameState {
    /**
     * The player whose move it is.
     */
    private int player;
    
    /**
     * Last move made in the game.
     */
    private int lastMove = -1;
    
    /**
     * Creates a new state given the player.
     * @param player
     */
    public GameState(int player) {
        this(player, -1);
    }
    
    /**
     * Creates a state with the specified player and last move.
     * @param player
     * @param lastMove
     */
    public GameState(int player, int lastMove) {
        this.player = player;
        this.lastMove = lastMove;
    }
    
    /**
     * Returns the list of child states.
     * @return
     */
    public abstract List<GameState> children();
    
    /**
     * Returns the winner if someone has won in this state.
     * Returns 3 if it's a draw.
     * @return
     */
    public abstract int getWinner();
    
    /**
     * Returns the player whose move it is.
     * @return
     */
    public int getPlayer() {
        return player;
    }
    
    /**
     * Returns the heuristical value.
     * @return
     */
    public abstract double heuristic();
    
    /**
     * Returns the last move made.
     * @return
     */
    public int lastMove() {
        return lastMove;
    }
}
