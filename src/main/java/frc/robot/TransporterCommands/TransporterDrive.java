/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.TransporterCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Transporter;
import frc.robot.OI;
import frc.robot.Robot;

public class TransporterDrive extends Command {
  Transporter t = Robot.m_transporter;
  public TransporterDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(t);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.armJoystick.getPOV() == 0 || OI.armJoystick.getRawButton(2)){
      t.setTransportSpeed(0.4);
    } else if(OI.armJoystick.getPOV() == 180){
      t.setTransportSpeed(-0.4);
    } else {
      t.setTransportSpeed(0);
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
