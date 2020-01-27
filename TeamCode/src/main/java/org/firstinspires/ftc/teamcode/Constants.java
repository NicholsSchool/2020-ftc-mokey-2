package org.firstinspires.ftc.teamcode;

public class Constants {

    public static final int kTicksPerDriveRotation = 1120;
    public static final double kDriveWheelDiameter = 3.92;
    public static final double kGearRatio = 5.0/7.0;
    public static final double kTicksPerInch = kTicksPerDriveRotation / (Math.PI * kDriveWheelDiameter) * kGearRatio;

    public static final double kAutoDriveSpeed = 0.4;
    public static final double kAutoIntakeSpeed = 1.0;

    public static final double kWinchSpeed = 1.0;
    public static final double kElevatorUpSpeed = 0.75;
    public static final double kElevatorDownSpeed = -0.75;


    public static final int kEncoderMargin = 50;
    public static final int kAngleMargin = 10;


    public static final int kSkystoneInner = 1;
    public static final int kSkystoneCenter = 2;
    public static final int kSkystoneOuter = 3;
    public static final int kSkystoneUnknown = 4;

    // Vision
    public static final int kRedScanLineStart = 1115;
    public static final int kRedScanLineEnd = 1275;
    public static final int kBlueScanLineStart = 200;
    public static final int kBlueScanLineEnd = 360;
    public static final int kScanLineY = 710;
    public static final int kYellowThresh = (kRedScanLineEnd - kRedScanLineStart) * 110;
}
