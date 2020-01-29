package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.SkystonePosition;
import org.firstinspires.ftc.teamcode.util.TrapezoidalProfile;

/**
 * An auto routine used for grabbing a Skystone from starting position.
 */
public class SkystoneRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     */
    public static void run(LinearOpMode opMode, String alliance) {
        Robot.init(opMode.hardwareMap, opMode.telemetry, true);

        SkystonePosition skystonePos = SkystonePosition.OUTER;

        while(!opMode.isStarted()) {
            SkystonePosition pos = Robot.vision.getSkystonePosition(alliance);
            if(pos != SkystonePosition.UNKNOWN) {
                skystonePos = pos;
            }
            Robot.telemetry.addData("Skystone position", pos);
            Robot.telemetry.update();
        }


        double ticks = 0;
        double angle = 0;
        TrapezoidalProfile tp = null;

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case INNER:
                ticks = 7 * Constants.kTicksPerInch;
                break;
            case CENTER:
                ticks = 18 * Constants.kTicksPerInch;
                break;
            case OUTER:
                ticks = 26 * Constants.kTicksPerInch;
                break;
        }
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.move((int)ticks, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();

        Robot.imu.reset();
        angle = alliance.equals("Red") ? 30 : -30;
        ticks = angle * Constants.kTicksPerDegree;
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.turn(angle, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case INNER:
                ticks = 34 * Constants.kTicksPerInch;
                break;
            case CENTER:
                ticks = 29 * Constants.kTicksPerInch;
                break;
            case OUTER:
                ticks = 16 * Constants.kTicksPerInch;
                break;
        }
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.move((int)ticks, tp.get()) && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kIntakeSpeed);
        }
        Robot.stop();

        Robot.drive.resetEncoders();
        ticks = -16 * Constants.kTicksPerInch;
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.move((int)ticks, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();

        angle = alliance.equals("Red") ? -90 : 90;
        ticks = angle * Constants.kTicksPerDegree;
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.turn(angle, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case INNER:
                ticks = 42 * Constants.kTicksPerInch;
                break;
            case CENTER:
                ticks = 38 * Constants.kTicksPerInch;
                break;
            case OUTER:
                ticks = 30 * Constants.kTicksPerInch;
                break;
        }
        tp = new TrapezoidalProfile(ticks);
        while(Robot.drive.move((int)ticks, tp.get()) && opMode.opModeIsActive()) {
        }
        Robot.stop();
    }
}
