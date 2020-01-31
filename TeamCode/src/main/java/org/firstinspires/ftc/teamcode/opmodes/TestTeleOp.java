package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * A driver-controlled OpMode for the TeleOp period.
 */
@TeleOp(name="TestTeleOp")
public class TestTeleOp extends OpMode
{

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        Robot.init(hardwareMap, telemetry);
        Robot.drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double speed = 0.25;
        if(gamepad1.dpad_left) {
//             Robot.drive.move(-speed, speed);
        } else if(gamepad1.dpad_right) {
//             Robot.drive.move(speed, -speed);
        } else {
            Robot.drive.stop();
        }
        //

        Robot.drive.debug();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        Robot.stop();
    }

}