package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Passthrough controls the passthrough subsystem of Mokey.
 */
public class Passthrough {

    private DcMotorSimple mLPassthrough;
    private DcMotorSimple mRPassthrough;

    /**
     * Creates an intake with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Passthrough(HardwareMap hardwareMap) {
        mLPassthrough = hardwareMap.get(DcMotorSimple.class, "lPassthrough");
        mRPassthrough = hardwareMap.get(DcMotorSimple.class, "rPassthrough");

        mLPassthrough.resetDeviceConfigurationForOpMode();
        mRPassthrough.resetDeviceConfigurationForOpMode();

        mRPassthrough.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * Passes the stone backwards.
     * @param speed speed of the servos, in the range [-1.0, 1.0]
     */
    public void move(double speed) {
        mLPassthrough.setPower(speed);
        mRPassthrough.setPower(speed);
    }


    /**
     * Soft stops the passthrough.
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
