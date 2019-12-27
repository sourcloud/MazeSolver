
public class Robot {
    
    public static enum Sides {LEFT, RIGHT, FRONT}
    
    private char[][] mazeToNavigate;
    private char[][] movementRecord;
    private char directionSymbol;
    private Point position;
    private Point direction;
    private int stepCount;
    private int turnCount;

    /**
     * Constructs robot by initializing step and turn count, the maze its in,
     * movement record, position, looking direction and setting the direction
     * symbol.
     * 
     * @param maze An two-dimensional array of chars representing a maze.
     */

    Robot(char[][] maze) {
        stepCount = 0;
        turnCount = 0;
        mazeToNavigate = maze;
        initializeMovementRecord();
        initializePosition();
        initializeDirection();
        setDirectionSymbol();
    }
    
    public void turnToSide(Sides sideToTurn) {
        direction.swapCoordinates();
        switch(sideToTurn) {
        case RIGHT:
            if (direction.getX() == 0)
                direction.invert();
            break;
        case LEFT:
            if (direction.getY() == 0)
                direction.invert();
            break;
        default:
            break;
        }
        setDirectionSymbol();
        increaseTurnCount();
    }

    /**
     * Makes the robot turn eventually, move forward and saves the move to the record.
     */

    public void moveToSideAndRecord(Sides sideToGo) {
        if (sideToGo == Sides.RIGHT || sideToGo == Sides.LEFT)
            turnToSide(sideToGo);
        stepForward();
        recordMove();
    }
    
    /**
     * Checks if robot has 
     * @param side
     * @return
     */
    
    public boolean hasSymbolToTheSide(Sides side, Symbols symbol) {
        Point positionOfSide = getPositionOfSide(side);
        char sign = symbol.getSign();
        int row = positionOfSide.getX();
        int col = positionOfSide.getY();
        return mazeToNavigate[row][col] == sign;
    }

    public boolean hasSymbolOnPosition(Symbols symbol) {
        int row = position.getX();
        int col = position.getY();
        char sign = symbol.getSign();
        return mazeToNavigate[row][col] == sign;
    }

    /**
     * Prints maze to console including the current position of robot.
     */

    public void printPositionInMaze() {
        System.out.println("Steps: " + stepCount + "\tTurns: " + turnCount + "\n");
        for (int row = 0; row < mazeToNavigate.length; row++) {
            for (int col = 0; col < mazeToNavigate[row].length; col++) {
                if (position.hasCoordinates(row, col))
                    System.out.print(directionSymbol + " ");
                else
                    System.out.print(mazeToNavigate[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints amount of steps the robot made to console.
     */

    public void printStepCount() {
        System.out.println("The robot took " + stepCount + " steps!");
    }

    /**
     * Prints amount of turns the robot did to console.
     */

    public void printTurnCount() {
        System.out.println("The robot did turn " + turnCount + " times!");
    }

    /**
     * Prints record of movement to console.
     */

    public void printMovementRecord() {
        System.out.println("The robot took the following route: \n");
        for (char[] row : movementRecord) {
            for (char col : row)
                System.out.print(col + " ");
            System.out.println();
        }
    }

    /**
     * Initializes record of movement as 2D map according to the size of maze.
     */

    private void initializeMovementRecord() {
        movementRecord = new char[mazeToNavigate.length][mazeToNavigate[0].length];
        for (char[] row : movementRecord)
            for (int col = 0; col < row.length; col++)
                row[col] = Symbols.EMPTY.getSign();
    }

    /**
     * Initializes position of robot according to starting point in maze.
     */

    private void initializePosition() {
        char sign = Symbols.START.getSign();
        for (int row = 0; row < mazeToNavigate.length; row++)
            for (int col = 0; col < mazeToNavigate[row].length; col++)
                if (mazeToNavigate[row][col] == sign)
                    position = new Point(row, col);
    }

    /**
     * Initializes looking direction of robot according to its starting position.
     */

    private void initializeDirection() {
        direction = (position.getX() == 0) 
                        ? Directions.SOUTH.getCoordinates()
                        : (position.getY() == 0) 
                            ? Directions.EAST.getCoordinates()
                            : (position.getX() == mazeToNavigate.length) 
                                ? Directions.NORTH.getCoordinates() 
                                : Directions.WEST.getCoordinates();
    }
    
    /**
     * Gets the position to the side of robot.
     * @param sideToGetPositionOf Representing the 
     * @return A point representing the position to the given side of robot.
     */
    
    private Point getPositionOfSide(Sides sideToGetPositionOf) {
        Point directionCopy = direction.getCopy();
        if (sideToGetPositionOf == Sides.RIGHT) {
            directionCopy.swapCoordinates();
            if (directionCopy.getX() == 0)
                directionCopy.invert();
        }
        return position.plus(directionCopy);
    }

    /**
     * Sets direction symbol according to direction in which the robot is looking.
     */

    private void setDirectionSymbol() {
        Symbols symbolToDirection;
        String directionName;
        for (Directions directionToCheck : Directions.values())
            if (directionToCheck.isEqualTo(direction)) {
                directionName = directionToCheck.name();
                symbolToDirection = Symbols.valueOf(directionName);
                directionSymbol = symbolToDirection.getSign();
            }
    }
    
    /**
     * Makes the robot move forward and increases the step count.
     */

    private void stepForward() {
        position.add(direction);
        increaseStepCount();
    }

    /**
     * Saves the looking direction of the robot to the record.
     */

    private void recordMove() {
        int row = position.getX();
        int col = position.getY();
        movementRecord[row][col] = directionSymbol;
    }

    /**
     * Increases the number of steps the robot made.
     */

    private void increaseStepCount() {
        stepCount++;
    }

    /**
     * Increases the number of turns the robot made.
     */

    private void increaseTurnCount() {
        turnCount++;
    }
    
}
