/**
 * A tile in the game with a symbol (e.g. A, B, ...)
 */
class Tile {
    private char symbol;
    private boolean flipped;

    public Tile(char symbol) {
        this.symbol = symbol;
        this.flipped = false;
    }

    /**
     * Returns the underside of the card
     * @return symbol
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Checks to see if the card has been flipped or not.
     * @return flipped
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * Sets the flipped state to either true or false
     * @param flipped
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
