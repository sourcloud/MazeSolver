package maze;

public class Helper {
    
    /**
     * Chooses value in a given range.
     * @param min An int representing the minimal value to be returned.
     * @param max An int representing the maximal value to be returned.
     * @return An randomly chosen int in the range of min and max.
     */
   
    public static int getRandomInRange(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
    
    /**
     * Chooses uneven value in a given range.
     * @param min An int representing the minimal value to be returned.
     * @param max An int representing the maximal value to be returned.
     * @return An randomly chose int in the range of min and max that is uneven.
     */
    
    public static int getUnevenRandomInRange(int min, int max) {
        int randomNumber = 0;
        while (randomNumber % 2 == 0) {
            randomNumber = Helper.getRandomInRange(min, max);
        }
        return randomNumber;   
    }
    
    /**
     * Fills a given char-array with a given char.
     * @param toFill Array to fill.
     * @param fillValue Char to be filled in array.
     */
    
    public static void fillArray(char[] toFill, char fillValue) {
        for (int i = 0; i < toFill.length; i++)
            toFill[i] = fillValue;
    }
    
    /**
     * Fills a given 2D-char-array with a given char.
     * @param toFill 2D-Array to fill.
     * @param fillValue Char to be filled in array.
     */
    
    public static void fill2DArray(char[][] toFill, char fillValue) {
        for (char[] array : toFill)
            fillArray(array, fillValue);
    }
    
    /**
     * Prints a given char-array to console.
     * @param toPrint Array to print.
     */
    
    public static void printArray(char[] toPrint) {
        for (char element : toPrint)
            System.out.print(element + " ");
        System.out.println();
    }
    
    /**
     * Prints a given 2D-char-array to console.
     * @param toPrint Array to print.
     */
    
    public static void print2DArray(char[][] toPrint) {
        for (char[] array : toPrint)
            printArray(array);
        System.out.println();
    }
    
    /**
     * Shuffels an given array of Directions.
     * @param toShuffle Array to be shuffle.
     * @return Shuffled Array.
     */
    
    public static Directions[] shuffleArray(Directions[] toShuffle) {
        int randomPosition = 0;
        for (int i = 0; i < toShuffle.length; i++) {
            randomPosition = getRandomInRange(0, toShuffle.length-1);
            Directions temp = toShuffle[i];
            toShuffle[i] = toShuffle[randomPosition];
            toShuffle[randomPosition] = temp;
        }
        return toShuffle;
    }
    
}
