package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.TestRoutine;


/**
 * An auto OpMode for testing.
 */
@Autonomous(name="Test")
public class TestAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);

        waitForStart();

        TestRoutine.run(this);
    }

}