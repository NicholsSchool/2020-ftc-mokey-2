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
    public static final double kBaseDiameter = 24.0;
    public static final double kTicksPerDegree = Math.PI * kBaseDiameter / 360.0 * kTicksPerInch;
    //

    // Drive train motion profiling
    public static final double kMaxVelocity = 3400.0;
    public static final double kMinVelocity = kMaxVelocity * 0.2;
    public static final double kCruiseVelocity = kMaxVelocity;
    public static final double kAcceleration = kCruiseVelocity;
    //

    public static final double kTurnNormalizationCoefficient = 1.35;
    public static final double kAutoDriveSpeed = 0.4;

    // Bang-bang margins
    public static final int kEncoderMargin = 50;
    public static final int kAngleMargin = 5;
    //

    // Subsystem constants
    public static final double kIntakeSpeed = 1.0;
    public static final double kWinchSpeed = 1.0;
    public static final double kDispenserSpeed = 1.0;
    public static final int kDispenserPosition = 70;
    //

    // Vision
    public static final int kRedScanLineStart = 1115;
    public static final int kRedScanLineEnd = 1275;
    public static final int kBlueScanLineStart = 200;
    public static final int kBlueScanLineEnd = 360;
    public static final int kScanLineY = 710;
    public static final int kYellowThresh = (kRedScanLineEnd - kRedScanLineStart) * 140;
    //
}
