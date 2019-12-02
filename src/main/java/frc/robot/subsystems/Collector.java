/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.CollectorCommands.CollectorDrive;

public class Collector extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Collector instance;
  private Spark roller;
  private DoubleSolenoid lifter1, lifter2;

  private Collector(){
    roller = new Spark(RobotMap.rollerP);
    lifter1 = new DoubleSolenoid(RobotMap.lifter1P1, RobotMap.lifter1P2);
    lifter2 = new DoubleSolenoid(RobotMap.lifter2P1, RobotMap.lifter2P2);
  }

  public static Collector getInstance(){
    if (instance == null) instance = new Collector();
    return instance;
  }

  public void setLifterState(boolean state){
    DoubleSolenoid.Value solestate = state ? Value.kForward : Value.kReverse;
    lifter1.set(solestate);
    lifter2.set(solestate);
  }

  public void setRoller(double power){
    roller.set(power);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new CollectorDrive());
  }
}
