/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ChassiCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Chassi;
import frc.robot.OI;

public class ChassiDrive extends Command {
  Chassi c = Robot.m_chassi;
  double XVAL = 0.7;
  double TURNVAL = 0.7;
  public ChassiDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_chassi);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // c.getDrive().tankDrive(OI.driveJoystick.getRawAxis(5)*0.8, OI.driveJoystick.getRawAxis(1)*0.8);
    // c.getDrive().arcadeDrive(OI.armJoystick.getRawAxis(1), OI.armJoystick.getRawAxis(2)*0.85);

    if (OI.guitar.getPOV() == 0){ //front
      c.getDrive().arcadeDrive(-XVAL,0);
    }
    
    else if (OI.guitar.getPOV() == 90){ //right
      c.getDrive().arcadeDrive(0, -TURNVAL);
    }
    
    else if (OI.guitar.getPOV() == 180){ //back
      c.getDrive().arcadeDrive(XVAL,0);
    }
    
    else if (OI.guitar.getPOV() == 270){ //left
      c.getDrive().arcadeDrive(0, TURNVAL);
    } else { // nothing
      c.getDrive().arcadeDrive(0,0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
