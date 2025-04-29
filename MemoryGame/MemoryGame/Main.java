import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        MemoryGame game = new MemoryGame();
        game.initializeTiles(6); // Change the number of pairs as per your preference
        game.play();
    }
}
