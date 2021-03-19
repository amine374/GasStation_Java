package gfs;

class Pump {

	private int id;
	String name;
	int FuelLimit;
	int FuelWarning;
	int existingFuel;
	private final int FREE = 0;
	private final int BUSY = 1;
	private int state = FREE;
	private int tankPump = 100;
	int xPump;

	public Pump(int id, String name, int FuelLimit, int FuelWarning, int existingFuel, int xPump) {
		this.id = id;
		this.name = name;
		this.FuelLimit = FuelLimit;
		this.FuelWarning = FuelWarning;
		this.existingFuel = existingFuel;
		this.xPump = xPump;
	}

	/**
	 * * Method to call when a car first wishes to use a pump. It * adds a 1/2
	 * second to the simulated time in the problem.
	 */
	synchronized public void usePump(Car car) {
		try { // Pre-condition processing (guard)
			System.out.println(Thread.currentThread().getName());
			while (true) {
				System.out.println("g " + Thread.currentThread().getName());
				if (state == FREE) {
					car.velocity = 0;
					break;
				}
				System.out.println(Thread.currentThread().getName() + " waiting...");

				wait();
			} // Simulate pulling to the pump by waiting 1/2 second.
			Thread.sleep(500); // Post-condition processing, change state.
			state = BUSY;
			notifyAll();
		} catch (InterruptedException e) {
		}
	}

	/** * Simulate pumping the gas by waiting 5 seconds. */
	synchronized public void pumpGas(Car car) {
		try {
			// Pre-condition processing (guard)
			Thread.sleep(5000);
			while (true) {
				if (state == BUSY) {
					existingFuel -= car.fuelOnDemand;
					car.gasMeter += car.fuelOnDemand;
					break;
				}
				wait();
			} // Simulate pumping gas by waiting 5 seconds.
				// Post-condition processing, no change state needed.
		} catch (InterruptedException e) {
		}
	}

	/** * Leave the pump, freeing it for the next customer. */
	synchronized public void leave(Car car) {
		try {
			// Pre-condition processing (guard)
			while (true) {
				if (state == BUSY) {
					car.velocity = car.DEFAULT_V;
					break;
				}
				wait();
			} // Simulate leaving the pump by waiting 1/2 second.
			Thread.sleep(500); // Post-condition processing, change state
			state = FREE;
			notifyAll();
		} catch (InterruptedException e) {
		}
	}

}
