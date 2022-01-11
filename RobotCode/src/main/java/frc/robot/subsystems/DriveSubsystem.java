/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase {
   // Put methods for controlling this subsystem
    // here. Call these from Commands.

	// Victor speed controller instantiations
	//RobotMap has CANBus ports
	
	private final TalonFX m_leftFront, m_leftBack, m_rightFront, m_rightBack;


	// Sample PWM motor instantiation
	
	public DriveSubsystem() {

		m_rightFront = new TalonFX(RobotMap.TALONFX_DRIVE_LEFT_FRONT);
		m_leftFront = new TalonFX(RobotMap.TALONFX_DRIVE_RIGHT_BACK);
		m_rightBack = new TalonFX(RobotMap.TALONFX_DRIVE_LEFT_BACK);
		m_leftBack = new TalonFX(RobotMap.TALONFX_DRIVE_RIGHT_FRONT);


		m_rightFront.setInverted(true);
		m_leftFront.setInverted(false);
		m_rightBack.setInverted(true);
		m_leftBack.setInverted(false);

		// Finalising instantiation of the drivetrain object
		// after setting motor inversions
	}
	// Drives drivetrain based on joystick input and dampening value (speed)
	// from 0 to 1
	public void driveJoystick(Joystick joystick, double speed) {
		
		drive.arcadeDrive(joystick.getY()*speed, joystick.getX()*speed);
	}
	
	// Drives drivetrain based on given speed and roation values
	public void drive(double speed, double rotationSpeed) {
		drive.arcadeDrive(speed, rotationSpeed);
	}

	// Stops the motors on the drivetrain
	public void stop() {
		drive.stopMotor();
	}

	// Gets a Raw encoder value
	// Similarly, getCount will give you the number of 
	// "clicks" it has recorded
	public double getLeftRaw(){
		return leftEnc.getRaw();
	}

	// Averages the raw values of the left and right encoders
	public double getRawAvg(){
		return (leftEnc.getRaw() + rightEnc.getRaw())/2; 
	}

	// Gets a distance based on a factor for units per
	// encoder value
	// Units used is up to the team
	public double getAvgDistance(){
		return getRawAvg() * 0.0008;
	}

	//Resets the encoders so that they read from 0 again
	public void encReset(){
		leftEnc.reset();
		rightEnc.reset();
	}

  @Override
  public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
  }
}