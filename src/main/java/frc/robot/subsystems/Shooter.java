/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.ShooterCommands.*;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Shooter instance;

  Spark sm1, sm2;
  SpeedControllerGroup shootGroup;
  Servo tiltServo;
  
  private Shooter(){
    tiltServo = new Servo(RobotMap.TILTSERVOP);
    sm1 = new Spark(RobotMap.SM1P);
    sm2 = new Spark(RobotMap.SM2P);
    shootGroup = new SpeedControllerGroup(sm1, sm2);
  }

  public static Shooter getInstance(){
    if (instance == null) instance = new Shooter();
    return instance;
  }

  public void setShootingPower(double p){
    shootGroup.set(p);
  }

  public void setTilt(double val){
    this.tiltServo.set(val);
  }

  public void incTilt(double val){
    this.tiltServo.set(this.tiltServo.get()+val);
  }

  public double distToPower(double dist){
    return dist/3; //TODO needs to be tuned
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ShooterDrive());
  }
}
