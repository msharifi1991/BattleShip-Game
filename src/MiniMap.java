import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MiniMap extends JPanel {
	JPanel reciever;

	public MiniMap(JPanel reciever) {
		super();
		this.enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
		this.reciever = reciever;
		setSize(280, 140);
		setLocation(500, 450);
		setBackground(Color.blue);
		setVisible(true);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	Graphics g;

	public void paintComponent(Graphics g) {
		this.g = this.getGraphics();
		super.paintComponent(g);
	}

	public void restart() {
		MainPanel mainpanel = (MainPanel) reciever;
		this.repaint();
		for (int i = 0; i < 280; i++) {
			for (int j = 0; j < 140; j++) {
				if (mainpanel.getElement(i, j, 1) != null) {
					g.setColor(mainpanel.getElement(i, j, 1).getColor());
				} else {
					g.setColor(mainpanel.getElement(i, j, 0).getColor());
				}
				// System.out.println(i + "," + j + "," +
				// mainpanel.getMaxLayer(i, j));
				g.drawLine(i, j, i, j);
			}
		}
	}

	protected void processComponentEvent(ComponentEvent e) {
		if (e.getID() == 2001) {
			MyEvent me = (MyEvent) e;
			g.setColor(me.getColor());
			g.drawLine(me.getX(), me.getY(), me.getX(), me.getY());
		}

		if (e.getID() == 2002) {
			MyEvent me = (MyEvent) e;
			refresh(me.getX(), me.getY());
		}

		if (e.getID() == 2020) {
			restart();
		}

		if (e.getID() == 3001) {
			MyEvent me = (MyEvent) e;
			g.setColor(me.getColor());
			g.drawLine(me.getX(), me.getY(), me.getX(), me.getY());
		}

		if (e.getID() == 3000) {
			MyEvent me = (MyEvent) e;
			g.setColor(((MainPanel) reciever).getElement(me.getX(), me.getY(),
					0).getColor());
			g.drawLine(me.getX(), me.getY(), me.getX(), me.getY());
		}

		super.processComponentEvent(e);

	}

	private void jbInit() throws Exception {
		this.addMouseMotionListener(new minimap_this_mouseMotionAdapter(this));
		this.addMouseListener(new minimap_this_mouseAdapter(this));
	}

	void this_mouseClicked(MouseEvent e) {
		if ((e.getX() > 15) && (e.getX() < 265) && (e.getY() > 8)
				&& (e.getY() < 142)) {
			reciever.dispatchEvent(new MyEvent(this, 2022, e.getX(), e.getY(),
					null));
			refresh(e.getX() - 14, e.getY() - 7);
		}
	}

	int xo = 10;
	int yo = 10;

	public void refresh(int x, int y) {
		MainPanel mainpanel = (MainPanel) reciever;
		for (int i = 0; i <= 28; i++) {
			g.setColor((mainpanel).getElement(xo + i, yo,
					mainpanel.getMaxLayer(xo + i, yo)).getColor());
			g.drawLine(xo + i, yo, xo + i, yo);
			g.setColor((mainpanel).getElement(xo + i, yo + 14,
					mainpanel.getMaxLayer(xo + i, yo + 14)).getColor());
			g.drawLine(xo + i, yo + 14, xo + i, yo + 14);
		}
		for (int j = 0; j <= 14; j++) {

			g.setColor((mainpanel).getElement(xo, yo + j,
					mainpanel.getMaxLayer(xo, yo + j)).getColor());
			g.drawLine(xo, yo + j, xo, yo + j);
			g.setColor((mainpanel).getElement(xo + 28, yo + j,
					mainpanel.getMaxLayer(xo + 28, yo + j)).getColor());
			g.drawLine(xo + 28, yo + j, xo + 28, yo + j);
		}
		g.setColor(Color.red);
		g.drawRect(x, y, 28, 14);
		xo = x;
		yo = y;
	}

	void this_mouseDragged(MouseEvent e) {
		this_mouseClicked(e);
	}

}

class minimap_this_mouseAdapter extends java.awt.event.MouseAdapter {
	MiniMap adaptee;

	minimap_this_mouseAdapter(MiniMap adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.this_mouseClicked(e);
	}
}

class minimap_this_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
	MiniMap adaptee;

	minimap_this_mouseMotionAdapter(MiniMap adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.this_mouseDragged(e);
	}

}
