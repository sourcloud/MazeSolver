package maze;

public class MazeGenerator {
    
    private static final int MINRANGE = 3;
    private static final int MAXRANGE = 35;
    
    private final int HEIGHT;
    private final int WIDTH;
  
    private Point pathStartingPoint;
    private char[][] maze;
    
    MazeGenerator() {
        HEIGHT = Helper.getUnevenRandomInRange(MINRANGE, MAXRANGE);
        WIDTH = Helper.getUnevenRandomInRange(MINRANGE, MAXRANGE);
        initializeMaze();
        initializePathStartingPoint();
        generatePath(pathStartingPoint);
        setEntryAndExit();
    }
    
    public char[][] getMaze() {
        return maze;
    }
    
    private int getValidCol(int row, int col) {
        while (maze[row][col] != Symbols.EMPTY.getSign()) {
            col = Helper.getRandomInRange(0, WIDTH - 1);
        }
        return col;
    }
    
    private int getValidRow(int row, int col) {
        while (maze[row][col] != Symbols.EMPTY.getSign()) {
            row = Helper.getRandomInRange(0, HEIGHT - 1);
        }
        return row;
    }
    
    
    private void setEntryAndExit() {
        Sides[] fieldSides = {Sides.TOP, Sides.BOTTOM, Sides.LEFT, Sides.RIGHT}; 
        int entryRow = 0;
        int entryCol = 0;
        int exitRow = 0;
        int exitCol = 0;
        int index = Helper.getRandomInRange(0, fieldSides.length-1);        
        switch (fieldSides[index]) {        
        case TOP:
            exitRow = HEIGHT-1;
            entryCol = getValidCol(entryRow + 1, entryCol);
            exitCol = getValidCol(exitRow - 1, exitCol);            
            break;
        case BOTTOM:
            entryRow = HEIGHT-1;
            entryCol = getValidCol(entryRow - 1, entryCol);
            exitCol = getValidCol(exitRow + 1, exitCol);
            break;
        case LEFT:
            exitCol = WIDTH - 1;
            entryRow = getValidRow(entryRow, entryCol + 1);
            exitRow = getValidRow(exitRow, exitCol - 1);
            break;
        case RIGHT:
            entryCol = WIDTH - 1;
            entryRow = getValidRow(entryRow, entryCol - 1);
            exitRow = getValidRow(exitRow, exitCol + 1);
            break;
        default:
            break;
        }
        maze[entryRow][entryCol] = Symbols.START.getSign();
        maze[exitRow][exitCol] = Symbols.END.getSign();
    }
    
    private void initializeMaze() {
        maze = new char[HEIGHT][WIDTH];
        Helper.fill2DArray(maze, Symbols.WALL.getSign());
    }
    
    private void initializePathStartingPoint() {
        int row = Helper.getUnevenRandomInRange(0, HEIGHT - 1);
        int col = Helper.getUnevenRandomInRange(0, WIDTH - 1);
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
               if (col + 2 >= WIDTH)
                   continue;
               if (maze[row][col+2] != pathSymbol) {
                   maze[row][col+2] = pathSymbol;
                   maze[row][col+1] = pathSymbol;
                   generatePath(row, col+2);
               }
               break;
           case SOUTH:
               if (row + 2 >= HEIGHT)
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
