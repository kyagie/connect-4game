
/**
 * A generic player interface.
 *
 */
public interface Player {
    /**
     * Returns the move that this player wants to make based on the board configuration.
     * @param b
     * @param player
     * @return
     */
    public int makeMove(Board b, int player);
}
