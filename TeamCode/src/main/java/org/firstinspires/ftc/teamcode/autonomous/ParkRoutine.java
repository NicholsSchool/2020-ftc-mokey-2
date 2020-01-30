package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.TrapezoidalProfile;

/**
 * An auto routine used for parking.
 */
public class ParkRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     */
    public static void run(LinearOpMode opMode, String direction) {
        Robot.init(opMode.hardwareMap, opMode.telemetry, true);

        opMode.waitForStart();

        double ticks = 0;
        double angle = 0;
        TrapezoidalProfile tp;

        Robot.drive.resetEncoders();
        ticks = 1 * Constants.kTicksPerInch;
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.move((int)ticks, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();

        Robot.imu.reset();
        angle = direction.equals("Left") ? 90 : -90;
        ticks = angle * Constants.kTicksPerDegree;
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.turn((int)angle, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();

        Robot.drive.resetEncoders();
        ticks = 12 * Constants.kTicksPerInch;
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.move((int)ticks, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();
    }
}
