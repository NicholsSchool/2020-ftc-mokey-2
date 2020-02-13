package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.openftc.revextensions2.ExpansionHubEx;
import org.openftc.revextensions2.ExpansionHubMotor;
import org.openftc.revextensions2.RevBulkData;

/*
 * Optimized mecanum drive implementation for REV ExHs. The time savings may significantly improve
 * trajectory following performance with moderate additional complexity.
 */
public class DriveEx extends MecanumDrive {
    public enum Mode {
        IDLE, FOLLOW_TRAJECTORY
    }

    private ExpansionHubEx mHub2, mHub3;
    private ExpansionHubMotor mLFDrive, mLBDrive, mRBDrive, mRFDrive;
    private BNO055IMU mImu;

    private HolonomicPIDVAFollower mFollower;
    private Mode mMode;

    public DriveEx(HardwareMap hardwareMap) {
        super(Constants.kV, Constants.kA, Constants.kStatic, Constants.kTrackWidth);

        mHub2 = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 2");
        mHub3 = hardwareMap.get(ExpansionHubEx.class, "Expansion Hub 3");

        mImu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        mImu.initialize(parameters);

        // TODO: if your mHub is mounted vertically, remap the IMU axes so that the z-axis points
        // upward (normal to the floor) using a command like the following:
        // BNO055IMUUtil.remapAxes(mImu, AxesOrder.XYZ, AxesSigns.NPN);

        mLFDrive = hardwareMap.get(ExpansionHubMotor.class, "lFDrive");
        mLBDrive = hardwareMap.get(ExpansionHubMotor.class, "lBDrive");
        mRBDrive = hardwareMap.get(ExpansionHubMotor.class, "rBDrive");
        mRFDrive = hardwareMap.get(ExpansionHubMotor.class, "rFDrive");

        mLFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mLBDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mRBDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        mLFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mLBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mRBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mRFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if(Constants.kMotorPIDF != null) {
            // if using custom PIDF coefficients
            mLFDrive.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, Constants.kMotorPIDF);
            mLBDrive.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, Constants.kMotorPIDF);
            mRBDrive.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, Constants.kMotorPIDF);
            mRFDrive.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, Constants.kMotorPIDF);
        }

        mRBDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        mRFDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        // TODO: reverse any mMotors using DcMotor.setDirection()

        // TODO: if desired, use setLocalizer() to change the localization method
        // for instance, setLocalizer(new ThreeTrackingWheelLocalizer(...));

        mFollower = new HolonomicPIDVAFollower(Constants.kTranslationalPID, Constants.kTranslationalPID, Constants.kHeadingPID);
    }

    public void followTrajectory(Trajectory trajectory) {
        mFollower.followTrajectory(trajectory);
        mMode = Mode.FOLLOW_TRAJECTORY;
    }

    public void followTrajectorySync(Trajectory trajectory) {
        followTrajectory(trajectory);
        waitForIdle();
    }

    private void waitForIdle() {
        while (!Thread.currentThread().isInterrupted() && isBusy()) {
            update();
        }
    }

    public TrajectoryBuilder getTrajectoryBuilder() {
        return new TrajectoryBuilder(getPoseEstimate(), Constants.kConstraints);
    }

    private void update() {
        updatePoseEstimate();
        Pose2d currentPose = getPoseEstimate();

        switch (mMode) {
            case IDLE:
                break;
            case FOLLOW_TRAJECTORY: {
                setDriveSignal(mFollower.update(currentPose));
                Pose2d error = mFollower.getLastError();
                Robot.telemetry.addData("Error X", error.getX());
                Robot.telemetry.addData("Error Y", error.getY());
                Robot.telemetry.addData("Pose X", currentPose.getX());
                Robot.telemetry.addData("Pose Y", currentPose.getY());
                Robot.telemetry.update();

                if (!mFollower.isFollowing()) {
                    mMode = Mode.IDLE;
                    setDriveSignal(new DriveSignal());
                }
                break;
            }
        }
    }

    public boolean isBusy() {
        return mMode != Mode.IDLE;
    }

    @Override
    public List<Double> getWheelPositions() {
        RevBulkData bulkData2 = mHub2.getBulkInputData();
        RevBulkData bulkData3 = mHub3.getBulkInputData();
        if (bulkData2 == null || bulkData3 == null) {
            return Arrays.asList(0.0, 0.0, 0.0, 0.0);
        }

        List<Double> wheelPositions = new ArrayList<>();

        wheelPositions.add(bulkData2.getMotorCurrentPosition(mLFDrive) / Constants.kTicksPerInch);
        wheelPositions.add(bulkData2.getMotorCurrentPosition(mLBDrive) / Constants.kTicksPerInch);
        wheelPositions.add(bulkData3.getMotorCurrentPosition(mRBDrive) / Constants.kTicksPerInch);
        wheelPositions.add(bulkData3.getMotorCurrentPosition(mRFDrive) / Constants.kTicksPerInch);

        return wheelPositions;
    }

    @Override
    public void setMotorPowers(double v0, double v1, double v2, double v3) {
        mLFDrive.setPower(v0);
        mLBDrive.setPower(v1);
        mRBDrive.setPower(v2);
        mRFDrive.setPower(v3);
    }

    @Override
    public double getRawExternalHeading() {
        return mImu.getAngularOrientation().firstAngle;
    }

    public void stop() {
        setDriveSignal(new DriveSignal());
        mMode = Mode.IDLE;
    }
}