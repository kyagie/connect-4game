import java.util.List;

/**
 * An alpha-beta implementation of the player. Uses a heuristic to cut the depth
 * of the search tree.
 */
public class HeuristicPlayer implements Player {

    private int cutoff;
    
    public HeuristicPlayer(int cutoff) {
        this.cutoff = cutoff;
    }
    
    /**
     * Returns the optimal move for 
     */
    public int makeMove(Board b, int player) {
        SearchNode n = new SearchNode(new Connect4State(b, player), 0, cutoff);
        return n.getBestMove();
    }

}
