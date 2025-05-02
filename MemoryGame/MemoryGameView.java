import java.util.List;

interface MemoryGameView {
    /**
     * Display the board
     */
    public void displayBoard(List<Tile> tiles);

    /**
     * Prompt user to enter tile number, quit, or restart
     */
    public String prompt();

    /**
     * Tell the user the game is over
     */
    public void gameOver();

    /**
     * Tell the user that the game is restarting
     */
    public void restartGame();

    /**
     * Tell the user that the game is quitting
     */
    public void quitGame();

    /**
     * Display a success message at end of game, if user
     * found all matches
     * @param playerScore Final player score
     * @param secondsElapsed Final seconds elapsed
     */
    public void successGameOver(int playerScore, int secondsElapsed);

}
