/**
 * Created by stephen on 9/30/15.
 */
import java.util.Scanner;
public class balogh_lab4 {
    public static void main(String[] args){
        int inputX = 0;
        int inputY = 0;
        char carColorChar = randomColor();
        Scanner userInput = new Scanner(System.in);
        int userSelection = 0;
        boolean dontExit = true; // Controller for while-loop
        String userDirection = "";

        // Initial car value determination
        int locX = (randPosition());
        int locY = (randPosition());
        int distanceMove = 0;
        boolean ignition = false;
        // End allocation of initial values

        // Display of initial values
        System.out.print("* Initial Vehicle State *\n");
        report(carColorChar, ignition, locX, locY);
        // End display
        while (dontExit) {
            System.out.println("\nSo what do you want to do now?\n1. toggle the ignition on/off\n2. change the position of the car\n3. quit this program");
            userSelection = userInput.nextInt();
            switch (userSelection) {
                case 1:
                    ignition = toggleIgnition(ignition);
                    break;
                case 2:
                    do {
                        System.out.println("Ok. Move in which direction?\nH. Horizontal\nV. Vertical");
                        userDirection = userInput.next();
                    } while (!(userDirection.equals("H") || userDirection.equals("V")));
                    if (userDirection.equals("H")) {
                        System.out.println("Move horizonally how far? Negative values for left, positive for right.");
                        distanceMove = userInput.nextInt();
                        System.out.println("Attempting to move horizontally, " + distanceMove + " steps.");
                        locX = moveHorizontal(locX, distanceMove, ignition);
                    } else if (userDirection.equals("V")) {
                        System.out.println("Move vertically how far? Negative values for up, positive for down.");
                        distanceMove = userInput.nextInt();
                        System.out.println("Attempting to move vertically, " + distanceMove + " steps.");
                        locY = moveVertical(locY, distanceMove, ignition);
                    }
                    break;
                case 3:
                    System.out.println("Adios.");
                    dontExit = false;
                    break;
            }
            if (dontExit) {
                report(carColorChar, ignition, locX, locY);
            }
        }
    }

    public static char randomColor() {
        int colorChoose = (int)(Math.random() * 5.00);
        char carColorChar = ' ';
        switch (colorChoose) {
            case 0:
                carColorChar = 'R';
                break;
            case 1:
                carColorChar = 'G';
                break;
            case 2:
                carColorChar = 'B';
                break;
            case 3:
                carColorChar = 'W';
                break;
            case 4:
                carColorChar = 'S';
                break;
        }

        return carColorChar;

    }

    public static void report(char carColorChar, boolean ignition, int x, int y) {
        System.out.println();
        String carColorString = "";
        switch (carColorChar) {
            case 'R':
                carColorString = "red";
                break;
            case 'G':
                carColorString = "green";
                break;
            case 'B':
                carColorString = "blue";
                break;
            case 'W':
                carColorString = "white";
                break;
            case 'S':
                carColorString = "silver";
                break;
        }

        System.out.println("Color: " + carColorString + "\nIgnition status: " + ignition + "\nLocation: (X: " + x + ", Y: " + y + ")\n");

        for (int a = 1; a < 21; a++) {
            if (a == y) {
                for (int b = 1; b < 21; b++) {
                    if (b == x) {
                        System.out.print(carColorChar + " ");
                    } else {
                        System.out.print("- ");
                    }
                }
                System.out.print("\n");
            } else {
                System.out.println("- - - - - - - - - - - - - - - - - - - -");
            }
        }
        return;
    }

    public static int randPosition(){
        return ((int)(Math.random() * 20.00)) + 1;
    }

    public static boolean toggleIgnition(boolean currentIgnition) {
        return !currentIgnition;
    }

    public static int moveHorizontal(int x, int distanceMove, boolean ignition) {
        int newPos = 0;
        if (ignition) {
            if ( ((x + distanceMove) <= 20 ) && ((x + distanceMove) >= 1 ) ) {
                return (x + distanceMove);
            } else {
                System.out.println("THAT WAS TOO FAR!!! The car remains put.");
                return (x);
            }
        } else {
            System.out.println("The car is off. You need to turn it on before moving. It remains put.");
            return x;
        }
    }

    public static int moveVertical(int y, int distanceMove, boolean ignition) {
        int newPos = 0;
        if (ignition) {
            if ( ((y + distanceMove) <= 20 ) && ((y + distanceMove) >= 1 ) ) {
                return (y + distanceMove);
            } else {
                System.out.println("THAT WAS TOO FAR!!! The car remains put.");
                return (y);
            }
        } else {
            System.out.println("The car is off. You need to turn it on before moving. It remains put.");
            return y;
        }
    }
}
