
public enum Directions {
   
    EAST (new Point(0,1)),
    SOUTH (new Point(1,0)),
    WEST (new Point(0,-1)),
    NORTH (new Point(-1,0));
    
    private final Point DIRECTION;
    
    Directions(Point direction) {
        this.DIRECTION = direction;
    }
    
    public Point getCoordinates() {
        return DIRECTION.getCopy();
    }
    
    public boolean isEqualTo(Point direction) {
        return this.DIRECTION.isEqualTo(direction);
    }
}
