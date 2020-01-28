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


        int ticks = 0;
        int angle = 0;
        TrapezoidalProfile tp = null;

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case INNER:
                ticks = (int)(9 * Constants.kTicksPerInch);
                break;
            case CENTER:
                ticks = (int)(18 * Constants.kTicksPerInch);
                break;
            case OUTER:
                ticks = (int)(26 * Constants.kTicksPerInch);
                break;
        }
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        Robot.imu.reset();
        angle = alliance.equals("Red") ? 25 : -25;
        while(Robot.drive.turn(angle, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case INNER:
                ticks = (int)(40 * Constants.kTicksPerInch);
                break;
            case CENTER:
                ticks = (int)(29 * Constants.kTicksPerInch);
                break;
            case OUTER:
                ticks = (int)(17 * Constants.kTicksPerInch);
                break;
        }
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kIntakeSpeed);
        }
        Robot.stop();

        Robot.drive.resetEncoders();
        ticks = (int)(-16 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        angle = alliance.equals("Red") ? -87 : 87;
        while(Robot.drive.turn(angle, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case INNER:
                ticks = (int)(45 * Constants.kTicksPerInch);
                break;
            case CENTER:
                ticks = (int)(35 * Constants.kTicksPerInch);
                break;
            case OUTER:
                ticks = (int)(30 * Constants.kTicksPerInch);
                break;
        }
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
    }
}
