package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

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


        Robot.drive.setTargetPosition(3);
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(direction.equals("Left")) Robot.drive.setTargetAngle(90); else Robot.drive.setTargetAngle(-90);;
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        Robot.drive.setTargetPosition(18);
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(direction.equals("Left")) Robot.drive.strafe(0.3); else Robot.drive.strafe(-0.3);;
        opMode.sleep(2000);

        Robot.stop();
    }
}
