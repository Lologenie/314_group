import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static String filterText(String text, String alphabet, String mode) {
        if (mode.equals("normal")) {
            alphabet += alphabet.toUpperCase() + "abcdefghijklmnopqrstuvwxyz";
        } else if (mode.equals("with_space")) {
            alphabet += alphabet.toUpperCase() + "abcdefghijklmnopqrstuvwxyz";
        } else {
            throw new IllegalArgumentException("Invalid mode. Please choose 'normal' or 'with_space'.");
        }

        if (mode.equals("normal")) {
            String filteredText = text.replaceAll("[^" + alphabet + "]", "");
            filteredText = filteredText.toLowerCase();
            return filteredText;
        } else if (mode.equals("with_space")) {
            String filteredText = text.replaceAll("[^" + alphabet + "]", " ");
            filteredText = filteredText.replaceAll("\\s+", " ");
            filteredText = filteredText.trim();
            filteredText = filteredText.toLowerCase();
            return filteredText;
        } else {
            throw new IllegalArgumentException("Invalid mode. Please choose 'normal' or 'with_space'.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть 1, щоб ввести текст вручну, або 2, щоб вибрати файл:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String text = "";
        if (choice == 1) {
            System.out.println("Введіть текст:");
            text = scanner.nextLine();
        } else if (choice == 2) {
            File[] files = listTextFiles();
            if (files.length == 0) {
                System.out.println("Немає файлів .txt у поточній директорії.");
                return;
            }

            System.out.println("Оберіть файл .txt:");
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + ". " + files[i].getName());
            }
            int fileChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (fileChoice < 1 || fileChoice > files.length) {
                System.out.println("Невірний вибір файлу.");
                return;
            }

            try {
                text = readTextFromFile(files[fileChoice - 1].getPath());
            } catch (IOException e) {
                System.out.println("Помилка при зчитуванні файлу: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Невірний вибір");
            return;
        }

        // Обчислення частот символів з пробілами
        Map<Character, Integer> charFreqsWithSpace = calculateCharFrequencies(text, true);

        // Обчислення частот символів без пробілів
        Map<Character, Integer> charFreqsNoSpace = calculateCharFrequencies(text, false);

        // Обчислення частот біграм з пробілами з перетином
        Map<String, Integer> bigramFreqsWithSpaceOverlap = calculateBigramFrequencies(text, true, true);
        // Обчислення частот біграм з пробілами без перетином
        Map<String, Integer> bigramFreqsWithSpaceNoOverlap = calculateBigramFrequencies(text, true, false);

        // Обчислення частот біграм без пробілів з перетином
        Map<String, Integer> bigramFreqsNoSpaceWithOverlap = calculateBigramFrequencies(text, false, true);

        // Обчислення частот біграм без пробілів без перетину
        Map<String, Integer> bigramFreqsNoSpaceNoOverlap = calculateBigramFrequencies(text, false, false);

        // Виведення результатів у відсотках
        int totalChars = text.replaceAll("\\s", "").length();
        int totalBigrams = text.replaceAll("\\s", "").length() - 1;
        printFrequencies(charFreqsWithSpace, totalChars, "Частоти символів з пробілами:");
        printFrequencies(charFreqsNoSpace, totalChars, "\nЧастоти символів без пробілів:");
        printFrequencies(bigramFreqsWithSpaceOverlap, totalBigrams, "\nЧастоти біграм з пробілами (з перетином):");
        printFrequencies(bigramFreqsWithSpaceNoOverlap, totalBigrams, "\nЧастоти біграм з пробілами (без перетину):");
        printFrequencies(bigramFreqsNoSpaceWithOverlap, totalBigrams, "\nЧастоти біграм без пробілів (з перетином):");
        printFrequencies(bigramFreqsNoSpaceNoOverlap, totalBigrams, "\nЧастоти біграм без пробілів (без перетину):");

        // Запис результатів у файл .xlsx
        System.out.println("\nБажаєте зберегти результати у файл? (так/ні)");
        String saveChoice = scanner.nextLine();
        if (saveChoice.equalsIgnoreCase("так")) {
            System.out.println("Введіть шлях для збереження файлу (з назвою файлу та розширенням):");
            String savePath = scanner.nextLine();
            try {
                saveResultsToExcel(savePath, charFreqsWithSpace, charFreqsNoSpace, bigramFreqsWithSpaceOverlap,
                        bigramFreqsWithSpaceNoOverlap, bigramFreqsNoSpaceWithOverlap, bigramFreqsNoSpaceNoOverlap);
                System.out.println("Результати збережено у файл: " + savePath);
            } catch (IOException e) {
                System.out.println("Помилка при збереженні файлу: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Функція для обчислення частот символів
    private static Map<Character, Integer> calculateCharFrequencies(String text, boolean includeSpace) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (!Character.isWhitespace(c) || includeSpace) {
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }
        }
        return frequencies;
    }

    // Функція для обчислення частот біграм
    private static Map<String, Integer> calculateBigramFrequencies(String text, boolean includeSpace, boolean overlap) {
        Map<String, Integer> frequencies = new HashMap<>();
        int step = overlap ? 1 : 2;
        for (int i = 0; i < text.length() - 1; i += step) {
            char c1 = text.charAt(i);
            char c2 = text.charAt(i + 1);
            if ((!Character.isWhitespace(c1) && !Character.isWhitespace(c2)) || includeSpace) {
                String bigram = String.valueOf(c1) + c2;
                frequencies.put(bigram, frequencies.getOrDefault(bigram, 0) + 1);
            }
        }
        return frequencies;
    }

    // Функція для виведення частот
    private static void printFrequencies(Map<?, Integer> frequencies, int total, String message) {
        System.out.println(message);
        for (Map.Entry<?, Integer> entry : frequencies.entrySet()) {
            double frequency = (double) entry.getValue() / total;
            System.out.println(entry.getKey() + ": " + frequency);
        }
    }






    // Функція для зчитування тексту з файлу
    private static String readTextFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return stringBuilder.toString();
    }

    // Функція для збереження результатів у файл .xlsx
    private static void saveResultsToExcel(String filePath, Map<Character, Integer> charFreqsWithSpace,
                                           Map<Character, Integer> charFreqsNoSpace, Map<String, Integer> bigramFreqsWithSpaceOverlap,
                                           Map<String, Integer> bigramFreqsWithSpaceNoOverlap, Map<String, Integer> bigramFreqsNoSpaceWithOverlap,
                                           Map<String, Integer> bigramFreqsNoSpaceNoOverlap) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write("Символ, Частота з пробілами, Частота без пробілів\n");
        for (Map.Entry<Character, Integer> entry : charFreqsWithSpace.entrySet()) {
            char c = entry.getKey();
            writer.write(c + ", " + entry.getValue() + ", " + charFreqsNoSpace.getOrDefault(c, 0) + "\n");
        }
        writer.write("\n");
        writer.write("Біграма, Частота з пробілами (з перетином), Частота з пробілами (без перетину), " +
                "Частота без пробілів (з перетином), Частота без пробілів (без перетину)\n");
        for (Map.Entry<String, Integer> entry : bigramFreqsWithSpaceOverlap.entrySet()) {
            String bigram = entry.getKey();
            writer.write(bigram + ", " + entry.getValue() + ", " +
                    bigramFreqsWithSpaceNoOverlap.getOrDefault(bigram, 0) + ", " +
                    bigramFreqsNoSpaceWithOverlap.getOrDefault(bigram, 0) + ", " +
                    bigramFreqsNoSpaceNoOverlap.getOrDefault(bigram, 0) + "\n");
        }
        writer.close();
    }

    // Функція для отримання списку файлів .txt у поточній директорії
    private static File[] listTextFiles() {
        File currentDir = new File("C:/Users/nitro/Desktop");
        return currentDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
    }
}
