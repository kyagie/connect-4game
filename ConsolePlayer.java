import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * An implementation of the player that reads its moves from the console input.
 *
 */
public class ConsolePlayer implements Player {

    public int makeMove(Board b, int player) {
        java.util.Collection<Integer> allowedMoves = b.getAllowedMoves();
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        
        System.out.print("Player " + player + ", enter your move: ");
        while (true) {
            int column = scanner.nextInt();
            if (allowedMoves.contains(column)) {
                return column;
            } else {
                System.out.print("That move is not allowed. Try again: ");
            }
        }
    }

}
