import java.io.*;
import java.util.*;

public class HangmanGame {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to Hangman! You have 6 guesses to guess the word correctly");
        Scanner input = new Scanner(System.in);

        String word;

        Scanner scanner = new Scanner(new File("C:/Users/jsymo/OneDrive/Documents/words_list.txt"));
        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }

        Random rand = new Random();
        word = words.get(rand.nextInt(words.size()));

        //System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;

        while(true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose");
                System.out.println("The word was : " + word);
                break;
            }

            printWordState(word, playerGuesses);
            if (!getPlayerGuess(input, word, playerGuesses)) {
                wrongCount++;
            }

            if(printWordState(word, playerGuesses)) {
                System.out.println("You win");
                break;
            }

            System.out.println("If you know the word, please enter your guess : ");

            if(input.nextLine().equals(word)) {
                System.out.println("You win");
                break;
            } else {
                System.out.println("Please try again");
            }
        }
    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" -------");
        System.out.println(" |");

        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }

        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner input, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter guess : ");
        String letterGuess = input.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }
        }

        System.out.println();

        return (word.length() == correctCount);
    }
}