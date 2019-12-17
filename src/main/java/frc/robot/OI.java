package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.ChassiCommands.toggleCompressorState;
import frc.robot.ShooterCommands.AutoShoot;
import frc.robot.TowerCommands.CenterTurrent;

public class OI{
    public static Joystick driveJoystick;
    public static Joystick armJoystick;

    private static OI instance;

    private static JoystickButton compressorButton;
    private static JoystickButton autoShoot;
    private static JoystickButton centerTurrent;

    private OI(){
        driveJoystick = new Joystick(1);
        armJoystick = new Joystick(0);

        compressorButton = new JoystickButton(driveJoystick,7);
        autoShoot = new JoystickButton(armJoystick, 11);
        centerTurrent = new JoystickButton(armJoystick, 5);
    }

    public static OI getInstance(){
        if (instance == null) instance = new OI();
        return instance;
    }

    public void initJoystickActions(){
        compressorButton.whenPressed(new toggleCompressorState());
        autoShoot.whenPressed(new AutoShoot());
        centerTurrent.whileHeld(new CenterTurrent());
    }


}