package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto routine used for testing.
 */
public class TestRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     */
    public static void run(LinearOpMode opMode) {
        Robot.initAuto(opMode.hardwareMap, opMode.telemetry);
        opMode.waitForStart();
        if(opMode.isStopRequested()) {
            return;
        }

        Robot.driveEx.followTrajectorySync(
                Robot.driveEx.getTrajectoryBuilder()
                        .splineTo(new Pose2d(20, 0, 0))
                        .build()
        );

        opMode.sleep(2000);
    }
}
