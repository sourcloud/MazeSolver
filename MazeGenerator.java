package maze;

public class MazeGenerator {
    
    private static final int MINRANGE = 3;
    private static final int MAXRANGE = 35;
    private static final int MININDEX = 0;
    
    private enum Sides {LEFT, RIGHT, TOP, BOTTOM}
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
    
    private int getValidCol(int row) {
        int col = MININDEX;
        row = (row == MININDEX) ? ++row : --row;
        while (maze[row][col] != Symbols.EMPTY.getSign()) {
            col = Helper.getRandomInRange(MININDEX, widthIndex);
        }
        return col;
    }
    
    private int getValidRow(int col) {
        int row = MININDEX;
        col = (col == MININDEX) ? ++col : --col;
        while (maze[row][col] != Symbols.EMPTY.getSign()) {
            row = Helper.getRandomInRange(MININDEX, heightIndex);
        }
        return row;
    }
    
    
    private void setEntryAndExit() {
        Sides[] fieldSides = Sides.values();       
        int index = Helper.getRandomInRange(MININDEX, fieldSides.length-1);       
        Sides side = fieldSides[index];
        
        int entryRow = (side == Sides.RIGHT) ? getValidRow(widthIndex) : (side == Sides.LEFT) ? getValidRow(MININDEX) : (side == Sides.BOTTOM) ? heightIndex : 0;
        int exitRow = (side == Sides.LEFT) ? getValidRow(widthIndex) : (side == Sides.RIGHT) ? getValidRow(MININDEX) : (side == Sides.TOP) ? heightIndex : 0;
        
        int entryCol = (side == Sides.BOTTOM) ? getValidCol(heightIndex) : (side == Sides.TOP) ? getValidCol(MININDEX) : (side == Sides.RIGHT) ? widthIndex : 0;
        int exitCol = (side == Sides.TOP) ? getValidCol(heightIndex) : (side == Sides.BOTTOM) ? getValidCol(MININDEX) :(side == Sides.LEFT) ? widthIndex : 0;
        
        maze[entryRow][entryCol] = Symbols.START.getSign();
        maze[exitRow][exitCol] = Symbols.END.getSign();
    }
    
    private void initializeSize() {
        height = Helper.getUnevenRandomInRange(MINRANGE, MAXRANGE);
        width = Helper.getUnevenRandomInRange(MINRANGE, MAXRANGE);
        heightIndex = height - 1;
        widthIndex = width - 1;
    }
    
    private void initializeMaze() {
        maze = new char[height][width];
        Helper.fill2DArray(maze, Symbols.WALL.getSign());
    }
    
    private void initializePathStartingPoint() {
        int row = Helper.getUnevenRandomInRange(MININDEX, heightIndex);
        int col = Helper.getUnevenRandomInRange(MININDEX, widthIndex);
        pathStartingPoint = new Point(row, col);
        maze[row][col] = Symbols.EMPTY.getSign();
    }
    
    public void generatePath(Point point) {
        int row = point.getX();
        int col = point.getY();
        generatePath(row, col);
    }
    
    private void generatePath(int row, int col) {
        
       Directions[] shuffledDirections = Helper.shuffleArray(Directions.values());
       
       char pathSymbol = Symbols.EMPTY.getSign();
       
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
