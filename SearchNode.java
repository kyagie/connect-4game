import java.util.ArrayList;
import java.util.List;

/**
 * Represents a search node. It contains information about the game state
 * and the player to make a move. 
 */
public class SearchNode {

    /**
     * The game state stored in this node.
     */
    private GameState state;
    
    /**
     * The search depth, after which we use the heuristic.
     */
    private int depth = 0;
    
    /**
     * Max search depth.
     */
    private int cutoff;
    
    /**
     * Creates a new node for the specified player.
     * @param player
     */
    public SearchNode(GameState state) {
        this(state, 0, 5);
    }
    
    /**
     * Creates a node given the game state and search depth.
     * @param state
     * @param depth
     */
    public SearchNode(GameState state, int depth, int cutoff) {
        this.state = state;
        this.depth = depth;
        this.cutoff = cutoff;
    }
    
    /**
     * Returns the list of children of this node.
     * @return
     */
    public List<SearchNode> children() {
        List<SearchNode> children = new ArrayList<SearchNode>();
        for (GameState childState : state.children()) {
            children.add(new SearchNode(childState, depth+1, cutoff));
        }
        return children;
    }
    
    /**
     * Returns the best move from the current game state.
     * @return
     */
    public int getBestMove() {
        double[] bestMove = alphaBeta(0, 1);
        // element 0 is player 1's utility, element 1 is the move that 
        // this node's player will make
        System.out.println("AI best move = " + (int)bestMove[1] + " value = " + bestMove[0]);
        return (int) bestMove[1];
    }
    
    /**
     * Element 0 of the returned array is player 1's utility.
     * Element 1 is the move that the current player will make.
     * 
     * player 1 can guarantee that he gets at least "alpha" utility
     * player 2 can guarantee that player 1 gets at most "beta" utility
     * 
     * The game state and the search depth are implicitly passed to this
     * function because they're stored in this SearchNode instance.
     * 
     * @param alpha
     * @param beta
     * @return
     */
    private double[] alphaBeta(double alpha, double beta) {
        
        /* 
         * This function returns 2 values - the value of the state
         * (0 means player 2 wins, 1 means player 1 wins, values
         * in between show some heuristic estimate of the probability that player 1
         * wins) and the move that the current player should make.
         * 
         * 1. call state.getWinner() to see if the game has finished.
         * If somebody has won, return the corresponding value. Here
         * we don't care to return the move, so we'll just return, say, -1
         * e.g.         
         * if (winner == 1) return new double[] {1, -1};
         * If winner==3, it's a draw and we'll return the value of 0.5
         * 
         * 2. Check the depth. If it's bigger than cutoff depth, return the heuristic.
         * Again, we don't care to return the move in that case, so we'll just return -1.
            if (depth > cutoff) {
                return new double[] {state.heuristic(), -1};
            }
         * 
         * 3. For remaining cases, code alpha-beta as in the lecture slides.
         * 
         * Some useful methods:
         * 
         * state.getPlayer() will return 1 if we're now considering player 1 (corresponding to alpha),
         * 2 if we're now considering player 2 (corresponding to beta).
         * 
         * children() will return a List<SearchNode> containing the successor search nodes.
         * 
         * For each child, we can call child.alphaBeta(alpha, beta), which will return
         * a pair of values - evaluation of that child node, and the move at that child. We actually
         * only care about the node's value and ignore the move here.
         * 
         * child.lastMove() will return the move that we made from the current node to
         * get to that child node.
         * 
         * Use Math.min and Math.max to find minimum/maximum of 2 numbers.
         * 
         * When we have calculated the value of this node as V and the optimal move as M,
         * return it like "return new double[] {V, M};"
         * 
         */
        
        // TODO: your code here.
    }
    
    /**
     * Returns the last move made to reach this node.
     * @return
     */
    public int lastMove() {
        return state.lastMove();
    }
}
