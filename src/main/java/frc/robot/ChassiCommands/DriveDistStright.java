/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ChassiCommands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Chassi;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveDistStright extends Command {
  Chassi c;
  PIDController pidcontrol;
  PIDOutput pidoutput;
  PIDSource encodersource;

  double dist;
  boolean stopped;
  double motorOut;
  int ontargettimes;
  double angleError;
  double originalAngle;

  double angleKp = 0.075;
  double ABSOLUTE_TOLERANCE = 100;

  public DriveDistStright(double dist) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_chassi);
    c = Robot.m_chassi; 
    this.dist = -dist;

    encodersource = new PIDSource(){
    
      @Override
      public void setPIDSourceType(PIDSourceType pidSource) {
      }
    
      @Override
      public double pidGet() {
        return (c.getRightEncoder() + c.getLeftEncoder())/2;
      }
    
      @Override
      public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
      }
    };

    pidoutput = new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        motorOut = -output;
        angleError = c.getGyroValue()-originalAngle;
        c.getDrive().arcadeDrive(motorOut, angleError*angleKp);
      }
    };

    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    pidcontrol = new PIDController(0.001, 0, 0, encodersource, pidoutput);
    pidcontrol.setContinuous(false);
    pidcontrol.setOutputRange(-0.4, 0.4);
    pidcontrol.setSetpoint(this.dist);

    pidcontrol.reset();
    c.resetEncoders();
    stopped = false;
    ontargettimes = 0;
    originalAngle = c.getGyroValue();

    pidcontrol.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if (Math.abs(pidcontrol.getError()) < ABSOLUTE_TOLERANCE) {
      ontargettimes ++;
    }
    else ontargettimes = 0;
    if (ontargettimes > 100) stopped = true;
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return stopped;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    c.getDrive().tankDrive(0,0);
    pidcontrol.disable();
    pidcontrol.close();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
