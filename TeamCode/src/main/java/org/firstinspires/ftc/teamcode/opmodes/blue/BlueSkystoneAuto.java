package org.firstinspires.ftc.teamcode.opmodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.SkystoneRoutine;

/**
 * An auto OpMode for grabbing a Skystone while starting on the blue alliance.
 */
@Autonomous(name="BlueSkystoneAuto")
public class BlueSkystoneAuto extends LinearOpMode {

    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        String[] args = getClass().getSimpleName().split("(?<=[a-z])(?=[A-Z])"); // split class name for args
        SkystoneRoutine.run(this, args[0]);
    }

}
