package gfs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class GasStation extends JLabel {

	static final int TOTAL_CARS = 1;

	BufferedImage background;
	BufferedImage golf;
	BufferedImage duster;
	BufferedImage fiesta;
	BufferedImage truck;

	Car car1;
	Car car2;
	Car car3;
	Car fuelTruck;

	int xGolf = 0;
	int xDuster = 0;
	int xFord = 0;

	Pump pump2;
	Pump pump1;

	public GasStation() throws IOException {
		background = ImageIO.read(new File("Images/station.png"));
		golf = ImageIO.read(new File("Images/1.png"));
		duster = ImageIO.read(new File("Images/3.png"));
		fiesta = ImageIO.read(new File("Images/2.png"));
		truck = ImageIO.read(new File("Images/truck.png"));
		pump2 = new Pump(2, "pump2", 100, 10, 100, 330);
		pump1 = new Pump(1, "pump1", 100, 10, 100, 20);
		car1 = new Car("Golf", 100, golf, 1, pump1, this);
		car2 = new Car("Duster", 100, duster, 2, pump2, this);
		car3 = new Car("Fiesta", 100, fiesta, 3, pump2, this);
		fuelTruck = new Car("Truck", 100, truck, 4, pump1, this);
		Thread th1 = new Thread(car1);
		Thread th2 = new Thread(car2);
		Thread th3 = new Thread(car3);
		th1.start();
		th2.start();
		th3.start();
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(background, 0, 0, this);
		g.drawImage(car1.image, car1.xCar1, 260, this);
		g.drawImage(car2.image, car2.xCar1, 260, this);
		g.drawImage(car3.image, car3.xCar1, 260, this);
		g.setFont(new Font("arial", Font.BOLD, 17));
		g.setColor(Color.RED);
		g.drawString("Pump1 : \n" + pump1.existingFuel + "L", 22, 250);
		g.drawString("Pump2 : " + pump2.existingFuel + "L", 320, 250);
	}

}
