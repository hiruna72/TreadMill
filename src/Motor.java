import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.Timer; //for timer

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//Motor is the class that has hardware attributes
public class Motor implements ActionListener {
	private float motorSpeed;
	private float motorAcl;
	//Motor is hardware.it takes time to change
	private Timer clock;
	//states are maintained as the right  time comes changes are passed to the motor
	private boolean state2,incMotorSpeedState,decMotorSpeedState,stopMotorState;
	Motor(){
		motorSpeed=0;
		motorAcl=0.5F;
		//Motor is able of changing its rotational speed twice in one second
		clock = new Timer(500, this);
		clock.start();
		state2=false;
		incMotorSpeedState=false;
		decMotorSpeedState=false;
		stopMotorState=false;
	}
	public void actionPerformed(ActionEvent e) {
		state2=!state2;
		
		if(stopMotorState){
		
			System.out.println("in");
			//when stopMotor is true all the changes are invalid
			incMotorSpeedState=false;
			decMotorSpeedState=false;
			if(motorSpeed>0){
				System.out.println("inside");
				motorSpeed-=motorAcl;
			}
			else{
				stopMotorState=false;
			}
		}
		if(motorSpeed<10*motorAcl && incMotorSpeedState){
			motorSpeed+=motorAcl;
		}
		if(motorSpeed>motorAcl && decMotorSpeedState){
			motorSpeed-=motorAcl;
		}
		//reset the states
		incMotorSpeedState=false;
		decMotorSpeedState=false;
	}
	//user presses buttons regardless of the state of the motor.
	public void incMotorSpeed(){
		incMotorSpeedState=true;
	}
	
	public void decMotorSpeed(){
		decMotorSpeedState=true;
	}
	public void stopMotor(){
		stopMotorState=true;
		
	}
	//to display the current speed
	public float getMotorSpeed(){
		return motorSpeed;
	}
}
