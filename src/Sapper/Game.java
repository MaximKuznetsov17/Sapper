package Sapper;

public class Game {

    private Matrix bombMap;
    private Bomb bomb;
    private Flag flag;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Position(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
    }

    public Box getBox(Position position) {
        if (flag.get(position) == Box.OPENED) {
            return bomb.get(position);
        } else {
            return flag.get(position);
        }
    }

    public void pressLeftButton(Position position) {
        flag.setOpenedToBox(position);
    }
}
