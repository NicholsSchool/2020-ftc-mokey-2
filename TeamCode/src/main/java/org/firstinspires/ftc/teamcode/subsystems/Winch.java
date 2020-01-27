package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Winch {

    private DcMotorSimple mLWinch;
    private DcMotorSimple mRWinch;

    /**
     * Creates an intake with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey 2
     */
    public Winch(HardwareMap hardwareMap) {
        mLWinch = hardwareMap.get(DcMotorSimple.class, "lWinch");
        mRWinch = hardwareMap.get(DcMotorSimple.class, "rWinch");

        mLWinch.resetDeviceConfigurationForOpMode();
        mRWinch.resetDeviceConfigurationForOpMode();

        mRWinch.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Intakes
     * @param speed speed of the motors, in the range [-1.0, 1.0]
     */
    public void wind(double speed) {
        mLWinch.setPower(speed);
        mRWinch.setPower(speed);
    }


    /**
     * Soft stops the intake.
     */
    public void stop() {
        wind(0.0);
    }

    /**
     * Adds debug values to the telemetry.
     */
    public void debug() {

    }
}
