/**
 * Created by stephen on 11/27/15.
 */
public class PlayLife {

    public static void main(String[] args) {
        Life myLife = new Life();
        myLife.printCurrent();

        for (int i = 1; i < 1000; i++) {
            System.out.println("\nGeneration " + i);
            myLife.nextGen();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLife.printCurrent();
        }


    }

}
