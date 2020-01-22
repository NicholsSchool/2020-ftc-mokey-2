package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto OpMode at saves the current camera view.
 */
@Autonomous(name="Save Image")
public class SaveImageAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap, telemetry);

        waitForStart();

        Robot.vision.write();
        sleep(5000);
    }

}