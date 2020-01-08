package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Intake {

    private DcMotor mLIntake;
    private DcMotor mRIntake;

    /**
     * Creates an intake with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey 2
     */
    public Intake(HardwareMap hardwareMap) {
        mLIntake = hardwareMap.get(DcMotor.class, "lIntake");
        mRIntake = hardwareMap.get(DcMotor.class, "rIntake");

        mLIntake.resetDeviceConfigurationForOpMode();
        mRIntake.resetDeviceConfigurationForOpMode();
    }

    /**
     * Intakes
     * @param speed speed of the motors, in the range [-1.0, 1.0]
     */
    public void intake(double speed) {
        mLIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mLIntake.setPower(speed);
        mRIntake.setPower(speed);
    }



    /**
     * Soft stops the intake.
     */
    public void stop() {
        intake(0.0);
    }

    /**
     * Adds debug values to the telemetry.
     * @param telemetry the current telemetry
     */
    public void debug(Telemetry telemetry) {

    }
}
