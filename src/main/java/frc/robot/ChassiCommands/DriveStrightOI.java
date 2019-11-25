/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ChassiCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Chassi;

public class DriveStrightOI extends Command {

  Chassi c;
  double turn_kP = 0.075;
  double originalAngle;
  double xSpeed;
  double zRotation;

  public DriveStrightOI() {
    requires(Robot.m_chassi);
    c = Robot.m_chassi;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    originalAngle = c.getGyroValue();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // xSpeed = OI.driveJoystick.getRawAxis(-1); //need to find this out with OI
    xSpeed = 0.3;
    zRotation = turn_kP * (c.getGyroValue() - originalAngle);

    c.getDrive().arcadeDrive(xSpeed, zRotation);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    c.getDrive().tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
