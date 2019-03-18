package Sapper;

public class Bomb {
    private Matrix bombMap;
    private int totalBomb;

    public Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombCount();
    }

    public int getTotalBomb() {
        return totalBomb;
    }

    public void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBomb; i++) {
            placeBomb();
        }
    }

    public Box get(Position position) {
        return bombMap.get(position);
    }

    private void fixBombCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBomb > maxBombs) {
            totalBomb = maxBombs;
        }
    }

    private void placeBomb() {
        while (true) {
            Position position = Ranges.getRandomPosition();
            if (Box.BOMB == bombMap.get(position)) {
                continue;
            }
            bombMap.set(position, Box.BOMB);
            incNumberAroundBomb(position);
            break;
        }
    }

    private void incNumberAroundBomb(Position position) {
        for (Position around : Ranges.getPositionAround(position)) {
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }
    }
}
