import java.util.Scanner;

public class Main {



        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть текст:");
            String text = scanner.nextLine();


            // Режим алфавіту з пробілом
            String filteredTextWithSpace = filterTextWithSpaceAlphabet(text);
            System.out.println("Фільтр у режимі алфавіту з пробілом:");
            System.out.println(filteredTextWithSpace);

            // Режим звичайного алфавіту
            String filteredTextNormalAlphabet = filterTextNormalAlphabet(text);
            System.out.println("Фільтр у режимі звичайного алфавіту:");
            System.out.println(filteredTextNormalAlphabet);
        }

        public static String filterTextWithSpaceAlphabet(String text) {
            // Вилучення символів, окрім символів алфавіту
            String filteredText = text.replaceAll("[^a-zA-Zа-яА-ЯіІїЇєЄґҐ ]", " ");
            // Заміна послідовностей пробілів на один пробіл
            filteredText = filteredText.replaceAll("\\s+", " ");
            // Видалення пробілів на початку та наприкінці тексту
            filteredText = filteredText.trim();
            // Прописні літери замінюються на відповідні стрічні
            filteredText = filteredText.toLowerCase();
            return filteredText;
        }

        public static String filterTextNormalAlphabet(String text) {
            // Вилучення символів, окрім символів алфавіту
            String filteredText = text.replaceAll("[^a-zA-Zа-яА-ЯіІїЇєЄґҐ]", "");
            // Прописні літери замінюються на відповідні стрічні
            filteredText = filteredText.toLowerCase();
            return filteredText;
        }
    }

