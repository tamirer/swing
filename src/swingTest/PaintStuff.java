package swingTest;

import java.awt.Graphics;
import javax.swing.JFrame;

public class PaintStuff extends JFrame {
	private static final long serialVersionUID = 1L;

	public PaintStuff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Map m = new Map();
		addKeyListener(m);
		setContentPane(m);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	public static void main(String[] args) {
		PaintStuff p = new PaintStuff();
		p.pack();
		p.setVisible(true);
	}
}
