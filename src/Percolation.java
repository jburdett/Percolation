
/**
 * 
 */

/**
 * Percolation is the main class of the program that will run the model.
 * The class steps through the basic steps in running a percolation
 * model and passes these responsibilities to the other classes in
 * the project
 * @author Jordan Burdett
 */
public class Percolation {

	/**
	 * Steps through the steps in the percolation model
	 * @param args
	 */
	public static void main(String[] args) {
		// Constants for the percolation
		final int WORLD_WIDTH = 150;
		final String OUTPUT_FILE = "output.txt";
		final String TOTAL_SAT_FILE = "saturation.txt";
		final String ROW_SAT_FILE = "rowsatuartion.txt";
		final int HARD = 0;
		final int SEMI = 1;
		final int SAT = 2;
		final char[] format = {'.', ',', 'x'};
		
		// The representation of the percolation soil
		Soil world;
		// The object that writes the output
		OutputManager writer;
		// Object that stores data as the program runs
		Stats stats;
		// Object that gathers input from user
		InputManager input;
		
		// The Setup Step
		stats = new Stats();
		stats.reset();
		input = new InputManager();
		// Get initial input from user
		input.getInput(stats.getTicker());
		input.askPrint();
		world = new Soil(WORLD_WIDTH, HARD, SEMI, SAT);
		world.setup(stats.getTicker(), input.getPorosity());
		stats.update(world);
		writer = new OutputManager(OUTPUT_FILE, TOTAL_SAT_FILE, 
				ROW_SAT_FILE, input.isPrintResults(), format);
		writer.report(world, stats);
		
		// The Update Step
		// Checks if active row has any saturated cells
		while(world.hasSaturated()){
			// Checks whether max runs have been performed
			if(stats.getTicker()-input.getLastCheck() == 
					input.getMaxRuns()) {
				// Prompt for new input
				input.getInput(stats.getTicker());
				world.setPorosity(input.getPorosity());
			}
			// Objects perform updates
			world.update(stats.getTicker());
			stats.update(world);
			// Output is performed
			writer.report(world, stats);
		}
		
		// Finish message
		writer.endMessage(stats.getTicker());
		// Closes inputs and writers to finish
		writer.finish();
		input.finish();
	}

}
