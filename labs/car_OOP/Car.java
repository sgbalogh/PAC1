/**
 * Created by stephen on 11/8/15.
 */
public class Car {

    private int posX;
    private int posY;
    private char color;
    private boolean ignition;

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

}
