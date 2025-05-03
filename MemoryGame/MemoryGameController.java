import java.util.Timer;
import java.util.TimerTask;

class MemoryGameController {

    private final MemoryGameView view;
    private final MemoryGameModel model;
    private static Timer timer;

    /**
     * Constructer for the game controller
     * 
     * @param model model component that interacts with the controller
     * @param view view component that interacts with the controller
     */
    public MemoryGameController(MemoryGameModel model, MemoryGameView view) {
        this.model = model;
        this.view = view;
        model.initializeTiles(6);
    }

    /**
     * Ends the game
     */
    public void endGame() {
        timer.cancel();
    }

    /**
     * Resets game state and adds 6 tile pairs
     */
    private void restartGame() {
        timer.cancel();
        model.resetGameState();
        model.initializeTiles(6);
        startTimer();
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                model.increaseSeconds();
                if (model.getElapsedTime() >= model.getGameDuration()) {
                    view.displayMessage("Time's up! Game over.");
                    endGame();
                }
            }
        }, 1000, 60000);
    }

    public  void play() {
        startTimer();
        while (model.getMatchesFound() < model.getNumberOfTiles() / 2) {
            view.displayBoard(model.getTiles());
            String input = view.prompt();
            if (input.equalsIgnoreCase("q")) {
                // Quits the program
                view.displayMessage("Good Bye!");
                endGame();
                return;
            } else if (input.equalsIgnoreCase("r")) {
                // Resets game state, recreates timer, and continues
                view.displayMessage("Restarting the game...");
                restartGame();
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
                if (model.checkForMatch()) {
                    view.displayMessage("Match found!");
                    model.increaseMatchesFound();
                    model.increasePlayerScore();
                } else if (model.getFlipsRemaining() == 0) {
                    view.displayMessage("No match. Out of flips. Next turn.");
                    model.resetFlippedTiles();
                    model.resetFlips();
                } else {
                    view.displayMessage("No match. Out of flips. Next turn.");
                }
            }
        }
        view.successGameOver(model.getPlayerScore(), model.getElapsedTime());
        endGame();
        view.close();
    }
}
