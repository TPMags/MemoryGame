
public class Main {
    public static void main(String[] args) {
        MemoryGameModel model = new MemoryGameModel();
        MemoryGameView view = new MemoryGameTextView();
        MemoryGameController controller = new MemoryGameController(model, view);
    }
}
