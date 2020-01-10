package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto routine used for grabbing a Skystone from starting position.
 */
public class SkystoneRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     */
    public static void run(LinearOpMode opMode, String alliance, int skystonePos) {
        int ticks = 0;
        int angle = 0;

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case Constants.kSkystoneInner:
                ticks = (int)(9 * Constants.kTicksPerInch);
                break;
            case Constants.kSkystoneCenter:
                ticks = (int)(18 * Constants.kTicksPerInch);
                break;
            case Constants.kSkystoneOuter:
                ticks = (int)(27 * Constants.kTicksPerInch);
                break;
        }
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        Robot.imu.reset();
        angle = alliance.equals("Red") ? 30 : -30;
        while(Robot.drive.turn(angle, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();

        Robot.drive.resetEncoders();
        switch (skystonePos) {
            case Constants.kSkystoneInner:
                ticks = (int)(42 * Constants.kTicksPerInch);
                break;
            case Constants.kSkystoneCenter:
                ticks = (int)(27 * Constants.kTicksPerInch);
                break;
            case Constants.kSkystoneOuter:
                ticks = (int)(18 * Constants.kTicksPerInch);
                break;
        }
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kAutoIntakeSpeed);
        }
        Robot.stop();

        Robot.drive.resetEncoders();
        ticks *= -1;
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
    }
}
