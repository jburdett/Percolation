/**
 * Stats stores important data for all classes
 * @author Jordan Burdett
 *
 */
public class Stats {
	private int total_saturated;
	private int current_saturated;
	private int ticker;

	/**
	 * Constructor
	 */
	public Stats() {}
	
	/**
	 * Resets all the stats values.
	 */
	public void reset() {
		total_saturated = 0;
		current_saturated = 0;
		ticker = 0;
	}

	/**
	 * Gets the total_saturated value.
	 * @return the total_saturated
	 */
	public int getTotalSaturated() {
		return total_saturated;
	}

	/**
	 * Gets the current_saturated value.
	 * @return the current_saturated
	 */
	public int getCurrentSaturated() {
		return current_saturated;
	}

	/**
	 * Gets the ticker value.
	 * @return the ticker
	 */
	public int getTicker() {
		return ticker;
	}
	
	/**
	 * Updates the stats from the updated state of the world.
	 * @param world the updated world representation
	 */
	public void update(Soil world) {
		int[] row = world.getCurrent_row();
		int num_sat = 0;
		// Counts number of saturated cells in row
		for (int i : row) {
			if (i == world.getSAT()) {
				num_sat++;
			}
		}
		// Updates stat values
		total_saturated += num_sat;
		current_saturated = num_sat;
		ticker += 1;
	}

}
