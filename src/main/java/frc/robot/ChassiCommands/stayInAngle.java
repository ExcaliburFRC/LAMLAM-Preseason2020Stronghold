/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ChassiCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Chassi;

public class stayInAngle extends Command {
  Chassi c = Robot.m_chassi;
  double originalAngle;

  public stayInAngle() {
    requires(Robot.m_chassi);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    originalAngle = c.getGyroValue();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double val = c.getGyroValue() - originalAngle;
    val *= -0.035;
    c.getDrive().arcadeDrive(0, val);
    SmartDashboard.putNumber("Gyro", val);
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
