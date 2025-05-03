import java.util.List;
import java.util.Scanner;

class MemoryGameTextView implements MemoryGameView {

    private final Scanner scanner;

    public MemoryGameTextView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
    }

    /**
     * Closes the scanner.
     */
    @Override
    public void close() {
        scanner.close();
    }

    /**
     * Lets the player Know that they've won the game,how much time they took, and their score.
     */
    @Override
    public void successGameOver(int playerScore, int secondsElapsed) {
        System.out.println("Congratulations! You found all the matches.");
        System.out.println("Your final score: " + playerScore);
        System.out.println("Time elapsed: " + secondsElapsed + " seconds");
    }

    /**
     * Prompts the user to input a card number, to quit the game, or restart the game.
     */
    @Override
    public String prompt() {
        System.out.println("Enter the tile number to flip, "
                + "'q' to quit, or 'r' to restart: ");

        return scanner.nextLine();
    }

    /**
     * Displays the game board in the terminal
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
     * Display a message given through the parameter.
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
