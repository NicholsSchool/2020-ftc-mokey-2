package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Dispenser controls the dispenser subsystem of Mokey.
 */
public class Dispenser {

    private DcMotor mGate;
    private DcMotor mSweeper;

    /**
     * Creates a dispenser with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Dispenser(HardwareMap hardwareMap) {
        mGate = hardwareMap.get(DcMotor.class, "gate");
        mSweeper = hardwareMap.get(DcMotor.class, "sweeper");

        mGate.resetDeviceConfigurationForOpMode();
        mSweeper.resetDeviceConfigurationForOpMode();

        mGate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mSweeper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoders();
    }

    /**
     * Opens the dispenser.
     * @param power the power of the motors
     */
    public void dispense(double power) {
        mGate.setTargetPosition(Constants.kDispenserPosition);
        mGate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mGate.setPower(power);

        if(!mGate.isBusy()) {
            mSweeper.setTargetPosition(Constants.kDispenserPosition);
            mSweeper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            mSweeper.setPower(power);
        }
    }

    /**
     * Resets the dispenser.
     * @param power the power of the motors
     */
    public void reset(double power) {
        mGate.setTargetPosition(0);
        mSweeper.setTargetPosition(0);

        mGate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mSweeper.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        mGate.setPower(power);
        mSweeper.setPower(power);
    }

    /**
     * Soft stops the dispenser.
     */
    public void stop() {
        mGate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mSweeper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mGate.setPower(0.0);
        mSweeper.setPower(0.0);
    }

    /**
     * Resets the drive encoders.
     */
    public void resetEncoders() {
        mGate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mSweeper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Adds debug values to the telemetry.
     */
    public void debug() {
        Robot.telemetry.addData("Gate", mGate.getCurrentPosition());
        Robot.telemetry.addData("Sweeper", mSweeper.getCurrentPosition());
    }
}
