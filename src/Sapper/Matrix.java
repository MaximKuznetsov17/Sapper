package Sapper;

class Matrix {
    private Box[][] matrix;

    Matrix (Box defaultBox) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for(Position position : Ranges.getAllPosition()) {
            matrix[position.x][position.y] = defaultBox;
        }
    }

    Box get(Position position) {
        if (Ranges.inRange(position)){
            return matrix[position.x][position.y];
        }
        return null;
    }

    void set(Position position, Box box) {
        if (Ranges.inRange(position)) {
            matrix[position.x][position.y] = box;
        }
    }
}
