import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyLabel extends JLabel {
	JFrame reciever;
	int label_number;

	ImageIcon type[];

	public MyLabel(JFrame jf, ImageIcon[] type, int ln) {
		super();
		this.type = type;
		this.enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
		this.enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		this.reciever = jf;
		this.label_number = ln;
		this.setSize(40, 40);
		this.setBackground(Color.white);
		this.setOpaque(true);
		this.setVisible(true);
		this.setIcon(type[ln]);
		this.addMouseListener(new myLabel_this_mouseAdapter(this));
	}

	void this_mouseClicked(MouseEvent e) {
		reciever.dispatchEvent(new ComponentEvent(this, 2000 + label_number));
	}
}

class myLabel_this_mouseAdapter extends java.awt.event.MouseAdapter {
	MyLabel adaptee;

	myLabel_this_mouseAdapter(MyLabel adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e) {
		adaptee.this_mouseClicked(e);
	}
}
