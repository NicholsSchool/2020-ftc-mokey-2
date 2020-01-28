package org.firstinspires.ftc.teamcode.util;

/**
 * The TrapezoidalProfile class is a utility class used to calculate trapezoidal motion profiles
 */
public class TrapezoidalProfile {

    private double mCruiseVelocity;
    private double mAcceleration;

    private double mCruiseStartTime;
    private double mCruiseEndTime;

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
     * Gets the speed at the specified time.
     * @param time the time in seconds
     * @return the speed in sensor units
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
