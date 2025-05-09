import java.util.Timer;
import java.util.TimerTask;

/**
 * The controller for the Memory Game.
 * It manages the game loop, handles user input, updates the model,
 * and communicates with the view to reflect game state changes.
 */
class MemoryGameController {

    private static Timer timer;
    private final MemoryGameView view;
    private final MemoryGameModel model;

    /**
     * Constructs the game controller.
     *
     * @param model the model component representing game logic and state
     * @param view  the view component responsible for user interaction
     */
    public MemoryGameController(MemoryGameModel model, MemoryGameView view) {
        this.model = model;
        this.view = view;
        model.initializeTiles(6);
    }

    /**
     * Ends the game by cancelling the active timer.
     */
    private void endGame() {
        timer.cancel();
    }

    /**
     * Restarts the game by resetting the model state,
     * reinitializing tiles, and restarting the timer.
     */
    private void restartGame() {
        timer.cancel();
        model.resetGameState();
        model.initializeTiles(6);
        startTimer();
    }

    /**
     * Starts a countdown timer that updates the elapsed time every second.
     * Ends the game if the time exceeds the set duration.
     */
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                model.increaseSeconds();
                if (model.getElapsedTime() >= model.getGameDuration()) {
                    view.displayMessage("Time's up! Game over.");
                    view.close();
                    endGame();
                }
            }
        }, 1000, 1000);
    }

    /**
     * Runs the main gameplay loop:
     * - Displays the board
     * - Handles user input
     * - Checks for matches
     * - Updates the model and view accordingly
     * Ends when all matches are found or the player quits.
     */
    public void play() {
        startTimer();
        while (model.getMatchesFound() < model.getNumberOfTiles() / 2) {
            view.displayBoard(model.getTiles());
            String input = view.prompt();

            // Handle quit or timeout
            if (input.equalsIgnoreCase("q") || model.getElapsedTime() > model.getGameDuration()) {
                view.displayMessage("Good Bye!");
                view.close();
                endGame();
                break;
            }

            // Handle restart
            if (input.equalsIgnoreCase("r")) {
                view.displayMessage("Restarting the game...");
                restartGame();
                continue;
            }

            int tileIndex;
            try {
                tileIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input. Please enter a tile number.");
                continue;
            }

            if (tileIndex < 0 || tileIndex >= model.getNumberOfTiles()) {
                view.displayMessage("Invalid tile number. Please enter a valid tile number.");
                continue;
            }

            if (model.getTile(tileIndex).isFlipped()) {
                view.displayMessage("Tile already flipped. Try again.");
            } else {
                model.flipTile(tileIndex);

                if (model.checkForMatch(model.getTile(tileIndex))) {
                    view.displayMessage("Match found!");
                    model.increaseMatchesFound();
                } else if (model.getFlipsRemaining() == 0) {
                    view.displayMessage("No match. Out of flips. Next turn.");
                    model.resetFlippedTiles();
                    model.resetFlips();
                } else {
                    // TODO: Should this say "Out of flips"??
                    view.displayMessage("No match. Out of flips. Next turn.");
                }
            }
        }

        // Game completed
        view.successGameOver(model.getPlayerScore(), model.getElapsedTime());
        endGame();
        view.close();
    }
}
