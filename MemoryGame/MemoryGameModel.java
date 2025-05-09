import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The model component of the Memory Game.
 * Manages game state, tile logic, time tracking, and scoring.
 */
class MemoryGameModel {
    private final List<Tile> tiles;
    private final int gameDuration;
    private int flipsRemaining;
    private int playerScore;    // TODO: Add player score
    private int matchesFound;
    private int secondsElapsed;

    /**
     * Constructs the model for the Memory Game.
     * Initializes the tile list and default game parameters.
     */
    public MemoryGameModel() {
        this.tiles = new ArrayList<>();
        this.flipsRemaining = 2;
        this.matchesFound = 0;
        this.secondsElapsed = 0;
        this.gameDuration = 60;
    }

    /**
     * Creates tile pairs with symbols starting from 'A' (e.g., A, A, B, B, ...),
     * then shuffles them to randomize positions.
     *
     * @param pairs the number of unique tile pairs to generate
     */
    public void initializeTiles(int pairs) {
        tiles.clear();
        for (char symbol = 'A'; symbol < 'A' + pairs; symbol++) {
            tiles.add(new Tile(symbol));
            tiles.add(new Tile(symbol));
        }
        Collections.shuffle(tiles);
    }

    /**
     * Resets the game state including flipped status, match count,
     * remaining flips, and time—but preserves game duration.
     */
    public void resetGameState() {
        flipsRemaining = 2;
        matchesFound = 0;
        secondsElapsed = 0;
        tiles.forEach(t -> t.setFlipped(false));
        resetPlayerScore();
    }

    /**
     * Returns the list of all tiles currently on the board.
     *
     * @return a list of {@code Tile} objects
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * Retrieves the tile at a specific index.
     *
     * @param index the index of the tile
     * @return the tile at the specified index
     */
    public Tile getTile(int index) {
        return tiles.get(index);
    }

    /**
     * Returns the total number of tiles currently in the game.
     *
     * @return the number of tiles
     */
    public int getNumberOfTiles() {
        return tiles.size();
    }

    /**
     * Returns the number of matches found by the player so far.
     *
     * @return current match count
     */
    public int getMatchesFound() {
        return matchesFound;
    }

    /**
     * Returns how many seconds have passed since the game started.
     *
     * @return elapsed time in seconds
     */
    public int getElapsedTime() {
        return secondsElapsed;
    }

    /**
     * Returns the total time limit for the game.
     *
     * @return game duration in seconds
     */
    public int getGameDuration() {
        return gameDuration;
    }

    /**
     * Increments the elapsed game time by one second.
     */
    public void increaseSeconds() {
        secondsElapsed++;
    }

    /**
     * Returns the number of flips remaining in the current turn.
     *
     * @return remaining flips
     */
    public int getFlipsRemaining() {
        return flipsRemaining;
    }

    /**
     * Increments the number of matches found by one.
     */
    public void increaseMatchesFound() {
        matchesFound++;
    }

    /**
     * Flips a tile at the specified index if it's not already flipped
     * and flips are still allowed this turn.
     *
     * @param index the index of the tile to flip
     */
    public void flipTile(int index) {
        Tile tile = tiles.get(index);
        if (!tile.isFlipped() && flipsRemaining > 0) {
            tile.setFlipped(true);
            flipsRemaining--;
            if(flipsRemaining == 1){
                tile.setSeen(true); // Set the card to seen
            }
        }
    }

    /**
     * Checks if a flipped tile has a matching pair that is also flipped.
     * If a match is found, both tiles are marked as matched.
     *
     * @param tile the tile to check for a match
     * @return {@code true} if a match is found, {@code false} otherwise
     */
    public boolean checkForMatch(Tile tile) {
        int count = 0;
        for (Tile t : tiles) {
            if (t.equals(tile)) continue; // Prevent self-checking
            if (t.isFlipped()) {
                // This is the one other card that is flipped
                if(t.getSymbol() == tile.getSymbol()){
                    t.setMatched(true);
                    tile.setMatched(true);
                    increaseMatchesFound();
                    increasePlayerScore(100);
                    return true;
                } else {
                    // Incorrect match
                    if(t.isSeen() && tile.isSeen()){ // Decrease their score for comparing a seen cards
                        increasePlayerScore(-100);
                    }
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Resets the flip counter to allow 2 flips for the next turn.
     */
    public void resetFlips() {
        flipsRemaining = 2;
    }

    /**
     * Resets all non-matched flipped tiles back to their hidden state.
     * Keeps matched tiles face-up.
     */
    public void resetFlippedTiles() {
        for (Tile tile : tiles) {
            tile.setFlipped(false);
        }

        for (Tile tile : tiles) {
            if (tile.isMatched()) {
                tile.setFlipped(true);
            }
        }
    }

    /**
     * Get the current player score
     * @return Player score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Reset the current player score to zero
     */
    public void resetPlayerScore() {
        this.playerScore = 0;
    }

    /**
     * Increase the player score by a certain amount
     * (can be negative)
     * @param amount Amount to increase player score
     */
    public void increasePlayerScore(int amount){
        // Prevent score lower than zero.
//        if(this.playerScore + amount < 0){ return; }
        System.out.println("increased player score by " + amount);
        this.playerScore += amount;
    }
}
