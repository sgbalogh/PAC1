/**
 * Created by Stephen Balogh on 11/27/15.
 * PAC 1, Professor Evan Korth
 * New York University
 */
import java.util.Scanner;
public class PlayLife {

    public static void main(String[] args) {
        Life myLife;
        Scanner userInput = new Scanner(System.in);
        String selection;
        String filepath;
        boolean dontExit = true;

        System.out.println("Welcome to the game of life.\n\nDo you want to start from\n1) a randomly generated state, or...\n2) load a " +
                "pre-configured state?\n");
        selection = userInput.next();
        if (selection.equals("1")) {
            myLife = new Life();
        } else if (selection.equals("2")) {
            System.out.println("Please type in the full filepath to the requisite .dat file:" +
            "\n(e.g. \"/Users/username/GameOfLife/worldStates/world.dat\")");
            filepath = userInput.next();
            myLife = new Life(filepath);
        } else {
            System.out.println("Invalid entry... so going to just generate a random initial state.");
            myLife = new Life();
        }

        while (dontExit) {
            System.out.println("Generation " + myLife.getGeneration() + ":");
            myLife.printCurrent();
            if (!myLife.hasChanged()) {
                dontExit = false;
                System.out.println("This generation (" + myLife.getGeneration() + ") is the same as the previous. This is the end of life.");
            } else if (myLife.currentlyEmpty()) {
                dontExit = false;
                System.out.println("This generation (" + myLife.getGeneration() + ") is completely empty. This is the end of life. There will never be more life.");
            } else {
                System.out.println("Progress a generation? (Y/N)");
                selection = userInput.next();
                if (selection.equals("N") || selection.equals("n")) {
                    dontExit = false;
                    System.out.println("Exiting...");
                } else {
                    myLife.nextGen();
                }
            }
        }
    }
}
