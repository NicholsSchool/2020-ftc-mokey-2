

package org.firstinspires.ftc.teamcode.opmodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.SkystonePosition;
import org.firstinspires.ftc.teamcode.autonomous.SkystoneRoutine;

/**
 * An auto OpMode for parking from the close loading zone position while on the blue alliance.
 */
@Autonomous(name="RedSkystoneAuto")
public class RedSkystoneAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap, telemetry);
        String[] args = getClass().getSimpleName().split("(?<=[a-z])(?=[A-Z])"); // split class name for args

        SkystonePosition skystonePos = SkystonePosition.OUTER;

        while(!isStarted()) {
            SkystonePosition pos = Robot.vision.getSkystonePosition(args[0]);
            if(pos != SkystonePosition.UNKNOWN) {
                skystonePos = pos;
            }
            telemetry.addData("Skystone position", pos);
            telemetry.update();
        }

        SkystoneRoutine.run(this, args[0], skystonePos);
    }

}
