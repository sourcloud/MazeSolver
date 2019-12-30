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
     * Searches given 2D-char-array for a given entry, returns position as Point if found
     * @param toSearch 2D-char-array to be searched search
     * @param toFind Char to be found
     * @return Point with row and col as coordinates if entry found, -1 and -1 if not found
     */
    
    public static Point getPositionOfEntryIn2DArray(char[][] toSearch, char toFind) {
        for (int row = 0; row < toSearch.length; row++)
            for (int col = 0; col < toSearch[0].length; col++)
                if (toSearch[row][col] == toFind)
                    return new Point(row, col);
        return new Point(-1,-1);
    }
    
    /**
     * Gets entry at a given position of an 2D-array.
     * @param position Point representing position in 2D-array.
     * @param array 2D-char-array to get entry from.
     */
    
    public static char getElementAtPositionOf2DArray(Point position, char[][] array) {
        int row = position.getX();
        int col = position.getY();
        return array[row][col];
    }
    
    /**
     * Sets entry at a given position of an 2D-array to a certain entry.
     * @param position Point representing position in 2D-array.
     * @param array 2D-char-array where entry will be set.
     * @param toBeSet char that will be set.
     */
    
    public static void setElementAtPositionOf2DArray(Point position, char[][] array, char toBeSet) {
        int row = position.getX();
        int col = position.getY();
        array[row][col] = toBeSet;
    }
    
    /**
     * Checks if entry at a given position of a 2D-char-array is equal to a given char.
     * @param position Point representing row and column of 2D-Array.
     * @param array 2D-char-array that's entry will be checked
     * @param compareChar 
     * @return Boolean answering if entry at given position is given char.
     */
    
    public static boolean isElementAtPositionOf2DArrayEqualTo(Point position, char[][] array, char compareChar) {
        int row = position.getX();
        int col = position.getY();
        return array[row][col] == compareChar;
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
        for (char[] row : toPrint)
            printArray(row);
        System.out.println();
    }
    
    /**
     * Prints an given char-array but replaces char at given index with given symbol.
     * @param toPrint Array to print.
     * @param index Int index where the char will be replaced. 
     * @param symbol Char to replace with.
     */
    
    public static void printArrayWithSymbolOnIndex(char[] toPrint, int index, char symbol) {
        for (int i = 0; i < toPrint.length; i++) {
            if (i != index)
                System.out.print(toPrint[i] + " ");
            else
                System.out.print(symbol + " ");
        }
        System.out.println();
    }
    
    /**
     * Prints a given 2D-char-array but replaces char at given position with given symbol.
     * @param toPrint 2D-char-array to print.
     * @param position Point where the char will be replaced.
     * @param symbol Char to replace with.
     */
    
    public static void print2DArrayWithSymbolOnPosition(char[][] toPrint, Point position, char symbol) {
        int col = position.getY();
        for (int row = 0; row < toPrint.length; row++) {
            if (row != position.getX())
                printArray(toPrint[row]);
            else
                printArrayWithSymbolOnIndex(toPrint[row], col, symbol);
        }
        System.out.println();
    }
    
    /**
     * Shuffles an given array of Directions.
     * @param toShuffle Array to shuffle.
     * @return Shuffled Array.
     */
    
    public static Directions[] shuffleArray(Directions[] toShuffle) {
        int randomIndex = 0;
        for (int i = 0; i < toShuffle.length; i++) {
            randomIndex = getRandomInRange(0, toShuffle.length-1);
            swapArrayElements(toShuffle, i, randomIndex);
        }
        return toShuffle;
    }
    
    /**
     * Swap two elements at given indexes in an array.
     * @param toSwap Array that's elements will be swapped.
     * @param firstIndex Int index of first element.
     * @param secondIndex Int index of second element.
     */
    
    public static void swapArrayElements(Directions[] toSwap, int firstIndex, int secondIndex) {
        Directions temp = toSwap[firstIndex];
        toSwap[firstIndex] = toSwap[secondIndex];
        toSwap[secondIndex] = temp;
    }
    
    /**
     * Tries to Sleep for a given amount of time.
     * @param timeInMilliSeconds Int representing an amount of time in milliseconds.
     */
    
    public static void tryToSleep(int timeInMilliSeconds) {
        try { 
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) { 
            e.printStackTrace();
        }
    }
    
}
