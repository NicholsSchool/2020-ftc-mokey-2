package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.TrapezoidalProfile;

/**
 * An auto routine used for testing.
 */
public class TestRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     */
    public static void run(LinearOpMode opMode) {
        Robot.init(opMode.hardwareMap, opMode.telemetry, true);
        opMode.waitForStart();


        Robot.drive.setTargetPosition(39);
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        Robot.drive.setTargetAngle(90);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        Robot.stop();
        opMode.sleep(3000);
    }
}
