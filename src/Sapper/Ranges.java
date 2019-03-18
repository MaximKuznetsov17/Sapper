package Sapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ranges {
    private static Position size;
    private static List<Position> allPosition;
    private static Random random = new Random();

    public static void setSize(Position newSize) {
        size = newSize;
        allPosition = new ArrayList<>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                allPosition.add(new Position(x, y));
            }
        }
    }

    public static Position getSize() {
        return size;
    }

    public static List<Position> getAllPositions() {
        return allPosition;
    }

    static boolean inRange(Position position) {
        return position.x >= 0 && position.x < size.x &&
                position.y >= 0 && position.y < size.y;
    }

    static Position getRandomPosition() {
        return new Position(random.nextInt(size.x), random.nextInt(size.y));
    }

    static ArrayList<Position> getPositionAround(Position position) {
        Position around;
        ArrayList<Position> positionList = new ArrayList<>();
        for (int x = position.x - 1; x <= position.x + 1; x++) {
            for (int y = position.y - 1; y <= position.y + 1; y++) {
                if (inRange(around = new Position(x, y))) {
                    if (!around.equals(position)) {
                        positionList.add(around);
                    }
                }
            }
        }
        return positionList;
    }
}
