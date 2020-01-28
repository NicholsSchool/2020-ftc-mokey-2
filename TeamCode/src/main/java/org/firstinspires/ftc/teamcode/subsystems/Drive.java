package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Drive controls the drive train subsystem of Mokey.
 */
public class Drive {

    private DcMotor mLFDrive;
    private DcMotor mLBDrive;
    private DcMotor mRFDrive;
    private DcMotor mRBDrive;

    private ElapsedTime mElapsedTime;

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

        mElapsedTime = new ElapsedTime();
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
     * Drives straight to a position with encoders.
     * @param position the target encoder position
     * @param power the power of the motors, in the range [0.0, 1.0]
     * @return true if the robot is still moving towards the target, false if the robot is at the target
     */
    public boolean move(int position, double power) {
        int currentPosition = mLFDrive.getCurrentPosition();

        double speed = currentPosition < position ? power : -power;

        if((speed > 0 && currentPosition < position) || (speed < 0 && currentPosition > position)) {
            move(speed, speed);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Turns in place to a position with the IMU.
     * @param angle the target angle, positive is counterclockwise
     * @param power the power of the motors, in the range [0.0, 1.0]
     * @return true if the robot is still moving towards the target, false if the robot is at the target
     */
    public boolean turn(double angle, double power) {
        double currentAngle = Robot.imu.getOrientation();

        double speed = currentAngle < angle ? power : -power;

        if((speed > 0 && currentAngle < angle) || (speed < 0 && currentAngle > angle)) {
            move(-speed, speed);

            return true;
        } else {
            return false;
        }
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
        Robot.telemetry.addData("Position: ", mLFDrive.getCurrentPosition());
        Robot.telemetry.addData("Velocity: ", mLFDrive.getCurrentPosition() / mElapsedTime.seconds());
    }
}
