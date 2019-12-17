/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Chassi;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tower;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Transporter;

//LINK FOR PHOENIX API:
//http://devsite.ctr-electronics.com/maven/release/com/ctre/phoenix/Phoenix-latest.json
public class Robot extends TimedRobot {

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  public static Chassi m_chassi;
  public static Shooter m_shooter;
  public static Tower m_tower;
  public static Transporter m_transporter;
  public static Limelight m_limelight;
  public static Collector m_collector;
  public static OI oi;

  @Override
  public void robotInit() {
    oi = OI.getInstance(); 
    m_chassi = Chassi.getInstance();
    m_shooter = Shooter.getInstance();
    m_tower = Tower.getInstance();
    m_limelight = Limelight.getInstance();
    m_collector = Collector.getInstance();
    m_transporter = Transporter.getInstance();
    oi.initJoystickActions();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("encoderVal", m_tower.getTurnValue());
    SmartDashboard.putNumber("Speed", m_shooter.getSpeed());
    SmartDashboard.putNumber("Error", m_shooter.getError());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
   
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
   
  }

  @Override
  public void teleopInit() {
    m_tower.resetEncoder();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
