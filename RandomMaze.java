
public class RandomMaze {
    
    private static final int MINRANGE = 5;
    private static final int MAXRANGE = 35;
    
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
        setEntryPoint();
        setExitPoint();
    }
    
    private void setEntryPoint() {
        int side = getRandomInRange(1, 4);
        int row;
        int col;
        do {
            row = 0;
            col = 0;
            switch (side) {
            case 1:                         // left
                while (maze[row][col+1] != Symbols.EMPTY.getSign()) {
                    row = getRandomInRange(0, HEIGHT-1);
                }
                break;
            case 2:                         // right
                while (maze[row][WIDTH-2] != Symbols.EMPTY.getSign()) {
                    row = getRandomInRange(0, HEIGHT-1);
                }
                col = WIDTH-1;
                break;
            case 3:                         // up
                while (maze[row+1][col] != Symbols.EMPTY.getSign()) {
                    col = getRandomInRange(0, WIDTH-1);
                }
                break;
            case 4:                         // down
                while (maze[HEIGHT-2][col] != Symbols.EMPTY.getSign()) {
                    col = getRandomInRange(0, WIDTH-1);
                }
                row = HEIGHT-1;
                break;
            default:
                break;
            }
        } while (isEdge(row, col));
        startingPoint = new Point(row, col);
        maze[row][col] = Symbols.START.getSign();
    }
    
    private void setExitPoint() {
        int row;
        int col;
        do {
            row = 0;
            col = 0;
            
            if (startingPoint.getX() == 0) {
                while (maze[HEIGHT-2][col] != Symbols.EMPTY.getSign())
                    col = getRandomInRange(0, WIDTH-1);
                row = HEIGHT-1;
            } else if (startingPoint.getX() == HEIGHT-1) {
                while (maze[row+1][col] != Symbols.EMPTY.getSign())
                    col = getRandomInRange(0, WIDTH-1);
            } else if (startingPoint.getY() == 0) {
                while (maze[row][WIDTH-2] == Symbols.EMPTY.getSign()) {
                    row = getRandomInRange(0, HEIGHT-1);
                }
                col = WIDTH-1;
            } else if (startingPoint.getY() != 0) {
                while (maze[row][col+1] != Symbols.EMPTY.getSign()) {
                    row = getRandomInRange(0, HEIGHT-1);
                }
            }
        } while (isEdge(row, col));
        maze[row][col] = Symbols.END.getSign();
    }
    
    private boolean isEdge(int row, int col) {
        return ((row == 0 && col == 0) || (row == 0 && col == WIDTH-1) || (row == HEIGHT-1 && col == 0) || (row == HEIGHT-1 && col == WIDTH-1));
    }
    
    public char[][] getMaze() {
        return maze;
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
    
    private Directions[] shuffleArray(Directions[] toShuffle) {
        for (int i = 0; i < toShuffle.length; i++) {
            int randomPosition = getRandomInRange(0, toShuffle.length-1);
            Directions temp = toShuffle[i];
            toShuffle[i] = toShuffle[randomPosition];
            toShuffle[randomPosition] = temp;
        }
        return toShuffle;
    }
    
    private void generatePath(int row, int col) {

       Directions[] shuffledDirections = shuffleArray(Directions.values());

       for (Directions direction : shuffledDirections) {
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
                   maze[row][col + 1] = Symbols.EMPTY.getSign();
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
