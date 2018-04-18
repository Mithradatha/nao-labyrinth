package edu.fit.nao.module.localization;

import edu.fit.nao.helper.geometry.Pose2D;

/**
 * noise = Guassian{variance=q, mean=0}
 */
public class Odometry {

    private Pose2D pose;
    private Pose2D noise;

    public Odometry(Pose2D pose, Pose2D noise) {

        this.pose = pose;
        this.noise = noise;
    }

    public Pose2D getPose() {

        return pose;
    }

    public Pose2D getNoise() {

        return noise;
    }
}
