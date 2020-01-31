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
        Robot.imu.reset();


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(7); else Robot.drive.setTargetPosition(6);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(18); else Robot.drive.setTargetPosition(17);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(28); else Robot.drive.setTargetPosition(28);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(36); else Robot.drive.setTargetAngle(-36);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(46); else Robot.drive.setTargetPosition(44);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(31); else Robot.drive.setTargetPosition(31);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(17); else Robot.drive.setTargetPosition(17);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        if(alliance.equals("Red")) Robot.drive.setTargetPosition(-20); else Robot.drive.setTargetPosition(-17);
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(-94); else Robot.drive.setTargetAngle(90);
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


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-65); else Robot.drive.setTargetPosition(-65);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-58); else Robot.drive.setTargetPosition(-58);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-49); else Robot.drive.setTargetPosition(-50);
                break;
        }        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(-Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(35); else Robot.drive.setTargetAngle(-35);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        Robot.drive.setTargetPosition(17);
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        Robot.drive.setTargetPosition(-17);
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(-93); else Robot.drive.setTargetAngle(89);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }



        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(66); else Robot.drive.setTargetPosition(66);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(60); else Robot.drive.setTargetPosition(60);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(51); else Robot.drive.setTargetPosition(52);
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
