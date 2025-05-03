public class Main {
    public static void main(String[] args) {
        MemoryGameModel model = new MemoryGameModel();
        // using the text-based view
        MemoryGameView view = new MemoryGameTextView(); 
        MemoryGameController controller = new MemoryGameController(model, view);

        controller.play();
    }
}
