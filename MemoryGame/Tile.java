/**
 * A tile in the game with a symbol (e.g. A, B, ...)
 */
class Tile {
    private char symbol;
    private boolean flipped;
    private boolean matched;

    public Tile(char symbol) {
        this.symbol = symbol;
        this.flipped = false;
        this.matched = false;
    }

    /**
     * Returns the underside of the card
     *
     * @return symbol
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Checks to see if the card has been flipped or not.
     *
     * @return flipped
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * Sets the flipped state to either true or false
     *
     * @param flipped
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    /**
     * lets the program know if the tile is matched or not.
     *
     * @return matched
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * Sets the tile to matched.
     *
     * @param matched
     */
    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
