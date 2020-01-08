package org.firstinspires.ftc.teamcode.autonomous;

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
        Robot.drive.resetEncoders();
        int ticks = 2000;
        while(Robot.drive.move(ticks, 0.5) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        Robot.imu.reset();
        int angle = 90;
        while(Robot.drive.turn(angle, 0.5) && opMode.opModeIsActive()) {

        }
        Robot.stop();
    }
}
