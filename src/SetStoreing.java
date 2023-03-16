
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.AWTEvent;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SetStoreing extends JLabel {
    int food=360,time=0,counter=0;
    Timer ts;
    JFrame od;
    JLabel st[];
    JLabel[] imk=new JLabel[5];
    ImageIcon im[];
    Dimension size=getToolkit().getDefaultToolkit().getScreenSize();;
    public SetStoreing(JFrame od) {
        super();
        this.od=od;
        enableEvents(AWTEvent.COMPONENT_EVENT_MASK+
                AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null);
        st=new JLabel[5];
        for(int i=0;i<5;i++){
            st[i]=new JLabel();
            imk[i]=new JLabel();

            st[i].setSize(8*size.width/103,4*size.height/115);
            imk[i].setSize(8*size.width/103,4*size.height/115);

            st[i].setLocation(8*size.width/103+(i*8*size.width/103),13*size.height/115);
            imk[i].setLocation(8*size.width/103+(i*8*size.width/103),(13*size.height/115)-(6*size.height/115));
            add(st[i]);
            add(imk[i]);
        }

        st[0].setText(Integer.toString(food));   
        st[1].setText(Integer.toString(time));
        imk[0].setText("Food");
        imk[1].setText("Timer");

        im=new ImageIcon[2];

      ts=new Timer(210, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    food-=1;
     time++;
    st[0].setText(Integer.toString(food));  
     st[4].setText(Integer.toString(time));
       }
 });

  setSize(size.width,size.height*1/5);
        setVisible(true);
}


   }
