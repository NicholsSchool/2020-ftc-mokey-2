package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.ParkRoutine;

/**
 * An auto OpMode for turning right and parking.
 */
@Autonomous(name="ParkRightAuto")
public class ParkRightAuto extends LinearOpMode {

    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        String[] args = getClass().getSimpleName().split("(?<=[a-z])(?=[A-Z])"); // split class name for args
        ParkRoutine.run(this, args[1]);
    }

}
