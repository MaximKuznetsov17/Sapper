package Sapper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Position position) {
        return flagMap.get(position);
    }

    void setOpenedToBox(Position position) {
        flagMap.set(position,Box.OPENED);
        countOfClosedBoxes--;
    }

    private void setFlaggedToBox(Position position) {
        flagMap.set(position,Box.FLAGGED);
    }

    private void setClosedToBox(Position position) {
        flagMap.set(position,Box.CLOSED);
    }

    public void toggleFlaggedToBox(Position position) {
        switch (flagMap.get(position)) {
            case FLAGGED: {
                setClosedToBox(position);
                break;
            }
            case CLOSED: {
                setFlaggedToBox(position);
                break;
            }
        }
    }

    public int getCountOfClosesBoxes() {
        return countOfClosedBoxes;
    }

    public void setBombedToBox(Position position) {
        flagMap.set(position, Box.BOMBED);
    }

    public void setOpenedToClosedBombBox(Position position) {
        if (flagMap.get(position) == Box.CLOSED) {
            flagMap.set(position, Box.OPENED);
        }
    }

    public void setNoBombToFlagedSafeBox(Position position) {
        if (flagMap.get(position) == Box.FLAGGED) {
            flagMap.set(position, Box.NOBOMB);
        }
    }

    public int getCountOfFlaggedBoxesAround(Sapper.Position position) {
        int count = 0;
        for (Position around : Ranges.getPositionAround(position)) {
            if (flagMap.get(around) == Box.FLAGGED) {
                count++;
            }
        }
        return count;
    }
}
