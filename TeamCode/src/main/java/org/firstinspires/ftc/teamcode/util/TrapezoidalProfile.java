package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;

/**
 * The TrapezoidalProfile class is a utility class used to calculate trapezoidal motion profiles
 */
public class TrapezoidalProfile {

    private double mCruiseVelocity;
    private double mAcceleration;

    private double mCruiseStartTime;
    private double mCruiseEndTime;

    private ElapsedTime mElapsedTime;

    /**
     * Generates a trapezoidal motion profile with the default cruise velocity and acceleration.
     * @param distance distance in sensor units
     */
    public TrapezoidalProfile(double distance) {
        this(Constants.kAutoDriveVelocity, Constants.kAutoDriveAcceleration, distance);
        mElapsedTime = new ElapsedTime();
    }

    /**
     * Generates a trapezoidal motion profile.
     * @param cruiseVelocity the cruise velocity in sensor units per second
     * @param acceleration the acceleration in sensor units per second per second
     * @param distance the distance in sensor units
     */
    public TrapezoidalProfile(double cruiseVelocity, double acceleration, double distance) {
        mCruiseVelocity = cruiseVelocity;
        mAcceleration = acceleration;

        mCruiseStartTime = cruiseVelocity / acceleration;
        double cruiseStartDistance = cruiseVelocity / 2 * mCruiseStartTime;

        // Triangular Profile
        if(cruiseStartDistance > distance / 2) {
            cruiseStartDistance = distance / 2;

            // Solve for time from acceleration and distance
            mCruiseStartTime = Math.sqrt(cruiseStartDistance / acceleration * 2);
        }

        double cruiseTime = (distance - 2 * cruiseStartDistance) / cruiseVelocity;
        mCruiseEndTime = mCruiseStartTime + cruiseTime;
    }

    /**
     * Gets the velocity from when the trapezoidal profile was created.
     * @return the velocity in percent output
     */
    public double get() {
        return get(mElapsedTime.seconds()) / Constants.kMaxDriveVelocity;
    }

    /**
     * Gets the velocity at the specified time.
     * @param time the time in seconds
     * @return the velocity in sensor units
     */
    public double get(double time) {
        // Acceleration
        if(time < mCruiseStartTime) {
            return mAcceleration * time;
        }

        // Cruise
        if(time >= mCruiseStartTime && time <= mCruiseEndTime) {
            return mCruiseVelocity;
        }

        // Deceleration
        return Math.max(0, mAcceleration * (mCruiseEndTime + mCruiseStartTime - time));
    }
}
