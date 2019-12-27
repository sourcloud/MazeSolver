
public class Point {

    private int xPos;
    private int yPos;
    
    /**
     * Constructs a point with x- and y-position 0.
     */
    
    Point() {
        xPos = 0;
        yPos = 0;
    }
    
    /**
     * Constructs a point with given x- and y-Position.
     * @param xPos x-position of point.
     * @param yPos y-position of point.
     */
    
    Point(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    /**
     * Gets x-position of the point.
     * @return An int representing the x-position of the point.
     */
    
    public int getX() {
        return this.xPos;
    }

    /**
     * Gets y-position of the point.
     * @return An int representing the y-position of the point.
     */
    
    public int getY() {
        return this.yPos;
    }
    
    /**
     * Swaps x- and y-position of the point.
     */
    
    public void swapCoordinates() {
        int temp = xPos;
        xPos = yPos;
        yPos = temp;
    }
    
    /**
     * Gets a copy of the point.
     * @return A new Point that has the same coordinates as original point.
     */
    
    public Point getCopy() {
        return new Point(xPos, yPos);
    }
    
    /**
     * Inverts x- and y-position by changing their signs.
     */
    
    public void invert() {
        xPos = -xPos;
        yPos = -yPos;
    }
    
    /**
     * Checks if another instance of Point has the same x- and y-positions as this instance.
     * @param other An instance of Point.
     * @return A boolean answering the question if two instances of Point have the same x- and y-positions.
     */
    
    public boolean isEqualTo(Point other) {
        return (this.xPos == other.xPos && this.yPos == other.yPos);
    }
    
    public boolean hasCoordinates(int xPos, int yPos) {
        return (this.xPos == xPos && this.yPos == yPos);
    }
    
    /**
     * Adds x- and y-position value of other instance of Point to this one's corresponding values.
     * @param other Another instance of Point.
     */
    
    public void add(Point other) {
        this.xPos += other.xPos;
        this.yPos += other.yPos;
    }
    
    /**
     * Creates a new point with x- and y-position values as sum of this and other instance of Point's x- respectively y- values.
     * @param other Another instance of point.
     * @return A new Point with x- and y-position as sum of two instances of Point's corresponding values.
     */
    
    public Point plus(Point other) {
        return new Point(this.xPos + other.xPos, this.yPos + other.yPos);
    }

}
