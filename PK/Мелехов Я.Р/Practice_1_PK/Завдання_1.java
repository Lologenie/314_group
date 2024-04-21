import java.util.Scanner;
import java.util.regex.*;

public class Main {

    public static String filterText(String text, String alphabet, String mode) {
        if (mode.equals("normal")) 
        {
            alphabet += alphabet.toUpperCase() + "abcdefghijklmnopqrstuvwxyz";
        } 
        else if (mode.equals("with_space")) 
        {
            alphabet += alphabet.toUpperCase() + "abcdefghijklmnopqrstuvwxyz";
        } else 
        {
            throw new IllegalArgumentException("Invalid mode. Please choose 'normal' or 'with_space'.");
        }

        if (mode.equals("normal")) 
        {

            String filteredText = text.replaceAll("[^" + alphabet + "]", "");

            filteredText = filteredText.toLowerCase();
            return filteredText;
        } 
        else if (mode.equals("with_space")) {

            String filteredText = text.replaceAll("[^" + alphabet + "]", " ");

            filteredText = filteredText.replaceAll("\\s+", " ");

            filteredText = filteredText.trim();

            filteredText = filteredText.toLowerCase();
            return filteredText;
        } 
        else 
        {
            throw new IllegalArgumentException("Invalid mode. Please choose 'normal' or 'with_space'.");
        }
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) 
        {
            System.out.println("Enter the text:");
            String text = scanner.nextLine();

            String ukrainianAlphabet = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
            String englishAlphabet = "abcdefghijklmnopqrstuvwxyz";

            System.out.println("Choose the filtering mode:");
            System.out.println("1. Normal alphabet");
            System.out.println("2. Alphabet with spaces");
            int modeChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            String filteredText;
            if (modeChoice == 1)
            {
                filteredText = filterText(text, ukrainianAlphabet + englishAlphabet, "normal");
            } 
            else if (modeChoice == 2) {
                filteredText = filterText(text, ukrainianAlphabet + englishAlphabet, "with_space");
            } else
            {
                System.out.println("Invalid mode choice. Using default mode (Normal alphabet).");
                filteredText = filterText(text, ukrainianAlphabet + englishAlphabet, "normal");
            }

            System.out.println("Filtered text:");
            System.out.println(filteredText);

            System.out.println("Do you want to continue? (yes/no)");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes"))
            {
                exit = true;
            }
        }

        scanner.close();
    }
}
