/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.TowerCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Tower;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TowerCommand extends Command {
  Tower t = Robot.m_tower;
  double turn_kP = 0.0005;
  double turn_Max = 0.32;
  double angleToTick = 56.74;
  double aFF = 0.0925;
  double turrentPower;

  public TowerCommand() {
    requires(Robot.m_tower);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.armJoystick.getPOV() == 90){
      t.setTurnRate(0.3);
    } else if (OI.armJoystick.getPOV() == 270) {
      t.setTurnRate(-0.3);
    } 
    else {
      // //Joystick is not being used
      if (Robot.m_limelight.getTv() == 1 && Robot.m_limelight.getCamMode() == 0){ //Limelight detected target
        turrentPower = turn_kP * (Robot.m_limelight.getTx()-0.2) * angleToTick;
        turrentPower += turrentPower > 0 ? aFF : -aFF;

        boolean towerInBounds = !((t.getTurnValue() > t.POS_LIMIT && turrentPower < 0) || (t.getTurnValue() < t.NEG_LIMIT && turrentPower > 0));
        boolean angleTolarance = Math.abs((Robot.m_limelight.getTx()-0.2)) > 0.75;

        if (towerInBounds && angleTolarance){ //check you are not exiting the sides
          Robot.m_tower.setTurnRate(clip(turrentPower,turn_Max));
        }
      } else {
        t.setTurnRate(0); //block mode
      }
    }

    // t.setTurnRate(OI.armJoystick.getRawAxis(2)*turn_Max);

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
