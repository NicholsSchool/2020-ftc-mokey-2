package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Elevator {

    private DcMotor mLElevator;
    private DcMotor mRElevator;

    /**
     * Creates an elevator with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey 2
     */
    public Elevator(HardwareMap hardwareMap) {
        mLElevator = hardwareMap.get(DcMotor.class, "lElevator");
        mRElevator = hardwareMap.get(DcMotor.class, "rElevator");

        mLElevator.resetDeviceConfigurationForOpMode();
        mRElevator.resetDeviceConfigurationForOpMode();
    }

    /**
     * Moves the elevator
     * @param speed speed of the motors, in the range [-1.0, 1.0]
     */
    public void move(double speed) {
        mLElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mLElevator.setPower(speed);
        mRElevator.setPower(speed);
    }



    /**
     * Soft stops the intake.
     */
    public void stop() {
        move(0.0);
    }

    /**
     * Adds debug values to the telemetry.
     */
    public void debug() {

    }
}
