/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.GeneralCommands;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {
  double ticks;
  int milliTime;
  double k_tickToTime = 0.02;
  public Wait(int milliTime) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.milliTime = milliTime;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    ticks = milliTime/k_tickToTime;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    ticks--;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return ticks<=0;
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
