package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto routine used for testing.
 */
public class TestRoutine extends Thread {

    /**
     * Runs the routine.
     */
    @Override
    public void run() {
        int ticks = 0;
        int angle = 0;

        Robot.drive.resetEncoders();
        ticks = (int)(2400 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kAutoDriveSpeed)) {

        }
        Robot.stop();

        Robot.imu.reset();
        angle = 90;
        while(Robot.drive.turn(angle, Constants.kAutoDriveSpeed)) {

        }
        Robot.stop();
    }

    /**
     * Soft stops the robot and interrupts the thread.
     */
    @Override
    public void interrupt() {
        Robot.stop();
        super.interrupt();
    }
}
