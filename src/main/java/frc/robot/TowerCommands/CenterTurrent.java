/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.TowerCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Tower;

public class CenterTurrent extends Command {
  Tower t = Robot.m_tower;

  double turn_kP = 0.000575;
  double turn_Max = 0.32;
  double aFF = 0.09;
  double turrentPower;


  public CenterTurrent() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(t);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {    
    turrentPower = turn_kP * (t.getTurnValue());
    turrentPower += turrentPower > 0 ? aFF : -aFF;

    boolean towerInBounds = !((t.getTurnValue() > t.POS_LIMIT && turrentPower < 0) || (t.getTurnValue() < t.NEG_LIMIT && turrentPower > 0));
    boolean angleTolarance = Math.abs(t.getTurnValue()) > 50;

    if (towerInBounds && angleTolarance){ //check you are not exiting the sides
      Robot.m_tower.setTurnRate(clip(turrentPower,turn_Max));
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

  private double clip(double val, double max){
    if (val > max) return max;
    else if (val < -max) return -max;
    return val;
  }
}
