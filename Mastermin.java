/*
 * Name:  [Hamzah Shafeeq]
 * Assignment:  [Assignment3]

 * [This is a Mastermin game. It generates four random numbers and prompt the 
 *  user to guess those numbers. The user has 9 gusses. Also it displays
 *  a hint for the user to help him/her guessing the numbers.]
 */
package assignment3_Hamzah_Shafeeq;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This class implements masterminInterface and uses its abstract methods to
 * make a Mastermin game that allows the user to enter their guess and whether
 * they have a correct guess or not. Also it displays a hint for them to help
 * them getting the right guess.
 *
 * @author Hamzah Shafeeq
 */
public class Mastermin implements MasterminInterface {

    //making some arrays and var. that can be accessed from all the methods.
    String[][] mainList = new String[10][5];
    int num = 9;
    boolean gameOver = false;
    int[] ranNum;

    @Override
    public void drawGame() {
        System.out.println("---------------");
        //checking if the user run out of guess or get the right answer
        //in order to retrive the random numbers.
        if (num < 0 || gameOver) {
            System.out.println("| " + ranNum[0] + " | " + ranNum[1] + " | "
                    + ranNum[2] + " | " + ranNum[3] + " |");
        } else {
            System.out.println("| X | X | X | X |");
        }
        //draws the game with the guesses and the hints.
        for (int i = 0; i < 10; i++) {
            System.out.println("----------------    ----");
            if (mainList[i][0] == null) {
                System.out.println("|   |   |   |   |==|    |");
            } else {
                System.out.println("| " + mainList[i][0] + " | "
                        + mainList[i][1] + " | " + mainList[i][2] + " | "
                        + mainList[i][3] + " |==" + "|" + mainList[i][4] + "|");
            }

        }
    }

    @Override
    public void newGame() {
        boolean again = false; //checks for duplicate numbers.
        ranNum = new int[4];
        int i; // used for the loop.
        int num1 = 0;      
        while (ranNum[3] == 0) {
            again = false;
            int currentNum = (int) (Math.random() * 8 + 1);
            for (i = 0; i <= 3; i++) {
                if (ranNum[i] == currentNum) {
                    again = true;
                    break;
                }
            }
            if (again == false) {
                ranNum[num1] = currentNum;
                num1++;
            }
        }
        //System.out.println(Arrays.toString(ranNum)); //used for debugging
    }

    @Override
    public String checkValues(String guessedNum) {
        int[] intGuessedNum = new int[4];
        //changing the guessed numbers to integers.
        for (int i = 0; i < 4; i++) {
            intGuessedNum[i] = Character.getNumericValue(guessedNum.charAt(i));
        }
        String correctGuess = ""; //used later to display the hints to the user.
        // check for the matching
        for (int i = 0; i < 4; i++) {
            for (int x = 0; x < 4; x++) {
                if (intGuessedNum[i] == ranNum[x]) {
                    if (i == x) {
                        correctGuess += "2";
                    } else {
                        correctGuess += "1";
                    }
                }
            }
        }
        while (correctGuess.length() < 4) {
            correctGuess += "0";
        }
        return correctGuess;    //return the hints.
    }

    @Override
    public void updateGame(String userGuess) {
        //putting the user guesses in an array so we can display it later.
        for (int i = 0; i < 4; i++) {
            mainList[num][i] = new Character(userGuess.charAt(i)).toString();
        }
        String check = checkValues(userGuess); // calling checkValues method.
        mainList[num][4] = check;
        num--;
        //checking if the user get the right guess.
        if (check.equals("2222")) {
            gameOver = true;
        }
    }

    public static void main(String[] args) {
        Mastermin game = new Mastermin();
        //System.out.println(Arrays.toString(game.ranNum));//used for debugging
        Scanner in = new Scanner(System.in);
        game.newGame();

        System.out.println("Welcome to Mastermin game. \nIn this "
                + " game the computer is going to generate four numbers"
                + " for you, and have to guess them in order to win.\n"
                + "Also there will be some hints to help you guessing"
                + " the numbers that are between 1-8.\n"
                + "Hints:\n"
                + "2 means you have right guess in the right spot.\n"
                + "1 means you have right guess in the wrong spot.\n"
                + "0 means none of the guessed numbers matches.\n\n");
        //checking if the user run out of guesses or get the right guess.
        do {
            //check if the user entry is correct.
            boolean nan; //nan == not a number.
            String guess;

            //checking if the user input in valued.
            do {
                System.out.println("Enter you four numbers: ");
                guess = in.next();
                nan = false;
                for (int i = 0; i < guess.length(); i++) {
                    if (!Character.isDigit(guess.charAt(i))) {
                        nan = true;
                    }
                }
                if (nan || guess.length() > 4) {
                    System.out.println("Oops you have either entered something"
                            + " other than numbers or you have entered more"
                            + " than 4 numbers.");
                }
            } while (nan || guess.length() < 4);

            game.updateGame(guess); // calling updateGame.
            game.drawGame();    //calling drawGame.
        } while (game.num >= 0 && !game.gameOver);
        if (game.gameOver) {
            System.out.println("Congratulation, You WIN");
        } else {
            System.out.println("Oops you have run out of guesses !");
        }
    }
}
