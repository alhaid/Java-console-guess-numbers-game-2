/*
 * Name:  [Hamzah Shafeeq]
 * Assignment:  [Assignment3]

 * [This is the interface mastermin game.]
 */
package assignment3_Hamzah_Shafeeq;

/**
 *
 * @author Hamzah Shafeeq
 */
public interface MasterminInterface {
    
    /**
     * It draws the game and display its content on the console.
     */
    public abstract void drawGame();
    /**
     * Generates four unique random numbers (one does not match the other).
     */
    public abstract void newGame();
    /**
     * Checks the user guess if it matches any of the random numbers.
     * @param guessedNum
     * @return a hit to the user.
     */
    public abstract String checkValues(String guessedNum);
    /**
     * It updates the output with the new guessed number and checks whether the 
     * user have a full correct guess or not.
     * @param userGuess 
     */
    public abstract void updateGame(String userGuess);
}
