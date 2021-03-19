package gfs;

import java.awt.image.BufferedImage;
import java.util.Random;

class Car implements Runnable {
	String name;
	int gasMeter;
	int fuelOnDemand;
	final int DEFAULT_V = 2;
	int gasMeterLimit;
	int velocity = DEFAULT_V;
	BufferedImage image;
	int xCar1 = 0;
	GasStation gs;

	private static int totalTime = 0, totalCars = 0;
	private int tankCar = 100;
//Total number of cars
	private int customerNumber; // The customer number to set
	// Random value, and for
	// intermediate output
	private Pump myPump; // The pump the customer
	// should use /** * Constructor: Set the customerNumber and pump. */

	public Car(String name, int gasMeterLimit, BufferedImage image, int customerNumber, Pump pump, GasStation gs) {
		this.name = name;
		this.gasMeter = (int) ((Math.random() * (gasMeterLimit - 1)) + 1);
		fuelOnDemand = (int) ((Math.random() * (gasMeterLimit - gasMeter)) + gasMeter);
		this.image = image;
		gasMeter = (new Random()).nextInt(99);
		this.myPump = pump;
		this.customerNumber = customerNumber;
		this.gs = gs;
	}

	/** * Static function to calculate average at the end of * simulation. */
	public static float calcAverage() {
		return totalTime / totalCars;
	}

	/** * Thread that implements the pump. */
	public void run() {
		try {
			Thread.sleep(new Random().ints(1, 4).findFirst().getAsInt() * 1000);
			while (xCar1 <= myPump.xPump) {
				xCar1 += velocity;
				gs.repaint();

				Thread.sleep(10);
			}

			myPump.usePump(this);
			myPump.pumpGas(this);
			myPump.leave(this);
			while (xCar1 <= gs.getWidth()) {
				xCar1 += velocity;
				gs.repaint();

				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
