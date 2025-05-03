import java.util.List;

interface MemoryGameView {
    /**
     * Display the board
     */
    void displayBoard(List<Tile> tiles);

    /**
     * Prompt user to enter tile number, quit, or restart
     */
    String prompt();


    /**
     * Display a success message at end of game, if user
     * found all matches
     * @param playerScore Final player score
     * @param secondsElapsed Final seconds elapsed
     */
    void successGameOver(int playerScore, int secondsElapsed);


    /**
     * closes input getter
     */
    void close();

    /**
     * Displays a message/prompt to the user
     * @param message Message to display
     */
    void displayMessage(String message);
 }
