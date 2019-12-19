package frc.robot.PathfinderCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

import java.io.IOException;

public class PathCommandSimple extends Command {
    private static final int k_ticks_per_rev = 360;
    private static final double k_wheel_diameter = 15.24;
    // private static final double k_max_velocity = 2.5;
    private static final double kP = 1, kD = 0.1, kV = 0, kA = 0, k_gp = 0.8;

    private Trajectory left_trajectory, right_trajectory;
    private EncoderFollower left, right;

    public PathCommandSimple(String filepath) {
        try {
            right_trajectory = PathfinderFRC.getTrajectory(filepath + ".right.pf1.csv");//reads from file
            left_trajectory = PathfinderFRC.getTrajectory(filepath + ".left.pf1.csv");
        } catch (IOException e) {
            System.out.println("file doesnt exist. Set one and download ASAP.");
            System.out.println("Original Error Message : " + e.getMessage());
        }

        left = new EncoderFollower(left_trajectory);
        right = new EncoderFollower(right_trajectory);

        left.configureEncoder(Robot.m_chassi.getLeftEncoder(), k_ticks_per_rev,
                k_wheel_diameter);
        right.configureEncoder(Robot.m_chassi.getRightEncoder(), k_ticks_per_rev,
                k_wheel_diameter);

        left.configurePIDVA(kP, 0, kD, kV, kA);
        right.configurePIDVA(kP, 0, kD, kV, kA);
    }

    @Override
    protected void initialize() {
        left.reset();
        right.reset();
    }

    @Override
    protected void execute() {
        double left_speed = left.calculate(Robot.m_chassi.getLeftEncoder());
        double right_speed = right.calculate(Robot.m_chassi.getRightEncoder());

        double heading = Robot.m_chassi.getGyroValue();
        double desired_heading = Pathfinder.r2d(left.getHeading());
        double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
        double turn =  k_gp * (-1.0/80.0) * heading_difference;

        Robot.m_chassi.getDrive().tankDrive(left_speed + turn, right_speed - turn);
    }

    @Override
    protected boolean isFinished() {
        return (left.isFinished() && right.isFinished());
    }

    @Override
    protected void end() {
        Robot.m_chassi.getDrive().tankDrive(0,0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}