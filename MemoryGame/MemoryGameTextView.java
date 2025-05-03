import java.util.List;
import java.util.Scanner;

class MemoryGameTextView implements MemoryGameView {

    private final Scanner scanner;

    MemoryGameTextView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void close() {
        scanner.close();
    }

    @Override
    public void successGameOver(int playerScore, int secondsElapsed) {
        System.out.println("Congratulations! You found all the matches.");
        System.out.println("Your final score: " + playerScore);
        System.out.println("Time elapsed: " + secondsElapsed + " seconds");
    }


    @Override
    public String prompt() {
        System.out.println("Enter the tile number to flip, "
                + "'q' to quit, or 'r' to restart: ");

        return scanner.nextLine();
    }

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

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
