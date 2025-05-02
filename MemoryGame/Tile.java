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

    public char getSymbol() {
        return symbol;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
