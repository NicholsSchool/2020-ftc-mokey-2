package org.firstinspires.ftc.teamcode;

public class Constants {

    public static final int kTicksPerDriveRotation = 1120;
    public static final double kDriveWheelDiameter = 3.92;
    public static final double kGearRatio = 0.75;
    public static final double kTicksPerInch = kTicksPerDriveRotation / (Math.PI * kDriveWheelDiameter) * kGearRatio;


    public static final int kEncoderMargin = 50;
    public static final int kAngleMargin = 5;
}
