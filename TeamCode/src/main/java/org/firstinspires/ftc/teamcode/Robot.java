package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.sensors.Imu;
import org.firstinspires.ftc.teamcode.sensors.Vision;
import org.firstinspires.ftc.teamcode.subsystems.Dispenser;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Winch;

/**
 * The Robot class contains static references to every subsystem and manages their initializations.
 */
public class Robot {
    public static Telemetry telemetry;

    public static Drive drive;
    public static Intake intake;
    public static Winch winch;
    public static Dispenser dispenser;

    public static Imu imu;
    public static Vision vision;

    /**
     * Initializes each subsystem and sensor to its default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     * @param telemetry the telemetry of the OpMode
     * @param isAuto if the robot is in autonomous
     */
    public static void init(HardwareMap hardwareMap, Telemetry telemetry, boolean isAuto) {
        Robot.telemetry = telemetry;

        drive = new Drive(hardwareMap);
        intake = new Intake(hardwareMap);
        winch = new Winch(hardwareMap);
        dispenser = new Dispenser(hardwareMap);

        if(isAuto) {
            imu = new Imu(hardwareMap);
            vision = new Vision();
            drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    /**
     *Initializes each subsystem and sensor to its default state at the beginning of TeleOp.
     * @param hardwareMap the hardware map of Mokey
     * @param telemetry the telemetry of the OpMode
     */
    public static void init(HardwareMap hardwareMap, Telemetry telemetry) {
        init(hardwareMap, telemetry, false);
    }

    /**
     * Soft stops the robot. Note this does not reset and sensors or encoders.
     */
    public static void stop() {
        drive.stop();
        intake.stop();
        winch.stop();
        dispenser.stop();
    }
}
