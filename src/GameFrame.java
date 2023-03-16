import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class GameFrame extends JFrame implements ActionListener{
	Dimension size;
	 boolean day = true;
	 boolean summer = true;
	 boolean spring = false;
	 boolean automn = false;
	 boolean winter = false;
	JPanel contentPane;
	MainPanel mp;
	Timer tm;
	Timer tv;
	Timer ts;
	boolean begin=true;
	boolean startsend;
	DataOutputStream output;
	SetStoreing store=new SetStoreing(this);
	int food=360,time=0,counter=0;
    JLabel st[];
    JLabel[] imk=new JLabel[5];
	int t=0;
	int f=360;
	int num=0,c=0;
	int max = 6;
	int a=0;
	int b=0;
	Dimension dm[] = new Dimension[max];
	int d=0;
	ObjectInputStream in;
	
//	int x = (int) dm[c].getHeight();
  //  int y=(int) dm[c].getWidth();
	
	JButton loadMapButton = new JButton("Load Map");
	JButton saveGameButton = new JButton("Save Game");
	JButton loadGameButton = new JButton("Load Game");
	JButton exitButton=new JButton("Exit");
	Dimension array[] = new Dimension[6];
	
	public GameFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	//	array[3]=new Dimension(9,8);
		try {
			jbInit();
		//	random();
		} catch (Exception ex) {
		}
	//while(k<1001){
	
	

		tv = new Timer(800, new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				    	if(t>-21){
				    		if(f<1001){
				    		imk[1].setText(Integer.toString(f));
			            		imk[0].setText(Integer.toString(t));    	
				    	t++;
				    	f--;
				    	}	}	

				    	for(int m=0;m<60;m++){
				    		if(t == m*5){
				    			//System.out.print("salaaaaaam");
				    		
				    		}
				    	}
			   }     
			    });

		enableEvents(AWTEvent.COMPONENT_EVENT_MASK+
	            AWTEvent.MOUSE_EVENT_MASK);
	    setLayout(null);
	    st=new JLabel[3];
	    for(int i=0;i<3;i++){
	    	 for(int j=0;j<3;j++){
	    	//System.out.println(i);
	        st[i]=new JLabel();
	        st[i].setForeground(Color.white);
	        st[i].setLocation(70*i+20, 490);
	        st[i].setSize(80,30);
	        add(st[i]);

	    	imk[j] = new JLabel();
	    	imk[j].setForeground(Color.white);
	    	imk[j].setLocation(70*j+20,510);
	    	imk[j].setSize(50,50);
	    	add(imk[j]);
	    	}
	   
	    }
	    st[0].setText("Timer"); 	  
	    st[1].setText("Food");  	
	    imk[0].setText("0");
	    imk[1].setText("360");
	    
	    //ts=new Timer(2500, new ActionListener() {
	    	//public void actionPerformed(ActionEvent e) {
	        		
	        	
	      //  		food++;
	        		
	     		    		
	    	
	    //});}


		

			//tv.start();
			
			
		
		 tm = new Timer(800, new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
		
			   if(a%20 == 0){
				   if(day == true){
				   night();
			       day = false;}
				   else
					   {day();
					   day = true;
					   }}
			   a++;
			   }     
			    });
		

		enableEvents(AWTEvent.COMPONENT_EVENT_MASK+
	         AWTEvent.MOUSE_EVENT_MASK);

	
	
		 ts = new Timer(800, new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
		
			   if(b%10 == 0)
				  changeSeason();
			   b++;
			   	}    
			    });
		
		 
		enableEvents(AWTEvent.COMPONENT_EVENT_MASK+
	         AWTEvent.MOUSE_EVENT_MASK);
		 

	
		
			
	}
	public void spring(){
	//System.out.print("sadaf" + x + "," + y );
		spring = true;
	System.out.println("SPRING");
		
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 140; i++) {
				for (int j = 0; j < 280; j++) {
					if(mp.getElement(j, i, 0).kind == 13){
						mp.setElement(j, i, 0, (new Element(mp,j,i,11,0)));
					mp.setElement(j, i, 1, (new Element(mp,j,i,11,0)));
					}
						}
						
					}
				}
	}
	public void summer(){
		summer = true;
		System.out.println("SUMMER");
		
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 140; i++) {
				for (int j = 0; j < 280; j++) {
					if(mp.getElement(j, i, 0).kind == 11){
						mp.setElement(j, i, 0, (new Element(mp,j,i,4,0)));
					mp.setElement(j, i, 1, (new Element(mp,j,i,4,0)));}
				
						}
						
					}
				}
	}
	public void automn(){
		automn= true;
		System.out.println("AUTOMN");
		
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 140; i++) {
				for (int j = 0; j < 280; j++) {
					if(mp.getElement(j, i, 0).kind == 4){
						mp.setElement(j, i, 0, (new Element(mp,j,i,12,0)));
					mp.setElement(j, i, 1, (new Element(mp,j,i,12,0)));}
				
						}
						
					}
				}
	}
	public void winter(){
		winter = true;
		System.out.println("WINTER");
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 140; i++) {
				for (int j = 0; j < 280; j++) {
					if(mp.getElement(j, i, 0).kind == 12){
						mp.setElement(j, i, 0, (new Element(mp,j,i,13,0)));
						mp.setElement(j, i, 1, (new Element(mp,j,i,13,0)));}
				
						}
						
					}
				}
	}
	
	public void changeSeason(){
		
		 if(summer == true){
			   automn();
		     summer= false;
			   }
		 else if(automn == true){
			    winter();
			    automn = false;
				   }
		 else if(winter == true){
				   spring();
				   winter = false;
				   }
		 else if(spring == true){
		summer();
		spring = false;
		}
	}
		public void day(){//System.out.print("day");
			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < 140; i++) {
					for (int j = 0; j < 280; j++) {
						if(mp.getElement(j, i, 0).kind == 0)
							mp.setElement(j, i, k, (new Element(mp,j,i,10,0)));
					
							}
							
						}
					}
				}
			 
		
		public void night(){
			for (int k = 0; k < 2; k++) {
				for (int i = 0; i < 140; i++) {
					for (int j = 0; j < 280; j++) {
						if(mp.getElement(j, i, 0).kind == 10)
							mp.setElement(j, i, k, (new Element(mp,j,i,0,0)));
					
							}
							
						}
					}
	}
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
	}
	}
	ImageIcon[] type = { new ImageIcon("engineering.gif"),
			new ImageIcon("hunter.png"), new ImageIcon("firing.png"),
			new ImageIcon("gunnery.png") };

	private void jbInit() throws Exception {
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		this.setSize(new Dimension(800, 600));
		this.enableEvents(AWTEvent.COMPONENT_EVENT_MASK);
		contentPane.setBackground(Color.black);
		this.setResizable(false);
		// this.setUndecorated(true);
		this.setTitle("Battle Ship");

		mp = new MainPanel(this);
		contentPane.add(mp);
		new Thread(mp).start();

		loadMapButton.setLocation(50, 450);
		saveGameButton.setLocation(350, 450);
		loadGameButton.setLocation(350, 480);
		exitButton.setLocation(350,510);
		loadMapButton.setSize(100, 25);
		saveGameButton.setSize(100, 25);
		loadGameButton.setSize(100, 25);
		exitButton.setSize(100, 25);
		loadMapButton.addMouseListener(new OpenMouseAdapter(this));
		// saveGameButton.
		// loadGameButton.
		exitButton.addActionListener(this);
		contentPane.add(loadMapButton);
		
	}
	
	

	protected void processComponentEvent(ComponentEvent e) {
		if (e.getID() >= 2000) {
			mp.dispatchEvent(e);
		}
		super.processComponentEvent(e);
	}

	void b3_mouseClicked(MouseEvent e) throws Exception {
		ObjectInputStream in;
		//Object obj = in.readObject();
    	JFileChooser jfc = new JFileChooser();
    	int x = jfc.showDialog(this, "Open");
		
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
							if(((Element) obj).kind.intValue() == 4){
								if(num < max){
								    dm [num] = new Dimension(i,j);
						//	System.out.println("island" + i +","+ j);
								}
								num++;
								
							}
							if (max < num){
								max = num;
							}
							
						}
					}
				}
				 //new Element(mp,85,136,1,0);	
			}
			
		    c = (int) ((Math.random()) * num);
		//    System.out.println(dm[c].width+"**&&**"+dm[c].height);
		    mp.setElement(dm[c].height,dm[c].width, 1, (new Element(mp,dm[c].height,dm[c].width,9,0)) );
		    mp.setElement(dm[c].height,dm[c].width, 1, (new Element(mp,dm[c].height,dm[c].width,9,1)) );
			mp.maxlayer[dm[c].height][dm[c].width] = 0;
		  //  mp.setElement(139, 81, 1, (new Element(mp,139,81,3,0)) );
		   // mp.setElement(139, 81, 1, (new Element(mp,139,81,3,1)) );
		//	mp.maxlayer[139][81] = 0;  
            
		//	System.out.println("mookhtasat" + dm[c].height +","+ dm[c].width +","+ c);
			in.close();
			mp.dispatchEvent(new ComponentEvent(this, 2020));
		}
		loadMapButton.setVisible(false);
		contentPane.add(saveGameButton);
		contentPane.add(loadGameButton);
		contentPane.add(exitButton);
		contentPane.add(st[0]);
		MyLabel[][] ml = new MyLabel[1][5];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 1; j++) {
				ml[j][i] = new MyLabel(this, type, j * 6 + i);
				contentPane.add(ml[j][i]);
				ml[j][i].setLocation(100 + 50 * i, 450 + j * 60);

			}
			}
	tv.start();
	tm.start();
	ts.start();
	
	new Element(mp, dm[c].height , dm[c].width-2, 5, 1);
    new Element(mp, dm[c].height+1, dm[c].width-2, 6, 1);
    new Element(mp, dm[c].height+1 , dm[c].width-3, 6, 1);
    new Element(mp, dm[c].height , dm[c].width+4, 7, 1);
    new Element(mp, dm[c].height+1, dm[c].width+4, 7, 1);
    new Element(mp, dm[c].height +1, dm[c].width+5, 8, 1);


	}
	

	public Dimension getIsland(){
		return dm[c];
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==exitButton){
			setVisible(false);
			Menu menu=new Menu();
		}
		
	}

}

class OpenMouseAdapter extends java.awt.event.MouseAdapter {
	GameFrame adaptee;

	OpenMouseAdapter(GameFrame gameFrame) {
		this.adaptee = gameFrame;
	}

	public void mouseClicked(MouseEvent e) {
		try {
			adaptee.b3_mouseClicked(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
