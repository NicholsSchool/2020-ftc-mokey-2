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
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        Robot.init(hardwareMap);

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
        // Tank drive and strafe
        double strafeSpeed = gamepad1.left_trigger - gamepad1.right_trigger;
        if(strafeSpeed != 0) {
            Robot.drive.strafe(strafeSpeed);
        } else {
            double lSpeed = -gamepad1.left_stick_y;
            double rSpeed = -gamepad1.right_stick_y;

            Robot.drive.move(lSpeed, rSpeed);
        }
        //
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        Robot.stop();
    }

}