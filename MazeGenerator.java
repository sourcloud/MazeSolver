package maze;

public class MazeGenerator {
    
    private static final int MINRANGE = 3;
    private static final int MAXRANGE = 35;
    private static final int MININDEX = 0;
    
    private enum Sides {LEFT, RIGHT, TOP, BOTTOM;}
    private Point pathStartingPoint;
    private char[][] maze;
    private int height;
    private int width;
    private int heightIndex;
    private int widthIndex;
    
    MazeGenerator() {}
    
    /**
     * Generate a maze by initializing size, maze and path starting point,
     * generating path and setting entry/exit points.
     * @return 2D-char-array representing the maze.
     */
    
    public char[][] getRandomMaze() {
        initializeSize();
        initializeMaze();
        initializePathStartingPoint();
        generatePath(pathStartingPoint);
        setEntryAndExit();
        return maze;
    }
    
    /**
     * Finds a column in a given row that has a path next to it. For example if row = 0 will check maze[1][col] for path.
     * @param row Int representing the index of the row the column is in. Is either MININDEX or heightIndex.
     * @return Int representing index of the valid column.
     */
    
    private int getValidColAtIndex(int row) {
        int col = MININDEX;
        row = (row == MININDEX) ? ++row : --row;
        while (maze[row][col] != Symbols.PATH.getSign()) {
            col = Helper.getRandomInRange(MININDEX, widthIndex);
        }
        return col;
    }
    
    /**
     * Finds a row in a given column that has a path next to it. For example if col = 0 will check maze[row][1] for path.
     * @param col Int representing the index of the column the row is in. Is either MININDEX or widthIndex.
     * @return Int representing index of the valid row.
     */
    
    private int getValidRowAtIndex(int col) {
        int row = MININDEX;
        col = (col == MININDEX) ? ++col : --col;
        while (maze[row][col] != Symbols.PATH.getSign()) {
            row = Helper.getRandomInRange(MININDEX, heightIndex);
        }
        return row;
    }
    
    /**
     * Set entry and exit to maze by randomly picking a side to set entry to and finding 
     * valid coordinates for entry and exit. Exit will always be on the opposite of entry.
     */
    
    private void setEntryAndExit() {
        
        Sides[] fieldSides = Sides.values();       
        int index = Helper.getRandomInRange(MININDEX, fieldSides.length-1);       
        Sides entrySide = fieldSides[index];
        
        int entryRow = (entrySide == Sides.RIGHT) 
                            ? getValidRowAtIndex(widthIndex) 
                            : (entrySide == Sides.LEFT) 
                                ? getValidRowAtIndex(MININDEX) 
                                : (entrySide == Sides.BOTTOM) 
                                    ? heightIndex 
                                    : 0;
                                
        int exitRow = (entrySide == Sides.LEFT) 
                            ? getValidRowAtIndex(widthIndex) 
                            : (entrySide == Sides.RIGHT) 
                                ? getValidRowAtIndex(MININDEX) 
                                : (entrySide == Sides.TOP) 
                                    ? heightIndex 
                                    : 0;
        
        int entryCol = (entrySide == Sides.BOTTOM) 
                            ? getValidColAtIndex(heightIndex) 
                            : (entrySide == Sides.TOP) 
                                ? getValidColAtIndex(MININDEX) 
                                : (entrySide == Sides.RIGHT) 
                                    ? widthIndex 
                                    : 0;
                                
        int exitCol = (entrySide == Sides.TOP) 
                            ? getValidColAtIndex(heightIndex) 
                            : (entrySide == Sides.BOTTOM) 
                                ? getValidColAtIndex(MININDEX) 
                                : (entrySide == Sides.LEFT) 
                                    ? widthIndex 
                                    : 0;
        
        maze[entryRow][entryCol] = Symbols.START.getSign();
        maze[exitRow][exitCol] = Symbols.END.getSign();
    }
    
    /**
     * Sets height and width to a randomly chosen value within the min and max range.
     * Sets index for height and width.
     */
    
    private void initializeSize() {
        height = Helper.getUnevenRandomInRange(MINRANGE, MAXRANGE);
        width = Helper.getUnevenRandomInRange(MINRANGE, MAXRANGE);
        heightIndex = height - 1;
        widthIndex = width - 1;
    }
    
    /**
     * Creates a new 2D-char-array the size of height and width and fills it with wall-symbols.
     */
    
    private void initializeMaze() {
        maze = new char[height][width];
        Helper.fill2DArray(maze, Symbols.WALL.getSign());
    }
    
    /**
     * Sets a randomly chosen point as starting point for path-generating and sets path symbol.
     */
    
    private void initializePathStartingPoint() {
        int row = Helper.getUnevenRandomInRange(MININDEX, heightIndex);
        int col = Helper.getUnevenRandomInRange(MININDEX, widthIndex);
        pathStartingPoint = new Point(row, col);
        maze[row][col] = Symbols.PATH.getSign();
    }
    
    /**
     * Calls function with x- and y- coordinate of a Point.
     * @param point Point in maze.
     */
    
    public void generatePath(Point point) {
        int row = point.getX();
        int col = point.getY();
        generatePath(row, col);
    }
    
    /**
     * Generates a path by trying to find a non-visited field in a direction.
     * If it is possible, move there and connect target field by setting field in between and target to path.
     * If not, try different direction.
     * If no direction is possible, go back and try remaining directions of the last visited field.
     * Order of directions to try will be shuffled for every visited field.
     * Uneven side dimensions are required for this method to work.
     * @param row Int representing the row in 2D-char-array.
     * @param col Int representing the column in 2D-char-array.
     */
    
    private void generatePath(int row, int col) {
        
       Directions[] shuffledDirections = Helper.shuffleArray(Directions.values());
       
       char pathSymbol = Symbols.PATH.getSign();
       
       for (Directions direction : shuffledDirections) {
           switch (direction) {
           case NORTH:
               if (row - 2 <= 0)
                   continue;
               if (maze[row-2][col] != pathSymbol) {
                   maze[row-2][col] = pathSymbol;
                   maze[row-1][col] = pathSymbol;
                   generatePath(row-2, col);
               }
               break;
           case EAST:
               if (col + 2 >= width)
                   continue;
               if (maze[row][col+2] != pathSymbol) {
                   maze[row][col+2] = pathSymbol;
                   maze[row][col+1] = pathSymbol;
                   generatePath(row, col+2);
               }
               break;
           case SOUTH:
               if (row + 2 >= height)
                   continue;
               if (maze[row+2][col] != pathSymbol) {
                   maze[row+2][col] = pathSymbol;
                   maze[row+1][col] = pathSymbol;
                   generatePath(row+2, col);
               }
               break;
           case WEST:
               if (col - 2 <= 0)
                   continue;
               if (maze[row][col-2] != pathSymbol) {
                   maze[row][col-2] = pathSymbol;
                   maze[row][col-1] = pathSymbol;
                   generatePath(row, col-2);
               }
               break;
           default:
               break;
           }
       }
   }

}
