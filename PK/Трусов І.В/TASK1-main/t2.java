import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class t2
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст:");
        String text = scanner.nextLine();


        Map<Character, Integer> charFreqsWithSpace = calculateCharFrequencies(text, true);


        Map<Character, Integer> charFreqsNoSpace = calculateCharFrequencies(text, false);


        Map<String, Integer> bigramFreqsWithSpaceOverlap = calculateBigramFrequencies(text, true, true);

        Map<String, Integer> bigramFreqsWithSpaceNoOverlap = calculateBigramFrequencies(text, true, false);


        Map<String, Integer> bigramFreqsNoSpaceWithOverlap = calculateBigramFrequencies(text, false, true);


        Map<String, Integer> bigramFreqsNoSpaceNoOverlap = calculateBigramFrequencies(text, false, false);


        System.out.println("Частоти символів з пробілами:");
        printFrequencies(charFreqsWithSpace);
        System.out.println("\nЧастоти символів без пробілів:");
        printFrequencies(charFreqsNoSpace);
        System.out.println("\nЧастоти біграм з пробілами (з перетином):");
        printFrequencies(bigramFreqsWithSpaceOverlap);
        System.out.println("\nЧастоти біграм з пробілами (без перетину):");
        printFrequencies(bigramFreqsWithSpaceNoOverlap);
        System.out.println("\nЧастоти біграм без пробілів (з перетином):");
        printFrequencies(bigramFreqsNoSpaceWithOverlap);
        System.out.println("\nЧастоти біграм без пробілів (без перетину):");
        printFrequencies(bigramFreqsNoSpaceNoOverlap);

        scanner.close();
    }


    private static Map<Character, Integer> calculateCharFrequencies(String text, boolean includeSpace)
    {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray())
        {
            if (!Character.isWhitespace(c) || includeSpace)
            {
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }
        }
        return frequencies;
    }

    private static Map<String, Integer> calculateBigramFrequencies(String text, boolean includeSpace, boolean overlap)
    {
        Map<String, Integer> frequencies = new HashMap<>();
        int step = overlap ? 1 : 2;
        for (int i = 0; i < text.length() - 1; i += step)
        {
            char c1 = text.charAt(i);
            char c2 = text.charAt(i + 1);
            if ((!Character.isWhitespace(c1) && !Character.isWhitespace(c2)) || includeSpace)
            {
                String bigram = String.valueOf(c1) + c2;
                frequencies.put(bigram, frequencies.getOrDefault(bigram, 0) + 1);
            }
        }
        return frequencies;
    }


    private static void printFrequencies(Map<?, Integer> frequencies)
    {
        for (Map.Entry<?, Integer> entry : frequencies.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}