/**
 * A tile in the game with a symbol (e.g. A, B, ...)
 */
public class Tile {
    private final char symbol;
    private boolean flipped;
    private boolean matched;
    private boolean seen;

    public Tile(char symbol) {
        this.symbol = symbol;
        this.flipped = false;
        this.matched = false;
        this.seen = false;
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
     * @param flipped If true, flipped, otherwise unflipped
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
     * Sets the tile's matched state.
     *
     * @param matched If true, matched, otherwise unmatched
     */
    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    /**
     * [UNDOCUMENTED]
     * Gets the seen state of this tile
     *
     * @return True if seen, false otherwise
     */
    public boolean isSeen() {
        return seen;
    }

    /**
     * [UNDOCUMENTED]
     * Sets the seen state of this tile.
     *
     * @param seen True if has been seen, false otherwise
     */
    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
