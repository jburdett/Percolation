import java.util.Scanner;

/**
 * InputManager handles all input into the program.
 * It also validates this input to be correct.
 * @author Jordan Burdett
 *
 */
public class InputManager {
	
	private Scanner scanner;
	private double porosity;
	private int max_runs;
	private boolean print_results;
	private int last_check;
	private final int DEFAULTNUM = -1;
	private final boolean DEFAULTBOOL = true;
	
	/**
	 * Constructor
	 */
	public InputManager() {
		this.scanner = new Scanner(System.in);
		porosity = DEFAULTNUM;
		max_runs = DEFAULTNUM;
		print_results = DEFAULTBOOL;
	}
	
	/**
	 * Gets porosity value.
	 * @return the porosity
	 */
	public double getPorosity() {
		return porosity;
	}

	/**
	 * Gets max_runs value.
	 * @return the max_runs
	 */
	public int getMaxRuns() {
		return max_runs;
	}

	/**
	 * Gets print_results value.
	 * @return the print_results
	 */
	public boolean isPrintResults() {
		return print_results;
	}
	
	/**
	 * Gets last_check value.
	 * @return the last_check
	 */
	public int getLastCheck() {
		return last_check;
	}

	/**
	 * Prompts the user for input of the porosity variable
	 * and then the max_runs variable.
	 * Also validates these values are correct.
	 * @param ticker to remember at what update this method is called
	 */
	public void getInput(int ticker) {
		// Checks when last time user was prompted for input
		last_check = ticker;
		porosity = DEFAULTNUM;
		max_runs = DEFAULTNUM;
		
		// Gets value for porosity and validates it
		while(porosity<0 || porosity>1) {
			System.out.println("Enter porosity value from 0 to 1");
			while(!scanner.hasNextDouble()) {
				System.out.println("Please enter decimal");
				scanner.next();
			}
			porosity = scanner.nextDouble();
		}

		// Gets value for max_runs and validates it
		while(max_runs<1) {
			System.out.println("Enter max number of runs : ");
			while(!scanner.hasNextInt()) {
				System.out.println("Please enter integer");
				scanner.next();
			}
			max_runs = scanner.nextInt();
		}
	}
	
	/**
	 * Prompts the user to indicate whether results should print
	 * to the command line.
	 */
	public void askPrint() {
		System.out.println("Print result to command line?(true/false)");
		// Gets value for print_results and validates it
		while(!scanner.hasNextBoolean()) {
			System.out.println("Please enter true or false");
			scanner.next();
		}
		print_results = scanner.nextBoolean();
	}
	
	/**
	 * Closes the scanners.
	 */
	public void finish() {
		scanner.close();
	}
	
}
