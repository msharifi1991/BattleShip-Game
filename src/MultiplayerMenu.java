import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class MultiplayerMenu extends JFrame implements ActionListener{
	
	JButton startButton=new JButton("Local Area Network");
	JButton exitButton=new JButton("Back To Menu");
	
	public MultiplayerMenu(){
		
		setSize(250, 250);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
		setTitle("Multiplayer Game Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		
		startButton.setSize(150, 50);
		exitButton.setSize(150, 50);
		
		startButton.setLocation(50, 50);
		exitButton.setLocation(50, 110);

		getContentPane().add(startButton);
		getContentPane().add(exitButton);
		
//		startButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
//		if(e.getSource() == startButton){
//			setVisible(false);
//			//TODO
//		}
		
		if(e.getSource() == exitButton){
			setVisible(false);
			Menu menu=new Menu();
		}
	}

}
