package swingTest;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel implements KeyListener {
	 
	private static final long serialVersionUID = 1L;
	List<Character> moveList = null;
	Ramram r;
	List<Calc> calcs;
	public static Point HeightAndWidth;

	public Map() {
		super();
		moveList = new ArrayList<>();
		setPreferredSize(new Dimension(400, 400));
		HeightAndWidth = new Point(400, 400);
		r = new Ramram();
		calcs = InitCalcs();
		addKeyListener(this);
		Timer t = new Timer(40, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (moveList != null) {
					for (char move : moveList) {
						//change to all boxes colliding with player
						MovableImage m = playerCollidingWith();
						if (m == null)
							r.move(move);
						else {
							movePlayerAndBox(m,move);
						}
					}
					moveList.clear();
				}
				repaint();
			}
		});
		for (Calc c : calcs)
			add(c);
		add(r);
		t.setRepeats(true);
		t.setCoalesce(true);
		t.start();
	}

	protected void movePlayerAndBox(MovableImage m,char move) {
		Point initLoc = m.getLocation();
		if(isDirOnClsnSide(move,m)){
			m.move(move);
			if(!initLoc.equals(m.getLocation())){
				r.move(move);
			}
		}
		else
			r.move(move);
	}
	
	private boolean isDirOnClsnSide(char direction, MovableImage m) {
		Rectangle boxBounds = m.getBounds(), playerBounds = r.getBounds(), collisionRect = boxBounds.intersection(playerBounds);
		double boxCenterX = boxBounds.getCenterX(), boxCenterY = boxBounds.getCenterY(), playerCenterX = playerBounds.getCenterX(),
				playerCenterY = playerBounds.getCenterY();
		switch(direction){
		case 'w':
			if(collisionRect.getWidth() > boxBounds.getWidth()/2 && boxCenterY < playerCenterY)
				return true;
			break;
		case 'a':
			if(collisionRect.getHeight() > boxBounds.getHeight()/2 && boxCenterX < playerCenterX)
				return true;
			break;
		case 's':
			if(collisionRect.getWidth() > boxBounds.getWidth()/2 && boxCenterY > playerCenterY)
				return true;
			break;
		case 'd':
			if(collisionRect.getHeight() > boxBounds.getHeight()/2 && boxCenterX > playerCenterX)
				return true;
			break;
		}
		return false;
	}

	private List<Calc> InitCalcs() {
		List<Calc> result = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			result.add(new Calc());
		}
		return result;
	}

	public MovableImage playerCollidingWith() {
		for (Calc c : calcs) {
			if (areColliding(r, c))
				return c;
		}
		return null;
	}

	public boolean areColliding(MovableImage m1, MovableImage m2) {
		Rectangle box1 = m1.getBounds();
		return box1.intersects(m2.getBounds());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		moveList.add(e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}