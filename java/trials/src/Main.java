import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to Word Puzzle Game!");
            System.out.println("1. Start a new game");
            System.out.println("2. Display high score");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    WordPuzzleGame game = new WordPuzzleGame();
                    game.play();
                    break;
                case 2:
                    // code to display high score
                    break;
                case 3:
                    System.out.println("Thank you for playing!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);

        scanner.close();
    }
}