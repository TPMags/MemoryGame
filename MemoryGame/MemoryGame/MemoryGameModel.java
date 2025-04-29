import java.util.*;

class MemoryGameModel {
    private List<Tile> tiles;

    public List<Tile> getTiles() {
        return clone(tiles);
    }

    public int getTilesSize() {
        return tiles.size();
    }

    /**
     * The number of matches that the player has found.
     * When == tiles.size()/2, main loop exits.
     */
    private int matchesFound;

    public int getMatchesFound() {
        return matchesFound;
    }

    private int flipsRemaining;
    private int playerScore;
    private Timer timer;
    /**
     * The time used (out of game duration)
     */
    private int secondsElapsed;

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    /**
     * The total time the user has to find matches
     */
    private int gameDuration;


    public int getGameDuration() {
        return gameDuration;
    }

    public void startGame() {
        // Start the timer

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
    }

    public void endGame() {
        timer.cancel();
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
        Collections.shuffle(tiles);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (matchesFound < tiles.size() / 2) {
            displayBoard();

            int tileIndex;

            // Check validity
            try {
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
        System.out.println("Congratulations! You found all the matches.");
        System.out.println("Your final score: " + playerScore);
        System.out.println("Time elapsed: " + secondsElapsed + " seconds");
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

    /**
     * Resets game state and adds 6 tile pairs
     */
    private void restartGame() {
        tiles.clear();
        matchesFound = 0;
        flipsRemaining = 2;
        playerScore = 0;
        secondsElapsed = 0;
        initializeTiles(6); // Change the number of pairs as per your preference
    }
}
