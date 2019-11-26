package frc.robot.Utils;

class Translations{
    public static double distToTicks(double dist){
        double numerator = 360 * dist;
        double denomenator = Math.PI * 15.24; // TODO needs to be defined
        return numerator/denomenator;
    }
}