package edu.fit.nao.module.navigation;

import edu.fit.nao.module.geometry.Pose2D;

/**
 * Weighted mean for pose estimation
 * Bayes filter for pose confidence
 */
public class Robot {

    private final float sensorWeight; // e.g. 0.7f
    private final float confidenceWithoutSensor; // e.g. 0.8f
    private final float confidenceWithSensor; // e.g. 0.2f

    private final float minimumConfidenceThreshold = 0.1f;

    private Pose2D pose;
    private float confidence;

    private Grid map;

    public Robot(Grid map, float sensorWeight, float confidenceWithSensor) {

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

//    // add uncertainty
//    public void actionUpdate(encoderValues, priorBelief) {
//
//        this.newBelief = Act(encoderValues, priorBelief);
//    }
//
//    // update belief
//    public void perceptionUpdate(sensorValues, newBelief) {
//
//        this.belief = See(sensorValues, newBelief);
//    }
//
//    // location from set of all possible locations L
//    public float bayesianProbability(location, sensorValues) {
//
//        //return (p(sensorValues | location) * p(location)) / p(sensorValues);
//        return (p(sensorValues | location) * p(location));
//    }
//
//    foreach(l : L -> baysianProbability(l, sensorValues)).then(renorm(1.0, p(l) in L));
//
//    public void Act(encoderValues, newBelief) {
//
//        return sum(beliefs.foreach(priorBelief -> p(newBelief | encoderValues, priorBelief) * p(priorBelief)));
//    }
}
