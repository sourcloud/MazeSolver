package maze;

public class Robot {
    
    private enum Sides {LEFT, RIGHT, FRONT;}
    private char[][] mazeToNavigate;
    private char[][] movementRecord;
    private char directionSymbol;
    private Point position;
    private Point direction;
    private int stepCount;
    private int turnCount;
  
    /**
     * Constructs robot by initializing step and turn count, a randomly generated maze its in,
     * movement record, position, looking direction and setting the direction symbol.
     */
    
    Robot() {
        stepCount = 0;
        turnCount = 0;
        MazeGenerator generator = new MazeGenerator();
        mazeToNavigate = generator.getRandomMaze();
        initializeMovementRecord();
        initializePosition();
        initializeDirection();
        setDirectionSymbol();
    }

    /**
     * Constructs robot by initializing step and turn count, the maze its in,
     * movement record, position, looking direction and setting the direction
     * symbol.
     * 
     * @param maze An two-dimensional array of chars representing a maze.
     */

    Robot(char[][] maze) {
        setNewMaze(maze);
    }
    
    public void setNewMaze(char[][] maze) {
        stepCount = 0;
        turnCount = 0;
        mazeToNavigate = maze;
        initializeMovementRecord();
        initializePosition();
        initializeDirection();
        setDirectionSymbol();
    }
    
    /**
     * Turns Robot to either left or right side, sets direction symbol and increases turn count.
     * @param sideToTurn
     */
    
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
     * Checks if robot has a given field on its given side.
     * @param sideToCheck Representing side next to robot.
     * @param field Representing 
     * @return Boolean answering if given field is on given side.
     */
    
    public boolean isNextToField(Sides sideToCheck, Symbols field) {
        Point positionOfSide = getPositionOfSide(sideToCheck);
        char sign = field.getSign();
        return Helper.isEntryAtPositionOf2DArrayEqualTo(positionOfSide, mazeToNavigate, sign);
    }
    
    /**
     * Checks if robot is on a given field.
     * @param field 
     * @return
     */

    public boolean isOnField(Symbols field) {
        char sign = field.getSign();
        return Helper.isEntryAtPositionOf2DArrayEqualTo(position, mazeToNavigate, sign);
    }
    
    
    public void findExit() {
        while (!isOnField(Symbols.END)) {                           // While exit not found 
            if (!isNextToField(Sides.RIGHT, Symbols.WALL))          //     If possible, move to the right
                moveToSideAndRecord(Sides.RIGHT);
            else if (!isNextToField(Sides.FRONT, Symbols.WALL))     //     else if possible, move forward
                moveToSideAndRecord(Sides.FRONT);
            else
                turnToSide(Sides.LEFT);                             //     else turn left       
            
            printPositionInMaze(); 
            Helper.tryToSleep(150);
        }
        System.out.println("Exit found!");  // Print recorded path and counts to console.
        printMovementRecord();
        printStepCount();
        printTurnCount();
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
        Helper.print2DArray(movementRecord);
    }

    /**
     * Initializes record of movement as 2D map according to the size of maze.
     */

    private void initializeMovementRecord() {
        movementRecord = new char[mazeToNavigate.length][mazeToNavigate[0].length];
        Helper.fill2DArray(movementRecord, Symbols.EMPTY.getSign());
    }

    /**
     * Initializes position of robot according to starting point in maze.
     */

    private void initializePosition() {
        char sign = Symbols.START.getSign();
        position = Helper.getPositionOfEntryIn2DArray(mazeToNavigate, sign);
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
        Helper.setEntryAtPositionOf2DArray(position, movementRecord, directionSymbol);
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
