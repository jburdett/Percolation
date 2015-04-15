/**
 * Soil is the representation of the model
 * Currently it only stores the world at the current level
 * and next level of soil.
 * @author Jordan Burdett
 *
 */
public class Soil {
	private int[] current_row;
	private int[] next_row;
	private int width;
	private int HARD;
	private int SEMI;
	private int SAT;
	private double porosity;
	
	/**
	 * Constructor
	 * @param width	how many cells wide the world is
	 * @param HARD	the value for hard cells
	 * @param SEMI	the value for semi-permeable cells
	 * @param SAT	the value for saturated cells
	 */
	public Soil(int width, int HARD, int SEMI, int SAT){
		this.width = width;
		this.HARD = HARD;
		this.SAT = SAT;
		this.SEMI = SEMI;
	}
	
	/**
	 * Gets the current_row.
	 * @return the current_row
	 */
	public int[] getCurrent_row() {
		return current_row;
	}
	
	/**
	 * Gets the value of SAT.
	 * @return the sAT
	 */
	public int getSAT() {
		return SAT;
	}

	/**
	 * Sets the value of porosity.
	 * @param porosity the porosity to set
	 */
	public void setPorosity(double porosity) {
		this.porosity = porosity;
	}

	/**
	 * Performs setup for world representation
	 * @param ticker 	needed for makeRow method
	 * @param porosity 	sets the world's value of porosity
	 */
	public void setup(int ticker, double porosity){
		this.porosity = porosity;
		current_row = new int[width];
		next_row = new int[width];
		// Initialise first row
		for(int i=0; i<width; i++) {
			if(i % 2 == 0) {
				current_row[i] = SAT;
			} else {
				current_row[i] = HARD;
			}
		}
		next_row = makeRow(ticker);
	}
	
	/**
	 * Makes a new unsaturated soil row.
	 * @param ticker used to keep track of checkboard pattern
	 * @return new row
	 */
	public int[] makeRow(int ticker) {
		int[] row = new int[width];
		int offset = ticker % 2;
		for (int i=0; i<width; i++) {
			if((i+offset)%2 == 0) {
				row[i] = HARD;
			} else {
				row[i] = SEMI;
			}
		}
		return row;
	}
	
	/**
	 * Checks if the current row has any saturated cells
	 */
	public boolean hasSaturated(){
		for (int i : current_row) {
			if (i == SAT) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Calculates a random value and checks it against
	 * the porosity value
	 * @return whether random is under porosity
	 */
	private boolean porosityTest(){
		double random = Math.random();
		if (random < porosity) {
			return true;
		}
		return false;
	}
	
	/**
	 * The update step for the world. 
	 * Calculates saturated cells for the next row of soil
	 * and then sets it to the current row
	 * @param ticker tracks number of updates
	 */
	public void update(int ticker){
		int i = 0;
		// First cell treated special
		// World does not wrap around
		if (current_row[i] == SAT) {
			// Check diagonally right
			if (porosityTest()) {
				next_row[i+1] = SAT;
			}
		}
		
		// Update rest of cells except last one
		for (i=1; i<(width-1); i++) {
			if (current_row[i] == SAT) {
				// Check diagonally left
				if (porosityTest()) {
					next_row[i-1] = SAT;
				}
				// Check diagonally right
				if (porosityTest()) {
					next_row[i+1] = SAT;
				}
			}
		}
		
		// Last cell treated special
		// World does not wrap around
		if (current_row[i] == SAT) {
			// Check diagonally left
			if (porosityTest()) {
				next_row[i-1] = SAT;
			}
		}
		
		// Assign new active row
		current_row = next_row;
		// Make a fresh new row
		next_row = makeRow(ticker);
	}

}
