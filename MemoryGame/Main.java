import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose your view: 1 (Text) or 2 (GUI): ");
        String choice = scanner.nextLine().trim();

        MemoryGameModel model = new MemoryGameModel();
        MemoryGameView view;

        if (choice.equals("2")) {
            view = new MemoryGameGUIView();
        } else {
            view = new MemoryGameTextView();
        }

        MemoryGameController controller = new MemoryGameController(model, view);
        controller.play();

        scanner.close();
    }
}

