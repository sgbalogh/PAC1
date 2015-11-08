/**
 * Created by stephen on 11/8/15.
 */

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int selectedCar = 0; // For first menu selection (selecting vehicle)
        boolean dontExit = true; // Controller for menu while-loop
        String userSelection; // For second menu (possible actions)
        String userDirection; // For third menu (Moving horizontal or vertical)
        int distanceMove = 0;


        // Initialize cars with random colors, positions, and ignition = false
        Car myCars[] = new Car[10];
        for (int i = 0; i < myCars.length; i++) {
            myCars[i] = new Car();
        }
        while (dontExit) {
            do {
                System.out.println("Which vehicle do you want to inspect or modify?\n\nPlease select a vehicle by " +
                        "typing its number: (1 - " + myCars.length + "), or type '0' to QUIT.");

                selectedCar = userInput.nextInt();
            }
            while (!((0 <= selectedCar) && (selectedCar <= myCars.length))); // Make sure that user inputs a valid car number or 0 for exit

            if (selectedCar == 0) {
                dontExit = false;
                System.out.print("Sayonara!!");
            } else {
                /* Retrieve corresponding car data from array and assign to variables */

                System.out.println("Car " + selectedCar + ":\n" + myCars[selectedCar - 1].toString());

        /* Begin while-loop, will allow user to change position, ignition unlimited number
         * of times until they EXIT program by passing in option '3' at the main menu. */

                System.out.println("\nSo what do you want to do with vehicle " + selectedCar + "?\n1. toggle the ignition on/off\n" + "" +
                        "2. change the position of the car\n3. choose a different car\n4. produce status reports and maps for all " +
                        myCars.length + " vehicles\n5. create \"super map\" of all vehicles displayed on one map\nQ. quit this program");
                userSelection = userInput.next(); // Collect selection from user

                if (userSelection.equals("1")) {
                    myCars[selectedCar - 1].toggleIgnition();
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
                        myCars[selectedCar - 1].moveHorizontal(distanceMove);
                    } else if (userDirection.equals("V")) {
                        System.out.println("Move vertically how far? Enter negative values for up, positive for down.");
                        distanceMove = userInput.nextInt();
                        System.out.println("Attempting to move vertically, " + distanceMove + " steps.");
                        myCars[selectedCar - 1].moveVertical(distanceMove);
                    }
                } else if (userSelection.equals("3")) {
                    System.out.println("Ok."); // Exits back to car selection menu

                } else if (userSelection.equals("4")) {
                    for (int x = 1; x <= myCars.length; x++) { // Makes maps for each vehicle
                        System.out.println("\nVehicle " + x + " (of " + myCars.length + ")");
                        System.out.println(myCars[x - 1].toString());
                    }
                    System.out.println("Finished.");
                } else if (userSelection.equals("5")) {
                    System.out.println("Can't do that quite yet buddy...");
                } else if (userSelection.equals("Q")) {
                    System.out.println("\nAdios. Here is vehicle " + selectedCar + " as you left it:"); // Exit message
                    dontExit = false; // Switches controller of while-loop to false
                } else {
                    System.out.println("\nPlease enter one of the options...");
                }

                System.out.println("\nStatus of selected vehicle (" + selectedCar + " of " + myCars.length + ")");
                myCars[selectedCar - 1].toString();
            }
        }
    }
}

