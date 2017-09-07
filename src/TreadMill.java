import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.Timer; //for timer

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//this class is the display 
public class TreadMill extends JPanel implements ActionListener{
	private JFrame f2;
	private JPanel p2;
	private Timer watch;
	private JButton startB,speedUp,slowDown;
	private boolean state,intrState;
	private JLabel timeDisplay,speedDisplay,distanceDisplay;
	private int min,sec;
	private float speed=0,distance=0;
	//class has Motor object as the treadMill has its Motor !
	private Motor m;
	TreadMill(){
		super(new BorderLayout());
		state=false;
		intrState=false;
		f2 = new JFrame("Tread Mill");
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(300, 150)); 
        p2.setLayout(null);
        startB = new JButton();
        startB.setText("Start");
        startB.addActionListener(this);
        startB.setBounds(100, 0, 100, 20);
        startB.setBackground(Color.WHITE);
        p2.add(startB);
        
        speedUp = new JButton();
        speedUp.setText("+");
        speedUp.addActionListener(this);
        speedUp.setBounds(50, 70, 50, 20);
        speedUp.setBackground(Color.BLACK);
        speedUp.setForeground(Color.WHITE);
        p2.add(speedUp);
        
        slowDown = new JButton();
        slowDown.setText("-");
        slowDown.addActionListener(this);
        slowDown.setBounds(200, 70, 50, 20);
        slowDown.setBackground(Color.BLACK);
        slowDown.setForeground(Color.WHITE);
        p2.add(slowDown);
        
        watch = new Timer(1000, this);
        
        timeDisplay  = new JLabel(min + ":" + sec);
        timeDisplay.setBounds(110, 30, 80, 30);
        timeDisplay.setOpaque(true);
        timeDisplay.setBackground(Color.GRAY);
        timeDisplay.setForeground(Color.WHITE);
        timeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(timeDisplay);
        
        speedDisplay  = new JLabel(speed+" m/sec");
        speedDisplay.setBounds(100, 70, 100, 20);
        speedDisplay.setOpaque(true);
        speedDisplay.setBackground(Color.DARK_GRAY);
        speedDisplay.setForeground(Color.WHITE);
        speedDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(speedDisplay);
        
        distanceDisplay  = new JLabel(distance+" m");
        distanceDisplay.setBounds(100, 100, 100, 20);
        distanceDisplay.setOpaque(true);
        distanceDisplay.setBackground(Color.DARK_GRAY);
        distanceDisplay.setForeground(Color.WHITE);
        distanceDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        p2.add(distanceDisplay);
        
        m =new Motor();
        f2.add(p2);
        f2.pack();
        f2.setVisible(true);
   
	}
	public void actionPerformed(ActionEvent e) {
		if(intrState && m.getMotorSpeed()==0){
		    //even though start button is enabled all the time it can only affect after the stop is complete!
			intrState=false;
			state=false;
	    	watch.stop();
		}
		if(e.getSource() == startB && state==false) {
		    startB.setText("Stop");
		    m.incMotorSpeed();
		    state=true;
		    sec = 0; 
		    min = 0; 
		    speed=0;
		    distance=0;
		    watch.start();
		    
		}
		else if(e.getSource() == startB && state==true){ 
			startB.setText("Start");
		    intrState=true;
		    m.stopMotor();
		    

		    
		}	
		if(e.getSource() == watch && state==true) {
			distance+=m.getMotorSpeed();
		    sec += 1; 
		    if(sec == 60) { 
			min ++;
			sec = 0;
		    }
		}
		if(e.getSource() == speedUp && state==true){
			m.incMotorSpeed();
		}
		
		else if (e.getSource() == slowDown && state==true){
			m.decMotorSpeed();
		}
		
		distanceDisplay.setText(distance+" m");
		timeDisplay.setText(min + ":" + sec);
		
		speedDisplay.setText(m.getMotorSpeed()+" m/sec");
	    }

}
