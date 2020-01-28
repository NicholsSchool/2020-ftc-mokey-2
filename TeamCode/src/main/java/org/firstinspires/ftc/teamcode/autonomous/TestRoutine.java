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

        int ticks = 0;
        int angle = 0;
        TrapezoidalProfile tp = null;

        Robot.drive.resetEncoders();
        ticks = (int)(39 * Constants.kTicksPerInch);
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.move(ticks, tp.get()) && opMode.opModeIsActive()) {
            Robot.telemetry.addData("Speed", tp.get());
            Robot.telemetry.update();
        }
        Robot.stop();

//        Robot.imu.reset();
//        angle = 90;
//        while(Robot.drive.turn(angle, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {
//
//        }
//        Robot.stop();

        opMode.sleep(3000);
    }
}
