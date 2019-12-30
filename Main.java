package maze;

public class Main {
    
    public static void main(String[] args) {

        MazeGenerator generator = new MazeGenerator();
        
        char[][] otherMaze = generator.getRandomMaze();
        char[][] yetAnotherMaze = generator.getRandomMaze();   

        Robot bb8 = new Robot();
        bb8.findExit();
        
        Helper.tryToSleep(7000);     
        
        bb8.setNewMaze(otherMaze);
        bb8.findExit();
        
        Helper.tryToSleep(7000);
        
        Robot r2d2 = new Robot(yetAnotherMaze);
        r2d2.findExit();
    }
}
