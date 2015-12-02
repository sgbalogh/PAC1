/**
 * Created by Stephen Balogh on 11/27/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */
public class Life {

    private final static int M = 25;
    private final static int N = 75;
    private char[][] oldMatrix;
    private char[][] currentMatrix;
    private int generationCount = 0;

    public Life() { // Constructor that begins from a randomly generated initial state
        Double randomizer;
        oldMatrix = new char[M + 2][N + 2];
        currentMatrix = new char[M + 2][N + 2];
        generationCount = 1; // First generation
        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++) {
                randomizer = Math.random();
                if (randomizer >= 0.85) {
                    currentMatrix[i][j] = 'X';
                } else {
                    currentMatrix[i][j] = '.';
                }
            }
        }
    }

    public Life(String filename) { // Constructor that reads an initial state
        oldMatrix = new char[M + 2][N + 2];
        currentMatrix = new char[M + 2][N + 2];
        generationCount = 1; // First generation
        String lineReader;
        FileStringReader myFile = new FileStringReader(filename);
        for (int mc = 0; mc < M; mc++) {
            lineReader = (myFile.readLine());
            for (int nc = 0; nc < N; nc++) {
                currentMatrix[mc + 1][nc + 1] = lineReader.charAt(nc);
            }
        }
    }

    public void printCurrent() {
        for (int i = 1; i < currentMatrix.length - 1; i++) {
            System.out.print(i + ": ");
            if (i < 10) {
                System.out.print(" ");
            }
            for (int j = 1; j < currentMatrix[i].length - 1; j++) {
                System.out.print(currentMatrix[i][j] + "");
            }
            System.out.print("\n");
        }
    }

    public int countNeighbors(char[][] world, int iM, int iN) {
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

    public void nextGen() {
        // Push current into oldMatrix
        oldMatrix = currentMatrix;
        currentMatrix = new char[M + 2][N + 2];
        this.generationCount++;
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

    public boolean hasChanged() {
        int nonmatching = 0;
        for (int aM = 1; aM <= M; aM++) {
            for (int aN = 1; aN <= N; aN++) {
                if (currentMatrix[aM][aN] != oldMatrix[aM][aN]) {
                    nonmatching++;
                }
            }
        }
        if (nonmatching > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(char[][] generation) {
        int nonEmpty = 0;
        for (int aM = 1; aM <= M; aM++) {
            for (int aN = 1; aN <= N; aN++) {
                if (generation[aM][aN] != '.') {
                    nonEmpty++;
                }
            }
        }
        if (nonEmpty == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean currentlyEmpty() {
        return isEmpty(this.currentMatrix);
    }

    public int getGeneration() {
        return this.generationCount;
    }
}
