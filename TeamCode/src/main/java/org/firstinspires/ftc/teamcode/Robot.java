package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Drive;

public class Robot {

    public static Drive drive;

    /**
     * Initializes each subsystem and sensor to its default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey 2
     */
    public static void init(HardwareMap hardwareMap) {
        drive = new Drive(hardwareMap);
    }

    /**
     * Soft stops the robot. Note this does not reset and sensors or encoders.
     */
    public static void stop() {
        drive.stop();
    }
}
