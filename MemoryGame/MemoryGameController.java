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
                    view.gameOver();
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
                view.quitGame();
                endGame();
                return;
            } else if (input.equalsIgnoreCase("r")) {
                // Resets game state, recreates timer, and continues
                view.restartGame();
                restartGame();
            }
            int tileIndex;
            try {
                tileIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                view.invalidInput();
                continue;
            }
            if (tileIndex < 0 || tileIndex >= model.getNumberOfTiles()) {
                view.invalidNum();
                continue;
            }
            if (model.getTile(tileIndex).isFlipped()) {
                view.invalidFlip();
            } else {
                model.flipTile(tileIndex);
                if (model.checkForMatch()) {
                    view.matchFound();
                    model.increaseMatchesFound();
                    model.increasePlayerScore();
                } else if (model.getFlipsRemaining() == 0) {
                    view.noMatch();
                    model.resetFlippedTiles();
                    model.resetFlips();
                } else {
                    view.noMatch();
                }
            }
        }
        view.successGameOver(model.getPlayerScore(), model.getElapsedTime());
        endGame();
        view.close();
    }
}
