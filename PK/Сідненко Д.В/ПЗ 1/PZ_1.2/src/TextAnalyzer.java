import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextAnalyzer {

    private String text;

    public TextAnalyzer(String text) {
        this.text = text;
    }

    public TextAnalyzer(File file) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            sb.append(fileScanner.nextLine()).append("\n");
        }
        fileScanner.close();
        this.text = sb.toString();
    }

    public static TextAnalyzer createFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть текст:");
        String text = scanner.nextLine();
        return new TextAnalyzer(text);
    }

    public static TextAnalyzer createFromFile(File file) throws FileNotFoundException {
        return new TextAnalyzer(file);
    }

    public Map<Character, Double> calculateSymbolFrequenciesWithSpaces() {
        Map<Character, Integer> frequencies = new HashMap<>();
        int totalSymbols = 0;

        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            totalSymbols++;
        }

        Map<Character, Double> symbolFrequencies = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            char symbol = entry.getKey();
            int count = entry.getValue();
            double frequency = (double) count / totalSymbols;
            symbolFrequencies.put(symbol, frequency);
        }

        return symbolFrequencies;
    }

    public void printSymbolFrequenciesTable(Map<Character, Double> symbolFrequencies) {
        System.out.println("Символ | Частота");
        System.out.println("----------------");
        for (Map.Entry<Character, Double> entry : symbolFrequencies.entrySet()) {
            char symbol = entry.getKey();
            double frequency = entry.getValue();
            System.out.printf("%6s | %.4f\n", symbol, frequency);
        }
    }

    public double calculateCoincidenceIndex() {
        Map<Character, Integer> frequencies = new HashMap<>();
        int totalSymbols = 0;

        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            totalSymbols++;
        }

        int n = 0;
        for (int f : frequencies.values()) {
            n += f * (f - 1);
        }

        return (double) n / (totalSymbols * (totalSymbols - 1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть джерело тексту: (1 - Ввід з клавіатури, 2 - Читання з файлу)");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистити буфер

        TextAnalyzer analyzer;
        switch (choice) {
            case 1:
                analyzer = TextAnalyzer.createFromKeyboard();
                break;
            case 2:
                System.out.println("Введіть шлях до файлу:");
                String filePath = scanner.nextLine();
                File file = new File(filePath);
                try {
                    analyzer = TextAnalyzer.createFromFile(file);
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не знайдено.");
                    return;
                }
                break;
            default:
                System.out.println("Невірний вибір.");
                return;
        }

        Map<Character, Double> symbolFrequenciesWithSpaces = analyzer.calculateSymbolFrequenciesWithSpaces();
        analyzer.printSymbolFrequenciesTable(symbolFrequenciesWithSpaces);

        double coincidenceIndex = analyzer.calculateCoincidenceIndex();
        System.out.println("\nІндекс відповідності тексту: " + coincidenceIndex);
    }
}
