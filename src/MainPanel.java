import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable {
	MiniMap mm;
	int number_of_picture = 0;
	public static final int size = 25;
	int x = 350, y = 175;
	Element[][][] store_array = new Element[7000 / size][3500 / size][3];
	int[][] maxlayer = new int[15000 / size][7000 / size];
	Element[][] reservedblock = new Element[7000 / size][3500 / size];
	Element[] objectFocused = new Element[50];// TODO
	Element[] movingObject = new Element[50];// TODO
	int lastobject = 0;
	int lastfocused = 0;
	int layer = 1;
	int startx = (7000 - 700) / (2 * size);
	int starty = (3500 - 350) / (2 * size);
	JFrame JF;
	Graphics g;

	public MainPanel(JFrame JF) {
		super();
		this.enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
		this.JF = JF;
		setSize(700 + 2 * size, 350 + 2 * size);
		setLocation(50 - size, 50 - size);
		setBackground(Color.lightGray);
		setVisible(true);

		mm = new MiniMap(this);
		JF.getContentPane().add(mm);
JF.getIconImage();

		
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int tx = 0, ty = 0;

	public Element getElement(int i, int j, int layer) {
		return store_array[i][j][layer];
	}

	public void setElement(int i, int j, int k, Element x) {
		store_array[i][j][k] = x;
	}

	public Element getOwner(int i, int j) {
		return reservedblock[i][j];
	}

	public void setOwner(int i, int j, Element x) {
		reservedblock[i][j] = x;
	}

	int clientnumber;// TODO

	// TODO

	public void scroll() {
		for (int k = 0; k < 2; k++) {// TODO
			for (int i = -1; i < (700 / size + 3); i++) {
				for (int j = -1; j < (350 / size + 3); j++) {
					if (store_array[i + startx][j + starty][k] != null) {
						store_array[i + startx][j + starty][k].draw(i * size
								- tx, j * size - ty, dbg);
					}
				}
			}
		}
	}

	int speed = 7;

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean flag = false;
		while (true) {
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if ((startx < ((7000 - 700) / size) - 3) && (x > 680 + size)) {
				tx += speed;
				if (tx >= size) {
					tx = 0;
					startx++;
				}
				flag = true;
			}
			if ((startx > 1) && (x < 20 + size)) {
				tx -= speed;
				if (tx <= -size) {
					tx = 0;
					startx--;
				}
				flag = true;
			}
			if ((starty < ((3500 - 350) / size) - 3) && (y > 330 + size)) {
				ty += speed;
				if (ty >= size) {
					ty = 0;
					starty++;
				}
				flag = true;
			}
			if ((starty > 1) && (y < 20 + size)) {
				ty -= speed;
				if (ty <= -size) {
					ty = 0;
					starty--;
				}
				flag = true;
			}

			if (flag) {
				mm.dispatchEvent(new MyEvent(this, 2002, startx, starty, null));
			}
			movementManager();
			flag = false;

			gameRender();
			repaint();
			if (mouseImage != null) {
				// boolean g = true;
				// int m = (int) ( (x + tx) / (size)) + startx;
				// int n = (int) ( (y + ty) / (size)) + starty;
				// for (int i = -1; i < 4; i++) {
				// for (int j = -1; j < 4; j++) {
				// if (getElement(m + i, n + j, 1) != null) {
				// g = false;
				// }
				// }
				// }
				// if (!g) {
				// mouseImage = new ImageIcon("buildingimage2.gif").getImage();
				// }
				dbg.drawImage(mouseImage, x, y - 50, null);
			}
		}

	}

	// TODO

	public void movementManager() {
		for (int i = 0; i < lastobject; i++) {
			movingObject[i].moveTo();
			if (movingObject[i].isStop()) {
				movingObject[i].stop();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.setColor(Color.blue);
		// for (int i = 0; i < (700/size+2) ; i++) {
		// g.drawLine(i * size, 0, i * size, 350+2*size);
		// }
		// for (int j = 0; j < (350/size+2); j++) {
		// g.drawLine(0, j * size, 700+2*size, j * size);
		// }

		this.g = this.getGraphics();
		if (g == null) {
			System.out.println("Graphics Error");
		}
		if (dbImage != null) {
			g.drawImage(dbImage, 0, 0, null);
		}
	
	}

	protected void processComponentEvent(ComponentEvent e) {
		if (JF instanceof Frame1) {
			if (e.getID() >= 2000) {
				if (e.getID() == 2020) {
					mm.dispatchEvent(e);
				} else if (e.getID() == 2022) {
					startx = ((MyEvent) e).getX() - 15;
					starty = ((MyEvent) e).getY() - 8;
					gameRender();
					repaint();
				} else if (e.getID() != 2020) {
					number_of_picture = (e.getID() - 2000);
				}

			}
		} else if (JF instanceof GameFrame) {
			if (e.getID() >= 2000) {
				if (e.getID() == 2020) {
					mm.dispatchEvent(e);
				} else if (e.getID() == 2022) {
					startx = ((MyEvent) e).getX() - 15;
					starty = ((MyEvent) e).getY() - 8;
					gameRender();
					repaint();
				} else if (e.getID() != 2020) {
					number_of_picture = (e.getID() - 2000);
				}

			}
		}
		super.processComponentEvent(e);
	}

	Image mouseImage;

	public void setMouseImage(ImageIcon im) {
		if (im != null) {
			mouseImage = im.getImage();
		} else {
			mouseImage = null;
		}
	}

	// TODO gozashtane keshtiha avale bazi

	private void jbInit() throws Exception {
		this
				.addMouseMotionListener(new mainPanel_this_mouseMotionAdapter(
						this));
		this.addMouseListener(new mainPanel_this_mouseAdapter(this));

		for (int i = 0; i < (7000 / size); i++) {
			for (int j = 0; j < (3500 / size); j++) {
				new Element(this, i, j, 0, 0);
			}
		}
		init();
		
	}

	// TODO

	public void open(File f) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(f));
		} catch (IOException ex) {
		}

		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 140; i++) {
				for (int j = 0; j < 280; j++) {
					Object obj = null;
					try {
						obj = in.readObject();
					} catch (ClassNotFoundException ex1) {
					} catch (IOException ex1) {
					}
					if ((obj instanceof Element) && (obj != null)) {
						setElement(j, i, k, (Element) obj);
						maxlayer[j][i] = ((Element) obj).layer.intValue();
						
					}
				
				}
			}
		}
		
		//Dimension dm= ((GameFrame)JF).getIsland();
		try {
			in.close();
		} catch (IOException ex2) {
		}
		JF.setVisible(true);
		// JF.repaint();
		JF.paintAll(JF.getGraphics());
		mm.dispatchEvent(new ComponentEvent(this, 2020));
	}

	public void init() {

		// UDPServer us = null;
		// try {
		// // us = new UDPServer();
		// } catch (Exception ex1) {
		// }

		open(new File("map.map"));
		// CheckRecieve cr;
		// try {
		// client = new Socket("127.0.0.1", 4000);
		// in = new DataInputStream(client.getInputStream());
		// out = new DataOutputStream(client.getOutputStream());
		// cr = new CheckRecieve(in, this);
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
	}

	/*public void removeObject(Element e) {// TODO
		int k = 0;
		e.setMoved(false);
		for (int i = 0; i < lastobject; i++) {
			if (movingObject[i] != e) {
				movingObject[k] = movingObject[i];
				k++;
			}
		}
		lastobject--;
	}*/

	// TODO

	void this_mouseClicked(MouseEvent e) {
		if (JF instanceof Frame1) {
			// Map Editor Frame
			int m = (int) ((e.getX() + tx) / (size));
			int n = (int) ((e.getY() + ty) / (size));
			if ((number_of_picture != -1) && (x > 10) && (x < 740) && (y > 10)
					&& (y < 690)) {
				new Element(this, m + startx, n + starty, number_of_picture,
						layer);
				store_array[m + startx][n + starty][layer].draw(m * size - tx,
						n * size - ty, this.getGraphics());
				maxlayer[m + startx][n + starty] = layer;
				mm.dispatchEvent(new MyEvent(this, 2001, startx + m,
						starty + n, store_array[m + startx][n + starty][layer]
								.getColor()));
				
			}
		}
		if(JF instanceof GameFrame){
			int m = (int) ((e.getX() + tx) / (size));
			int n = (int) ((e.getY() + ty) / (size));
			if ((number_of_picture != -1) && (x > 10) && (x < 740) && (y > 10)
					&& (y < 690)) {
				new Element(this, m + startx, n + starty, number_of_picture+5,
						layer);
				store_array[m + startx][n + starty][layer].draw(m * size - tx,
						n * size - ty, this.getGraphics());
				maxlayer[m + startx][n + starty] = layer;
				mm.dispatchEvent(new MyEvent(this, 2001, startx + m,
						starty + n, store_array[m + startx][n + starty][layer]
								.getColor()));
			}
		}
		// TODO
	}

	private void runObject(Element e) {
		if (!e.isRun()) {
			movingObject[lastobject] = e;
			lastobject++;
			e.setMoved(true);
			e.setRun(true);
		}
	}

	private void addMovingObject(Element e) {
		boolean g = true;
		for (int j = 0; j < lastobject; j++) {
			if (movingObject[j] == e) {
				g = false;
			}
		}
		if (g) {
			movingObject[lastobject] = e;
			lastobject++;
			e.setMoved(true);
		}

	}

	public int getMaxLayer(int i, int j) {
		return maxlayer[i][j];
	}

	void this_mouseMoved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	void this_mouseExited(MouseEvent e) {
		this.x = 350;
		this.y = 175;
	}

	void this_mouseDragged(MouseEvent e) {
		if (JF instanceof Frame1) {
			this_mouseClicked(e);
			this.x = e.getX();
			this.y = e.getY();
		} else if (JF instanceof GameFrame) {
			g.setColor(Color.black);
			g.drawLine(fx, fy, fx, e.getY());
			g.drawLine(fx, e.getY(), e.getX(), e.getY());
			g.drawLine(e.getX(), e.getY(), e.getX(), fy);
			g.drawLine(e.getX(), fy, fx, fy);
		}
	}

	private Graphics dbg;
	private Image dbImage = null;

	private void gameRender()
	// draw the current frame to an image buffer
	{
		if (dbImage == null) { // create the buffer
			dbImage = createImage(700 + 2 * size, 350 + 2 * size);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else {
				dbg = dbImage.getGraphics();
			}
		}
		scroll();
	}

	int firstx, firsty;
	int fx, fy;

	void this_mousePressed(MouseEvent e) {// TODO
		if (JF instanceof GameFrame) {
			fx = e.getX();
			fy = e.getY();
			firstx = (int) ((e.getX() + tx) / (size)) + startx;
			firsty = (int) ((e.getY() + ty) / (size)) + starty;
		}
	}

}

class mainPanel_this_mouseAdapter extends java.awt.event.MouseAdapter {
	MainPanel adaptee;

	mainPanel_this_mouseAdapter(MainPanel adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.this_mouseClicked(e);
	}

	public void mouseExited(MouseEvent e) {
		adaptee.this_mouseExited(e);
	}

	public void mousePressed(MouseEvent e) {
		adaptee.this_mousePressed(e);
	}

	// public void mouseReleased(MouseEvent e) {
	// adaptee.this_mouseReleased(e);
	// }
}

class mainPanel_this_mouseMotionAdapter extends
		java.awt.event.MouseMotionAdapter {
	MainPanel adaptee;

	mainPanel_this_mouseMotionAdapter(MainPanel adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseMoved(MouseEvent e) {
		adaptee.this_mouseMoved(e);
	}

	public void mouseDragged(MouseEvent e) {
		adaptee.this_mouseDragged(e);
	}
}
