/**
 * Created by stephen on 11/27/15.
 */
public class Life {

    private final static int M = 25;
    private final static int N = 75;
    private char[][] oldMatrix;
    private char[][] currentMatrix;

    public Life() {
        Double randomizer;
        oldMatrix = new char[M + 2][N + 2];
        currentMatrix = new char[M + 2][N + 2];
        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                randomizer = Math.random();
                if (randomizer >= 0.7) {
                    currentMatrix[i][j] = 'X';
                } else {
                    currentMatrix[i][j] = '.';
                }
            }
        }

    }
    public Life(String filename) {
        oldMatrix = new char[M + 2][N + 2];
        currentMatrix = new char[M + 2][N + 2];
        String lineReader;
        FileStringReader myFile = new FileStringReader(filename);
        // Initialize current matrix with '.'
        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                currentMatrix[i][j] = '.';
            }
        }
        for (int mc = 0; mc < M; mc++) {
            lineReader = (myFile.readLine());
            for (int nc = 0; nc < N; nc++) {
                currentMatrix[mc + 1][nc + 1] = lineReader.charAt(nc);
            }
        }
    }


    public void printCurrent() {
        for (int i = 0; i < currentMatrix.length; i++) {
            System.out.print(i + ": ");
            if (i < 10) {
                System.out.print(" ");
            }
            for (int j = 0; j < currentMatrix[i].length; j++) {
                System.out.print(currentMatrix[i][j] + "");
            }
            System.out.print("\n");
        }
    }

    public static int countNeighbors(char[][] world, int iM, int iN) {
        int neighborCount = 0;

        for (int a = iN - 1; a <= iN + 1; a++) {
            if (world[iM - 1][a] == 'X') {
                neighborCount++;
            }
        }
        if (world[iM][iN - 1] == 'X') { neighborCount++; }
        if (world[iM][iN + 1] == 'X') { neighborCount++; }

        for (int c = iN - 1; c <= iN + 1; c++) {
            if (world[iM + 1][c] == 'X') {
                neighborCount++;
            }
        }
        return neighborCount;
    }

    public boolean isEmpty(char[][] world) {
        return true;
    }

    public void nextGen() {
        // Push current into oldMatrix
        oldMatrix = currentMatrix;
        currentMatrix = new char[M + 2][N + 2];

        // Initialize new currentMatrix
        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                currentMatrix[i][j] = '.';
            }
        }

        for (int aM = 1; aM <= M; aM++) {
            for (int aN = 1; aN <= N; aN++) {
                if (oldMatrix[aM][aN] == 'X') { // If alive
                    if ((countNeighbors(oldMatrix, aM, aN) == 2) || (countNeighbors(oldMatrix, aM, aN) == 3)) {
                        currentMatrix[aM][aN] = 'X';
                    } else {
                        currentMatrix[aM][aN] = '.';
                    }
                } else { // If dead
                    if (countNeighbors(oldMatrix, aM, aN) == 3) {
                        currentMatrix[aM][aN] = 'X';
                    }
                }
            }
        }
    }


}
