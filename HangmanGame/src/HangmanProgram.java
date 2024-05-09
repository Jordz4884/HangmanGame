import java.util.Arrays;
import java.util.Scanner;

public class HangmanProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] possibleWords = {"easy", "medium", "hard"};
        boolean gameOver = false;
        boolean correctAnswer = false;
        int incorrectAttempts = 7;

        String randomWord = possibleWords[(int)(Math.random() * possibleWords.length - 1)];
        char[] toDisplay = new char[possibleWords.length];

        Arrays.fill(toDisplay, '_');

        main:while (!gameOver) {
            System.out.println("You have " + incorrectAttempts + " attempts left");
            System.out.println("Please enter your guess : ");
            String userInput = input.next();

            for (int i = 0; i < randomWord.length(); i++) {
                String currentCharacter = userInput.substring(i, i+1);
                String fromRandom = randomWord.substring(i, i+1);
                if (currentCharacter.equalsIgnoreCase(fromRandom)) {

                    if (Arrays.toString(toDisplay).contains(currentCharacter)) {
                        toDisplay[i] = currentCharacter.charAt(0);
                        System.out.println("Correct");
                        correctAnswer = true;
                    } else {
                        System.out.println("You already got this one");
                        continue main;
                    }
                }

                if (!correctAnswer) {
                    incorrectAttempts--;
                } else {
                    correctAnswer = false;
                }

                if (Arrays.toString(toDisplay).equalsIgnoreCase(randomWord)) {
                    System.out.println("Congrats, you won the game");
                    break;
                }

                if (incorrectAttempts == 0) {
                    gameOver = true;
                    System.out.println("Sorry you have no more chances left");
                }
            }
        }
    }
}
