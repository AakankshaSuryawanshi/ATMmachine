
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

    private static final List<String> WORD_LIST = Arrays.asList(
        "apple", "grape", "peach", "mango", "lemon", "berry", "melon", "mango"
    );
    private static final int MAX_ATTEMPTS = 6;
    private String hiddenWord;
    private Scanner scanner;

    public Wordle() {
        this.hiddenWord = selectRandomWord();
        this.scanner = new Scanner(System.in);
    }

    private String selectRandomWord() {
        Random random = new Random();
        return WORD_LIST.get(random.nextInt(WORD_LIST.size()));
    }

    private boolean isValidWord(String word) {
        return WORD_LIST.contains(word);
    }

    private void provideFeedback(String guess) {
        char[] feedback = new char[5];
        for (int i = 0; i < 5; i++) {
            if (guess.charAt(i) == hiddenWord.charAt(i)) {
                feedback[i] = guess.charAt(i); // Correct letter and position
            } else if (hiddenWord.indexOf(guess.charAt(i)) != -1) {
                feedback[i] = '?'; // Correct letter, wrong position
            } else {
                feedback[i] = '.'; // Incorrect letter
            }
        }
        System.out.println("Feedback: " + new String(feedback));
    }

    public void startGame() {
        System.out.println("Welcome to Wordle!");
        System.out.println("Guess the 5-letter word.");
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.print("Attempt " + attempt + " of " + MAX_ATTEMPTS + ": ");
            String guess = scanner.nextLine().trim().toLowerCase();
            
            if (guess.length() != 5) {
                System.out.println("Please enter a 5-letter word.");
                attempt--;
                continue;
            }

            if (!isValidWord(guess)) {
                System.out.println("Invalid word. Please try again.");
                attempt--;
                continue;
            }

            if (guess.equals(hiddenWord)) {
                System.out.println("Congratulations! You guessed the word!");
                return;
            } else {
                provideFeedback(guess);
            }
        }
        System.out.println("Sorry, you've used all attempts. The word was: " + hiddenWord);
    }

    public static void main(String[] args) {
        new Wordle().startGame();
    }
}
