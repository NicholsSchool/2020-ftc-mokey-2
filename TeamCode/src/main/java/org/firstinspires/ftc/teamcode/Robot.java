package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Imu;
import org.firstinspires.ftc.teamcode.sensors.Vision;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class Robot {

    public static Drive drive;

    public static Intake intake;

    public static Imu imu;
    public static Vision vision;

    /**
     * Initializes each subsystem and sensor to its default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey 2
     */
    public static void init(HardwareMap hardwareMap) {
        drive = new Drive(hardwareMap);

        intake = new Intake(hardwareMap);

        imu = new Imu(hardwareMap);
        vision = new Vision();
    }

    /**
     * Soft stops the robot. Note this does not reset and sensors or encoders.
     */
    public static void stop() {
        drive.stop();
        intake.stop();
    }
}
