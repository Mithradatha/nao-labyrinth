package edu.fit.nao.module.localization;

import edu.fit.nao.helper.geometry.Pose2D;
import edu.fit.nao.module.mapping.Landmark;

import java.util.ArrayList;
import java.util.List;

public class ExtendedKalmanFilter {

    private Pose2D pose;
    private List<Landmark> landmarks;

    public ExtendedKalmanFilter() {

        this.pose = new Pose2D(0.0f, 0.0f, 0.0f);
        this.landmarks = new ArrayList<>();
    }

    public ExtendedKalmanFilter(Pose2D pose, List<Landmark> landmarks) {

        this.pose = pose;
        this.landmarks = landmarks;
    }

    public void predictPose(Odometry odometry) {

        Pose2D controlMotion = odometry.getPose();

        double x = pose.x +
                controlMotion.x * Math.cos(pose.theta) -
                controlMotion.y * Math.sin(pose.theta);

        double y = pose.y +
                controlMotion.x * Math.sin(pose.theta) +
                controlMotion.y * Math.cos(pose.theta);

        double theta = pose.theta + controlMotion.theta;

        pose = new Pose2D((float) x, (float) y, (float) theta).add(odometry.getNoise());

        // TODO: Update covariance matrix
    }

    public void correctPose(Observation observation, int landmarkId) {

        Observation predicted = new Observation(Frame.ROBOT,
                pose.getPosition(), // local
                getLandmarkById(landmarkId).getPosition()); // reference

        Observation innovation = observation.subtract(predicted);

        // TODO: Compute Kalman gain K
        // TODO: Update map
        // TODO: Update covariance matrix
    }

    private Landmark getLandmarkById(int landmarkId) {

        return landmarks.stream()
                .filter((Landmark landmark) -> landmark.getId() == landmarkId)
                .findFirst().orElse(null);
    }
}
