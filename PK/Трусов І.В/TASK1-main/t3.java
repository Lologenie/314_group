import java.util.Scanner;

public class t3
{

    public static final String UKRAINIAN_ALPHABET = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    public static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст для обчислення індексу відповідності:");
        String text = scanner.nextLine();


        System.out.println("Виберіть алфавіт: ");
        System.out.println("1. Українська");
        System.out.println("2. Російська");
        System.out.println("3. Англійська");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String alphabet;
        switch (choice)
        {
            case 1:
                alphabet = UKRAINIAN_ALPHABET;
                break;
            case 2:
                alphabet = RUSSIAN_ALPHABET;
                break;
            case 3:
                alphabet = ENGLISH_ALPHABET;
                break;
            default:
                System.out.println("Невірний вибір алфавіту. Використано англійський алфавіт за замовчуванням.");
                alphabet = ENGLISH_ALPHABET;
        }

        double index = calculateIndex(text, alphabet);

        System.out.println("Індекс відповідності тексту: " + index);

        scanner.close();
    }

    public static double calculateIndex(String text, String alphabet)
    {

        int[] letterCounts = new int[alphabet.length()];

        text = text.toLowerCase();


        for (char c : text.toCharArray())
        {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                letterCounts[index]++;
            }
        }


        double totalLetters = 0;
        double index = 0;

        for (int count : letterCounts)
        {
            totalLetters += count;
            index += count * (count - 1);
        }

        if (totalLetters > 1)
        {
            index /= totalLetters * (totalLetters - 1);
        }

        return index;
    }
}
