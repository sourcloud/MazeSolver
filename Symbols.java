package maze;

public enum Symbols {
    
    EAST ('>'),
    SOUTH ('v'),
    WEST ('<'),
    NORTH ('^'),
    WALL('.'),
    PATH(' '),
    START('B'),
    END('A');
    
    private final char SIGN;
    
    Symbols(char sign) {
        this.SIGN = sign;
    }
    
    public char getSign() {
        return SIGN;
    }
    
}
