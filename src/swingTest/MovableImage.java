package swingTest;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MovableImage extends JPanel {
	private static final long serialVersionUID = 1L;
	Image img;

	public MovableImage(String url) {
		super();
		try {
			img = ImageIO.read(new File("images/" + url)).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		setPreferredSize(new Dimension(100, 100));
	}

	public void move(char c) {
		Point p = getLocation();
		switch (c) {
		case 'w':
			if (p.y >= 5)
				p.y -= 5;
			break;
		case 's':
			if (p.y <= Map.HeightAndWidth.y - getHeight() - 5)
				p.y += 5;
			break;
		case 'a':
			if (p.x >= 5)
				p.x -= 5;
			break;
		case 'd':
			if (p.x <= Map.HeightAndWidth.x - getWidth() - 5)
				p.x += 5;
			break;
		}
		setLocation(p);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		int x = (getWidth() - img.getWidth(null)) / 2;
		int y = (getHeight() - img.getHeight(null)) / 2;
		g2d.drawImage(img, x, y, this);
		g2d.dispose();
	}
}
