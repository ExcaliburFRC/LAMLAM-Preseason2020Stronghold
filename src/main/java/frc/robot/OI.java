package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI{
    public static Joystick driveJoystick;
    public static Joystick armJoystick;

    private static OI instance;

    private OI(){
        driveJoystick = new Joystick(1);
        armJoystick = new Joystick(0);
    }

    public static OI getInstance(){
        if (instance == null) instance = new OI();
        return instance;
    }


}