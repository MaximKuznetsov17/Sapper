package Sapper;

import java.util.Objects;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
       if (object instanceof Position) {
           Position to = (Position) object;
           return to.x == x && to.y == y;
       }
       return super.equals(object);
    }
}
