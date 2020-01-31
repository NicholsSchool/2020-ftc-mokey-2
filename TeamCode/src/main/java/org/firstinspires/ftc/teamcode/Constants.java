package org.firstinspires.ftc.teamcode;

/**
 * The Constants class defines static constants for various robot operations.
 */
public class Constants {

    // Measurements
    public static final int kTicksPerRotation = 1120;
    public static final double kWheelDiameter = 3.92;
    public static final double kGearRatio = 5.0/7.0;
    public static final double kTicksPerInch = kTicksPerRotation / (Math.PI * kWheelDiameter) * kGearRatio;
    public static final double kBaseDiameter = 20.0;
    public static final double kInchesPerDegree = Math.PI * kBaseDiameter / 360.0;
    //

    // Drive train motion profiling
    public static final double kMaxVelocity = 3400.0;
    public static final double kMinVelocity = kMaxVelocity * 0.25;
    public static final double kCruiseVelocity = kMaxVelocity;
    public static final double kAcceleration = kCruiseVelocity;
    //

    // Bang-bang margins
    public static final int kEncoderMargin = 50;
    public static final int kAngleMargin = 8;
    //

    // Subsystem constants
    public static final double kIntakeSpeed = 1.0;
    public static final double kWinchSpeed = 1.0;
    public static final double kDispenserSpeed = 1.0;
    public static final int kDispenserPosition = 70;
    //

    // Vision
    public static final int kRedScanLineStart = 1110;
    public static final int kRedScanLineEnd = 1280;
    public static final int kBlueScanLineStart = 195;
    public static final int kBlueScanLineEnd = 365;
    public static final int kScanLineY = 710;
    public static final int kYellowThresh = (kRedScanLineEnd - kRedScanLineStart) * 140;
    //
}
