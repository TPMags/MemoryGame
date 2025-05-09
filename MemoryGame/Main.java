import java.util.InputMismatchException; 
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MemoryGameView view;
        MemoryGameController controller;
        int rows;

        MemoryGameModel model = new MemoryGameModel();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose difficulty: 1. Easy  2. Medium  3. Hard");
        try {
            int choice = scanner.nextInt();
            if (choice == 3) {
                rows = 4;
            } else if (choice == 2) {
                rows = 3;
            } else {
                rows = 2;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice. Defaulting to Easy.");
            rows = 2;
            scanner.nextLine(); // Clear invalid input
        }

        System.out.println("Choose view mode: 1. Text  2. GUI");
        try {
            int choice = scanner.nextInt();
            if (choice == 1) {
                view = new MemoryGameTextView(rows);
                controller = new MemoryGameController(model, view, rows * 3);
            } else if (choice == 2) {
                view = new MemoryGameGUIView(rows);
                controller = new MemoryGameController(model, view, rows * 3);
            } else {
                System.out.println("Invalid choice. Defaulting to Text View.");
                view = new MemoryGameTextView(rows);
                controller = new MemoryGameController(model, view, rows * 3);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice. Defaulting to Text View.");
            view = new MemoryGameTextView(rows);
            controller = new MemoryGameController(model, view, rows * 3);
        }

        scanner.close();
        controller.play();
    }
}

