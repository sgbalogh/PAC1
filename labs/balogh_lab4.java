/**
 * Created by Stephen Balogh on 9/30/15.
 */
import java.util.Scanner;
public class balogh_lab4 {
    public static void main(String[] args){

        /* Begin declaring variables and setting initial values. */

        char carColorChar = randomColor();
        Scanner userInput = new Scanner(System.in); // This object used for all user-input
        int userSelection = 0; // For first-level menu options during while-loop
        boolean dontExit = true; // Controller for while-loop
        String userDirection = ""; // Used during second-level menu option, nested under "change position"
        int locX = (randPosition()); // Assign initial X value using randPosition()
        int locY = (randPosition()); // Assign initial Y value using randPosition()
        int distanceMove = 0;
        boolean ignition = false; // Assign initial ignition value

        /* End of variable declaration and value allocation. */

        System.out.print("* Initial Vehicle State *\n");
        report(carColorChar, ignition, locX, locY); // Generate initial report

        /* Begin while-loop, will allow user to change position, ignition unlimited number
         * of times until they EXIT program by passing in option '3' at the main menu. */

        while (dontExit) {
            System.out.println("\nSo what do you want to do now?\n1. toggle the ignition on/off\n" + "" +
                    "2. change the position of the car\n3. quit this program");
            userSelection = userInput.nextInt(); // Collect selection from user
            switch (userSelection) {
                case 1:
                    ignition = toggleIgnition(ignition); // Use toggleIgnition() to change swap value
                    break;
                case 2:
                    /* Uses do/while-loop to ensure that user input is one of two acceptable options, else
                     * they will be asked again. */
                    do {
                        System.out.println("Ok. Move in which direction?\nH. Horizontal\nV. Vertical");
                        userDirection = userInput.next(); // Gathers second-level menu option
                    } while (!(userDirection.equals("H") || userDirection.equals("V")));

                    if (userDirection.equals("H")) {
                        System.out.println("Move horizontally how far? Enter negative values for left, positive " +
                                "for right.");
                        distanceMove = userInput.nextInt();
                        System.out.println("Attempting to move horizontally, " + distanceMove + " steps.");
                        locX = moveHorizontal(locX, distanceMove, ignition); // Method returns new X value
                    } else if (userDirection.equals("V")) {
                        System.out.println("Move vertically how far? Enter negative values for up, positive for down.");
                        distanceMove = userInput.nextInt();
                        System.out.println("Attempting to move vertically, " + distanceMove + " steps.");
                        locY = moveVertical(locY, distanceMove, ignition); // Method returns new Y value
                    }
                    break;
                case 3:
                    System.out.println("\nAdios. Here is the car as you left it:"); // Exit message
                    dontExit = false; // Switches controller of while-loop to false
                    break;
            }

            report(carColorChar, ignition, locX, locY);

        }
    }

    public static char randomColor() {
        /* Uses random number generator, casts result to integer, then assigns characters representing color
         * of car to each of the five possible integers (0-4). */
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
        /* Used to generate report of car status, including its color, ignition state, grid position, and
        * a graphical representation of that grid position. Returns nothing, but prints directly to console
        * when invoked. */
        System.out.println();
        String carColorString = "";
        String ignitionStatus = "";
        switch (carColorChar) {
            /* Associates a color character with the name of that color as a String. */
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
        if (ignition) {
            ignitionStatus = "on";
        } else {
            ignitionStatus = "off";
        }

        System.out.println("Color: " + carColorString + "\nIgnition: " + ignitionStatus + "\nLocation: (X: " + x +
                ", Y: " + y + ")\n");

        /* Begin grid position drawing. */

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
    }

    public static int randPosition(){
        /* Generates a random integer value from 1-20. */
        return ((int)(Math.random() * 20.00)) + 1;
    }

    public static boolean toggleIgnition(boolean currentIgnition) {
        /* Gives inverse of current ignition state. */
        return !currentIgnition;
    }

    public static int moveHorizontal(int x, int distanceMove, boolean ignition) {
        /* Moves vehicle horizontally given an initial position and distance to move, assuming ignition is 'true';
         * will only return a new integer position if the value does not go over the grid dimensions. */
        int newPos = 0;
        if (ignition) {
            if ( ((x + distanceMove) <= 20 ) && ((x + distanceMove) >= 1 ) ) {
                return (x + distanceMove);
            } else {
                System.out.println("\nTHAT WAS TOO FAR!!! The car remains put.");
                return (x);
            }
        } else {
            System.out.println("\nThe car is off. You need to turn it on before moving. It remains put.");
            return x;
        }
    }

    public static int moveVertical(int y, int distanceMove, boolean ignition) {
        /* Does the exact same thing as moveHorizontal(). */
        int newPos = 0;
        if (ignition) {
            if ( ((y + distanceMove) <= 20 ) && ((y + distanceMove) >= 1 ) ) {
                return (y + distanceMove);
            } else {
                System.out.println("\nTHAT WAS TOO FAR!!! The car remains put.");
                return (y);
            }
        } else {
            System.out.println("\nThe car is off. You need to turn it on before moving. It remains put.");
            return y;
        }
    }
}
