// Stephen Balogh, Sept. 17, 2015
// 'java balogh_lab2' to run

// import javax.swing.JOptionPane; // Uncomment this import statement if window output used
import java.util.Scanner;

public class balogh_lab2 {
	public static void main(String args[]) {

		System.out.print("\nThis program computes the difference between two times.\nPlease enter " +
				"both times in HHMMSS format, using 24-hour (not AM/PM) times.\n\n");
		Scanner collect = new Scanner(System.in);
		System.out.print("Enter first time: ");
		int input1Int = collect.nextInt();
		System.out.print("Enter second time: ");
		int input2Int = collect.nextInt();

		// Seperate input times into hour/min/sec integer values
		int hours_in1 = input1Int / 10000;
		int hours_in2 = input2Int / 10000;
		int min_in1 = (input1Int - (hours_in1 * 10000)) / 100;
		int min_in2 = (input2Int - (hours_in2 * 10000)) / 100;
		int sec_in1 = (input1Int - (hours_in1 * 10000) - (min_in1 * 100));
		int sec_in2 = (input2Int - (hours_in2 * 10000) - (min_in2 * 100));

		// Convert input time into seconds
		int secondsTime1 = (hours_in1 * 3600) + (min_in1 * 60) + sec_in1;
		int secondsTime2 = (hours_in2 * 3600) + (min_in2 * 60) + sec_in2;

		// Find difference in seconds between two inputs
		int diffTotalSeconds = secondsTime1 - secondsTime2;

		// Convert difference in seconds into hour/min/sec values
		int diffHours = diffTotalSeconds / 3600;
		int diffMin = (diffTotalSeconds % 3600) / 60;
		int diffSec = (diffTotalSeconds % 3600) % 60;

		// Add separate hour/min/sec values into string that represents HHMMSS difference
		int diff = (diffHours * 10000) + (diffMin * 100) + diffSec;

		// Print time difference on console
		System.out.print("Time difference: " + diff);

		// Print time difference on dialog window
		//JOptionPane.showMessageDialog(null, "Time difference: " + diff);	

		// The rest of this class is just me experimenting; it is not directly relevant to the assignment!
		String preposition;
		if (diffTotalSeconds >= 0) {
			preposition = "earlier than";
		} else {
			preposition = "later than";
		}

		System.out.print("\n(Your second time is " + Math.abs(diffHours) + " hour[s], " +
				Math.abs(diffMin) + " minute[s], and " + Math.abs(diffSec) + " second[s] " +
				preposition + " the first time)\n");

	}

}