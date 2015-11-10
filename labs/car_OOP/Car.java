/**
 * Stephen Balogh, 11/8/2015
 * PAC 1, New York University
 * Professor Evan Korth
 */
import java.util.Scanner;
public class Car {

    // Defines private instance variables
    private int posX;
    private int posY;
    private char color;
    private boolean ignition;

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

        // Begin menu
        while (dontExit) {
            do {
                System.out.println("Which vehicle do you want to inspect or modify?\n\nPlease select a vehicle by " +
                        "typing its number: (1 - " + myCars.length + "), or type '0' to QUIT.");
                selectedCar = userInput.nextInt();
            }
            while (!((0 <= selectedCar) && (selectedCar <= myCars.length))); // Makes sure that user inputs a valid car number or 0 for exit

            if (selectedCar == 0) {
                dontExit = false;
                System.out.println("Sayonara!!");
            } else {
                System.out.println("Car " + selectedCar + ":\n" + myCars[selectedCar - 1].toString());

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
                    Car.superMap(myCars);
                } else if (userSelection.equals("Q")) {
                    System.out.println("\nAdios. Here is vehicle " + selectedCar + " as you left it:"); // Exit message
                    dontExit = false; // Switches controller of while-loop to false
                } else {
                    System.out.println("\nPlease enter one of the options...");
                }
                System.out.println("\nStatus of selected vehicle (" + selectedCar + " of " + myCars.length + ")");
                System.out.println(myCars[selectedCar - 1].toString());
            }
        }
    }

    Car(int x, int y, char color, boolean ignition) {
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.ignition = ignition;
    }

    Car() {
        this(randomPos(), randomPos(), randomColor(), false);
    }

    public static int randomPos() {
        return ((int) (Math.random() * 20.00)) + 1;
    }

    public static char randomColor() {

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

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public boolean getIgnition() {
        return this.ignition;
    }

    public String getColor() {
        return "" + this.color;
    }

    public void toggleIgnition() {
        this.ignition = !(this.ignition);
    }

    public void moveHorizontal(int places) {
        int newX = places + this.posX;
        if (this.ignition) {
            if ((newX <= 20) && (newX >= 1)) {
                this.posX = newX;
            } else {
                System.out.println("That was too far!\nYour movement would result in an X position of "
                        + newX +".\nCar remains in place.\n");
            }
        } else {
            System.out.println("Ignition is off; the car can't move!");
        }
    }

    public void moveVertical(int places) {
        int newY = places + this.posY;
        if (this.ignition) {
            if ((newY <= 20) && (newY >= 1)) {
                this.posY = newY;
            } else {
                System.out.println("That was too far!\nYour movement would result in an Y position of "
                        + newY +".\nCar remains in place.\n");
            }
        } else {
            System.out.println("Ignition is off; the car can't move!");
        }
    }

    public void move(int xPlaces, int yPlaces) {
        this.moveHorizontal(xPlaces);
        this.moveVertical(yPlaces);
    }

    public String toString() {
        String graphicRepresentation = "\n\nOn map:\n";
        for (int a = 1; a < 21; a++) {
            if (a == this.getPosY()) {
                for (int b = 1; b < 21; b++) {
                    if (b == this.getPosX()) {
                        graphicRepresentation += this.getColor() + " ";
                    } else {
                        graphicRepresentation += "- ";
                    }
                }
                graphicRepresentation += "\n";
            } else {
                graphicRepresentation += "- - - - - - - - - - - - - - - - - - - -\n";
            }
        }
        return "Car info:\nColor: " + this.getColor() + "\nIgnition: " + this.getIgnition() + "\nLocation: " +
                this.getPosX() + ", " + this.getPosY() + graphicRepresentation;

    }

    public static void superMap(Car[] carArray) {

        String[] superMap = new String[400];
        for (int i = 0; i < 400; i++) { // Initialize superMap array with "- " indexes
            superMap[i] = "- ";
        }
        for (int i = 0; i < carArray.length; i++) {
            int longX = ((carArray[i].getPosX() + ((carArray[i].getPosY() * 20) - 20)) - 1);

            if (superMap[longX].equals("- ")) {
                superMap[longX] = carArray[i].getColor() + " ";
            } else {
                superMap[longX] = "* ";
            }
        }

        System.out.println("\n\"Super Map\" of all vehicles\nCollisions (two or more vehicles with same position) marked with \'*\'");

        for (int i = 0; i < 400; i++) {
            System.out.print(superMap[i]);
            if (((i + 1) % 20) == 0) {
                System.out.print("\n");
            }
        }
    }
}
