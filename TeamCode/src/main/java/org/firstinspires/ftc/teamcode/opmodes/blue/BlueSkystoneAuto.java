package org.firstinspires.ftc.teamcode.opmodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.SkystoneRoutine;

/**
 * An auto OpMode for parking from the close loading zone position while on the blue alliance.
 */
@Autonomous(name="BlueSkystoneAuto")
public class BlueSkystoneAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap, telemetry);
        String[] args = getClass().getSimpleName().split("(?<=[a-z])(?=[A-Z])"); // split class name for args

        int skystonePos = Constants.kSkystoneOuter;

        while(!isStarted()) {
            int pos = Robot.vision.getSkystonePosition(args[0]);
            if(pos != Constants.kSkystoneUnknown) {
                skystonePos = pos;
            }
            telemetry.addData("Skystone position", pos);
            telemetry.update();
        }

        SkystoneRoutine.run(this, args[0], skystonePos);
    }

}
