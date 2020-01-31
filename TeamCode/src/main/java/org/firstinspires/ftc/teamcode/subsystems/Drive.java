package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.TrapezoidalProfile;

/**
 * Drive controls the drive train subsystem of Mokey.
 */
public class Drive {

    private DcMotor mLFDrive;
    private DcMotor mLBDrive;
    private DcMotor mRFDrive;
    private DcMotor mRBDrive;

    private TrapezoidalProfile mProfile;
    private double mTargetPosition;
    private double mTargetAngle;

    /**
     * Creates a drive train with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Drive(HardwareMap hardwareMap) {
        mLFDrive = hardwareMap.get(DcMotor.class, "lFDrive");
        mLBDrive = hardwareMap.get(DcMotor.class, "lBDrive");
        mRFDrive = hardwareMap.get(DcMotor.class, "rFDrive");
        mRBDrive = hardwareMap.get(DcMotor.class, "rBDrive");

        mLFDrive.resetDeviceConfigurationForOpMode();
        mLBDrive.resetDeviceConfigurationForOpMode();
        mRFDrive.resetDeviceConfigurationForOpMode();
        mRBDrive.resetDeviceConfigurationForOpMode();

        mRFDrive.setDirection(DcMotor.Direction.REVERSE);
        mRBDrive.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        mLFDrive.setZeroPowerBehavior(behavior);
        mLBDrive.setZeroPowerBehavior(behavior);
        mRFDrive.setZeroPowerBehavior(behavior);
        mRBDrive.setZeroPowerBehavior(behavior);
    }

    /**
     * Tank drive.
     * @param lSpeed speed of the left motors, in the range [-1.0, 1.0]
     * @param rSpeed speed of the right motors, in the range [-1.0, 1.0]
     */
    public void move(double lSpeed, double rSpeed) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mLBDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRBDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mLFDrive.setPower(lSpeed);
        mLBDrive.setPower(lSpeed);
        mRFDrive.setPower(rSpeed);
        mRBDrive.setPower(rSpeed);
    }

    /**
     * Mecanum strafe horizontally.
     * @param speed speed of the strafe, in the range [-1.0, 1.0], a positive speed moves left
     */
    public void strafe(double speed) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mLBDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRBDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mLFDrive.setPower(-speed);
        mLBDrive.setPower(speed);
        mRFDrive.setPower(speed);
        mRBDrive.setPower(-speed);
    }

    /**
     * Drives to the set target position with encoders.
     * @return true if the robot is still moving towards the target, false if the robot is at the target
     */
    public boolean move() {
        int currentPosition = mLFDrive.getCurrentPosition();

        double speed = currentPosition < mTargetPosition ? mProfile.get() : -mProfile.get();

        if(Math.abs(currentPosition - mTargetPosition) > Constants.kEncoderMargin) {
            move(speed, speed);
            return true;
        } else {
            stop();
            return false;
        }
    }

    /**
     * Turns in place to the set target orientation with the IMU.
     * @return true if the robot is still moving towards the target, false if the robot is at the target
     */
    public boolean turn() {
        double currentAngle = Robot.imu.getOrientation();

        double speed = currentAngle < mTargetAngle ? mProfile.get() : -mProfile.get();

        if(Math.abs(currentAngle - mTargetAngle) > Constants.kAngleMargin) {
            move(-speed, speed);
            return true;
        } else {
            stop();
            return false;
        }
    }

    /**
     * Sets the closed loop target position and resets the encoders.
     * @param position the position in inches
     */
    public void setTargetPosition(double position) {
        resetEncoders();
        mTargetPosition = position * Constants.kTicksPerInch;
        mProfile = new TrapezoidalProfile(mTargetPosition);
    }

    /**
     * Sets the closed loop target orientation and resets the imu.
     * @param angle the angle in degrees
     */
    public void setTargetAngle(double angle) {
        Robot.imu.reset();
        mTargetAngle = angle;
        setTargetPosition(angle * Constants.kInchesPerDegree);
    }


    /**
     * Resets the drive encoders.
     */
    public void resetEncoders() {
        mLFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mLBDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mRBDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Soft stops the drive train.
     */
    public void stop() {
        move(0.0, 0.0);
    }

    /**
     * Adds debug values to the telemetry.
     */
    public void debug() {
        Robot.telemetry.addData("Zero power behavior", mLFDrive.getZeroPowerBehavior());
    }
}
