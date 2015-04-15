import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * OutputManager handles all the output of the program including
 * writing to output files and printing to the command line
 * @author Jordan Burdett
 */
public class OutputManager {
	private PrintWriter main_out;
	private PrintWriter total_sat;
	private PrintWriter row_sat;
	private boolean print_results;
	private char[] format;
	
	/**
	 * Constructor
	 * @param main_outf 	filename for main_out writer
	 * @param total_satf	filename for total_sat writer
	 * @param row_satf		filename for row_sat writer
	 * @param print_results	bool to set print_results
	 * @param format		representation of cells
	 */
	public OutputManager(String main_outf, String total_satf, 
			String row_satf, boolean print_results, char[] format) {
		try {
			this.main_out = new PrintWriter(main_outf);
			this.total_sat = new PrintWriter(total_satf);
			this.row_sat = new PrintWriter(row_satf);
			this.print_results = print_results;
			this.format = format;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes data to file and command line.
	 * @param world	the current world representation
	 * @param stats	the current stats
	 */
	public void report(Soil world, Stats stats) {
		int[] row = world.getCurrent_row();
		int total = stats.getTotalSaturated();
		int current = stats.getCurrentSaturated();
		
		// Outputs world representation
		for (int i : row) {
			if(print_results) {
				System.out.print(format[i]);
			}
			main_out.write(format[i]);
		}
		if(print_results) {
			System.out.println();
		}
		main_out.write("\n");
		
		// Outputs total saturation data
		total_sat.write(String.valueOf(total));
		total_sat.write("\n");
		
		// Outputs current row saturation data
		row_sat.write(String.valueOf(current));
		row_sat.write("\n");
	}
	
	/**
	 * Prints a message indicating when model terminates
	 */
	public void endMessage(int ticker) {
		System.out.println("Program finishes at " + ticker);
	}
	
	/**
	 * Closes the writers.
	 */
	public void finish() {
		main_out.close();
		total_sat.close();
		row_sat.close();
	}
}
