package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends Subsystem {

     /*
        Values:
            Getters:
                tv - Whether the limelight has any valid targets (0 or 1)
                tx - Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
                ty - Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
                ta - Target Area (0% of image to 100% of image)
                tshort - Sidelength of shortest side of the fitted bounding box (pixels)
                tlong - Sidelength of longest side of the fitted bounding box (pixels)
                thor - Horizontal sidelength of the rough bounding box (0 - 320 pixels)
                tvert - Vertical sidelength of the rough bounding box (0 - 320 pixels)
            Setters:
                LED mode : 1 (off), 2 (blink), 3 (on)
                Cam Mode : 0 Vision, 1 Driving
    */
    int tv;
    double tx;
    double ty;
    double ta;
    double tshort;
    double tlong;
    double thor;
    double tvert;
    double camMode, ledMode, pipeline;

    private static Limelight instance;

    private Limelight(){}

    public static Limelight getInstance(){
        if (instance == null) instance = new Limelight();
        return instance;
    }

    private double getVar(String var){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry(var).getDouble(0);
    }

    @Override
    public void periodic(){
        //need to think about only activating some times so that it isnt too CPU intensive
        this.tv = (int) (this.getVar("tv"));
        this.tx = this.getVar("tx");
        this.ty = this.getVar("ty");
        this.ta = this.getVar("ta");
        this.tshort = this.getVar("tshort");
        this.tlong = this.getVar("tlong");
        this.thor = this.getVar("thor");
        this.tvert = this.getVar("tvert");
        this.ledMode = this.getVar("ledMode");
        this.camMode = this.getVar("camMode");
        this.pipeline = this.getVar("pipeline");
    }
    
    public void setLEDMode(int mode){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(mode);
    }

    public void setCamMode(int mode){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(mode);
    }

    public void setPipeline(int pipenum){
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipenum);
    }

    public int getTv() {
        return tv;
    }

    public double getTx() {
        return tx;
    }

    public double getTy() {
        return ty;
    }

    public double getTa() {
        return ta;
    }

    public double getTshort() {
        return tshort;
    }

    public double getTlong() {
        return tlong;
    }


    public double getThor() {
        return thor;
    }

    public double getTvert() {
        return tvert;
    }

    public double getCamMode() {
        return camMode;
    }

    public double getLEDMode() {
        return ledMode;
    }

    public double getPipeline(){
        return pipeline;
    }

    public double getDistance(){
        return getTy()*0.8; //TODO needs to be tuned
    }

    @Override
    public void initDefaultCommand() {
    }
}