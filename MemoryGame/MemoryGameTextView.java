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
    public void gameOver() {
        System.out.println("Time's up! Game over.");
        
    }

    @Override
    public void successGameOver(int playerScore, int secondsElapsed){
        System.out.println("Congratulations! You found all the matches.");
        System.out.println("Your final score: " + playerScore);
        System.out.println("Time elapsed: " + secondsElapsed + " seconds");
    }


    @Override
    public String prompt() {
        System.out.println("Enter the tile number to flip, "
                + "'q' to quit, or 'r' to restart: ");
        
        String input = scanner.nextLine();

        return input;
    }

    @Override
    public void quitGame() {
        System.out.println("Good Bye!");
    }

    @Override
    public void restartGame() {
        System.out.println("Restarting the game...");
    }

    @Override
    public void invalidInput() {
        System.out.println("Invalid input. Please enter a tile number.");
    }

    @Override
    public void invalidNum() {
        System.out.println("Invalid tile number. Please enter a valid tile number.");
    }

    @Override
    public void invalidFlip() {
        System.out.println("Tile already flipped. Try again.");
    }

    @Override
    public void matchFound() {
        System.out.println("Match found!");
    }

    @Override
    public void noMatch() {
        System.out.println("No match. Out of flips. Next turn.");
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
}
