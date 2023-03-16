import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Element implements Serializable {

	 Integer kind;
	 Integer layer;
//int num = 0;
int n;
int c;
	static Color[] color = { Color.blue, Color.red, Color.black,
			Color.lightGray, Color.orange, Color.gray, Color.gray, Color.gray,
			Color.gray,Color.orange, Color.blue,Color.green,Color.orange,Color.white };

	static ImageIcon[] im;
	static Image type[] = { new ImageIcon("highDepth.gif").getImage(),
				new ImageIcon("fish.png").getImage(),
			new ImageIcon("whale.gif").getImage(),
			new ImageIcon("lowDepth.gif").getImage(),
			new ImageIcon("island.jpg").getImage(),
			new ImageIcon("engineering.gif").getImage(),
			new ImageIcon("hunter.png").getImage(),
			new ImageIcon("firing.png").getImage(),
			new ImageIcon("gunnery.png").getImage(),
			new ImageIcon("central.png").getImage(),
			new ImageIcon("night.gif").getImage(),
			new ImageIcon("spring.png").getImage(),
			new ImageIcon("automn.png").getImage(),
			new ImageIcon("winter.png").getImage()};

	public Element(MainPanel mp, int x, int y, int kind, int layer) {
		mp.setElement(x, y, layer, this);
		this.kind = new Integer(kind);
		this.layer = new Integer(layer);
//
//		if (kind == 4) {
//			if (((mp.getElement(x, y - 1, 1) != null) && (mp.getElement(x,
//					y - 1, 1).getKind() != 4))
//					|| (mp.getElement(x, y - 1, 1) == null)) {
//				new Element(mp, x, y - 1, 3, 1);
//			}
//			if (((mp.getElement(x, y + 1, 1) != null) && (mp.getElement(x,
//					y + 1, 1).getKind() != 4))
//					|| (mp.getElement(x, y + 1, 1) == null)) {
//				new Element(mp, x, y + 1, 3, 1);
//			}
//			if (((mp.getElement(x - 1, y, 1) != null) && (mp.getElement(x - 1,
//					y, 1).getKind() != 4))
//					|| (mp.getElement(x - 1, y, 1) == null)) {
//				new Element(mp, x - 1, y, 3, 1);
//			}
//			if (((mp.getElement(x + 1, y, 1) != null) && (mp.getElement(x + 1,
//					y, 1).getKind() != 4))
//					|| (mp.getElement(x + 1, y, 1) == null)) {
//				new Element(mp, x + 1, y, 3, 1);
//			}
//			if (((mp.getElement(x - 1, y - 1, 1) != null) && (mp.getElement(
//					x - 1, y - 1, 1).getKind() != 4))
//					|| (mp.getElement(x - 1, y - 1, 1) == null)) {
//				new Element(mp, x - 1, y - 1, 3, 1);
//			}
//			if (((mp.getElement(x - 1, y + 1, 1) != null) && (mp.getElement(
//					x - 1, y + 1, 1).getKind() != 4))
//					|| (mp.getElement(x - 1, y + 1, 1) == null)) {
//				new Element(mp, x - 1, y + 1, 3, 1);
//			}
//			if (((mp.getElement(x + 1, y - 1, 1) != null) && (mp.getElement(
//					x + 1, y - 1, 1).getKind() != 4))
//					|| (mp.getElement(x + 1, y - 1, 1) == null)) {
//				new Element(mp, x + 1, y - 1, 3, 1);
//			}
//			if (((mp.getElement(x + 1, y + 1, 1) != null) && (mp.getElement(
//					x + 1, y + 1, 1).getKind() != 4))
//					|| (mp.getElement(x + 1, y + 1, 1) == null)) {
//				new Element(mp, x + 1, y + 1,3, 1);
//			}
//
//		}
	}

	public Element() {
	}

	public boolean isRun() {
		return false;
	}

	public void setRun(boolean b) {
	}

	public int getArmy() {
		return 0;
	}

	public void action(int actnum) {
	}

	public synchronized void moveTo() {
	}

	public void stop() {
	}

	public boolean ismoveable() {
		return false;
	}

	public boolean isFocused() {
		return false;
	}

	public boolean isMoved() {
		return false;
	}

	public boolean isStop() {
		return false;
	}

	public void setFocused(boolean b) {
	}

	public void setMoved(boolean b) {
	}

	public void setStop(boolean s) {
	}

	public void setCanMove() {
	}

	public void setDestination(int m, int n) {
	}

	public void setDestination(int x, int y, int dir) {
	}

	public void transferData(int i) {
	}

	public void creat(int i) {
	}

	public void shoot(int i, int j) {
	}

	public void draw(int x, int y, Graphics g) {
	int i = 0, j = 0;
	int	array[][] = new int[i][j];
		if (kind.intValue() != 14) {
			g.drawImage(type[kind.intValue()], x, y, null);
		} else if (kind.intValue() == 14) {
			g.drawImage(type[1], x, y, null);
		}
		
	//	c = (int) (Math.random()*num);

	}

	public Color getColor() {
		return color[kind.intValue()];
	}

	public ImageIcon[] getImage() {
		return im;
	}

	public int getKind() {
		return kind.intValue();
	}

}
