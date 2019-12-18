package frc.robot.XcaLIB.Singletons;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

import java.io.IOException;

public class PathCommandSimple extends Command {
    private static final int k_ticks_per_rev = 1024;
    private static final double k_wheel_diameter = 4.0 / 12.0;
    private static final double k_max_velocity = 1.7;
    private static final double P = 1, D = 0.1, kV = 1 / k_max_velocity, k_gp = 1;

    Trajectory left_traj, right_traj;
    EncoderFollower left, right;

    public PathCommandSimple(String filepath) {
        try {
            right_traj = PathfinderFRC.getTrajectory(filepath + ".right.pf1.csv");//reads from file
            left_traj = PathfinderFRC.getTrajectory(filepath + ".left.pf1.csv");
        } catch (IOException e) {
            System.out.println("file doesnt exist. Set one and download ASAP.");
            System.out.println("Original Error Message : " + e.getMessage());
        }

        left = new EncoderFollower(left_traj);
        right = new EncoderFollower(right_traj);

        left.configureEncoder(Robot.m_chassi.getLeftEncVal(), k_ticks_per_rev,
                k_wheel_diameter);
        right.configureEncoder(Robot.m_chassi.getLeftEncVal(), k_ticks_per_rev,
                k_wheel_diameter);

        left.configurePIDVA(P, 0, D, kV, 0);
        right.configurePIDVA(P, 0, D, kV, 0);
    }

    @Override
    protected void execute() {
        double left_speed = left.calculate(Robot.m_chassi.getLeftEncoder());
        double right_speed = right.calculate(Robot.m_chassi.getRightEncoder());

        double desired_heading = Pathfinder.r2d(left.getHeading());

        //TODO("check gyro val handling")
        double turn = k_gp * (desired_heading - Robot.m_chassi.gyro.getAngle());


        Robot.m_chassi.setLeft(left_speed + turn);
        Robot.m_chassi.setRight(right_speed - turn);
    }

    @Override
    protected boolean isFinished() {
        return (left.isFinished() || right.isFinished());
    }
}