import java.util.Timer;
import java.util.TimerTask;

class MemoryGameController {

    private static MemoryGameView view;
    private static MemoryGameModel model;
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
    }

    /**
     * Starts the game by initializing game elements
     */
    public void startGame() {
        // Start the timer

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                model.increaseSeconds();;
                if (model.getElapsedTime() >= model.getGameDuration()) {
                    view.gameOver();
                    endGame();
                }
            }
        }, 1000, 1000);
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
        /* Todo */
        return;
    }

    private void play() {
        /* Todo */
        return;
    }
}
//---------------------------------------------------------------------------------------------------------------------------------

    // ** THIS NEEDS TO BE REFACTORED **

    // public void play() {

    //     while (model.getMatchesFound() < model.getTilesSize() / 2) {
    //         view.displayBoard(model.getTiles());
    //         String input = view.prompt();
    //         if (input.equalsIgnoreCase("q")) {
    //             // Quits the program
    //             view.quitGame();
    //             endGame();
    //             timer.cancel();
    //             return;
    //         } else if (input.equalsIgnoreCase("r")) {
    //             // Resets game state, recreates timer, and continues
    //             view.restartGame();
    //             timer.cancel();
    //             restartGame();
    //             timer = new Timer();
    //             timer.scheduleAtFixedRate(new TimerTask() {
    //                 @Override
    //                 public void run() {
    //                     secondsElapsed++;
    //                     if (secondsElapsed >= gameDuration) {
    //                         view.gameOver();
    //                         endGame();
    //                     }
    //                 }
    //             }, 1000, 1000);
    //             continue;
    //         }
    //     }
    //     view.successGameOver();
    // } 

