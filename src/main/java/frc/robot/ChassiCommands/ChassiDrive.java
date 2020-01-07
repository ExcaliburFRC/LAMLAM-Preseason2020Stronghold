/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.ChassiCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Chassi;
import frc.robot.OI;

public class ChassiDrive extends Command {
  Chassi c = Robot.m_chassi;
  double xSpeed, yTurn;
  double turnSpeed;
  boolean isQuickTurn;
  public ChassiDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_chassi);
    turnSpeed = 0.85;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    isQuickTurn = OI.armJoystick.getRawAxis(3) < 0;
    xSpeed = OI.armJoystick.getRawAxis(1);
    yTurn = OI.armJoystick.getRawAxis(2);

    int type = (int) Robot.driveType.getSelected();

    switch (type){
      case 0:
        //TankDrive
        c.getDrive().tankDrive(OI.driveJoystick.getRawAxis(5)*0.8, OI.driveJoystick.getRawAxis(1)*0.8);
        break;
      case 1:
       //Arcade Drive
        c.getDrive().arcadeDrive(OI.armJoystick.getRawAxis(1), OI.armJoystick.getRawAxis(2)*0.85);
        break;
      case 2:
        //Curvature Drive
        c.getDrive().curvatureDrive(xSpeed, yTurn  * (isQuickTurn? 0.75 : 1), false);
        break;
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
