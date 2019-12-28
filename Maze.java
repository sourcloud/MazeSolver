
public class Maze {
    
    public static void main(String[] args) {
        
        Robot bb8 = new Robot();
        
        while (!bb8.hasSymbolOnPosition(Symbols.END)) {                      // While exit not found 
            if (!bb8.hasSymbolToTheSide(Sides.RIGHT, Symbols.WALL))          //     If possible, move to the right
                bb8.moveToSideAndRecord(Sides.RIGHT);
            else if (!bb8.hasSymbolToTheSide(Sides.FRONT, Symbols.WALL))     //     else if possible, move forward
                bb8.moveToSideAndRecord(Sides.FRONT);
            else
                bb8.turnToSide(Sides.LEFT);                                  //     else turn left
            
            bb8.printPositionInMaze(); 
            
            try { 
                Thread.sleep(200);
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        }
        System.out.println("Exit found!");  // Print recorded way and counts to console.
        bb8.printStepCount();
        bb8.printTurnCount();
        bb8.printMovementRecord();
        
    }
}
