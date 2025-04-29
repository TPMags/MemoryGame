import java.util.Timer;
import java.util.TimerTask;

class MemoryGameController {

    private static MemoryGameView _view;
    private static MemoryGameModel _model;
    private static Timer timer;

    MemoryGameController() {
        _view = new MemoryGameTextView();
        _model = new MemoryGameModel();

        _model.initializeTiles(6);
    }

    public void play() {

        while (_model.getMatchesFound() < _model.getTilesSize() / 2) {
            _view.displayBoard(_model.getTiles());
            String input = _view.prompt();
            if (input.equalsIgnoreCase("q")) {
                // Quits the program
                _view.quitGame();
                endGame();
                timer.cancel();
                return;
            } else if (input.equalsIgnoreCase("r")) {
                // Resets game state, recreates timer, and continues
                _view.restartGame();
                timer.cancel();
                restartGame();
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        secondsElapsed++;
                        if (secondsElapsed >= gameDuration) {
                            _view.gameOver();
                            endGame();
                        }
                    }
                }, 1000, 1000);
                continue;
            }
        }

    }
}
