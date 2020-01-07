/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.CollectorCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Collector;
import frc.robot.OI;

public class CollectorDrive extends Command {
  Collector c = Robot.m_collector;
  public CollectorDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(c);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.armJoystick.getRawButton(3)){
      c.setLifterState(true);
    } else if (OI.armJoystick.getRawButton(4)) {
      c.setLifterState(false);
    }
    
    if (OI.armJoystick.getRawAxis(3) < 0){
      c.setRoller(0.6);
      return;
    }

    if (OI.armJoystick.getRawButton(2)){
      c.setRoller(-0.6);
    } else {
      c.setRoller(0);
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
