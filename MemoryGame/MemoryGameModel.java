import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MemoryGameModel {
    private int flipsRemaining;
    private int playerScore;
    // The number of matches that the player has found.
    // When == tiles.size()/2, main loop exits.
    private int matchesFound;
    private List<Tile> tiles;
    private int secondsElapsed;
    private int gameDuration;

    public MemoryGameModel() {
        this.tiles = new ArrayList<>();
        this.flipsRemaining = 2;
        this.playerScore = 0;
        this.matchesFound = 0;
        this.secondsElapsed = 0;
        this.gameDuration = 60;
    }

    /**
     * Creates tile pairs with smybols increasing from A
     * (i.e. A, A, B, B, C, C, ...)
     *
     * @param pairs Number of tiles to create
     */
    public void initializeTiles(int pairs) {
        tiles.clear();
        for (char symbol = 'A'; symbol < 'A' + pairs; symbol++) {
            tiles.add(new Tile(symbol));
            tiles.add(new Tile(symbol));
        }

        // Makes sure that the tile matches aren't directly next to each other.
        Collections.shuffle(tiles);
    }

    /**
     * Resets all game state variables except game duration.
     */
    public void resetGameState() {
        flipsRemaining = 2;
        playerScore = 0;
        matchesFound = 0;
        secondsElapsed = 0;
        if (tiles != null) {
            tiles.forEach(t -> t.setFlipped(false));
        }
    }

    /**
     * Get a list of all tiles.
     * @return Each tile
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * Returns a tile at a specific index.
     * @param index
     * @return Tile
     */
    public Tile getTile(int index) {
        return tiles.get(index);
    }

    /**
     * Gets the total number of tiles on the board
     * @return int
     */
    public int getNumberOfTiles() {
        int toBeReturned = 0;
        if (!tiles.isEmpty()) {
            for (Tile tile : tiles) {
                toBeReturned++;
            }
            return toBeReturned;
        } else {
            return 0;
        }
    }

    /**
     * Gets all of the matches found in the current game
     * @return the current number of matches found
     */
    public int getMatchesFound() {
        return matchesFound;
    }

    /**
     * Gets how much time has passed in the game
     * @return secondsElapsed
     */
    public int getElapsedTime() {
        return secondsElapsed;
    }

    /**
     * Gets the set game duration
     * @return gameDuration
     */
    public int getGameDuration() {
        return gameDuration;
    }

    /**
     * Increases the seconds Elapsed by one
     */
    public void increaseSeconds() {
        secondsElapsed++;
    }

    /** 
     * @return remaining flips in the turn. 
     */
    public int getFlipsRemaining() { 
        return flipsRemaining; 
    }

    /** 
     * @return player score. 
     */
    public int getPlayerScore() { 
        return playerScore; 
    }

    /**
     * Increases the number of matches found by one.
     */
    public void increaseMatchesFound() {
        matchesFound++;
    }

    /**
     * Increases the player score by one.
     */
    public void increasePlayerScore() {
        playerScore++;
    }

    /**
     * Attempts to flip a tile at a specific index.
     * @param index tile index to flip.
     */
    public void flipTile(int index) {
        Tile tile = tiles.get(index);
        if (!tile.isFlipped() && flipsRemaining > 0) {
            tile.setFlipped(true);
            flipsRemaining--;
        }
    }

    /**
     * Checks whether two flipped tiles match and updates score if they do.
     * @return true if a match is found.
     */
    public boolean checkForMatch() {
        List<Tile> flipped = new ArrayList<>();
        for (Tile t : tiles) {
            if (t.isFlipped()) {
                flipped.add(t);
            }
        }
        if (flipped.size() == 2 && flipped.get(0).getSymbol() == flipped.get(1).getSymbol()) {
            return true;
        }
        return false;
    }

    /** 
     * Resets flip counter for next turn. 
     */
    public void resetFlips() {
        flipsRemaining = 2;
    }

    /** 
     * Resets all flipped tiles to hidden state. 
     */
    public void resetFlippedTiles() {
        for (Tile tile : tiles) {
            if (tile.isFlipped()) {
                tile.setFlipped(false);
            }
        }
    }
}
