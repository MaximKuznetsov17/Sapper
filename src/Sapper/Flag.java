package Sapper;

class Flag {
    private Matrix flagMap;

    public void start() {
        flagMap = new Matrix(Box.CLOSED);
    }

    public Box get(Position position) {
        return flagMap.get(position);
    }

    public void setOpenedToBox(Position position) {
        flagMap.set(position,Box.OPENED);
    }
}
