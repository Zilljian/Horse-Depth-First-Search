public class Field {

    private String fieldToString = "Not initialized";
    //private final int stepToX[] = { 1, 2, 2, 1,-1,-2,-2,-1}; // Lists with steps for the first approach.
    //private final int stepToY[] = {-2,-1, 1, 2, 2, 1,-1,-2};

    private int field[][];
    private int OX, OY;
    private int x0, y0, x, y;
    private int minPath;
    private int tempCounter = 0;

    Field(int N, int M, int x0, int y0, int x, int y) {
        OX = N;
        OY = M;
        minPath = OX > OY ? OX : OY;
        this.x0 = x0 - 1;
        this.y0 = y0 - 1;
        this.x = x - 1;
        this.y = y - 1;

        if (isDestination(x0, y0)) fieldToString = "0 0";
        else if (isProper()) {
            initialize();
            findCell(this.x0, this.y0);
            fieldToString = Integer.toString(minPath);
        }
    }

    private void initialize() {
        field = new int[OY][OX];

        for (int i = 0; i < OY; i++){
            for (int j = 0; j < OX; j++){
                field[i][j] = minPath;
            }
        }

        field[y0][x0] = -1;
        fieldToString = "Initialized";
    }

    private boolean isProper() {

        if ((x0 >= OX) || (y0 >= OY) || (x0 < 0) || (y0 < 0)) {
            fieldToString = "Failure: initial position of figure must be in range of field!";
            return false;
        }

        if (((OY < 3) && (OX < 3)) || (OY < 2) || (OX < 2)){
            fieldToString = "Failure: input data don't satisfy the conditions!";
            return  false;
        }

        if ((OY ==3) && (OX == 3) && ((x0 != x) || (y0 != y)) && (x == y) && (x == 2)){
            fieldToString = "Failure: input data don't satisfy the conditions. Purposely set inaccessible cell!";
            return false;
        }

        return true;
    }

// The first approach.
    /*private void findCell(int x, int y) {             // This approach is more clear and less error prone,
        if (minPath >= tempCounter + 1) {               // but in the other hand, it's one and a half times slower then the next one.
            for (int i = 0; i < 8; i++) {
                if (isFit(x + stepToX[i], y + stepToY[i])) {
                    tempCounter++;
                    field[y + stepToY[i]][x + stepToX[i]] = tempCounter;

                    if (isDestination(x + stepToX[i], y + stepToY[i])) minPath = tempCounter;
                    else findCell(x + stepToX[i], y + stepToY[i]);

                    tempCounter--;
                }
            }
        }
    }*/

// The second approach.
    private void findCell(int x, int y){
        if (minPath >= tempCounter + 1) {

            if (isFit(x + 2,y + 1)) {
                tempCounter++;
                field[y + 1][x + 2] = tempCounter;

                if (isDestination(x + 2, y + 1)) minPath = tempCounter;
                else findCell(x + 2, y + 1);

                tempCounter--;
            }

            if (isFit(x + 2,y - 1)) {
                tempCounter++;
                field[y - 1][x + 2] = tempCounter;

                if (isDestination(x + 2, y - 1)) minPath = tempCounter;
                else findCell(x + 2, y - 1);

                tempCounter--;
            }

            if (isFit(x + 1,y + 2)) {
                tempCounter++;
                field[y + 2][x + 1] = tempCounter;

                if (isDestination(x + 1, y + 2)) minPath = tempCounter;
                else findCell(x + 1, y + 2);

                tempCounter--;
            }

            if (isFit(x - 1,y + 2)) {
                tempCounter++;
                field[y + 2][x - 1] = tempCounter;

                if (isDestination(x - 1 , y + 2)) minPath = tempCounter;
                else findCell(x - 1, y + 2);

                tempCounter--;
            }

            if (isFit(x - 2,y + 1)) {
                tempCounter++;
                field[y + 1][x - 2] = tempCounter;

                if (isDestination(x - 2, y + 1)) minPath = tempCounter;
                else findCell(x - 2, y + 1);

                tempCounter--;
            }

            if (isFit(x - 1,y - 2)) {
                tempCounter++;
                field[y - 2][x - 1] = tempCounter;

                if (isDestination(x - 1, y - 2)) minPath = tempCounter;
                else findCell(x - 1, y - 2);

                tempCounter--;
            }

            if (isFit(x + 1,y - 2)) {
                tempCounter++;
                field[y - 2][x + 1] = tempCounter;

                if (isDestination(x + 1, y - 2)) minPath = tempCounter;
                else findCell(x + 1, y - 2);

                tempCounter--;
            }

            if (isFit(x - 2,y - 1)) {
                tempCounter++;
                field[y - 1][x - 2] = tempCounter;

                if (isDestination(x - 2, y - 1)) minPath = tempCounter;
                else findCell(x - 2, y - 1);

                tempCounter--;
            }
        }
    }

    private boolean isDestination(int x1, int y1){
        return (x1 == x) && (y1 == y);
    }

    private boolean isFit (int x, int y) {
        return (x >= 0 && y >= 0 && x < OX && y < OY && field[y][x] > tempCounter + 1);
    }

    @Override
    public String toString () {
        return fieldToString;
    }
}
