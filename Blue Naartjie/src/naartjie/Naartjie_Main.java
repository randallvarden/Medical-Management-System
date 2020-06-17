package naartjie;

import java.awt.EventQueue;

public class Naartjie_Main {

	public static void main(String[] args) {
		Naartjie_Main naartjie = new Naartjie_Main();
		naartjie.start();
	}
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Naartjie_login frame = new Naartjie_login();
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
