package gfs;

import java.io.IOException;

import javax.swing.JFrame;

public class MainJ extends JFrame {
	public MainJ() throws IOException {
		setSize(600, 440);
		GasStation g = new GasStation();
		add(g);
		this.setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MainJ m = new MainJ();
		m.setVisible(true);
	}

}
