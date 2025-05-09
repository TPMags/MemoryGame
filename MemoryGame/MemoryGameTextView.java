import java.util.List;
import java.util.Scanner;

/**
 * A text-based view for the Memory Game.
 * Handles user interaction via the terminal.
 */
class MemoryGameTextView implements MemoryGameView {

    private final Scanner scanner;

    /**
     * Constructs a new MemoryGameTextView and initializes the Scanner for user input.
     */
    public MemoryGameTextView(int rowDim) {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the current game board to the terminal.
     * Flipped tiles show their symbols, while unflipped tiles are represented with their index.
     *
     * @param tiles the list of tiles representing the game board
     */
    @Override
    public void displayBoard(List<Tile> tiles) {
        System.out.println("\n------- Memory Game -------");
        System.out.print("   ");
        for (int i = 0; i < tiles.size(); i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < tiles.size(); i++) {
            if (i % 6 == 0) {
                System.out.println();
                System.out.print(i / 6 + "  ");
            }
            Tile tile = tiles.get(i);
            if (tile.isFlipped()) {
                System.out.print(tile.getSymbol() + "\t");
            } else {
                System.out.print("[" + i + "]\t");
            }
        }
        System.out.println("\n---------------------------");
    }

    /**
     * Prompts the user to input the next move.
     * The input can be a tile number, 'q' to quit, or 'r' to restart the game.
     *
     * @return the user's input as a String
     */
    @Override
    public String prompt() {
        System.out.println("Enter the tile number to flip, "
                + "'q' to quit, or 'r' to restart: ");

        return scanner.nextLine();
    }

    /**
     * Displays a congratulatory message to the player upon successfully finishing the game.
     *
     * @param playerScore    the player's final score
     * @param secondsElapsed the time taken to complete the game in seconds
     */
    @Override
    public void successGameOver(int playerScore, int secondsElapsed) {
        System.out.println("Congratulations! You found all the matches.");
        System.out.println("Your final score: " + playerScore);
        System.out.println("Time elapsed: " + secondsElapsed + " seconds");
    }

    /**
     * Closes the scanner to release system resources.
     */
    @Override
    public void close() {
        scanner.close();
    }

    /**
     * Displays a generic message to the terminal.
     *
     * @param message the message to display
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
