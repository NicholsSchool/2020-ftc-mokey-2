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
        Robot.initAuto(opMode.hardwareMap, opMode.telemetry);
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
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(18); else Robot.drive.setTargetPosition(16);
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
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(31); else Robot.drive.setTargetPosition(30);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(17); else Robot.drive.setTargetPosition(17);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        if(alliance.equals("Red")) Robot.drive.setTargetPosition(-20); else Robot.drive.setTargetPosition(-18);
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(-94); else Robot.drive.setTargetAngle(90);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(47); else Robot.drive.setTargetPosition(47);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(40); else Robot.drive.setTargetPosition(40);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(32); else Robot.drive.setTargetPosition(32);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-67); else Robot.drive.setTargetPosition(-69);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-60); else Robot.drive.setTargetPosition(-60);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-52); else Robot.drive.setTargetPosition(-52);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(-Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(35); else Robot.drive.setTargetAngle(-35);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(14); else Robot.drive.setTargetPosition(14);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(18); else Robot.drive.setTargetPosition(18);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(18); else Robot.drive.setTargetPosition(18);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-14); else Robot.drive.setTargetPosition(-14);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-18); else Robot.drive.setTargetPosition(-18);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(-18); else Robot.drive.setTargetPosition(-18);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        if(alliance.equals("Red")) Robot.drive.setTargetAngle(-93); else Robot.drive.setTargetAngle(88);
        while(Robot.drive.turn() && opMode.opModeIsActive()) { }



        switch (skystonePos) {
            case INNER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(68); else Robot.drive.setTargetPosition(70);
                break;
            case CENTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(62); else Robot.drive.setTargetPosition(62);
                break;
            case OUTER:
                if(alliance.equals("Red")) Robot.drive.setTargetPosition(53); else Robot.drive.setTargetPosition(54);
                break;
        }
        while(Robot.drive.move() && opMode.opModeIsActive()) { }


        Robot.drive.setTargetPosition(-14);
        while(Robot.drive.move() && opMode.opModeIsActive()) {
            Robot.intake.intake(-Constants.kIntakeSpeed);
        }
        Robot.intake.stop();


        Robot.stop();
    }
}
