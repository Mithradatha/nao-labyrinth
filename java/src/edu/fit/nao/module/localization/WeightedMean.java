package edu.fit.nao.module.localization;

import edu.fit.nao.helper.geometry.Pose2D;
import edu.fit.nao.module.navigation.Grid;

/**
 * Weighted mean for pose estimation
 * Bayes filter for pose confidence
 */
public class WeightedMean {

    private final float sensorWeight; // e.g. 0.7f
    private final float confidenceWithoutSensor; // e.g. 0.8f
    private final float confidenceWithSensor; // e.g. 0.2f

    private final float minimumConfidenceThreshold = 0.1f;

    private Pose2D pose;
    private float confidence;

    private Grid map;

    public WeightedMean(Grid map, float sensorWeight, float confidenceWithSensor) {

        this.map = map;

        this.pose = new Pose2D(0.0f, 0.0f, 0.0f);
        this.confidence = minimumConfidenceThreshold;

        this.sensorWeight = sensorWeight;
        this.confidenceWithoutSensor = 1.0f - confidenceWithSensor;
        this.confidenceWithSensor = confidenceWithSensor;
    }

    public void updatePoseEstimate(Pose2D odometryValue, Pose2D sensorValue) {

        this.pose = sensorValue.scale(sensorWeight).add(pose.add(odometryValue).scale(1 - sensorWeight));

        float prior = confidenceWithSensor * confidence;
        this.confidence = prior / (prior + (1 - confidenceWithSensor) * (1 - confidence));
    }

    public void updatePoseEstimate(Pose2D odometryValue) {

        this.pose = this.pose.add(odometryValue);

        float prior = confidenceWithoutSensor * confidence;
        this.confidence = prior / (prior + (1 - confidenceWithoutSensor) * (1 - confidence));
    }
}
