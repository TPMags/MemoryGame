import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        MemoryGameView view;
        MemoryGameController controller;

        MemoryGameModel model = new MemoryGameModel();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose view mode: 1. Text  2. GUI;");
        System.out.println("Then choose number of rows.");

        try {
            int choice = scanner.nextInt();
            int rows = scanner.nextInt();

            if (choice == 1) {
                view = new MemoryGameTextView(rows);
                controller = new MemoryGameController(model, view, 9);
                controller.play();
            } else if (choice == 2) {
                view = new MemoryGameGUIView(rows);
                controller = new MemoryGameController(model, view, 9);
                controller.play();
            } else {
                System.out.println("Invalid choice. Defaulting to Text View.");
                view = new MemoryGameTextView(rows);
                controller = new MemoryGameController(model, view, 9);
                controller.play();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice. Defaulting to Text View.");
            view = new MemoryGameTextView(3);
            controller = new MemoryGameController(model, view, 9);
            controller.play();
        } finally {
            scanner.close();
        }

    }
}

