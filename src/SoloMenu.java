import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class SoloMenu extends JFrame implements ActionListener{
	
	MainPanel mp;
	
	JButton startButton=new JButton("Start Game");
	JButton loadMapButton=new JButton("Load Map");
	JButton loadGameButton=new JButton("Load Saved Game");
	JButton exitButton=new JButton("Back To Menu");
	
	public SoloMenu(){
		
		setSize(300, 400);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
		setTitle("Solo Game Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		
		startButton.setSize(150, 50);
		loadMapButton.setSize(150, 50);
		loadGameButton.setSize(150, 50);
		exitButton.setSize(150, 50);

		startButton.setLocation(75, 50);
		loadMapButton.setLocation(75, 110);
		loadGameButton.setLocation(75, 170);
		exitButton.setLocation(75, 230);

		getContentPane().add(startButton);
		getContentPane().add(loadMapButton);
		getContentPane().add(loadGameButton);
		getContentPane().add(exitButton);

//		startButton.addActionListener(this);
		loadMapButton.addActionListener(this);
//		loadGameButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		setVisible(true);
		
	}
	
//	void b3_mouseClicked(MouseEvent e) throws Exception {
//		JFileChooser jfc = new JFileChooser();
//		int x = jfc.showDialog(this, "Open");
//		ObjectInputStream in;
//		if (x == JFileChooser.APPROVE_OPTION) {
//			File file = jfc.getSelectedFile();
//			in = new ObjectInputStream(new FileInputStream(file));
//			setVisible(false);
////			GameFrame gameFrame1=new GameFrame();
//			Frame1 frame1=new Frame1();
//			for (int k = 0; k < 2; k++) {
//				for (int i = 0; i < 140; i++) {
//					for (int j = 0; j < 280; j++) {
//						mp.setElement(j, i, k, null);
//					}
//				}
//			}
//
//			for (int k = 0; k < 2; k++) {
//				for (int i = 0; i < 140; i++) {
//					for (int j = 0; j < 280; j++) {
//						Object obj = in.readObject();
//						if ((obj instanceof Element) && (obj != null)) {
//							mp.setElement(j, i, k, (Element) obj);
//							mp.maxlayer[j][i] = ((Element) obj).layer
//									.intValue();
//						}
//					}
//				}
//			}
//			
//			in.close();
//			mp.dispatchEvent(new ComponentEvent(this, 2020));
//
//		}
//	}
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == startButton) {
//			setVisible(false);
//		//TODO
//		}

		if (e.getSource() == loadMapButton){
			setVisible(false);
			GameFrame gameFrame=new GameFrame();
		}
		
//		if(e.getSource() == loadGameButton){
//			setVisible(false);
//			//TODO
//		}
		
		if(e.getSource() == exitButton){
			setVisible(false);
			Menu menu=new Menu();
		}
	}
}

//class OpenMouseAdapter extends java.awt.event.MouseAdapter {
//	Frame1 adaptee;
//
//	OpenMouseAdapter(Frame1 frame1) {
//		this.adaptee = frame1;
//	}
//
//	public OpenMouseAdapter(SoloMenu soloMenu) {
//		// TODO Auto-generated constructor stub
//	}
//
//	public void mouseClicked(MouseEvent e) {
//		try {
//			adaptee.b3_mouseClicked(e);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//}


