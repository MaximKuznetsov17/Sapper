package Sapper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Position(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public GameState getState() {
        return state;
    }

    public Box getBox(Position position) {
        if (flag.get(position) == Box.OPENED) {
            return bomb.get(position);
        } else {
            return flag.get(position);
        }
    }

    public void pressLeftButton(Position position) {
        if (gameOver()) {
            return;
        }
        openBox(position);
        checkWinner();
    }

    private void openBox(Position position) {
        switch (flag.get(position)) {
            case OPENED: setOpenedToCloseBoxesAroundNumber(position); return;
            case FLAGGED: return;
            case CLOSED: {
                switch (bomb.get(position)) {
                    case ZERO: {
                        openBoxAround(position);
                        return;
                    }
                    case BOMB: {
                        openBombs(position);
                        return;
                    }
                    default: {
                        flag.setOpenedToBox(position);
                        return;
                    }
                }
            }

        }
    }

    private void setOpenedToCloseBoxesAroundNumber(Position position) {
        if (bomb.get(position) != Box.BOMB) {
            if (flag.getCountOfFlaggedBoxesAround(position) == bomb.get(position).getNumber()) {
                for (Position around : Ranges.getPositionAround(position)) {
                    if (flag.get(around) == Box.CLOSED) {
                        openBox(around);
                    }
                }
            }
        }
    }

    private void openBombs(Position position) {
        state = GameState.BOMBED;
        flag.setBombedToBox(position);
        for (Position coord : Ranges.getAllPositions()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBombBox(coord);
            } else {
                flag.setNoBombToFlagedSafeBox(coord);
            }
        }
    }

    private void openBoxAround(Position position) {
        flag.setOpenedToBox(position);
        for (Position around : Ranges.getPositionAround(position)) {
            openBox(around);
        }
    }

    public void pressRightButton(Position position) {
        if (gameOver()) {
            return;
        }
        flag.toggleFlaggedToBox(position);
    }

    private boolean gameOver() {
        if (state == GameState.PLAYED) {
            return false;
        }
        start();
        return true;
    }

    private void checkWinner() {
        if (state == GameState.PLAYED) {
            if (flag.getCountOfClosesBoxes() == bomb.getTotalBomb()) {
                state = GameState.WINNER;
            }
        }
    }
}
