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

public class TowerCommand extends Command {
  Tower t = Robot.m_tower;
  double turn_kP = 0.04;
  double turn_Max = 0.3;
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
    } else {
      t.setTurnRate(0);
    }
    // } else {
    //   t.setTurnRate(0);
    //   // //Joystick is not being used
    //   // if (Robot.m_limelight.getTv() == 1){ //Limelight detected target
    //   //   t.setTurnRate(turn_kP * Robot.m_limelight.getTx());
    //   // } else {
    //   //   t.setTurnRate(0); //block mode
    //   // }
    // }

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
}
