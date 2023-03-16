import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame1 extends JFrame implements ActionListener {
	JPanel contentPane;
	MainPanel mp;

	JButton openButton = new JButton("Open");
	JButton saveButton = new JButton("Save");
	JButton exitButton = new JButton("Exit");
	public Frame1() {

		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
 			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ImageIcon[] type = { new ImageIcon("highDepth.gif"),
			new ImageIcon("fish.png"), new ImageIcon("whale.gif"),
			new ImageIcon("lowDepth.gif"), new ImageIcon("island.jpg") };

	private void jbInit() throws Exception {
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		this.setSize(new Dimension(800, 600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
		contentPane.setBackground(Color.black);
		this.setResizable(false);
		// this.setUndecorated(true);
		this.setTitle("Map Editor");
        
		// mp = new MainPanel(this);
		// contentPane.add(mp);
		// new Thread(mp).start();

		MyLabel[][] ml = new MyLabel[1][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 1; j++) {
				ml[j][i] = new MyLabel(this, type, j * 6 + i);
				contentPane.add(ml[j][i]);
				ml[j][i].setLocation(120 + 50 * i, 450 + j * 60);
            
			}
		}

		saveButton.setLocation(100, 520);
		openButton.setLocation(200, 520);
		exitButton.setLocation(300, 520);
		saveButton.setSize(70, 25);
		exitButton.setSize(70, 25);
		openButton.setSize(70, 25);
		saveButton.addMouseListener(new SaveButtonMouseAdapter(this));
		exitButton.addActionListener(this);
		openButton.addMouseListener(new OpenButtonMouseAdapter(this));
		contentPane.add(openButton);
		contentPane.add(saveButton);
		contentPane.add(exitButton);

		mp = new MainPanel(this);
		contentPane.add(mp);
		new Thread(mp).start();
	}

	protected void processComponentEvent(ComponentEvent e) {
		if (e.getID() >= 2000) {
			mp.dispatchEvent(e);
		}
		super.processComponentEvent(e);
	}

	// protected void processWindowEvent(WindowEvent e) {
	// super.processWindowEvent(e);
	// if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	// System.exit(0);
	// }
	// }

	void b1_mouseClicked(MouseEvent e) throws Exception {
		JFileChooser jfc = new JFileChooser();
		int x = jfc.showDialog(this, "Save");
		ObjectOutputStream out;
		if (x == jfc.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			out = new ObjectOutputStream(new FileOutputStream(file));
			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < 140; i++) {
					for (int j = 0; j < 280; j++) {
						out.writeObject(mp.getElement(j, i, k));
					}
				}
			}
			out.close();
		}
	}

	// void b2_mouseClicked(MouseEvent e) {
	// System.exit(0);
	// }

	void b3_mouseClicked(MouseEvent e) throws Exception {
		JFileChooser jfc = new JFileChooser();
		int x = jfc.showDialog(this, "Open");
		ObjectInputStream in;
		if (x == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			in = new ObjectInputStream(new FileInputStream(file));

			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < 140; i++) {
					for (int j = 0; j < 280; j++) {
						mp.setElement(j, i, k, null);
					}
				}
			}
			
			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < 140; i++) {
					for (int j = 0; j < 280; j++) {
						Object obj = in.readObject();
						if ((obj instanceof Element) && (obj != null)) {
							mp.setElement(j, i, k, (Element) obj);
							mp.maxlayer[j][i] = ((Element) obj).layer
									.intValue();
							
						}
					}
				}
			}
			new Element(mp,10,10,1,0);
			in.close();
			mp.dispatchEvent(new ComponentEvent(this, 2020));
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			setVisible(false);
			Menu menu = new Menu();
		}

	}
}

class SaveButtonMouseAdapter extends java.awt.event.MouseAdapter {
	Frame1 adaptee;

	SaveButtonMouseAdapter(Frame1 adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		try {
			adaptee.b1_mouseClicked(e);
		} catch (Exception ex) {
			ex.printStackTrace();
			// JOptionPane.showMessageDialog(adaptee,"Fileto dorost entekhab
			// kon","Error");
		}
	}
}

// class ExitButtonMouseAdapter extends java.awt.event.MouseAdapter {
// Frame1 adaptee;
//
// ExitButtonMouseAdapter(Frame1 adaptee) {
// this.adaptee = adaptee;
// }
class OpenButtonMouseAdapter extends java.awt.event.MouseAdapter {
	Frame1 adaptee;
	OpenButtonMouseAdapter(Frame1 adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		try {
			adaptee.b3_mouseClicked(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
// public void mouseClicked(MouseEvent e) {
// adaptee.b2_mouseClicked(e);
// }
// }

