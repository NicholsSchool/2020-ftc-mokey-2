package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.SkystonePosition;

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


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(5); else Robot.drive.setTargetPosition(5);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(18); else Robot.drive.setTargetPosition(18);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(26); else Robot.drive.setTargetPosition(26);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(30); else Robot.drive.setTargetAngle(-30);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(48); else Robot.drive.setTargetPosition(48);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(31); else Robot.drive.setTargetPosition(31);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(19); else Robot.drive.setTargetPosition(19);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        Robot.drive.setTargetPosition(-17);
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(-120); else Robot.drive.setTargetAngle(120);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(45); else Robot.drive.setTargetPosition(45);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(38); else Robot.drive.setTargetPosition(38);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(30); else Robot.drive.setTargetPosition(30);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        Robot.drive.setTargetPosition(-12);
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(-Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        Robot.stop();
    }
}
