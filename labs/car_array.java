import java.util.Scanner;

/**
 * Created by stephen on 10/7/15.
 */
public class car_array {

    public static void main(String[] args) {

        /* Begin declaring variables and setting initial values. */

        Scanner userInput = new Scanner(System.in); // This object used for all user-input
        String userSelection = ""; // For first-level menu options during while-loop
        boolean dontExit = true; // Controller for while-loop
        String userDirection = ""; // Used during second-level menu option, nested under "change position"
        int distanceMove = 0;

        int selectedCar = 0;
        final int NUM_CARS = 10;

        // Array initialization

        boolean ignitionAr[] = new boolean[NUM_CARS];
        int locXAr[] = new int[NUM_CARS];
        int locYAr[] = new int[NUM_CARS];
        char colorAr[] = new char[NUM_CARS];

        /*
        * OK... I swear the purpose of the following array, 'superMap[]', will become clear...
        * It is for a feature of the program that is not required by the spec though.
        * */
        String superMap[] = new String[400];
        /*
        * End of weird stuff.
        * */


        int thisLocX = 0;
        int thisLocY = 0;
        boolean thisIgnition = false;
        char thisColor;


        for (int x = 0; x < NUM_CARS; x++) {
            locXAr[x] = randPosition();
            locYAr[x] = randPosition();
            colorAr[x] = randomColor();
        }


        /* End of variable declaration and value allocation. */
        superMap(locXAr, locYAr, superMap, colorAr);
        System.out.println("\nA total of " + NUM_CARS + " vehicles are presently being tracked.");
        while (dontExit) {

            do {
                System.out.println("Which vehicle do you want to inspect or modify?\n\nPlease select a vehicle by " +
                        "typing its number: (1 - " + NUM_CARS + "), or type '0' to QUIT.");

                selectedCar = userInput.nextInt();
            }
            while (!((0 <= selectedCar) && (selectedCar <= NUM_CARS))); // Make sure that user inputs a valid car number or 0 for exit

            if (selectedCar == 0) {
                dontExit = false;
                System.out.print("Sayonara!!");
            } else {

                thisLocX = locXAr[selectedCar - 1];
                thisLocY = locYAr[selectedCar - 1];
                thisIgnition = ignitionAr[selectedCar - 1];
                thisColor = colorAr[selectedCar - 1];

                System.out.println("\nStatus of selected vehicle (" + selectedCar + " of " + NUM_CARS + "):");

                report(thisColor, thisIgnition, thisLocX, thisLocY); // Generate initial report

        /* Begin while-loop, will allow user to change position, ignition unlimited number
         * of times until they EXIT program by passing in option '3' at the main menu. */


                System.out.println("\nSo what do you want to do with vehicle " + selectedCar + "?\n1. toggle the ignition on/off\n" + "" +
                        "2. change the position of the car\n3. choose a different car\n4. produce status reports and maps for all " +
                        NUM_CARS + " vehicles\n5. create \"super map\" of all vehicles displayed on one map\nQ. quit this program");
                userSelection = userInput.next(); // Collect selection from user


                if (userSelection.equals("1")) {
                    thisIgnition = toggleIgnition(thisIgnition); // Use toggleIgnition() to change swap value
                    ignitionAr[selectedCar - 1] = thisIgnition; // Sends change back to array
                } else if (userSelection.equals("2")) {
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
                        thisLocX = moveHorizontal(thisLocX, distanceMove, thisIgnition); // Method returns new X value
                        locXAr[selectedCar - 1] = thisLocX; // Save new value returned from method back to array
                    } else if (userDirection.equals("V")) {
                        System.out.println("Move vertically how far? Enter negative values for up, positive for down.");
                        distanceMove = userInput.nextInt();
                        System.out.println("Attempting to move vertically, " + distanceMove + " steps.");
                        thisLocY = moveVertical(thisLocY, distanceMove, thisIgnition); // Method returns new Y value
                        locYAr[selectedCar - 1] = thisLocY; // Save new value returned from method back to array
                    }
                } else if (userSelection.equals("3")) {
                    System.out.println("Ok.");

                } else if (userSelection.equals("4")) {
                    for (int x = 0; x < NUM_CARS; x++) {
                        System.out.println("\nVehicle " + (x + 1) + " (of " + NUM_CARS + ")");
                        report(colorAr[x], ignitionAr[x], locXAr[x], locYAr[x]);
                    }
                    System.out.println("Finished.");
                } else if (userSelection.equals("5")) {
                    superMap(locXAr, locYAr, superMap, colorAr);
                } else if (userSelection.equals("Q")) {
                    System.out.println("\nAdios. Here is vehicle " + selectedCar + " as you left it:"); // Exit message
                    dontExit = false; // Switches controller of while-loop to false
                } else {
                    System.out.println("\nPlease enter one of the options...");
                }

                System.out.println("\nStatus of selected vehicle (" + selectedCar + " of " + NUM_CARS + ")");
                report(thisColor, thisIgnition, thisLocX, thisLocY);
            }
        }
    }

    public static char randomColor() {
        /* Uses random number generator, casts result to integer, then assigns characters representing color
         * of car to each of the five possible integers (0-4). */
        int colorChoose = (int) (Math.random() * 5.00);
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

    public static int randPosition() {
        /* Generates a random integer value from 1-20. */
        return ((int) (Math.random() * 20.00)) + 1;
    }

    public static boolean toggleIgnition(boolean currentIgnition) {
        /* Gives inverse of current ignition state. */
        return !currentIgnition;
    }

    public static void superMap(int[] x, int[] y, String[] map, char[] color) {
          /* THIS IS AN EXPERIMENT */
        for (int i = 0; i < 400; i++) { // Initialize superMap array with "- " indexes
            map[i] = "- ";
        }
        for (int i = 0; i < 10; i++) { // Write current values (from X, Y, and color arrays) into superMap
            int currX = x[i];
            int currY = y[i];
            int longX = ((currX + ((currY * 20) - 20)) - 1);
            char currColor = color[i];

            if (map[longX].equals("- ")) {
                map[longX] = currColor + " ";
            } else {
                map[longX] = "* ";
            }
        }
        System.out.println("\n\"Super Map\" of all vehicles\nCollisions (two or more vehicles with same position) marked with \'*\'");

        for (int i = 0; i < 400; i++) {
            System.out.print(map[i]);
            if (((i + 1) % 20) == 0) {
                System.out.print("\n");
            }
        }
    }

    public static int moveHorizontal(int x, int distanceMove, boolean ignition) {
        /* Moves vehicle horizontally given an initial position and distance to move, assuming ignition is 'true';
         * will only return a new integer position if the value does not go over the grid dimensions. */
        int newPos = 0;
        if (ignition) {
            if (((x + distanceMove) <= 20) && ((x + distanceMove) >= 1)) {
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
            if (((y + distanceMove) <= 20) && ((y + distanceMove) >= 1)) {
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
