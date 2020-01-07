/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ChassiCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Chassi;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;

public class TXPersuite extends Command {
  Chassi c = Robot.m_chassi;
  Limelight l = Robot.m_limelight;

  double error, kP, aFF;

  double forward;
  double lastTime;

  boolean ended;
  double endTimes;

  public TXPersuite() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(c);
    requires(Robot.m_collector);

    kP = 0.0365;
    aFF = 0.2825;
    endTimes = 750;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    ended = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (ended) {
      Robot.m_chassi.getDrive().arcadeDrive(0, 0);
      return;
    }
    error = (l.getTx()-0.2) * kP;
    error += error > 0 ? aFF : -aFF; 
    SmartDashboard.putNumber("Error", System.currentTimeMillis());
    error = clip(error,0.65);

    if (l.getTv() == 1) {
      lastTime = System.currentTimeMillis();
    }

    if (l.getTv() == 1 || System.currentTimeMillis() - lastTime <= endTimes){
      forward = -0.625;
    } else {
      forward = 0;
    }

    if (l.getTv() == 0 && System.currentTimeMillis() - lastTime <= endTimes){
      Robot.m_collector.setRoller(-0.6);
    } else {
      Robot.m_collector.setRoller(0);
    }
    // c.getDrive().arcadeDrive(OI.armJoystick.getRawAxis(1),error);
    c.getDrive().arcadeDrive(forward, error);

    if (System.currentTimeMillis() - lastTime > endTimes){
      ended = true;
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
    c.getDrive().arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    c.getDrive().arcadeDrive(0, 0);
  }

  private double clip(double val, double max){
    if (val > max) return max;
    else if (val < -max) return -max;
    return val;
  }
}
