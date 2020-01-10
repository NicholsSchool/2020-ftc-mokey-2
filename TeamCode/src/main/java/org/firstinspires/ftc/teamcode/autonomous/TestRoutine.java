package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
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
        int ticks = (int)(24 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        Robot.imu.reset();
        int angle = 0;
        while(Robot.drive.turn(angle, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
    }
}
