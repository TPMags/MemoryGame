import java.util.*;

class MemoryGameModel {
    private int flipsRemaining;
    private int playerScore;
    // The number of matches that the player has found.
    // When == tiles.size()/2, main loop exits.
    private int matchesFound;

    public void resetFlips() {
        flipsRemaining = 2;
    }

    public void resetScore() {
        playerScore = 0;
    }

    public void resetMatches() {
        matchesFound = 0;
    }

    public int getMatchesFound() {
        return matchesFound;
    }
    //-----------------------------------------------------------------------------------------------------------------------------

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (matchesFound < tiles.size() / 2) {

            int tileIndex;

            // Check validity

            try { // THIS GOES IN THE VIEW
                tileIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a tile number.");
                continue;
            }
            if (tileIndex < 0 || tileIndex >= tiles.size()) {
                System.out.println("Invalid tile number. Please enter a valid tile number.");
                continue;
            }

            Tile tile = tiles.get(tileIndex);
            if (tile.isFlipped()) {
                System.out.println("Tile already flipped. Try again.");
            } else {
                tile.setFlipped(true);
                flipsRemaining--;
                if (checkForMatch(tile)) {
                    System.out.println("Match found!");
                    matchesFound++;
                    playerScore++;
                } else if (flipsRemaining == 0) {
                    System.out.println("No match. Out of flips. Next turn.");
                    resetFlippedTiles();
                    flipsRemaining = 2; // Reset the flips remaining for the next turn
                } else {
                    System.out.println("No match. Try again.");
                }
            }
        }


        endGame();
        scanner.close();
    }

    /**
     * See if there's another tile with the same symbol
     *
     * @param tile Reference title
     * @return True if found match, false otherwise
     */
    private boolean checkForMatch(Tile tile) {
        int count = 0;
        for (Tile t : tiles) {
            if (t.isFlipped() && t.getSymbol() == tile.getSymbol()) {
                count++;
            }
        }
        return count == 2;
    }

    /**
     * Unflips all tiles in the game.
     */
    private void resetFlippedTiles() {
        for (Tile tile : tiles) {
            if (tile.isFlipped()) {
                tile.setFlipped(false);
            }
        }
    }
}
