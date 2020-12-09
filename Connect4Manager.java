import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


/**
 * The manager that runs the game.
 *
 */
public class Connect4Manager {
    
    /**
     * The main console application.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // create an empty board
        Board board = new BoardImpl();
        
        // we will start with the first player
        int player = 1;
        
        Player[] players;
        System.out.print("Type 1 to play 1st player vs computer, \n" +
        		"2 to play 2nd player vs computer, \n" +
        		"3 to play against another human, \n" +
        		"4 to have two computer players play against each other: ");
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int playOption = sc.nextInt();

        int cutoff = 5;
        if (playOption != 3) {
            System.out.print("Enter alpha-beta cutoff depth (maybe up to 6-7 moves): ");
            cutoff = sc.nextInt() - 1;
        }
        
        if (playOption == 1) {
            players = new Player[] {new ConsolePlayer(), new HeuristicPlayer(cutoff)};
        } else if (playOption == 2) {
            players = new Player[] {new HeuristicPlayer(cutoff), new ConsolePlayer()};
        } else if (playOption == 3) {
            players = new Player[] {new ConsolePlayer(), new ConsolePlayer()};
        } else {
            players = new Player[] {new HeuristicPlayer(cutoff), new HeuristicPlayer(cutoff)};
        }
        
        System.out.println("The game starts:");
        System.out.println(board);
        
        // invariant: the board is not full, nobody has won yet
        while (true) {
            System.out.println("Waiting for player " + player + "...");
            int column = players[player-1].makeMove(board.getReader(), player);
            board.putDisc(column, player);
            System.out.println("Player " + player + " put his disc into column " 
                    + column + ":");
            System.out.println(board);
            
            int winner = board.getWinner();
            if (winner != 0) {
                System.out.println("Player " + winner + " won!");
                return;
            }
            
            if (board.isFull()) {
                System.out.println("The game ended in a draw!");
                return;
            }
            
            player = 3 - player;
        }
    }
}
