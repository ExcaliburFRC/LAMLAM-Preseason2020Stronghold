/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ShooterCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AccelerateFlyWheelTimed extends Command {
  Shooter s = Robot.m_shooter;
  Limelight l = Robot.m_limelight;
  double milliTime;
  double ticks;

  double k_TickToTime = 0.02;
  public AccelerateFlyWheelTimed(int milliTime) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(s);
    this.milliTime = milliTime;
    ticks = milliTime/k_TickToTime;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    ticks = milliTime/k_TickToTime;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    s.setShootingPower(s.distToPower(l.getDistance()));
    ticks--;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (ticks <= 0);
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
