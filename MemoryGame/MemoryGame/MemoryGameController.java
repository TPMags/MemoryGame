import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class MemoryGameController {

    private static MemoryGameView view;
    private static MemoryGameModel model;
    private static Timer timer;
    private List<Tile> tiles;
    // The time used (out of game duration)
    private int secondsElapsed;
    // The total time the user has to find matches
    private int gameDuration;

    /**
     * Constructer for the game controller
     * 
     * @param model model component that interacts with the controller
     * @param view view component that interacts with the controller
     */
    public MemoryGameController(MemoryGameModel model, MemoryGameView view) {
        this.model = model;
        this.view = view;

        initializeTiles(6);

        secondsElapsed = 0;
        gameDuration = 60;
    }

    /**
     * 
     * @return Each tile
     */
    public List<Tile> getTiles() {
        return clone(tiles);
    }

    /**
     * 
     * @return The amount of tiles
     */
    public int getTilesSize() {
        return tiles.size();
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
                secondsElapsed++;
                if (secondsElapsed >= gameDuration) {
                    view.gameOver();
                    endGame();
                }
            }
        }, 1000, 1000);
    }

    /**
     * Creates tile pairs with smybols increasing from A
     * (i.e. A, A, B, B, C, C, ...)
     *
     * @param pairs Number of tiles to create
     */
    public void initializeTiles(int pairs) {
        for (char symbol = 'A'; symbol < 'A' + pairs; symbol++) {
            tiles.add(new Tile(symbol));
            tiles.add(new Tile(symbol));
        }

        // Makes sure that the tile matches aren't directly next to each other.
        Collections.shuffle(tiles);
    }

    /**
     * Resets game state and adds 6 tile pairs
     */
    private void restartGame() {
        tiles.clear();
        model.resetMatches();
        model.resetFlips();
        model.resetScore();
        secondsElapsed = 0;

        // Change the number of pairs as per your preference
        initializeTiles(6); 
    }

    /**
     * Ends the game
     */
    public void endGame() {
        timer.cancel();
    }

    /**
     * Clones the tile list
     *  
     * @param tiles
     * @return Copy of the tile list
     */
    private List<Tile> clone(List<Tile> tiles) {
        List<Tile> cloneList = new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++) {
            cloneList.add(tiles.get(i));
        }

        return cloneList;
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
}
