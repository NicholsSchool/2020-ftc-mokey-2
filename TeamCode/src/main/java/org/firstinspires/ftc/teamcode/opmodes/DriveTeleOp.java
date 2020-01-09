package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Robot;


/**
 * A driver-controlled OpMode for the TeleOp period.
 */
@TeleOp(name="Drive")
public class DriveTeleOp extends OpMode
{
    private double mDriverSpeedMultiplier;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        Robot.init(hardwareMap);
        mDriverSpeedMultiplier = 1.0;

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
        // Fast and slow modes for driver
        if(gamepad1.a) {
            mDriverSpeedMultiplier = 1.0;
        } else if(gamepad1.b) {
            mDriverSpeedMultiplier = 0.33;
        }
        //

        // Tank drive and strafe
        double strafeSpeed = (gamepad1.left_trigger - gamepad1.right_trigger) * mDriverSpeedMultiplier;
        if(strafeSpeed != 0) {
            Robot.drive.strafe(strafeSpeed);
        } else {
            double lSpeed = -gamepad1.left_stick_y * mDriverSpeedMultiplier;
            double rSpeed = -gamepad1.right_stick_y * mDriverSpeedMultiplier;

            Robot.drive.move(lSpeed, rSpeed);
        }
        //

        double intakeSpeed = gamepad2.left_trigger - gamepad2.right_trigger;
        Robot.intake.intake(intakeSpeed);

        Robot.imu.debug(telemetry);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        Robot.stop();
    }

}