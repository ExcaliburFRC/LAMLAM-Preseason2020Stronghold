/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.commands.TowerCommand;

/**
 * Add your docs here.
 */
public class Tower extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Spark turnMotor;
  Encoder turnMotorEncoder;

  static Tower instance;

  private Tower(){
    turnMotor = new Spark(RobotMap.TTM);
    turnMotorEncoder = new Encoder(RobotMap.TME1, RobotMap.TME2);
  }

  public static Tower getInstance(){
    if (instance==null) instance = new Tower();
    return instance;
  }

  public void setTurnRate(double p){
    turnMotor.set(p);
  }

  public int getTurnValue(){
    return turnMotorEncoder.get();
  }

  public void resetEncoder(){
    turnMotorEncoder.reset();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TowerCommand());
  }
}
