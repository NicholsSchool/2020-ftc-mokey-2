package org.firstinspires.ftc.teamcode.opmodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.SkystoneRoutine;

/**
 * An auto OpMode for grabbing a Skystone while starting on the red alliance.
 */
@Autonomous(name="RedSkystoneAuto")
public class RedSkystoneAuto extends LinearOpMode {

    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        String[] args = getClass().getSimpleName().split("(?<=[a-z])(?=[A-Z])"); // split class name for args
        SkystoneRoutine.run(this, args[0]);
    }

}
