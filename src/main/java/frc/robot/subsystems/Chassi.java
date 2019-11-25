/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.ChassiCommands.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Chassi extends Subsystem {

  private static Chassi instance;
  Spark LBM, LFM, RBM, RFM;
  Encoder LE, RE;
  SpeedControllerGroup right;
  SpeedControllerGroup left;
  DifferentialDrive drive;
  AHRS gyro;
  
  private Chassi(){
    LBM = new Spark(RobotMap.LBMP);
    LFM = new Spark(RobotMap.LFMP);
    RBM = new Spark(RobotMap.RBMP);
    RFM = new Spark(RobotMap.RFMP);

    right = new SpeedControllerGroup(RBM, RFM);
    left = new SpeedControllerGroup(LBM, LFM);

    drive = new DifferentialDrive(left, right);

    LE = new Encoder(RobotMap.LE1, RobotMap.LE2);
    RE = new Encoder(RobotMap.RE1, RobotMap.RE2);

    gyro =  new AHRS(SPI.Port.kMXP);
  }

  public static Chassi getInstance(){
    if (instance == null) instance = new Chassi();
    return instance;
  }
  public DifferentialDrive getDrive(){
    return drive;
  }

  public int getLeftEncoder(){
    return LE.get();
  }

  public int getRightEncoder(){
    return RE.get();
  }

  public void resetEncoders(){
    LE.reset();
    RE.reset();
  }

  public void resetGyro(){
    gyro.reset();
  }

  public double getGyroValue(){
    return gyro.getAngle();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ChassiDrive());
  }
}
