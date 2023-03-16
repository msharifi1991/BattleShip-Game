import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame implements ActionListener {
	JButton soloButton = new JButton("Solo Game");
	JButton multiButton = new JButton("Multiplayer Game");
	JButton editButton = new JButton("Map Editor");
	JButton optionButton = new JButton("Option");
	JButton exitButton = new JButton("Exit");

	public Menu() {
		setSize(300, 400);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
		setTitle("Battle Ship");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);

		soloButton.setSize(150, 50);
		multiButton.setSize(150, 50);
		editButton.setSize(150, 50);
		optionButton.setSize(150, 50);
		exitButton.setSize(150, 50);

		soloButton.setLocation(75, 50);
		multiButton.setLocation(75, 110);
		editButton.setLocation(75, 170);
		optionButton.setLocation(75, 230);
		exitButton.setLocation(75, 290);

		getContentPane().add(soloButton);
		getContentPane().add(multiButton);
		getContentPane().add(editButton);
		getContentPane().add(optionButton);
		getContentPane().add(exitButton);

		soloButton.addActionListener(this);
		multiButton.addActionListener(this);
		editButton.addActionListener(this);
		// optionButton.addActionListener(this);
		exitButton.addActionListener(this);
		setVisible(true);

	}

	public static void main(String args[]) {
		new Menu();
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == soloButton){
			setVisible(false);
			SoloMenu solo=new SoloMenu();
		}
		
		if(e.getSource() == multiButton){
			setVisible(false);
			MultiplayerMenu multi=new MultiplayerMenu();
		}
		
		if (e.getSource() == editButton) {
			setVisible(false);
			Frame1 frame1 = new Frame1();
		}
		
//		if(e.getSource() == optionButton){
//			setVisible(false);
//			//TODO
//		}
		
		if (e.getSource() == exitButton)
			System.exit(0);
		
	}
}
