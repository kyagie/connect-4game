import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * A simple implementation of a player. Each move is picked randomly.
 * 
 */
public class RandomPlayer implements Player {

    private static Random random = new Random();
    
    public int makeMove(Board b, int player) {
        List<Integer> allowedMoves = new ArrayList<Integer>(b.getAllowedMoves());
        return allowedMoves.get(random.nextInt(allowedMoves.size()));
    }

}
