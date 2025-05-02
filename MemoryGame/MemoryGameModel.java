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
     * 
     * @return Each tile
     */
    public List<Tile> getTiles() {
        return tiles;
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
     * Attempts to flip a tile at a specific index.
     * @param index tile index to flip.
     * @return true if flipped, false if already flipped.
     */
    public boolean flipTile(int index) {
        Tile tile = tiles.get(index);
        if (!tile.isFlipped() && flipsRemaining > 0) {
            tile.setFlipped(true);
            flipsRemaining--;
            return true;
        }
        return false;
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
            matchesFound++;
            playerScore++;
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
//-----------------------------------------------------------------------------------------------------------------------------

    // public void play() {
    //     Scanner scanner = new Scanner(System.in);

    //     while (matchesFound < tiles.size() / 2) {

    //         int tileIndex;

    //         // Check validity

    //         try { // THIS GOES IN THE VIEW
    //             tileIndex = Integer.parseInt(input);
    //         } catch (NumberFormatException e) {
    //             System.out.println("Invalid input. Please enter a tile number.");
    //             continue;
    //         }
    //         if (tileIndex < 0 || tileIndex >= tiles.size()) {
    //             System.out.println("Invalid tile number. Please enter a valid tile number.");
    //             continue;
    //         }

    //         Tile tile = tiles.get(tileIndex);
    //         if (tile.isFlipped()) {
    //             System.out.println("Tile already flipped. Try again.");
    //         } else {
    //             tile.setFlipped(true);
    //             flipsRemaining--;
    //             if (checkForMatch(tile)) {
    //                 System.out.println("Match found!");
    //                 matchesFound++;
    //                 playerScore++;
    //             } else if (flipsRemaining == 0) {
    //                 System.out.println("No match. Out of flips. Next turn.");
    //                 resetFlippedTiles();
    //                 flipsRemaining = 2; // Reset the flips remaining for the next turn
    //             } else {
    //                 System.out.println("No match. Try again.");
    //             }
    //         }
    //     }


    //     endGame();
    //     scanner.close();
    // }

    /**
     * See if there's another tile with the same symbol
     *
     * @param tile Reference title
     * @return True if found match, false otherwise
     */
//     private boolean checkForMatch(Tile tile) {
//         int count = 0;
//         for (Tile t : tiles) {
//             if (t.isFlipped() && t.getSymbol() == tile.getSymbol()) {
//                 count++;
//             }
//         }
//         return count == 2;
//     }

//     /**
//      * Unflips all tiles in the game.
//      */
//     private void resetFlippedTiles() {
//         for (Tile tile : tiles) {
//             if (tile.isFlipped()) {
//                 tile.setFlipped(false);
//             }
//         }
//     }
// }
