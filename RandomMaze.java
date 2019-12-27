
public class RandomMaze {
    
    private static final int MINRANGE = 7;
    private static final int MAXRANGE = 20;
    
    private final int HEIGHT;
    private final int WIDTH;
    
    private Point startingPoint;
    private char[][] maze;
    
    RandomMaze() {
        HEIGHT = getRandomOddSize();
        WIDTH = getRandomOddSize();
        initializeMaze();
        initializeStartingPoint();
        setStartingPointInMaze();
        generatePath(startingPoint.getX(), startingPoint.getY());
    }
    
    public char[][] getMaze() {
        return maze;
    }
    
    
    public void printMaze() {
        for (char[] row : maze) {
            for (char col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    private int getRandomInRange(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
    
    
    private int getRandomOddSize() {
        int randomNumber = 0;
        while (randomNumber % 2 == 0) {
            randomNumber = getRandomInRange(MINRANGE, MAXRANGE);
        }
        return randomNumber;       
    }
   
    
    private void initializeMaze() {
        maze = new char[HEIGHT][WIDTH];
        for (char[] row : maze)
            for (int col = 0; col < row.length; col++)
                row[col] = Symbols.WALL.getSign();
    }
    
    private void initializeStartingPoint() {
        int row = 0;
        int col = 0;
        
        while (row % 2 == 0) {
            row = getRandomInRange(0, HEIGHT-1);
        }
        while (col % 2 == 0) {
            col = getRandomInRange(0, WIDTH-1);
        }
        startingPoint = new Point(row, col);
    }

    private void setStartingPointInMaze() {
        int row = startingPoint.getX();
        int col = startingPoint.getY();
        maze[row][col] = Symbols.EMPTY.getSign();
    }
   
    
   private void generatePath(int row, int col) {
       
       for (Directions direction : Directions.values()) {
           switch (direction) {
           case NORTH:
               if (row - 2 <= 0)
                   continue;
               if (maze[row-2][col] != Symbols.EMPTY.getSign()) {
                   maze[row-2][col] = Symbols.EMPTY.getSign();
                   maze[row-1][col] = Symbols.EMPTY.getSign();
                   generatePath(row - 2, col);
               }
               break;
           case EAST:
               if (col + 2 >= WIDTH)
                   continue;
               if (maze[row][col + 2] != Symbols.EMPTY.getSign()) {
                   maze[row][col + 2] = Symbols.EMPTY.getSign();
                   maze[row][col + 2] = Symbols.EMPTY.getSign();
                   generatePath(row, col + 2);
               }
               break;
           case SOUTH:
               if (row + 2 >= HEIGHT)
                   continue;
               if (maze[row+2][col] != Symbols.EMPTY.getSign()) {
                   maze[row+2][col] = Symbols.EMPTY.getSign();
                   maze[row+1][col] = Symbols.EMPTY.getSign();
                   generatePath(row + 2, col);
               }
               break;
           case WEST:
               if (col - 2 <= 0)
                   continue;
               if (maze[row][col-2] != Symbols.EMPTY.getSign()) {
                   maze[row][col-2] = Symbols.EMPTY.getSign();
                   maze[row][col-1] = Symbols.EMPTY.getSign();
                   generatePath(row, col - 2);
               }
               break;
           default:
               break;
           }
       }
   }

}
