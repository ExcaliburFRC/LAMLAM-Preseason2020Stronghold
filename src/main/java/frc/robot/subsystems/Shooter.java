/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.ShooterCommands.*;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Shooter instance;

  TalonSRX shooterMotor;

  public double SPEED_SETPOINT;
  private final double SPEED_TOLARANCE;
  private boolean PERSUIT_SPEED;
  
  private Shooter(){
    shooterMotor = new TalonSRX(RobotMap.SMP);

    SPEED_SETPOINT = 0;
    SPEED_TOLARANCE = 1000;
    PERSUIT_SPEED = false;

    shooterMotor.setSelectedSensorPosition(0);
    shooterMotor.setSensorPhase(true);
  }

  public static Shooter getInstance(){
    if (instance == null) instance = new Shooter();
    return instance;
  }

  public void setShootingPower(double p){
    shooterMotor.set(ControlMode.PercentOutput,p);
  }

  public double distToPower(double dist){
    return dist/3; //TODO needs to be tuned
  }

  @Override
  public void periodic(){
    if (PERSUIT_SPEED){
      // SmartDashboard.putBoolean("Speed_Persuit", true);
      shooterMotor.set(ControlMode.Velocity, SPEED_SETPOINT);
    } else {
      // SmartDashboard.putBoolean("Speed_Persuit", false);
    }
  }

  public boolean isInSpeedRange(){
    return Math.abs(this.getError()) < SPEED_TOLARANCE;
  }

  public void setSpeedPersuit(boolean t){
    PERSUIT_SPEED = t;
  }

  public void setSpeedSetpoint(double speed){
    SPEED_SETPOINT = -speed;
  }

  public int getSpeed(){
    return shooterMotor.getSelectedSensorVelocity();
  }

  public int getError(){
    return shooterMotor.getClosedLoopError()+4400;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ShooterDrive());
  }
}
